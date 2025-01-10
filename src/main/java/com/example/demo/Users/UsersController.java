package com.example.demo.Users;

import com.example.demo.SubscriptionType.ChangeSubscriptionRequest;
import com.example.demo.SubscriptionType.SubscriptionType;
import com.example.demo.SubscriptionType.SubscriptionTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;
    public final SubscriptionTypeRepository subscriptionTypeRepository;

    @GetMapping("/all")
    public List<Users> getAll() {
        return usersService.getAll();
    }

    @GetMapping("/allDTO")
    public List<UsersDTO> getAllDTO() {
        return usersService.getAllDTO();
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

    @PutMapping("/update-by-email/{email}")
    public ResponseEntity<?> updateUserByEmail(
            @PathVariable String email,
            @RequestBody Map<String, String> updates) {
        // Găsește utilizatorul după email
        Optional<Users> optionalUser = usersRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        Users user = optionalUser.get();

        // Actualizează email-ul dacă este prezent
        if (updates.containsKey("email")) {
            user.setEmail(updates.get("email"));
        }

        // Actualizează tipul de abonament dacă este prezent
        if (updates.containsKey("subscriptionType")) {
            Optional<SubscriptionType> subscriptionType =
                    subscriptionTypeRepository.findByTypeName(updates.get("subscriptionType"));
            if (subscriptionType.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid subscription type.");
            }
            user.setSubscriptionType(subscriptionType.get());
        }

        // Salvează modificările
        usersRepository.save(user);

        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        Optional<Users> optionalUser = usersRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        usersRepository.delete(optionalUser.get());
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Users user = usersService.findByEmail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        String token = "dummyToken"; // Generează un token JWT aici
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Verifică credențialele
        if ("admin@admin.com".equals(username) && "admin".equals(password)) {
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UsersDTO usersDTO) {
        try {
            Users newUser = usersService.create(usersDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            // Capturăm eroarea când email-ul este deja folosit
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid subscription type");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<UsersDTO> getUserProfile(@PathVariable String email) {
        try {
            UsersDTO userDTO = usersService.getUserByEmail(email);
            return ResponseEntity.ok(userDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/profile/increment-files/{email}")
    public ResponseEntity<?> incrementFilesConverted(@PathVariable String email) {
        Users user = usersService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        int currentCount = user.getNrOfFilesConvertedPerMonth();
        int limit = user.getSubscriptionType().getFileNumberLimitPerDay(); // Înlocuiește cu logica corectă

        if (currentCount >= limit) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("File conversion limit reached.");
        }

        usersService.incrementFilesConverted(email);
        return ResponseEntity.ok("File count incremented successfully.");
    }

    @PutMapping("/profile/decrement-files/{email}")
    public ResponseEntity<?> decrementFilesConverted(@PathVariable String email) {
        Users user = usersService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        if (user.getNrOfFilesConvertedPerMonth() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot decrement below zero.");
        }

        usersService.decrementFilesConverted(email);
        return ResponseEntity.ok("File count decremented successfully.");
    }

    @PutMapping("/profile/clear-history/{email}")
    public ResponseEntity<?> clearConversionHistory(@PathVariable String email) {
        Users user = usersService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Doar logica frontend-ului necesită resetare locală, backend-ul rămâne neschimbat.
        return ResponseEntity.ok("Conversion history cleared successfully.");
    }

    @PutMapping("/profile/change-subscription/{email}")
    public ResponseEntity<?> changeSubscriptionType(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newSubscriptionType = request.get("subscriptionType");

        Users user = usersService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findByTypeName(newSubscriptionType);
        if (optionalSubscriptionType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid subscription type.");
        }

        SubscriptionType subscriptionType = optionalSubscriptionType.get();

        // Setează noul tip de abonament
        user.setSubscriptionType(subscriptionType);

        // Convertește LocalDate la Date și setează data curentă
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        user.setSubscriptionStartDate(currentDate);

        usersRepository.save(user);

        return ResponseEntity.ok("Subscription type and start date updated successfully.");
    }



}