package cmpt470.group7.project.controller;

import cmpt470.group7.project.service.SessionService;

/**
 * Abstract controller which checks sessions.
 * 
 * @author yanshi
 */
public abstract class AbstractController {

    /**
     * Get delegated session service.
     * 
     * @return session service
     */
    protected abstract SessionService getSessionService();

    /**
     * Retrieve id by cookie
     * 
     * @param cookieValue
     *            cookie value
     * @return id
     */
    protected Integer getIdByCookie(String cookieValue) {
        return getSessionService().getId(cookieValue);
    }

}
