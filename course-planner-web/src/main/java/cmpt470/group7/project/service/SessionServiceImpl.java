package cmpt470.group7.project.service;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private static Logger LOG = LoggerFactory.getLogger(SessionServiceImpl.class);

    private static final Long DEFAULT_SESSION_TIME = 1000L * 60 * 60; // 1 hour
    private static final int RANDOM_BYTE_LENGTH = 32; // random byte array length for tokens

    private final Random rand = new SecureRandom();
    private final ConcurrentMap<String, AuthValue> authMap = new ConcurrentHashMap<>();

    @Override
    public Integer getId(String cookieValue) {
        if (StringUtils.isBlank(cookieValue)) {
            return null;
        }
        AuthValue av = this.authMap.get(cookieValue);
        if (null == av) {
            return null;
        }
        long current = System.currentTimeMillis();
        if (av.getLastActivityTime() + av.getLife() < current) {
            // expired, delete from map and return null;
            this.authMap.remove(cookieValue);
            return null;
        }
        AuthValue newAV = new AuthValue(av.getId(), av.getLife(), current);
        this.authMap.replace(cookieValue, av, newAV);
        return newAV.getId();
    }

    @Override
    public Cookie generateCookie(Integer id) {
        if (null == id) {
            throw new IllegalArgumentException("id can not be null");
        }
        String cookieValue = null;
        AuthValue av = null;
        Cookie c = null;
        // this while loop make sure no conflict
        // even though the probability is quite low
        do {
            byte[] bytes = new byte[RANDOM_BYTE_LENGTH];
            this.rand.nextBytes(bytes);
            cookieValue = Base64.encodeBase64String(bytes);
            c = new Cookie(SESSION_COOKIE_NAME, cookieValue);
            c.setPath("/");
            av = new AuthValue(id, DEFAULT_SESSION_TIME, System.currentTimeMillis());
        } while (null != this.authMap.putIfAbsent(cookieValue, av));

        return c;
    }

    @Override
    public void removeSession(String cookieValue) {
        this.authMap.remove(cookieValue);
    }

    // run every hour
    @Scheduled(cron = "0 0 0-23 * * *")
    public void cleanSessions() {
        long current = System.currentTimeMillis();
        for (Iterator<Map.Entry<String, AuthValue>> iter = this.authMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, AuthValue> entry = iter.next();
            if (entry.getValue().getLastActivityTime() + entry.getValue().getLife() < current) {
                iter.remove();
                LOG.warn("Removed session [{}] since there is no activities for 1 hour.", entry.getKey());
            }
        }
    }

    // immutable auth value
    private static class AuthValue {

        private final Integer id;
        private final Long life;
        private final long lastActivityTime;

        private AuthValue(Integer id, Long life, long lastActivityTime) {
            this.id = id;
            this.life = life;
            this.lastActivityTime = lastActivityTime;
        }

        private Integer getId() {
            return id;
        }

        private Long getLife() {
            return life;
        }

        private long getLastActivityTime() {
            return lastActivityTime;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(this.id).append(this.life).append(this.lastActivityTime).build();
        }

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

    }

}
