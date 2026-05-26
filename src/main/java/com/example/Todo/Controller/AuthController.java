package com.example.Todo.Controller;

import com.example.Todo.Dto.LoginDto;
import com.example.Todo.Dto.Loginresponse;
import com.example.Todo.Dto.signupDto;
import com.example.Todo.Service.AuthService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private  AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Richa !!";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> authsignup(@RequestBody signupDto request)
    {
        String response=authService.signupService(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request)
    {
        Loginresponse resposne = authService.loginService(request);
        return  ResponseEntity.ok(resposne);
    }
}
