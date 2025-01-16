package com.stockexchange.userservice.service;


import com.stockexchange.userservice.controller.dto.UserRequest;
import com.stockexchange.userservice.model.Role;
import com.stockexchange.userservice.model.User;
import com.stockexchange.userservice.repository.RoleRepository;
import com.stockexchange.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        Role role;
        if (Boolean.TRUE.equals(userRequest.isAdmin())) {
           role = roleRepository.findByName("ROLE_ADMIN");
            if (role == null) {
                throw new RuntimeException("Role 'ROLE_ADMIN' não encontrada!");
            }
            user.getRoles().add(role);
        } else {
            role = roleRepository.findByName("ROLE_USER");
            if (role == null) {
                throw new RuntimeException("Role 'ROLE_USER' não encontrada!");
            }
            user.getRoles().add(role);
        }
        userRepository.save(user);
    }
    
   
    
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(RuntimeException::new);
    }
}
