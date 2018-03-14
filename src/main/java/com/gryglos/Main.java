package com.gryglos;

import javafx.scene.control.ProgressBar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Professor professor1 = new Professor();
        professor1.setFirstName("Jan");
        professor1.setLastName("Nowak");
        professor1.setSalary(7563.0);

        Student student1 = new Student();
        student1.setFirstName("Marek");
        student1.setLastName("Kowalski");
        student1.setAverageGrade(4.75);

        entityManager.getTransaction().begin();
        entityManager.persist(professor1);
        entityManager.persist(student1);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
