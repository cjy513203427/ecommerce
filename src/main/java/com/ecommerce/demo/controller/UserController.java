package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.ResponseDto;
import com.ecommerce.demo.dto.SignInDto;
import com.ecommerce.demo.dto.SignInResponseDto;
import com.ecommerce.demo.dto.SignupDto;
import com.ecommerce.demo.exception.CustomException;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.UserRepo;
import com.ecommerce.demo.service.AuthenticationService;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepo userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

/*    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }*/

    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }

}