package com.web.app.webapp.beans;

import com.web.app.webapp.entity.Privilege;
import com.web.app.webapp.entity.Users;
import com.web.app.webapp.entity.facade.PrivilegeFacadeLocal;
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
    private int roleId;
    private int privilegeTemp;

    @Inject
    PrivilegeFacadeLocal privilegeFacadeLocal;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    

    public String login() {
        Users user = usersFacadeLocal.findByUsername(usernameInput);
        if (usernameInput != null && passwordInput != null) {
            if (user.getUsername() != null && user.getPassword() != null) {
                if (usernameInput.equals(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                    privilegeTemp = user.getIdPrivilege().getId();
                }
            }
        }
        if (privilegeTemp == 1) {
            return "admin";
        } else if (privilegeTemp == 2) {
            return "editor";
        } else {
            return "";
        }
    }

    public String register() {
        Users user = new Users();
        Privilege privilege = privilegeFacadeLocal.find(roleId);
        user.setIdPrivilege(privilege);
        user.setUsername(usernameInput);
        user.setPassword(passwordInput);
        usersFacadeLocal.create(user);
        return "login";
    }
}
