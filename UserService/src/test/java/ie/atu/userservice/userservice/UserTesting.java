package ie.atu.userservice.userservice;




import ie.atu.userservice.userservice.Model.UserInfo;
import ie.atu.userservice.userservice.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class UserTesting
{
    /*
    private UserService Uservice;

    @BeforeEach
    void setup()
    {
        Uservice = new UserService(); // before each test, create a new instance of passenger service
    }

    @Test
    void createThenFindById()
    {
        UserInfo u = UserInfo.builder()
                .userID("J7")
                .userName("Jim")
                .email("j@email.com")
                .build();

        Uservice.create(u);

        Optional<UserInfo> found = Uservice.findById("J7");
        assertTrue(found.isPresent());
        assertEquals("Jim", found.get().getUserName());
    }
    */
}