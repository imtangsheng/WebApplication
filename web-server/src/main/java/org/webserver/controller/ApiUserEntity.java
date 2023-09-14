package org.webserver.controller;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class ApiUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String guid;
    private String username;
    private String token;
    private String password;

    private String permission;

    public String getGuid() {
        return guid;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPermission() {
        return permission;
    }
}
