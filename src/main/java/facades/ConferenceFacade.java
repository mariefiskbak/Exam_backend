package facades;

import dtos.TalkDTO;
import entities.Conference;
import entities.Speaker;
import entities.Talk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<TalkDTO.SpeakerInnerDTO> getAllSpeakers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Speaker> query = em.createQuery("SELECT s FROM Speaker s", Speaker.class);
            List<Speaker> speakers = query.getResultList();
            return TalkDTO.SpeakerInnerDTO.getDTOs(speakers);
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
        EntityManager em = getEntityManager();
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
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conference);
            em.getTransaction().commit();
            return new TalkDTO.ConferenceInnerDTO(conference);
        } finally {
            em.close();
        }
    }

    public TalkDTO getTalkByTalkId(Long talkId) {
        EntityManager em = getEntityManager();
        try {
            Talk talk = em.createQuery("SELECT t FROM Talk t WHERE t.id = :talkId", Talk.class)
                    .setParameter("talkId", talkId).getSingleResult();
            return new TalkDTO(talk);
        } finally {
            em.close();
        }

    }

    public TalkDTO updateTalk(Talk talk, Long addSpeakerId) {
        EntityManager em = getEntityManager();

        Talk oldTalk = em.find(Talk.class, talk.getId());
        Speaker extraSpeaker = em.find(Speaker.class, addSpeakerId);

        if (oldTalk == null) {
            throw new WebApplicationException(String.format("Talk with id: (%d) not found", talk.getId()), 400);
        }

        oldTalk.setTopic(talk.getTopic());
        oldTalk.setDuration(talk.getDuration());
        oldTalk.setPropsList(talk.getPropsList());
        Set<Speaker> speakerSet = oldTalk.getSpeakers();
        Set<Talk> talksSet = extraSpeaker.getTalkSet();
        speakerSet.add(extraSpeaker);
        talksSet.add(oldTalk);
        oldTalk.setSpeakers(speakerSet);


        try {
            em.getTransaction().begin();
            em.merge(oldTalk);
            em.getTransaction().commit();
            return new TalkDTO(oldTalk);
        } finally {
            em.close();
        }


    }

    public TalkDTO deleteTalk(Long id) {
        EntityManager em = getEntityManager();
        Talk talk = em.find(Talk.class, id);
        if (talk== null) {
            throw new WebApplicationException(String.format("Talk with id: (%d) not found", id), 404);
        }
        try {
            em.getTransaction().begin();
            em.remove(talk);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TalkDTO(talk);
    }
}
