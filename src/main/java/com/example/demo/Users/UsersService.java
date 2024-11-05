package com.example.demo.Users;

import com.example.demo.SubscriptionType.SubscriptionTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public Users create(UsersDTO user) {

        return usersRepository.save(Users.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .subscriptionType(subscriptionTypeRepository.findByTypeName(user.getSubscriptionType()).orElseThrow(() -> new EntityNotFoundException("Subscription type not found")))
                .subscriptionStartDate(user.getSubscriptionStartDate())
                .build());

    }

    public Users getOne(Integer idUser) {
        return usersRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Users update(Users userToUpdate) {
        Users user = usersRepository.findById(userToUpdate.getUser_id()).orElse(new Users());
        user.setUsername(userToUpdate.getUsername());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        user.setSubscriptionType(userToUpdate.getSubscriptionType());
        user.setSubscriptionStartDate(userToUpdate.getSubscriptionStartDate());
        return usersRepository.save(user);
    }

    public void delete(Integer idUser) throws Exception {
        Users user = usersRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
        usersRepository.delete(user);
    }

}
