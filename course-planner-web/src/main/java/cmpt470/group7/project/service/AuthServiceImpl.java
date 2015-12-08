package cmpt470.group7.project.service;

import org.springframework.stereotype.Service;

import cmpt470.group7.project.auth.PasswordHelper;
import cmpt470.group7.project.domain.StudentJdbc;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean checkCredential(String login, String password, StudentJdbc studentJdbc) {
        if (null == studentJdbc) {
            return false;
        }
        return PasswordHelper.checkPassword(password, studentJdbc.getSalt(), studentJdbc.getHash());
    }

}
