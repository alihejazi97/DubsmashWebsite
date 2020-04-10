package com.ali.security.service;

import com.ali.database.model.User;
import com.ali.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository studentRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = studentRepository.findByusername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void saveUser(User user){
        studentRepository.save(user);
    }
}
