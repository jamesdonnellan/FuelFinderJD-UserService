package ie.atu.userservice.userservice.Controller;


import ie.atu.userservice.userservice.ErrorHandling.UserNotFoundException;
import ie.atu.userservice.userservice.Model.UserInfo;
import ie.atu.userservice.userservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final UserService uService;

    public UserController(UserService uService)
    {
        this.uService = uService;
    }

    @GetMapping // Gets all Users Information
    public ResponseEntity<List<UserInfo>> findAll()
    {
        return ResponseEntity.ok(uService.findAll());
    }

    @GetMapping("/{id}") // Gets one user by their ID
    public ResponseEntity<UserInfo> getOne(@PathVariable String id)
    {
       return ResponseEntity.ok(uService.findById(id));
    }


    @PostMapping // Updated Method for create user
    public ResponseEntity<UserInfo> create(@Valid @RequestBody UserInfo user)
    {
        UserInfo created = uService.create(user);
        return ResponseEntity
                .created(URI.create("/api/users" + created.getUserID()))
                .body(created);
    }

    @PutMapping("/{id}") // New method for updating user data
    public ResponseEntity<UserInfo> update(@PathVariable String id, @Valid @RequestBody UserInfo updated)
    {
       return ResponseEntity.ok(uService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
       uService.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}