package cmpt470.group7.project.exception;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is designed to filter descriptive exception messages from HTTP 5xx error responses before they hit the
 * client (i.e. any exception which has not already been caught and converted into a view by the time it reaches this
 * filter.
 */
public class HttpErrorStatusFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(HttpErrorStatusFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            LOG.debug(Arrays.toString(t.getStackTrace()));
            throw new ServletException("Internal Server Exception");
        }
    }

    @Override
    public void destroy() {
    }

}
