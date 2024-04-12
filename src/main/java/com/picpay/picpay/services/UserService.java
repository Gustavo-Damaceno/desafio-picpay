package com.picpay.picpay.services;

import com.picpay.picpay.domain.user.User;
import com.picpay.picpay.domain.user.UserType;
import com.picpay.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal ammount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
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
}
