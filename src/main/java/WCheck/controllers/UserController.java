package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.UserDTO;
import WCheck.entities.User;
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
    @GetMapping("/user/get")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(EntityDtoConverter.convertToDto(user, UserDTO.class));
    }

    // Создание новой записи
    @PostMapping("/user/post")
    public ResponseEntity<UserDTO> createYourEntity(@RequestBody UserDTO user) {
        User createdUser = userService.createUser(EntityDtoConverter.convertToEntity(user, User.class));
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(createdUser, UserDTO.class), HttpStatus.CREATED);
    }
}