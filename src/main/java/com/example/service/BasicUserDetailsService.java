package com.example.service;


import com.example.entity.UserEntity;
import com.example.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by home on 29.01.16.
 */
@Service
@Primary
public class BasicUserDetailsService implements UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepository.findByEmail(username);
            if (user == null) {
                LOG.debug("Credentials [{}] failed to locate a user - hint, username.", username.toLowerCase());
                throw new UsernameNotFoundException("User not found");
            }
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            String[] items = user.getRole().split(",");
            for (String item : items) {
                GrantedAuthority authority = new SimpleGrantedAuthority(item);
                authorities.add(authority);
            }
            return new User(user.getIdUser().toString(), user.getPassword(), authorities);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            LOG.error(e.getLocalizedMessage());
            throw new UsernameNotFoundException(e.getLocalizedMessage());
        }
    }
}
