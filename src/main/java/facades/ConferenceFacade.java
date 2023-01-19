package facades;

import dtos.TalkDTO;
import entities.Conference;
import entities.Talk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public List<TalkDTO.ConferenceInnerDTO> getAllConferences() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Conference> query = em.createQuery("SELECT c FROM Conference c", Conference.class);
            List<Conference> conferences = query.getResultList();
            return TalkDTO.ConferenceInnerDTO.getDTOs(conferences);
        } finally {
            em.close();
        }
    }

    public List<TalkDTO> getTalksBeConferenceId(Long conferenceId) {
        List<TalkDTO> talkDTOList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            Conference conference = em.createQuery("SELECT c FROM Conference c WHERE c.id = :conferenceId", Conference.class)
                    .setParameter("conferenceId", conferenceId).getSingleResult();
            List<Talk> talks = em.createQuery("SELECT t FROM Talk t WHERE t.conference = :conference", Talk.class)
                    .setParameter("conference", conference).getResultList();
            talkDTOList = Talk.getTalkDTOList(talks);
            return talkDTOList;
        } finally {
            em.close();
        }
    }

    public TalkDTO.ConferenceInnerDTO createConference(Conference conference) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conference);
            em.getTransaction().commit();
            return new TalkDTO.ConferenceInnerDTO(conference);
        } finally {
            em.close();
        }
    }
}
