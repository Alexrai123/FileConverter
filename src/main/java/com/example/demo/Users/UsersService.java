package com.example.demo.Users;

import com.example.demo.SubscriptionType.SubscriptionType;
import com.example.demo.SubscriptionType.SubscriptionTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public Users create(UsersDTO user) {

        return usersRepository.save(Users.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .subscriptionType(subscriptionTypeRepository.findByTypeName(user.getSubscriptionType()).orElseThrow(() -> new EntityNotFoundException("Subscription type not found")))
                .subscriptionStartDate(user.getSubscriptionStartDate())
                .nrOfFilesConvertedPerMonth(user.getNrOfFilesConvertedPerMonth())
                .build());

    }

    public Users getOne(Integer idUser) {
        return usersRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UsersDTO getUserById(Integer id) {
        return usersRepository.findById(id)
                .map(user -> new UsersDTO(
                        user.getEmail(),
                        user.getPassword(),
                        user.getSubscriptionType().getTypeName(),
                        user.getSubscriptionStartDate(),
                        user.getNrOfFilesConvertedPerMonth()
                ))
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public List<UsersDTO> getAllDTO(){
        return usersRepository.findAll().stream()
                .map(user -> new UsersDTO(user.getEmail(), user.getPassword(), user.getSubscriptionType().getTypeName(), user.getSubscriptionStartDate(), user.getNrOfFilesConvertedPerMonth()))
                .toList();
    }

    public UsersDTO getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(user -> new UsersDTO(
                        user.getEmail(),
                        user.getPassword(),
                        user.getSubscriptionType().getTypeName(),
                        user.getSubscriptionStartDate(),
                        user.getNrOfFilesConvertedPerMonth()
                ))
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    public Users update(Users userToUpdate) {
        Users user = usersRepository.findById(userToUpdate.getUser_id()).orElse(new Users());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        user.setSubscriptionType(userToUpdate.getSubscriptionType());
        user.setSubscriptionStartDate(userToUpdate.getSubscriptionStartDate());
        user.setNrOfFilesConvertedPerMonth(userToUpdate.getNrOfFilesConvertedPerMonth());
        return usersRepository.save(user);
    }

    public void updateUser(UsersDTO userDTO) {
        Users user = usersRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userDTO.getEmail()));
        user.setPassword(userDTO.getPassword());
        user.setSubscriptionType(subscriptionTypeRepository.findByTypeName(userDTO.getSubscriptionType())
                .orElseThrow(() -> new EntityNotFoundException("Subscription type not found")));
        user.setSubscriptionStartDate(userDTO.getSubscriptionStartDate());
        user.setNrOfFilesConvertedPerMonth(userDTO.getNrOfFilesConvertedPerMonth());
        usersRepository.save(user);
    }

    public void delete(Integer idUser) throws Exception {
        Users user = usersRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
        usersRepository.delete(user);
    }

    public void deleteUserByEmail(String email) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        usersRepository.delete(user);
    }


}
