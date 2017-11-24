package com.davioooh.srt.services;

import com.davioooh.srt.domain.User;
import com.davioooh.srt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SuperRubricaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(
                username
                , user.getPassword()
                , Collections.singleton(new SimpleGrantedAuthority("user")));
    }
}