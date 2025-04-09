package com.App.fawryTask.Service;

import com.App.fawryTask.Entity.User;
import com.App.fawryTask.Repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
     public User saveUser( User user ){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
     }
     public User searchForUser(String userName){
         return userRepository.findByUserName(userName);
     }

}
