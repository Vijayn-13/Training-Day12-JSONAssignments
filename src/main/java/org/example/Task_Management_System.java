package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static java.rmi.dgc.VMID.isUnique;

public class Task_Management_System {

    static Scanner sc=new Scanner(System.in);
    public static void add_task(List<Task> ls){
        int taskID;
        String description,due_date;
        boolean completed;
        boolean isunique;
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Taskfile.json"));
            if(!str.isEmpty()){
                ls=obj.readValue(str,new TypeReference<List<Task>>(){});
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        int i=0;
        do{
            if(i==0) {
                System.out.print("Enter taskID:");
                taskID=sc.nextInt();
                isunique=Task.isUnique(ls,taskID);
                i++;
            }
            else{
                System.out.println("!!!Oops User entered taskID is already taken");
                System.out.print("Enter unique taskID:");
                taskID=sc.nextInt();
                isunique=Task.isUnique(ls,taskID);
            }
        }while(isunique==false);
        sc.nextLine();  // this is for getting bug input which means new line sometimes considered as input. so,to avoid that I was encountering the newline using unreferenced scanner object;
        System.out.print("Enter description: ");
        description=sc.nextLine();
        System.out.print("Enter due date(date/month/year): ");
        due_date=sc.nextLine();
        System.out.print("Is the task completed? (true/false): ");
        completed=sc.nextBoolean();
        Task t=new Task(taskID,description,due_date,completed);
        ls.add(t);
        try{
            obj.writeValue(new File("Taskfile.json"),ls);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void display_tasks(){
        try{
            String str=Files.readString(Path.of("Taskfile.json"));
            if(str.isEmpty()){
                System.out.println("No tasks found");
            }
            else{
                System.out.println(str);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void update_task(List<Task> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Taskfile.json"));
            if(str.isEmpty()){
                System.out.println("No tasks found");
            }
            else {
                ls = obj.readValue(str, new TypeReference<List<Task>>() {});
                System.out.print("Enter taskID: ");
                int id=sc.nextInt();
                boolean flag = true;
                for (Task t : ls) {
                    if (t.getTaskID() == id) {
                        flag = false;
                        sc.nextLine();
                        System.out.print("Enter new description: ");
                        String description = sc.nextLine();
                        t.setDescription(description);
                        System.out.print("Enter revised due date: ");
                        String due_date = sc.nextLine();
                        t.setDue_date(due_date);
                        try {
                            obj.writeValue(new File("TaskFile.json"), ls);
                            System.out.println("Task updated successfully!!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
                if (flag) {
                    System.out.println("TaskID not found");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void remove_task(List<Task> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Taskfile.json"));
            if(str.isEmpty()){
                System.out.println("No tasks found");
            }
            else {
                ls = obj.readValue(str, new TypeReference<List<Task>>() {
                });
                System.out.print("Enter taskID: ");
                int id = sc.nextInt();
                boolean flag = true;
                for (Task t : ls) {
                    if (t.getTaskID() == id) {
                        flag = false;
                        ls.remove(t);
                        try {
                            obj.writeValue(new File("Taskfile.json"), ls);
                            System.out.println("TaskID \"" + id + "\" removed successfully");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
                if (flag) {
                    System.out.println("TaskID not found");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void mark_task(List<Task> ls){
        ObjectMapper obj=new ObjectMapper();
        try{
            String str=Files.readString(Path.of("Taskfile.json"));
            if(str.isEmpty()){
                System.out.println("No tasks found");
            }
            else {
                ls = obj.readValue(str, new TypeReference<List<Task>>() {
                });
                System.out.print("Enter taskID: ");
                int id = sc.nextInt();
                boolean flag = true;
                for (Task t : ls) {
                    if (t.getTaskID() == id) {
                        flag = false;
                        t.setCompleted(true);
                        try {
                            obj.writeValue(new File("Taskfile.json"), ls);
                            System.out.println("TaskID \"" + id + "\" marked as Completed");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
                if (flag) {
                    System.out.println("TaskID not found");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        int n;
        List<Task> ls=new ArrayList<>();
        do{
            System.out.println("\nMenu Options:-");
            System.out.println("1.Add new task");
            System.out.println("2.View tasks");
            System.out.println("3.Update task details");
            System.out.println("4.Delete task");
            System.out.println("5.Mark task as completed");
            System.out.println("6.Exit");
            System.out.print("Select option: ");
            n=sc.nextInt();
            switch (n){
                case 1:
                    add_task(ls);
                    break;
                case 2:
                    display_tasks();
                    break;
                case 3:
                    update_task(ls);
                    break;
                case 4:
                    remove_task(ls);
                    break;
                case 5:
                    mark_task(ls);
                    break;
                case 6:
                    System.out.println("Program closed by the user...");
                    break;
                default:
                    System.out.println("!!!Enter valid option");
                    break;
            }
        }while(n!=6);
    }
}

