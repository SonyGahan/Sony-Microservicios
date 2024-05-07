package com.sonyairlines.user.Controller;

import com.sonyairlines.user.Model.User;
import com.sonyairlines.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.listaUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.buscarUsuarioPorId(id);
    }

    @PostMapping("/add")
    public Optional<User> createUser(@RequestBody User user) {
        return userService.crearUsuario(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.borrarUsuarioPorid(id);

    }

    @PutMapping("/update")
    public Optional<User> updateUser(@RequestBody User user) {
        return userService.actualizarUsuario(user);
    }
}