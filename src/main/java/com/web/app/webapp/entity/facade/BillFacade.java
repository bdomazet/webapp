/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.app.webapp.entity.facade;

import com.web.app.webapp.entity.Bill;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author borisdom
 */
@Stateless
public class BillFacade extends AbstractFacade<Bill> implements BillFacadeLocal {

    @PersistenceContext(unitName = "WA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillFacade() {
        super(Bill.class);
    }
    
}
