package ie.atu.userservice.userservice.Service;

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

    public List<UserInfo> findAll()
    {
        return repo.findAll();
    }

    public UserInfo findById(String userID)
    {
      List<UserInfo> existingUser = repo.findByUserID(userID);

      if(existingUser.isEmpty())
      {
          throw new UserNotFoundException("User with ID " + userID + " not found.");
      }
      return existingUser.getFirst();
    }

    public UserInfo create(UserInfo user)
    {
        List <UserInfo> existingUser = repo.findByUserID(user.getUserID());
        if(!existingUser.isEmpty())
        {
            throw new DuplicateException("User with id " + user.getUserID() + " already exists");
        }
        return repo.save(user);
    }

    public UserInfo update(String userID, UserInfo updated)
    {
        List<UserInfo> updatedUser = repo.findByUserID(userID);

        if(updatedUser.isEmpty())
        {
            throw new UserNotFoundException("User with ID " + userID + " not found.");
        }

        updatedUser.getFirst().setUserName(updated.getUserName());
        updatedUser.getFirst().setPassword(updated.getPassword());
        updatedUser.getFirst().setEmail(updated.getEmail());

        return repo.save(updatedUser.getFirst());
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
}
