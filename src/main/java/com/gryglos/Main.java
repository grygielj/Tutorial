package com.gryglos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        Query firstQuery = entityManager
                .createQuery("select avg(e.salary), min(e.salary), max(e.salary), sum(e.salary), count(e) from Pracownicy e");

        Object[] result = (Object[]) firstQuery.getSingleResult();
        System.out.println("Srednia : "+result[0]);
        System.out.println("Min : "+result[1]);
        System.out.println("Max : "+result[2]);
        System.out.println("Suma : "+result[3]);
        System.out.println("Pracownikow : "+result[4]);

        Query secondQuery = entityManager
                .createQuery("select substring(e.firstName,1,3),trim(e.lastName), lower(e.firstName), upper(e.firstName),length(e.firstName)" +
                        " from Pracownicy e where e.firstName = 'Karol'");
        Object[] secondResult =(Object[]) secondQuery.getSingleResult();
        System.out.println("Pierwsze trzy litery imienia "+secondResult[0]);
        System.out.println("Nazwisko: |"+secondResult[1]+"|");
        System.out.println("Imię małymi "+secondResult[2]);
        System.out.println("Imię dużymi "+secondResult[3]);
        System.out.println("Długość imienia: "+secondResult[4]);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void addEmployees() {
        addEmployee("Karol", "   Mateusiak   ", 2633);
        addEmployee("Marika", "Bednarek", 2345);
        addEmployee("Jan ", "Mateusiak", 7346);
        addEmployee("Daria", "Kowalska", 2352);
        addEmployee("Monika", "Ucińska", 4263);
        addEmployee("Ernest", "Pająk", 2634);
        addEmployee("Kamil", "Stępień", 2345);
        addEmployee("Przemek", "Maciejewski", 5433);
        addEmployee("Robert", "Woźniak", 3324);
        addEmployee("Agnieszka", "Nowak", 2808);
        addEmployee("Angelika", "Bednarska", 1000);
    }

    public static void addEmployee(String firstName, String lastName, double salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }
}
