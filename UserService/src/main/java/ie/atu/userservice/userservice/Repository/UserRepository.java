package ie.atu.userservice.userservice.Repository;

import ie.atu.userservice.userservice.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserInfo, Long>
{
    List<UserInfo> findByUserID(String userID);
}
