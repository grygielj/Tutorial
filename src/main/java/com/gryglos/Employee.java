package com.gryglos;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @TableGenerator(name = "mojGenerator",
            table = "tabela_z_identyfikatorami",
            pkColumnName = "nazwa_sekwencji",
            valueColumnName = "wartosc_identyfikatora",
            pkColumnValue = "id_pracownika",
            initialValue = 10,
            allocationSize = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mojGenerator")
    private long id;

    private String firstName;
    private String lastName;
    private double salary;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
