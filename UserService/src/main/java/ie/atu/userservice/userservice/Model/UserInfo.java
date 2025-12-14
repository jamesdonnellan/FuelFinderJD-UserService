package ie.atu.userservice.userservice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key


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
