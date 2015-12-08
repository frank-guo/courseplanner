package cmpt470.group7.project.json;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthRequestTest {
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void test() throws JsonProcessingException {
        AuthRequest req = new AuthRequest();
        req.setLogin("amirkhan");
        req.setPassword("password");
        System.out.println(this.mapper.writeValueAsString(req));
    }

}
