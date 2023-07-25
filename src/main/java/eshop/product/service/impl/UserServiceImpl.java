package eshop.product.service.impl;


import eshop.product.dtos.UserDto;
import eshop.product.entity.Role;
import eshop.product.entity.User;
import eshop.product.repository.RoleRepository;
import eshop.product.repository.UserRepository;
import eshop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName("ROLE_USER");

        if (role == null)
            role = roleRepository.save(new Role("ROLE_USER"));

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}