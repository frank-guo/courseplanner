package cmpt470.group7.project.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * This class is doing real salt hash password generation and validation
 */
public class PasswordHelper {

    private static final String ALGORITHM = "SHA-256";
    private static final int ITERATION_COUNT = 512;
    private static final int BYTE_ARRAY_LENGTH = 32;

    public static boolean checkPassword(String password, String salt, String hash) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(salt) || StringUtils.isBlank(hash)) {
            throw new IllegalArgumentException("pw, salt, hash can not be blank");
        }

        byte[] saltBytes = Base64.decodeBase64(salt);
        byte[] hashBytes = Base64.decodeBase64(hash);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        digest.reset();
        digest.update(saltBytes);
        byte[] hashCalculated = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < ITERATION_COUNT; i++) {
            digest.reset();
            hashCalculated = digest.digest(hashCalculated);
        }
        return Arrays.equals(hashCalculated, hashBytes);
    }

	/**
	 * Return 2 items string array, first is salt string, second is salt string,
	 * all base64 encoded.
	 * 
	 * @param password
	 *            password string
	 * @return string array which contains salt as first item and hash as the
	 *         second item
	 */
    static String[] createSaltAndHash(String password) {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("pw can not be blank");
        }
        Random rand = new SecureRandom();
        byte[] saltBytes = new byte[BYTE_ARRAY_LENGTH];
        rand.nextBytes(saltBytes);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        digest.reset();
        digest.update(saltBytes);
        byte[] hashCalculated = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < ITERATION_COUNT; i++) {
            digest.reset();
            hashCalculated = digest.digest(hashCalculated);
        }
        return new String[] { Base64.encodeBase64String(saltBytes), Base64.encodeBase64String(hashCalculated) };
    }

}
