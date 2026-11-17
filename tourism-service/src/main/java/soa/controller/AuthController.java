package soa.controller;

import soa.model.User;
import soa.repository.UserRepository;
import soa.security.JwtService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository repo;

    @Autowired
    JwtService jwt;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User u) {

        u.setPassword(encoder.encode(u.getPassword()));

        repo.save(u);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User u) {

        User user = repo.findByUsername(u.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(u.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwt.generateToken(u.getUsername());

        return Map.of("token", token);
    }
}