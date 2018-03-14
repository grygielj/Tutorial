package com.gryglos;

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

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.setFirstName("Jan");
        employee1.setLastName("Kowalski");
        employee1.setSalary(2333.3);

        employee2.setFirstName("Kamil");
        employee2.setLastName("Skowronek");
        employee2.setSalary(1777.3);


        entityManager.getTransaction().begin();
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.getTransaction().commit();



        entityManager.close();
        entityManagerFactory.close();
    }
}
