package com.example.demo.Entities;


import javax.persistence.*;

@Entity
@Table(name = "users_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @OneToOne(mappedBy = "user")
    private UserApi api;

    public User() {}

    public User(String login, byte[] password, String nickname) {
        this.login = login;
        this.password = new String(password);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
