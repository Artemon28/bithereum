package com.example.demo.Entities;

public class User {
    private Long id;

    private String login;

    private String password;

    private String nickname;

    private UserApi api;

    public User() {}

    public User(Long id, String login, byte[] password, String nickname) {
        this.id = id;
        this.login = login;
        this.password = new String(password);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public Long getId() {
        if (id == null){
            return 0L;
        }
        return id;
    }
}
