package com.example.seguridadcap2.controller;

import com.example.seguridadcap2.controller.request.CreateUserDto;
import com.example.seguridadcap2.models.ERole;
import com.example.seguridadcap2.models.RoleEntity;
import com.example.seguridadcap2.models.UserEntity;
import com.example.seguridadcap2.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
//@RequestMapping("/principal")
public class PrincipalController {

    final UserRepository userRepository;

    public PrincipalController(UserRepository userRepository) {this.userRepository = userRepository;}

    @GetMapping("/holaNotSecured")
    public String helloNotSecured(){
        return "Hello World , Not secured";
    }

    @GetMapping("/holaSecured")
    public String helloSecured(){
        return "Hello World , Secured";
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("HOLA");
    }
    @PostMapping("/createUser" )
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto user){
        log.info("Creating user: {}", user);

        Set<RoleEntity> roles = user.getRoles().stream()
                .map( role -> RoleEntity.builder()
                        .roleName(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity =  UserEntity.builder()
                .userName(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();

        this.userRepository.save(userEntity);

        log.info("User created" + user );

        return ResponseEntity.ok(userEntity);

    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(String id){
        this.userRepository.deleteById(Long.valueOf(id));
        return "User deleted  ID -> " + id ;
    }

}
