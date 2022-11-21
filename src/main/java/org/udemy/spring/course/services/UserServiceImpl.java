package org.udemy.spring.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.udemy.spring.course.domain.bo.User;
import org.udemy.spring.course.exceptions.InvalidPasswordException;
import org.udemy.spring.course.repositories.UserDAO;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO dao;

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found on database"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        return org.springframework.security.core.userdetails
                .User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails auth(User user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean match = encoder().matches(user.getPassword(), userDetails.getPassword());
        if (match)
            return userDetails;

        throw new InvalidPasswordException("The password does not match");
    }

    @Transactional
    public User save(User user) {
        String encryptedPassword = encoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return dao.save(user);
    }
}
