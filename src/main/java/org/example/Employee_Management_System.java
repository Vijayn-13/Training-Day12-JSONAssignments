package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Employee_Management_System {
    static Scanner sc=new Scanner(System.in);

    public static void add_employee(List<Employee> ls){
        ObjectMapper obj=new ObjectMapper();
        boolean flag=true;
        try{
            String str= Files.readString(Path.of("Employeedetails.json"));
            if(!str.isEmpty()){
                ls=obj.readValue(str, new TypeReference<List<Employee>>() {});
            }
            int id;
            do {
                if(flag) {
                    System.out.print("\nEnter employeeID: ");
                    id = sc.nextInt();
                    flag=Employee.isUnique(ls,id);
                }
                else{
                    System.out.println("!!Oops user entered employeeID is already taken");
                    System.out.print("Enter unique employeeID: ");
                    id = sc.nextInt();
                    flag=Employee.isUnique(ls,id);
                }
            }while(!flag);
            sc.nextLine();    // this is for getting bug input which means new line sometimes considered as input. so,to avoid that I was encountering the newline using unreferenced scanner object;
            System.out.print("Enter name: ");
            String name=sc.nextLine();
            System.out.print("Enter department: ");
            String department=sc.nextLine();
            System.out.print("Enter street: ");
            String street=sc.nextLine();
            System.out.print("Enter city: ");
            String city=sc.nextLine();
            System.out.print("Enter zipcode: ");
            String zipcode=sc.nextLine();
            System.out.print("Enter number of projects: ");
            int n=sc.nextInt();
            List<Project> proj=new ArrayList<>();
            for(int i=0;i<n;i++){
                System.out.print("Enter projectID: ");
                int proj_id=sc.nextInt();
                sc.nextLine();
                System.out.print("Enter project name: ");
                String proj_name=sc.nextLine();
                System.out.print("Enter status: ");
                String proj_status=sc.nextLine();
                proj.add(new Project(proj_id,proj_name,proj_status));
            }
            ls.add(new Employee(id,name,department,new Address(street,city,zipcode),proj));
            obj.writerWithDefaultPrettyPrinter().writeValue(new File("Employeedetails.json"),ls);
            System.out.println("Employee added successfully!!!");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void view_all_employees(List<Employee> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Employeedetails.json"));
            if(!str.isEmpty()) {
                System.out.println("\nEmployee Details:-");
                System.out.println(str);
            }
            else{
                System.out.println("No Employee details found!!!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void update_employee_details(List<Employee> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Employeedetails.json"));
            if(!str.isEmpty()){
                ls=obj.readValue(str, new TypeReference<List<Employee>>() {});
                System.out.print("\nEnter employeeID: ");
                int id=sc.nextInt();
                boolean flag=true;
                int n;
                for(Employee e:ls){
                    if(e.getEmployeeId()==id){
                        flag=false;
                        do {
                            System.out.println("\nUpdate options(Under Menu):-");
                            System.out.println("1.Name");
                            System.out.println("2.Department");
                            System.out.println("3.Address");
                            System.out.println("4.Projects");
                            System.out.println("5.Exit");
                            System.out.print("Select any update option: ");
                            n =sc.nextInt();
                            sc.nextLine();
                            switch (n){
                                case 1:
                                    System.out.print("Enter name: ");
                                    String name=sc.nextLine();
                                    e.setName(name);
                                    System.out.println("Name updated successfully!!!");
                                    break;
                                case 2:
                                    System.out.print("Enter department: ");
                                    String department=sc.nextLine();
                                    e.setDepartment(department);
                                    System.out.println("Department updated successfully!!!");
                                    break;
                                case 3:
                                    System.out.print("Enter street: ");
                                    String street=sc.nextLine();
                                    System.out.print("Enter city: ");
                                    String city=sc.nextLine();
                                    System.out.print("Enter zipcode: ");
                                    String zipcode=sc.nextLine();
                                    e.setAddress(new Address(street,city,zipcode));
                                    System.out.println("Address updated successfully!!!");
                                    break;
                                case 4:
                                    System.out.print("Enter projectID: ");
                                    int projID=sc.nextInt();
                                    sc.nextLine();
                                    boolean bool=true;
                                    for(Project p:e.getProjects()){
                                        if(p.getProjectId()==projID){
                                            bool=false;
                                            System.out.print("Enter project name: ");
                                            String proj_name=sc.nextLine();
                                            System.out.print("Enter status: ");
                                            String proj_status=sc.nextLine();
                                            p.setProjectName(proj_name);
                                            p.setStatus(proj_status);
                                            System.out.println("Project updated successfully!!!");
                                            break;
                                        }
                                    }
                                    if(bool){
                                        System.out.println("ProjectID not found!!!");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Update program closed...");
                                    break;
                                default:
                                    System.out.println("Enter valid options only");
                            }
                        }while(n!=5);
                        try {
                            obj.writerWithDefaultPrettyPrinter().writeValue(new File("Employeedetails.json"),ls);
                            System.out.println("Employee details updated successfully!!!");
                        }
                        catch(Exception e1){
                            System.out.println(e1.getMessage());
                        }
                        break;
                    }
                }
                if(flag){
                    System.out.println("EmployeeID not found!!!");
                }
            }
            else{
                System.out.println("No Employee details found!!!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void delete_employee(List<Employee> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Employeedetails.json"));
            if(!str.isEmpty()){
                ls=obj.readValue(str, new TypeReference<List<Employee>>() {});
                System.out.print("\nEnter EmployeeID: ");
                int id=sc.nextInt();
                boolean flag=true;
                for(Employee e:ls){
                    if(e.getEmployeeId()==id){
                        flag=false;
                        ls.remove(e);
                        System.out.println("EmployeeID \""+id+"\" is deleted successfully!!!");
                        try{
                            obj.writerWithDefaultPrettyPrinter().writeValue(new File("Employeedetails.json"),ls);
                        }
                        catch(Exception e1){
                            System.out.println(e1.getMessage());
                        }
                        break;
                    }
                }
                if(flag){
                    System.out.println("EmployeeID not found!!");
                }
            }
            else{
                System.out.println("No employee details found!!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void search_employee(List<Employee> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Employeedetails.json"));
            if(!str.isEmpty()){
                ls=obj.readValue(str, new TypeReference<List<Employee>>() {});
                System.out.print("\nEnter employeeID: ");
                int id=sc.nextInt();
                boolean flag=true;
                for(Employee e:ls){
                    if(e.getEmployeeId()==id){
                        flag=false;
                        System.out.println("Employee details of id \""+id+"\" :-");
                        System.out.println("Name: "+e.getName());
                        System.out.println("Department: "+e.getDepartment());
                        System.out.println("Address:-");
                        System.out.println("  Street: "+e.getAddress().getStreet());
                        System.out.println("  City: "+e.getAddress().getCity());
                        System.out.println("  Zipcode: "+e.getAddress().getZipcode());
                        int n=e.getProjects().size();
                        System.out.println("Projects details:-");
                        for(Project p:e.getProjects()){
                            System.out.println("  Project ID: "+p.getProjectId());
                            System.out.println("  Project name: "+p.getProjectName());
                            System.out.println("  Project status: "+p.getStatus());
                        }
                        break;
                    }
                }
                if(flag){
                    System.out.println("EmployeeID not found");
                }
            }
            else{
                System.out.println("No employee details found!!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        int n;
        List<Employee> ls=new ArrayList<>();
        do{
            System.out.println("\nMenu Options:-");
            System.out.println("1.Add new employee");
            System.out.println("2.View all employees");
            System.out.println("3.Update employee details");
            System.out.println("4.Delete employee");
            System.out.println("5.Search an employee");
            System.out.println("6.Exit");
            System.out.print("Select option: ");
            n=sc.nextInt();
            switch (n){
                case 1:
                    add_employee(ls);
                    break;
                case 2:
                    view_all_employees(ls);
                    break;
                case 3:
                    update_employee_details(ls);
                    break;
                case 4:
                    delete_employee(ls);
                    break;
                case 5:
                    search_employee(ls);
                    break;
                case 6:
                    System.out.println("Program closed by the user....");
                    break;
                default:
                    System.out.println("!!!Enter valid options only");
            }
        }while(n!=6);
    }
}
