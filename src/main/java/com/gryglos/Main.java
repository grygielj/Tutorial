package com.gryglos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = new Employee();
        employee.setFirstName("Jakub");
        employee.setLastName("Grygiel");
        employee.setSalary(3333.3);
        employee.setLocality("Konskie");
        employee.setZipCode("26-200");
        employee.setStreet("Piłsudskiego");
        employee.setStreetNumber(111);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
