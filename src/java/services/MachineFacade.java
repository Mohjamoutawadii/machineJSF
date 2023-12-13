/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Employe;
import entities.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author a
 */
@Stateless
public class MachineFacade extends AbstractFacade<Machine> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MachineFacade() {
        super(Machine.class);
    }
     public List<Machine> getMachinesByEmployee(Employe employee) {
        TypedQuery<Machine> query = em.createNamedQuery("Machine.findByEmployee", Machine.class);
        query.setParameter("employe", employee);
        return query.getResultList();
    }
     
     public List<Object[]> getMachineAcquisitionData() {
        String jpqlQuery = "SELECT FUNCTION('YEAR', m.dateAchat), COUNT(m) FROM Machine m GROUP BY FUNCTION('YEAR', m.dateAchat)";
         Query query = getEntityManager().createQuery(jpqlQuery);
        return query.getResultList();
    }
    
}
