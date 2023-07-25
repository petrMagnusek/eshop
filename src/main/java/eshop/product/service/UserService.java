package eshop.product.service;

import eshop.product.dtos.UserDto;
import eshop.product.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}