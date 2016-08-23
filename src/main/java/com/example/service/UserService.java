package com.example.service;

import com.example.entity.UserEntity;
import com.example.ext.PasswordValidator;
import com.example.model.UserApi;
import com.example.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * Created by home on 29.01.16.
 */

@Service
public class UserService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordValidator passwordValidator;


    /**
     * create user
     *
     * @param userApi
     * @param authentication
     * @return
     * @throws SecurityException
     */
    @Transactional
    public UserApi create(@NotNull UserApi userApi, final Authentication authentication) throws SecurityException {
        try {
            UserEntity user = userRepository.findByEmail(userApi.getEmail());
            if (user != null) {
                throw new SecurityException("user already exists");
            }
            if (!passwordValidator.validate(userApi.getPassword())) {
                throw new SecurityException(String.format("password incorrect, password pattern: %s",
                        passwordValidator.getPatternBuilder().toString()));
            }
            if (StringUtils.isEmpty(userApi.getFirstname())) {
                throw new SecurityException("firstname is null");
            }
            String hashedPassword = passwordEncoder.encode(userApi.getPassword());
            UserEntity userEntity = userApi.toEntity();
            userEntity.setPassword(hashedPassword);
            userEntity.setRole(authoritesToString(authentication.getAuthorities()));
            userEntity.setCreated(ZonedDateTime.now());
            //TODO not check email
            userEntity.setVerified(true);
            userRepository.saveAndFlush(userEntity);
            return new UserApi(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Create user: " + e.getLocalizedMessage());
            throw new SecurityException(e.getLocalizedMessage());
        }
    }


    /**
     * get info user
     *
     * @param email
     * @param password
     * @return
     * @throws SecurityException
     */
    @Transactional(readOnly = true)
    public UserApi get(String email, String password) throws SecurityException {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword())) {
                throw new SecurityException("email or password incorrect");
            }
            return new UserApi(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Get user: " + e.getLocalizedMessage());
            throw new SecurityException(e.getLocalizedMessage());
        }
    }


    /**
     * update user
     *
     * @param email
     * @param password
     * @param userApi
     * @return
     * @throws SecurityException
     */
    @Transactional
    public UserApi update(String email, String password, UserApi userApi) throws SecurityException {
        try {
            UserEntity user = userRepository.findByEmail(email);
            if (user == null) {
                throw new SecurityException("user not found");
            }
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new SecurityException("email or password incorrect");
            }
            if (!StringUtils.isEmpty(userApi.getEmail()) && !Objects.equals(user.getEmail(), userApi.getEmail())) {
                UserEntity findUser = userRepository.findByEmail(userApi.getEmail());
                if (findUser != null) {
                    throw new SecurityException("email already exists");
                } else {
                    user.setEmail(userApi.getEmail());
                }
            }
            if (!StringUtils.isEmpty(userApi.getPassword())) {
                if (passwordValidator.validate(userApi.getPassword())) {
                    String hashedPassword = passwordEncoder.encode(userApi.getPassword());
                    user.setPassword(hashedPassword);
                } else {
                    throw new SecurityException(String.format("new password incorrect, password pattern: %s", passwordValidator.getPatternBuilder().toString()));
                }
            }
            if (userApi.getFirstname() != null) {
                user.setFirstname(userApi.getFirstname());
            }
            if (userApi.getLastname() != null) {
                user.setLastname(userApi.getLastname());
            }
            user.setUpdated(ZonedDateTime.now());
            userRepository.saveAndFlush(user);
            return new UserApi(user);
        } catch (Exception e) {
            LOG.debug("Update user: " + e.getLocalizedMessage());
            throw new SecurityException(e.getLocalizedMessage());
        }
    }


    /**
     * delete user
     *
     * @param params
     * @return
     * @throws SecurityException
     */
    @Transactional
    public boolean delete(Map<String, String> params) throws SecurityException {
        try {
            throw new SecurityException("not supported");
        } catch (Exception e) {
            LOG.debug("Delete User: " + e.getLocalizedMessage());
            throw new SecurityException(e.getLocalizedMessage());
        }
    }


    String authoritesToString(Collection<? extends GrantedAuthority> authorities) {
        String result = Arrays.toString(authorities.toArray());
        result = result.replace("[", "").replace("]", "");
        return result;
    }



}
