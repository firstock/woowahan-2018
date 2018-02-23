package com.woowahan.woowahan2018.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {

    @Column(nullable =  false, unique = true)
    @Length(min = 5, max = 30)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false)
    @Length(max = 30)
    private String name;

    public User() {

    }

    public User(String email, String encryptedPassword, String name) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.name = name;
    }

    public boolean isCorrectPassword(String inputPassword, PasswordEncoder encoder) {
        return encoder.matches(inputPassword, this.encryptedPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(encryptedPassword, user.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, encryptedPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                '}';
    }
}
