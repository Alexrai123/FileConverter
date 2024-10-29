package com.example.demo.Controller;

import com.example.demo.Model.Users;
import com.example.demo.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private final UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @GetMapping("/{idUser}")
    public Users getUserById(@PathVariable String idUser) {
        Optional<Users> user = usersRepository.findById(Integer.valueOf(idUser));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        usersRepository.save(user);
        return user;
    }

    @PutMapping
    public Users updateUser(@RequestBody Users userToUpdate){
        Users user = usersRepository.findById(userToUpdate.getUser_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setUsername(userToUpdate.getUsername());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        user.setSubscriptionType(userToUpdate.getSubscriptionType());
        user.setSubscriptionStartDate(userToUpdate.getSubscriptionStartDate());
        return usersRepository.save(user);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Integer idUser){
        Users user = usersRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        usersRepository.delete(user);
    }
}
