package com.App.fawryTask.Service;


import com.App.fawryTask.Entity.Token;
import com.App.fawryTask.Entity.TokenType;
import com.App.fawryTask.Entity.User;
import com.App.fawryTask.Repsitory.TokenRepository;
import com.App.fawryTask.Repsitory.UserRepository;
import com.App.fawryTask.model.request.AuthenticationResponse;
import com.App.fawryTask.model.request.LoginRequest;
import com.App.fawryTask.model.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private  AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user = userRepository.findUserByEmail(request.getEmail());
        Map<String , Object> extraClaims = new HashMap<>();
        String jwtToken = jwtService.createToken(user , extraClaims);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse();
    }

    public AuthenticationResponse register(RegisterRequest request)
    {
        User user = new User(request.getFirstname() , request.getLastname() , request.getEmail() ,
                passwordEncoder.encode(request.getPassword()) ,
                request.getRole());

        User savedUser = userRepository.save(user);
        Map<String , Object> extraClaims = new HashMap<>();
        String jwtToken = jwtService.createToken(user , extraClaims);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken , TokenType.BEARER , false , false , user);
        tokenRepository.save(token);
    }
}
