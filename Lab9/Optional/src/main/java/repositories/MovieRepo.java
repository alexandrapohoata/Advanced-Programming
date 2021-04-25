package repositories;

import entity.MoviesEntity;
import entity.MyEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MovieRepo extends AbstractRepository<MoviesEntity> {
    private final MyEntityManager myEntityManager = MyEntityManager.getInstance();
    private final EntityManagerFactory entityManagerFactory = myEntityManager.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(MoviesEntity e){
        entityManager.getTransaction().begin();
        entityManager.persist(e);
        entityManager.flush();
        entityManager.close();
    }

    @Override
    public MoviesEntity findById(String id){
        entityManager.getTransaction().begin();
        MoviesEntity m = entityManager.find(MoviesEntity.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return m;
    }

    @Override
    public List<MoviesEntity> findByName(String name){
        entityManager.getTransaction().begin();
        List<MoviesEntity> movies = entityManager
                .createQuery("select m from MoviesEntity m where m.title = ?1")
                .setParameter(1, name)
                .getResultList();
        entityManager.close();
        return movies;
    }
}