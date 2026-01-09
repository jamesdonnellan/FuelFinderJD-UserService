package ie.atu.userservice.userservice.Service;

import ie.atu.userservice.userservice.dto.UserRequestDTO;
import ie.atu.userservice.userservice.dto.UserResponseDTO;
import ie.atu.userservice.userservice.ErrorHandling.DuplicateException;
import ie.atu.userservice.userservice.ErrorHandling.UserNotFoundException;
import ie.atu.userservice.userservice.Model.UserInfo;
import ie.atu.userservice.userservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
  private final UserRepository repo;

  public UserService(UserRepository repo)
  {
      this.repo = repo;
  }

    public List<UserResponseDTO> findAll()
    {
        List<UserInfo> users = repo.findAll();
        return users.stream().map(this::toResponse).toList();
    }

    public UserResponseDTO findById(String userID)
    {
      List<UserInfo> existingUser = repo.findByUserID(userID);

      if(existingUser.isEmpty())
      {
          throw new UserNotFoundException("User with ID " + userID + " not found.");
      }

      return toResponse(existingUser.getFirst());
    }

    public UserResponseDTO create(UserRequestDTO user)
    {
        List <UserInfo> existingUser = repo.findByUserID(user.getUserID());
        if(!existingUser.isEmpty())
        {
            throw new DuplicateException("User with id " + user.getUserID() + " already exists");
        }

        UserInfo toSave = UserInfo.builder()
                .userID(user.getUserID())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        UserInfo saved = repo.save(toSave);
        return toResponse(saved);
    }

    public UserResponseDTO update(String userID, UserRequestDTO updated)
    {
        List<UserInfo> updatedUser = repo.findByUserID(userID);

        if(updatedUser.isEmpty())
        {
            throw new UserNotFoundException("User with ID " + userID + " not found.");
        }

        updatedUser.getFirst().setUserName(updated.getUserName());
        updatedUser.getFirst().setPassword(updated.getPassword());
        updatedUser.getFirst().setEmail(updated.getEmail());

        UserInfo saved = repo.save(updatedUser.getFirst());
        return toResponse(saved);
    }

    public void deleteById(String userID)
    {
      List<UserInfo> deletedUser = repo.findByUserID(userID);

      if(deletedUser.isEmpty())
      {
          throw new UserNotFoundException("User with ID " + userID + " not found.");
      }

      repo.delete(deletedUser.getFirst());
    }

    private UserResponseDTO toResponse(UserInfo user)
    {
        return new UserResponseDTO(
                user.getUserID(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
