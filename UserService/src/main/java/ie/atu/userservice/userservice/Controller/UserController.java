package ie.atu.userservice.userservice.Controller;

import ie.atu.userservice.userservice.dto.UserRequestDTO;
import ie.atu.userservice.userservice.dto.UserResponseDTO;
import ie.atu.userservice.userservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<UserResponseDTO>> findAll()
    {
        return ResponseEntity.ok(uService.findAll());
    }

    @GetMapping("/{id}") // Gets one user by their ID
    public ResponseEntity<UserResponseDTO> getOne(@PathVariable String id)
    {
       return ResponseEntity.ok(uService.findById(id));
    }


    @PostMapping // Updated Method for create user
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO user)
    {
        UserResponseDTO created = uService.create(user);
        return ResponseEntity
                .created(URI.create("/api/users/" + created.getUserID()))
                .body(created);
    }

    @PutMapping("/{id}") // New method for updating user data
    public ResponseEntity<UserResponseDTO> update(@PathVariable String id, @Valid @RequestBody UserRequestDTO updated)
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