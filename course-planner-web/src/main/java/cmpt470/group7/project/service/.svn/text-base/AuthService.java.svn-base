package cmpt470.group7.project.service;

import cmpt470.group7.project.domain.StudentJdbc;

/**
 * Doing authentication by comparing generated salt/hash from password is
 * matching the salt/hash in student table
 * 
 * @author yanshi
 * 
 */
public interface AuthService {
    
    boolean checkCredential(String login, String password, StudentJdbc studentJdbc);

}
