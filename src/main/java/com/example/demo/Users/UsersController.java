package com.example.demo.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/all")
    public List<Users> getAll() {
        return usersService.getAll();
    }

    @GetMapping("/{idUser}")
    public Users getOne(@PathVariable Integer idUser) {
        try {
            return usersService.getOne(idUser);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID format", e);
        }
    }

    @PostMapping
    public Users create(@RequestBody UsersDTO user) {
        return usersService.create(user);
    }

    @PutMapping
    public Users update(@RequestBody Users userToUpdate) {
        return usersService.update(userToUpdate);
    }

    @DeleteMapping("/{idUser}")
    public void delete(@PathVariable Integer idUser) throws Exception {
        usersService.delete(idUser);
    }
}