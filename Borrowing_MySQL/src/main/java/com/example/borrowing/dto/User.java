package com.example.borrowing.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="users")
@ApiModel(description = "User model description")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    @Column(name = "id")
    private long id;
    @NotEmpty
    @Size(min = 2, message = "FirstName should be at least 2 characters.")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "FirstName should be at least 2 characters.")
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_of_birth")
    private Date dob;
    @NotEmpty
    @ApiModelProperty(notes = "isAvailable cannot be empty.")
    @Email
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})", message = "Wrong format for phone number.")
    private String phone;
    @NotEmpty
    private String address;

    public User() {
    }

    public User(String firstName, String lastName, Date dob, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
