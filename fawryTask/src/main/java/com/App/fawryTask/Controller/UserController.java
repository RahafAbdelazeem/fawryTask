package com.App.fawryTask.Controller;

import com.App.fawryTask.Entity.User;
import com.App.fawryTask.Repsitory.UserRepository;
import com.App.fawryTask.token.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
     private UserRepository userRepository;
    @Autowired
    private JWTToken jwttoken;
    @PostMapping("/register")
    public void register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
         user.setAdmin(false);
         userRepository.save(user);
    }
    @PostMapping("/Login")
     public String login(@RequestBody User user){
        User foundUser=userRepository.findByUserName(user.getUserName());
        if(foundUser!=null && new BCryptPasswordEncoder().matches(user.getPassword(),user.getPassword())){
            return jwttoken.generateToken(foundUser.getUserName());
        }
         else
             throw  new RuntimeException("Invalid Credintioal");


    }

}
