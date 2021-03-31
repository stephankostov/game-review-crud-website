package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("get/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        Optional<User> user = userService.retrieve(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> allUsers() {
        List<User> allUsers = userService.retrieveAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        Long userId = userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                                     @RequestBody User user) {
        Optional<User> updatedUser = userService.update(user);
        if (updatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser.get());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        LOGGER.info("User deleted");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

    