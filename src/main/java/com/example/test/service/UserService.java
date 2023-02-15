package com.example.test.service;

import com.example.test.DAO.UserDAO;
import com.example.test.dto.LoginForm;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    UserDAO userDAO = new UserDAO();

    public void saveUser(LoginForm loginForm) {
        userDAO.saveUser(loginForm, LocalDate.now());
    }
}
