/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.app.webapp.entity.facade;

import com.web.app.webapp.entity.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author borisdom
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();
    
    public Users findByUsername(String username);

    List<Users> findRange(int[] range);

    int count();
    
}
