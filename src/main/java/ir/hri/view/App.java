package ir.hri.view;

import ir.hri.Entity.City;
import ir.hri.Entity.State;
import ir.hri.Entity.User;
import ir.hri.util.FileUtil;
import ir.hri.util.JPAUtility;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {

        List<State> states = FileUtil.readState();
        List<City> cities = FileUtil.readCity();

        Iterator<State> stateIterator = states.iterator();
        while (stateIterator.hasNext()) {
            ArrayList<City> cities1 = new ArrayList<City>();
            State state = stateIterator.next();

            for (City city : cities) {
                if (state.getId() == city.getStateId()) {
                    cities1.add(city);
                }
            }
            state.setCities(cities1);
        }


        EntityManager entityManager = JPAUtility.getEntityManager();
        entityManager.getTransaction().begin();
        int i = 0;
        for (State state : states) {
            entityManager.persist(state);
            if (i == 10) {
                entityManager.getTransaction().commit();
                entityManager.getTransaction().begin();
                i = 0;
            }
            i++;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtility.close();
    }
}
