package eshop.product.controller;

import eshop.product.dtos.UserDto;
import eshop.product.entity.User;
import eshop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String registrationForm() {
        return "user";
    }
    /*public ResponseEntity<UserDto> registrationFrom() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(((UserDetails)principal).getUsername());
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setId(user.getId());
        return ResponseEntity.ok(dto);
    }*/
}