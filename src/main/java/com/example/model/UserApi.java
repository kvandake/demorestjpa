package com.example.model;

import com.example.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by home on 22.02.16.
 */
public class UserApi {

    public static final String EMAIL_ADDRESS = "email";

    public static final String FIRSTNAME = "firstname";

    public static final String LASTNAME = "lastname";


    public static final String ID = "id";

    public static final String PASSWORD = "password";

    public static final String UPDATED = "updated";


    private UUID id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    private ZonedDateTime updated;

    public UserApi() {

    }

    public UserApi(UserEntity userEntity) {
        this.id = userEntity.getIdUser();
        this.email = userEntity.getEmail();
        this.firstname = userEntity.getFirstname();
        this.lastname = userEntity.getLastname();
        this.updated = userEntity.getUpdated();
    }

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setFirstname(firstname);
        userEntity.setLastname(lastname);
        return userEntity;
    }

    @JsonProperty(ID)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty(EMAIL_ADDRESS)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(FIRSTNAME)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(LASTNAME)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(UPDATED)
    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }
}
