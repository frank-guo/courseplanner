package cmpt470.group7.project.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordHelperTest {

    @Test
    public void testGeneration() {
        String[] saltHash = PasswordHelper.createSaltAndHash("password");
        System.out.println("SALT : " + saltHash[0]);
        System.out.println("HASH : " + saltHash[1]);
    }

    @Test
    public void testValidation() {
        String[] saltHash = PasswordHelper.createSaltAndHash("password");
        assertTrue(PasswordHelper.checkPassword("password", saltHash[0], saltHash[1]));
    }

    @Test
    public void testValidationFail() {
        String[] saltHash = PasswordHelper.createSaltAndHash("password");
        assertFalse(PasswordHelper.checkPassword("password", "blah", saltHash[1]));
        assertFalse(PasswordHelper.checkPassword("password", saltHash[0], "blah"));
    }

}
