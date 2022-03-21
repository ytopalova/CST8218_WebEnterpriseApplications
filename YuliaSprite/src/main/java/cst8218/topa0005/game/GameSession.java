 /*
 *Author:   Yuliia Topalova - 040981104
 *Project:  Assignment 1
 *Class:    GameSession
 *Purpose:  With the help of stateless session bean of the sprite game 
            will connect entity manager to my_persistence_unit.
 */
package cst8218.topa0005.game;

import cst8218.topa0005.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GameSession {   
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    //method returns the sprites
    public List<Sprite> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sprite.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    //method edits the sprites 
    public void edit(Sprite entity) {
        getEntityManager().merge(entity);
    }
}
