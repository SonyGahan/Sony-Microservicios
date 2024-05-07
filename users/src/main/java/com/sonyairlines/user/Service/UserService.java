package com.sonyairlines.user.Service;

import com.sonyairlines.user.Model.User;
import com.sonyairlines.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> listaUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> crearUsuario(User user) {
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> buscarUsuarioPorId(Long id) {
        return userRepository.findById(id);
    }

    public void borrarUsuarioPorid(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> actualizarUsuario(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

}