package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by home on 29.01.16.
 */
@Entity
@Table(name = "Users", schema = "public", catalog = "postgres")
public class UserEntity {

    private UUID idUser;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Boolean verified;
    private String role;
    private ZonedDateTime updated;
    private ZonedDateTime created;

    public void clearPassword(){
        this.password = null;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id_user")
    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String emailaddress) {
        this.email = emailaddress;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String hashedpassword) {
        this.password = hashedpassword;
    }

    @Basic
    @Column(name = "verified")
    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Basic
    @Column(name = "updated")
    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "created", updatable = false)
    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime timecreated) {
        this.created = timecreated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (verified != null ? !verified.equals(that.verified) : that.verified != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (verified != null ? verified.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

}
