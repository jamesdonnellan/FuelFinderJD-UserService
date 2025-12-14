package ie.atu.userservice.userservice.ErrorHandling;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
