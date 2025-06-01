package com.healthfintel.backend.security;

import com.healthfintel.backend.model.User;
import com.healthfintel.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public User loadUserByUserEmail(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));


        return user;
    }

    public User loadUserByUserId(Long userId) throws UsernameNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + userId));


        return user;

    }
}
