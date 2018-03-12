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

        TypedQuery<Employee> query = entityManager.createQuery("select e from Pracownicy e where e.salary> :minSalary",Employee.class);
        query.setParameter("minSalary",5000.0);
        for(Employee employee: query.getResultList()){
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getSalary());
            System.out.println();
        }

        TypedQuery<Employee> secondQuery = entityManager.createQuery("select e from Pracownicy e where e.salary > ?1 and e.salary < ?2",Employee.class);
        secondQuery.setParameter(1,2000.0);
        secondQuery.setParameter(2,3000.0);
        for(Employee employee: secondQuery.getResultList()){
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getSalary());
            System.out.println();
        }

        TypedQuery<Employee> thirdQuery = entityManager.createQuery("select e from Pracownicy e where e.lastName in :names",Employee.class);
        List<String> names = new ArrayList<>();
        names.add("Mateusiak");
        names.add("Bednarek");
        thirdQuery.setParameter("names",names);

        for(Employee employee: thirdQuery.getResultList()){
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getSalary());
            System.out.println();
        }


        entityManager.close();
        entityManagerFactory.close();
    }

    public static void addEmployees(){
        addEmployee("Karol","Mateusiak",2633);
        addEmployee("Karol","Bednarek",2345);
        addEmployee("Jan ","Mateusiak",7346);
        addEmployee("Daria","Kowalska",2352);
        addEmployee("Monika","Ucińska",4263);
        addEmployee("Ernest","Pająk",2634);
        addEmployee("Kamil","Stępień",2345);
        addEmployee("Przemek","Maciejewski",5433);
        addEmployee("Robert","Woźniak",3324);
        addEmployee("Agnieszka","Nowak",2808);
        addEmployee("Angelika","Bednarska",1000);
    }
    public static void addEmployee(String firstName, String lastName, double salary){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }
}
