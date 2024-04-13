package com.picpay.picpay.services;

import com.picpay.picpay.domain.user.User;
import com.picpay.picpay.domain.user.UserType;
import com.picpay.picpay.dtos.UserDTO;
import com.picpay.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal ammount) throws Exception {
        if (sender.getUserType() == UserType.MARCHANT) {
            throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação!");
        }

        if (sender.getBalance().compareTo(ammount) < 0) {
            throw new Exception("Saldo insuficiente para realizar a transação!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception(("Usuário não encontrado!")));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
