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
        Address address = new Address();

        address.setLocality("Warszawa");
        address.setStreet("Krochmalna");
        address.setZipCode("00-864");
        address.setStreetNumber(3);

        employee.setFirstName("Jakub");
        employee.setLastName("Grygiel");
        employee.setSalary(3333.3);
        employee.setAddress(address);


        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
