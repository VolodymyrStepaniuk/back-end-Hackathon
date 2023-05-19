package com.example.FinPlanner.service;

import com.example.FinPlanner.model.User;
import com.example.FinPlanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ServiceInterface<User>{

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        User old = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        user.setCardList(old.getCardList());
        userRepository.save(user);
    }

    public User update(Long id,String changeable,PrivateChangingType type){
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        switch (type){
            case EMAIL -> user.setEmail(changeable);
            case PASSWORD -> user.setPassword(changeable);
            default -> user.setPhone(changeable);
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User getByEmailAndPassword(String email,String password){
        return userRepository.findUserByEmailAndPassword(email, password).orElseThrow(EntityNotFoundException::new);
    }

    @Getter
    public enum PrivateChangingType {
        EMAIL("Email"), PASSWORD("Password"),PHONE("phoneNumber");

        private final String type;

        PrivateChangingType(String type) {
            this.type = type;
        }
    }
}
