/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.sql.Time;

/**
 * @author tha
 */
public class Populator {
    private static APIFacade instance;
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

    private static final APIFacade FACADE = APIFacade.getInstance(emf);

    public static void populate() {

        EntityManager em = emf.createEntityManager();
        //Create test users
        User user = new User("user", "123");
        User admin = new User("admin", "123");

        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);

        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);

//        em.getTransaction().commit();
//        em.close();

//        Add speakers
        Speaker s1 = new Speaker("Pernille Hansen", "Doctor", "Female");
        Speaker s2 = new Speaker("Rasmus White", "Teacher", "Male");
        Speaker s3 = new Speaker("Anna Holmes", "Programmer", "Female");


        Conference c1 = new Conference("January Summit", "Green Solution House", 300, Date.valueOf("2023-01-25"), Time.valueOf("15:00:00"));
        Conference c2 = new Conference("Good Health", "Multihuset", 200, Date.valueOf("2023-04-10"), Time.valueOf("11:00:00"));
        Conference c3 = new Conference("Child development", "Hotel Griffen", 150, Date.valueOf("2023-09-30"), Time.valueOf("10:00:00"));

//TODO conference-id in the constructor?
        Talk t1 = new Talk("Breastcancer", 100, "White board, laser pen", c2);
        Talk t2 = new Talk("AI", 70, "laser pen", c1);
        Talk t3 = new Talk("Importance of play", 60, "water, slideshow", c3);


        //TODO not working..?
        t1.addSpeaker(s1);
        t2.addSpeaker(s3);
        t3.addSpeaker(s2);
        t1.addSpeaker(s3);


////this doesnt add the conference id to the talk-table
//        c1.addTalk(t2);
//        c2.addTalk(t1);
//        c3.addTalk(t3);


        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);

        em.getTransaction().commit();
        System.out.println("PW: " + user.getUserPass());
        System.out.println("Testing user with OK password: " + user.verifyPassword("123"));
        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");

        em.close();
    }

    public static void main(String[] args) {

//        populate();
    }

}
