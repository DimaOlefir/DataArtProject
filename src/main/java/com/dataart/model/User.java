package com.dataart.model;

import com.dataart.model.enums.Role;
import com.dataart.model.enums.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 26.10.2017.
 */
@Entity
@Table(name = "users")//, schema = "mapper"
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "firstName")
    //@NotEmpty(message = "*Please provide your name")
    private String firstName;

    @Column(name = "lastName")
    //@NotEmpty(message = "*Please provide your surname")
    private String lastName;

    @Column(name = "login", unique = true, nullable = false)
    //@NotEmpty(message = "*Please provide your login")
    private String login;

    @Column(name = "user_password",nullable = false)
    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email")
    //@NotEmpty(message = "*Please provide your email")
    private String email;

    @Column(name = "dateOfBirth")
    //@NotEmpty(message = "*Please provide your date of birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "about")
    private String about;

    @Column(name = "user_sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "userToMsg")
    private List<Header> headersIncoming;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "userFromMsg")
    private List<Header> headersOutgoing;

    public User(){

    }

    public User(String firstName, String lastName, String login, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Header> getHeadersIncoming() {
        return headersIncoming;
    }

    public void setHeadersIncoming(List<Header> headersIncoming) {
        this.headersIncoming = headersIncoming;
    }

    public List<Header> getHeadersOutgoing() {
        return headersOutgoing;
    }

    public void setHeadersOutgoing(List<Header> headersOutgoing) {
        this.headersOutgoing = headersOutgoing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (confirmPassword != null ? !confirmPassword.equals(user.confirmPassword) : user.confirmPassword != null)
            return false;
        if (!email.equals(user.email)) return false;
        if (!birthDate.equals(user.birthDate)) return false;
        if (about != null ? !about.equals(user.about) : user.about != null) return false;
        if (sex != user.sex) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + sex.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", about='" + about + '\'' +
                ", sex=" + sex +
                ", role=" + role +
                '}';
    }
}
