package com.upc.appcafe.security.controllers;

import com.upc.appcafe.security.entities.Role;
import com.upc.appcafe.security.entities.User;
import com.upc.appcafe.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            String bcryptPassword = bcrypt.encode(user.getPassword());
            user.setPassword(bcryptPassword);
            User saved = userService.save(user);
            return ResponseEntity.ok(saved);
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El nombre de usuario ya existe. Por favor elige otro.");
        }
    }
    @PostMapping("/rol")
    public void createRol(@RequestBody Role rol) {
        userService.grabar(rol);
    }


    @PostMapping("/save/{user_id}/{rol_id}")

    public ResponseEntity<Integer> saveUseRol(@PathVariable("user_id") Long user_id,
                                              @PathVariable("rol_id") Long rol_id){
        return new ResponseEntity<Integer>(userService.insertUserRol(user_id, rol_id), HttpStatus.OK);

    }
}
