package com.app.burenok.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "birthday")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = "login")
    @NotNull
    @Size(min = 2, max = 20, message = "Login length must between 5 and 20 characters!")
    private String login;

    @Column(name = "password")
    @NotNull
    @Size(min = 6, max = 20, message = "Password length must between 6 and 20 characters!")
    private String password;

    @Column(name = "aboutcontact")
    @NotNull
    private String aboutContact;

    @Column(name = "address")
    @NotNull
    private String address;

    public Contact(String name, String surname, Date birthday, String login, String password, String aboutContact, String address) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.aboutContact = aboutContact;
        this.address = address;
    }

    public Contact copyFrom (Contact source){

        this.setName(source.getName());
        this.setSurname(source.getSurname());
        this.setLogin(source.getLogin());
        this.setBirthday(source.getBirthday());
        this.setPassword(source.getPassword());
        this.setAboutContact(source.getAboutContact());
        this.setAddress(source.getAddress());
        return this;
    }
}
