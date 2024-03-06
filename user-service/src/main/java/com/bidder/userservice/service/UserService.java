package com.bidder.userservice.service;

import com.bidder.userservice.dto.AuthRequest;
import com.bidder.userservice.dto.PasswordResetDTO;
import com.bidder.userservice.model.User;
import com.bidder.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void addUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
        }

        // Check if the password is at least 8 characters long
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long");
        }

        // Save the user if the checks pass
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id " + id));
        return user;
    }

    public void updateUser(Long id, User user) {

        userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID " + id));

        user.setId(id);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {

        // check if id exist
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID " + id));

        userRepository.delete(user);
    }

    public void deleteUser(String userName) {

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username: " + userName));


        userRepository.delete(user);
    }



   public void updateuserName(Long id, AuthRequest userDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID " + id));

        user.setUsername(userDTO.getUsername()); // Corrected: call getUsername on the userDTO instance
        userRepository.save(user);
    }

    public boolean authenticateUser(String userName, String password) {
        // Attempt to find the user by their username
        Optional<User> userOptional = userRepository.findByUsername(userName);

        // Check if the user exists and the password matches
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password); // This is NOT secure for real-world use
        }

        // User not found or password does not match
        return false;

    }


    public void resetPassword(String userName, PasswordResetDTO newPassword)
    {

        User user = userRepository.findByUsername(userName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Not Found"));

        user.setPassword(newPassword.getNewPassword());
        userRepository.save(user);
    }


}





