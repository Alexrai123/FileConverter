package com.example.demo.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:8080/**"})
public class UsersController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    /// //////////////////////////////////////////////////////////
                        /// FRONT PAGE
    @GetMapping("/test")
    public String getUsers(Model model){
        List<UsersDTO> users = usersService.getAllDTO();
        model.addAttribute("users", users);
        return "users";
    }

                        ///ADD USER
    @GetMapping("/add")
    public String showAddUserForm(Model model){
        UsersDTO usersDTO = new UsersDTO();
        model.addAttribute("user", usersDTO);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("usersDTO") UsersDTO usersDTO) {
        usersService.create(usersDTO);
        return "redirect:/users/test";
    }
                        ///EDIT USER
    @GetMapping("/edit/{email}")
    public String showEditUserForm(@PathVariable String email, Model model) {
        UsersDTO userDTO = usersService.getUserByEmail(email);
        model.addAttribute("user", userDTO);
        return "editUser";
    }

    @PutMapping("/editUser")
    public String updateUser(@ModelAttribute("userDTO") UsersDTO userDTO) {
        usersService.updateUser(userDTO);
        return "redirect:/users/test";
    }
                        ///DELETE USER
    @GetMapping("/delete/{email}")
    public String showDeleteUserForm(@PathVariable("email") String email, Model model) {
        UsersDTO user = usersService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @DeleteMapping("/delete/{email}")
    public String deleteUser(@ModelAttribute UsersDTO userDTO) {
        usersService.deleteUserByEmail(userDTO.getEmail());
        return "redirect:/users/test";
    }

    /// /////////////////////////////////////////////////////////
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

//    @PostMapping
//    public Users create(@RequestBody UsersDTO user) {
//        return usersService.create(user);
//    }
//
//    @PutMapping
//    public Users update(@RequestBody Users userToUpdate) {
//        return usersService.update(userToUpdate);
//    }

    @DeleteMapping("/{idUser}")
    public void delete(@PathVariable Integer idUser) throws Exception {
        usersService.delete(idUser);
    }
}