package cmpt470.group7.project.json;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthResponseTest {
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void test() throws JsonProcessingException {
        AuthResponse resp = new AuthResponse();
        resp.setSuccess(true);
        System.out.println(this.mapper.writeValueAsString(resp));
    }

}
