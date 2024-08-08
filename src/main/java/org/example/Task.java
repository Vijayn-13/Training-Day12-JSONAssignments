package org.example;
import java.util.*;
public class Task {
    private int taskID;
    private String description,due_date;
    private boolean completed;

    Task(){

    }

    public Task(int taskID, String description, String due_date, boolean completed) {
        this.taskID = taskID;
        this.description = description;
        this.due_date = due_date;
        this.completed = completed;
        System.out.println("Task added successfully!!");
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", description='" + description + '\'' +
                ", due_date='" + due_date + '\'' +
                ", completed=" + completed +
                '}';
    }

    public static boolean isUnique(List<Task> ls,int taskID){
        for(Task t:ls){
            if(Integer.valueOf(t.taskID).equals(Integer.valueOf(taskID))){
                return false;
            }
        }
        return true;
    }
}

