package com.bidder.userservice.controller;

import com.bidder.userservice.dto.AuthRequest;
import com.bidder.userservice.dto.PasswordResetDTO;
import com.bidder.userservice.dto.UsernameResetDTO;
import com.bidder.userservice.model.User;
import com.bidder.userservice.repository.UserRepository;
import com.bidder.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public String addUser(@RequestBody User user)
    {
        userService.addUser(user);
        return "Endpoint hit successfully";

    }

    //get list
    @GetMapping()
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    // get from id
    @GetMapping("/get")
    public User getUser(@RequestParam Long id)
    {
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User user)
    {
        userService.updateUser(id,user);
        return ResponseEntity.noContent().build();
    }

    //delete from ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //delete from userName
    @DeleteMapping("/deletebyUser/{username}")
    public ResponseEntity<Void> deleteUser (@PathVariable String userName)
    {
        userService.deleteUser(userName);
        return ResponseEntity.noContent().build();
    }

    // update their first name
    @PatchMapping ("/update-name/{id}")
    public ResponseEntity<Void> updateuserName(@PathVariable Long id, @RequestBody AuthRequest userDTO)
    { userService.updateuserName(id,userDTO);
        return ResponseEntity.noContent().build();
    }

    //Update Password
    @PatchMapping("/reset-password/{userName}")
    public ResponseEntity<Void> resetPassword(@PathVariable String userName, @RequestBody PasswordResetDTO password) {
        userService.resetPassword(userName, password);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest loginRequest)
    {
        boolean validAccount;
        validAccount = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (validAccount) {
            // Login successful
            return ResponseEntity.ok().body("Login successful");
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }



}
