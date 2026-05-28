package com.example.Todo.Service;

import com.example.Todo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailServic implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException
        {
            return userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("Username not Found"));

    }
    }

