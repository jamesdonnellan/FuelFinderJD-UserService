package ie.atu.userservice.userservice.ErrorHandling;

public class DuplicateException extends RuntimeException
{
    public DuplicateException(String message)
    {
        super(message);
    }

}
