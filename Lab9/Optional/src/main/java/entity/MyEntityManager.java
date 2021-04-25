package entity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManager {
    EntityManagerFactory entityManagerFactory;
    static MyEntityManager instance = null;

    //constructor pentru obiectul de tipul EntityManagerFactory
    private MyEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    }

    //getter pentru obiectul creat
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // metoda statica ce returneaza instanta creata
    public static MyEntityManager getInstance() {
        if (instance == null)
            instance = new MyEntityManager();

        return instance;
    }
}
