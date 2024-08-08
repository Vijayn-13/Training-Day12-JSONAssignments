package org.example;

import java.util.*;

public class Employee {
    private int employeeId;
    private String name,department;
    private Address address;
    private List<Project> Projects;

    Employee(){

    }

    public Employee(int employeeId, String name, String department, Address address, List<Project> projects) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.address = address;
        Projects = projects;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Project> getProjects() {
        return Projects;
    }

    public void setProjects(List<Project> projects) {
        Projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", address=" + address +
                ", Projects=" + Projects +
                '}';
    }

    public static boolean isUnique(List<Employee> ls,int id){
        for(Employee e:ls){
            if(e.getEmployeeId()==id){
                return false;
            }
        }
        return true;
    }

}
