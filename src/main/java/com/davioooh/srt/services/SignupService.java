package com.davioooh.srt.services;

import com.davioooh.srt.domain.User;
import com.davioooh.srt.model.SignupForm;
import com.davioooh.srt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    public String signup(SignupForm signupForm) {
        User user = new User();
        user.setUsername(signupForm.getUsername());
        user.setPassword(signupForm.getPassword());
        user.setEmail(signupForm.getEmail());

        return userRepository.save(user).getUsername();
    }
}
