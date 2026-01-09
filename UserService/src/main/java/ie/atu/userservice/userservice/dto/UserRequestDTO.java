package ie.atu.userservice.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO
{
    @NotBlank(message = "ID is required")
    @Size(message = "ID cannot exceed 40 characters",max = 40)
    private String userID;

    @NotBlank(message = "Name is required")
    @Size (message = "Name cannot exceed 60 characters", max = 60)
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(message = "Password must be at least 6 characters", min = 6)
    private String password;
}
