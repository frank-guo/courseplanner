package cmpt470.group7.project.service;

import javax.servlet.http.Cookie;

/**
 * Manage sessions.
 */
public interface SessionService {

    public static final String SESSION_COOKIE_NAME = "course_planner_session";

    /**
     * Retrieve id by cookie from memory, and extend the session
     * 
     * @param cookieValue
     * @return id
     */
    Integer getId(String cookieValue);

    /**
     * Generate cookie by id and store in memory
     * 
     * @param id
     * @return
     */
    Cookie generateCookie(Integer id);
    
	/**
	 * Used to logout.
	 * 
	 * @param cookieValue
	 */
    void removeSession(String cookieValue);

}
