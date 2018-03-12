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

        Employee employee = new Employee();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        employee.setFirstName("Jan");
        employee.setLastName("Nowak");
        employee.setSalary(1000.33);

        phone1.setType("mobile");
        phone2.setType("home");
        phone1.setNumber("13231321");
        phone2.setNumber("3424234265");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);

        employee.setPhones(phones);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(phone1);
        entityManager.persist(phone2);
        entityManager.getTransaction().commit();



        entityManager.close();
        entityManagerFactory.close();
    }
}
