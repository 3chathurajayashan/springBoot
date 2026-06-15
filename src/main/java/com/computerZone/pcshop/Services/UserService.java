package com.computerZone.pcshop.Services;

import com.computerZone.pcshop.DTO.User.UserCreateDTO;
import com.computerZone.pcshop.DTO.User.UserResponseDTO;
import com.computerZone.pcshop.Models.UserModel;
import com.computerZone.pcshop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {

        List<UserModel> userModelList = userRepository.findAll();

        return modelMapper.map(
                userModelList,
                new TypeToken<List<UserResponseDTO>>() {}.getType()
        );
    }

    public UserResponseDTO createUser(UserCreateDTO userDTO) {

        // 1. DTO → Entity
        UserModel user = modelMapper.map(userDTO, UserModel.class);

        // 2. HASH PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. SAVE ENTITY
        UserModel savedUser = userRepository.save(user);

        // 4. RETURN SAFE RESPONSE DTO
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }


}
