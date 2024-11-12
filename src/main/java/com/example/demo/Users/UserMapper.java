package com.example.demo.Users;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class UserMapper {
    public static UsersDTO userToUserDTO(Users user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UsersDTO.class);
    }
}
