package com.web.app.webapp.beans;

import com.web.app.webapp.entity.Users;
import com.web.app.webapp.entity.facade.UsersFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "usersManagedBean")
@RequestScoped
public class UsersManagedBean implements Serializable {

    private List<Users> _users;
    private String usernameInput;
    private String passwordInput;

    @Inject
    UsersFacadeLocal usersFacadeLocal;

    @PostConstruct
    private void init() {
        _users = usersFacadeLocal.findAll();
    }

    public UsersManagedBean() {
    }

    public List<Users> getUsers() {
        return _users;
    }

    public void setUsers(List<Users> _users) {
        this._users = _users;
    }

    public String getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String login(){
        return "";
    }
}
