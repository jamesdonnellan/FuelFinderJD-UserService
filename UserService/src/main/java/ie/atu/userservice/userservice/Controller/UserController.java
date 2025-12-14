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
public class UserController {
    private final UserService uService;

    public UserController(UserService uService) {
        this.uService = uService;
    }

    @GetMapping // Gets all Users Information
    public ResponseEntity<List<UserInfo>> findAll() {
        return ResponseEntity.ok(uService.findAll());
    }

    @GetMapping("/{id}") // Gets one user by their ID
    public ResponseEntity<UserInfo> getOne(@PathVariable String id) {
        Optional<UserInfo> maybe = uService.findById(id);
        if (maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping // Updated Method for create user
    public ResponseEntity<UserInfo> create(@Valid @RequestBody UserInfo user) {
        UserInfo created = uService.create(user);
        return ResponseEntity
                .created(URI.create("/api/users" + created.getUserID()))
                .body(created);
    }

    @PutMapping("/{id}") // New method for updating user data
    public ResponseEntity<UserInfo> update(@PathVariable String id, @Valid @RequestBody UserInfo updated) {
        Optional<UserInfo> maybe = uService.update(id, updated);
        return maybe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
       if(!uService.deleteById(id))
       {
           throw new UserNotFoundException("User with id" + id + "not found.");
       }
       return ResponseEntity.noContent().build();
    }
}