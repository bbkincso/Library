package com.library.Borrowing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="users")
@ApiModel(description = "User model of Borrowing Service")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The id of user.")
//    @Column(name = "user_id")
    @Column(name = "id")
    private Long userId;
    @NotEmpty
    @ApiModelProperty(notes = "The first name of user. Needs to be at least 2 characters.")
    @Size(min = 2, message = "FirstName should be at least 2 characters.")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty
    @ApiModelProperty(notes = "The surname of user. Needs to be at least 2 characters.")
    @Size(min = 2, message = "Surname should be at least 2 characters.")
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @ApiModelProperty(notes = "The date of birth of user.")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_of_birth")
    private Date dob;
    @NotEmpty
    @ApiModelProperty(notes = "The email address of user. Needs to be a valid email address.")
    @Email
    private String email;
    @ApiModelProperty(notes = "The phone number of user. Needs to be a valid phone number.")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Wrong format for phone number.")
    private String phone;
    @NotEmpty
    @ApiModelProperty(notes = "The address of user.")
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
                "id=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
