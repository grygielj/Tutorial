package com.gryglos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.salary > 3000 order by e.salary",Employee.class);

        List<Employee>  employees = query.getResultList();
        for(Employee employee : employees) {
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
