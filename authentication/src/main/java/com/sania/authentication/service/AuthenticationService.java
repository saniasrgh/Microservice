package com.sania.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sania.authentication.User;
import com.sania.authentication.login.LoginResponse;
import com.sania.authentication.register.RegisterRequest;
import com.sania.authentication.repository.UserRepository;
import com.sania.authentication.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request){

    if (userRepository.findByUsername(request.getUsername()).isPresent()){
        throw new RuntimeException("Username sudah ada, buat username baru");
    }

    if (userRepository.findByEmail(request.getEmail()).isPresent()){
        throw new RuntimeException("Email sudah digunakan");
    }

    User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role("USER")
            .build();

    userRepository.save(user);

    return "User berhasil didaftarkan";
}

    public LoginResponse login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User tidak ditemukan"));

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Password Salah");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        
            return new LoginResponse(token);
    }
}