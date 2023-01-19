package facades;

import dtos.TalkDTO;
import entities.Talk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ConferenceFacade {

    private static ConferenceFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ConferenceFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ConferenceFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ConferenceFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<TalkDTO> getAllTalks() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Talk> query = em.createQuery("SELECT t FROM Talk t", Talk.class);
            List<Talk> talks = query.getResultList();
            return TalkDTO.getDTOs(talks);
        } finally {
            em.close();
        }

    }
}
