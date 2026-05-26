package com.example.Todo.Service;

import com.example.Todo.Dto.LoginDto;
import com.example.Todo.Dto.Loginresponse;
import com.example.Todo.Dto.signupDto;
import com.example.Todo.Entity.User;
import com.example.Todo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String signupService(signupDto request) {

        if(userRepository.existsByEmail(request.getEmail()))
            return "Email already exists";

        try {

            User user = new User();

            user.setName(request.getName());

            user.setEmail(request.getEmail());
            System.out.println(request.getPassword());
            user.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()
                    )
            );

            user.setRole("USER");

            userRepository.save(user);

            return "Successfully registered !!";

        } catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();
        }
    }


    public Loginresponse loginService(LoginDto request)
    {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(user == null)
          throw  new RuntimeException(" user not found");
            boolean valid= passwordEncoder.matches(request.getPassword(),user.getPassword());
            if(!valid)
                throw new RuntimeException(" invalid user");
        String token =
                jwtService.generateToken(user);
        return new Loginresponse(token);

    }
}