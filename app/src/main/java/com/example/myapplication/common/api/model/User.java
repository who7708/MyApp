package com.example.myapplication.common.api.model;


/**
 * @author Chris
 * @since 1.0
 */
public class User {

    // {
    //     "id": 7,
    //         "email": "michael.lawson@reqres.in",
    //         "first_name": "Michael",
    //         "last_name": "Lawson",
    //         "avatar": "https://reqres.in/img/faces/7-image.jpg"
    // }

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
