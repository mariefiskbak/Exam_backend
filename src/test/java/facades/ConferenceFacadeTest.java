package facades;

import dtos.TalkDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConferenceFacadeTest {
    private static EntityManagerFactory emf;
    private static ConferenceFacade facade;

    private Conference c1;
    private Conference c2;
    private Conference c3;
    private TalkDTO.ConferenceInnerDTO c1DTO;
    private TalkDTO.ConferenceInnerDTO c2DTO;
    private TalkDTO.ConferenceInnerDTO c3DTO;
    private Talk t1;
    private Talk t2;
    private Talk t3;
    private TalkDTO t1DTO;
    private TalkDTO t2DTO;
    private TalkDTO t3DTO;
    private Speaker s1;
    private Speaker s2;
    private Speaker s3;
    private TalkDTO.SpeakerInnerDTO s1DTO;
    private TalkDTO.SpeakerInnerDTO s2DTO;
    private TalkDTO.SpeakerInnerDTO s3DTO;

    public ConferenceFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ConferenceFacade.getInstance(emf);
    }


    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


//        User user = new User("user", "456");
//        User admin = new User("admin", "456");
//
//        Role userRole = new Role("user");
//        Role adminRole = new Role("admin");
//        user.addRole(userRole);
//        admin.addRole(adminRole);
//        em.persist(userRole);
//        em.persist(adminRole);
//        em.persist(user);
//        em.persist(admin);

        c1 = new Conference("c1", "sted1", 100, "2024-01-01", "11:00:00");
        c2 = new Conference("c2", "sted2", 100, "2024-01-01", "11:11:00");
        c3 = new Conference("c3", "sted3", 100, "2024-01-01", "11:11:11");
        s1 = new Speaker("Anja Andersen", "handball player", "female");
        s2 = new Speaker("Mads Mikkelsen", "actor", "male");
        s3 = new Speaker("Una Thurman", "actor", "female");
        t1 = new Talk("t1", 80, "thing1", c1);
        t2 = new Talk("t2", 90, "thing2", c2);
        t3 = new Talk("t3", 100, "thing3", c3);

        s1.addTalk(t1);
        s2.addTalk(t2);
        s3.addTalk(t1);
        s3.addTalk(t3);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.getTransaction().commit();
        em.clear();
        em.close();

        c1DTO = new TalkDTO.ConferenceInnerDTO(c1);
        c2DTO = new TalkDTO.ConferenceInnerDTO(c2);
        c3DTO = new TalkDTO.ConferenceInnerDTO(c3);

        t1DTO = new TalkDTO(t1);
        t2DTO = new TalkDTO(t2);
        t3DTO = new TalkDTO(t3);
    }

    @AfterEach
    public void tearDown() {
//        we do this before setup, so we can see the data in workbench
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

//    @Test
//    void getAllConferences() {
//        List<TalkDTO.ConferenceInnerDTO> conferences = facade.getAllConferences();
//        int expected = 3;
//        int actual = conferences.size();
//        assertEquals(expected, actual);
 //       assertThat(conferences, containsInAnyOrder(c1DTO, c2DTO, c3DTO));
//    }

    @Test
    void getAllTalks() {
        List<TalkDTO> talks = facade.getAllTalks();
        int expected = 3;
        int actual = talks.size();
        assertEquals(expected, actual);
        assertThat(talks, containsInAnyOrder(t1DTO, t2DTO, t3DTO));
    }

}