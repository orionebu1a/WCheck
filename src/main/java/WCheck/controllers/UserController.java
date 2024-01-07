package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.UserDTO;
import WCheck.entities.UserName;
import WCheck.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Получение записи по ID
    @GetMapping("/user/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserName userName = userService.getUser(id);
        return ResponseEntity.ok(EntityDtoConverter.convertToDto(userName, UserDTO.class));
    }

    // Создание новой записи
    @PostMapping("/user/post")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserName userName = EntityDtoConverter.convertToEntity(user, UserName.class);
        UserName createdUserName = userService.createUser(userName);
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(createdUserName, UserDTO.class), HttpStatus.CREATED);
    }
}