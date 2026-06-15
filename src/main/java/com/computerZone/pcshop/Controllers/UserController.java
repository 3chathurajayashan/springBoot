package com.computerZone.pcshop.Controllers;

import com.computerZone.pcshop.DTO.User.UserCreateDTO;
import com.computerZone.pcshop.DTO.User.UserResponseDTO;
import com.computerZone.pcshop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserResponseDTO> getUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/addUser")
    public UserResponseDTO addUser(@RequestBody UserCreateDTO userDTO) {
        return userService.createUser(userDTO);
    }

}
