package ir.hri.view;

import ir.hri.Entity.User;
import ir.hri.util.JPAUtility;

import javax.persistence.EntityManager;


public class App {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtility.getEntityManager();
        entityManager.getTransaction().begin();
        //persist user
        User user = new User(1, "Hamid");
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        //refresh user
        entityManager.refresh(user);
        //update user
        entityManager.getTransaction().begin();
        user.setName("HamidReza");
        entityManager.getTransaction().commit();
        //remove user
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        //persist user
        entityManager.getTransaction().begin();
        User user2 = new User(1, "HamidReza Ireh");
        entityManager.persist(user2);
        entityManager.getTransaction().commit();

        entityManager.close();
        JPAUtility.close();
    }
}
