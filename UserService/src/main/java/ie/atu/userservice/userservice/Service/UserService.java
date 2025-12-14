package ie.atu.userservice.userservice.Service;

import ie.atu.userservice.userservice.ErrorHandling.DuplicateException;
import ie.atu.userservice.userservice.Model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService
{
    private final List<UserInfo> store = new ArrayList<>();

    public List<UserInfo> findAll()
    {
        return new ArrayList<>(store);
    }

    public Optional<UserInfo> findById(String id)
    {
        for(UserInfo user : store)
        {
            if(user.getUserID().equals(id))
            {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public UserInfo create(UserInfo user)
    {
        if(findById(user.getUserID()).isPresent())
        {
            throw new DuplicateException("User with id " + user.getUserID() + " already exists");
        }
        store.add(user);
        return user;
    }

    public Optional <UserInfo> update(String id, UserInfo updated)
    {
        for (UserInfo user : store)
        {
            if (user.getUserID().equals(id))
            {
                user.setUserName(updated.getUserName());
                user.setPassword(updated.getPassword());
                user.setEmail(updated.getEmail());
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(String id)
    {
        return store.removeIf(user -> user.getUserID().equals(id));
    }
}
