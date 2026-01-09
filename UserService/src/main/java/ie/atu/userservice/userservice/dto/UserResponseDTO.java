package ie.atu.userservice.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO
{
    private String userID;
    private String userName;
    private String email;
}
