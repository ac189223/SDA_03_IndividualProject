package IP_07.Model;

import java.util.ArrayList;

/**
 * Represents a project
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Project {
    private String id;
    private String title;
    private String dueDate;
    private boolean done;
    private ArrayList<String> assignedTasks;

    /**
     * Constructor for creation of a new project with defined name and due date
     *
     * @param title                 title of the project
     * @param dueDate               due date of the project (must be given as YYYYMMDD)
     */
    public Project(String title, String dueDate) {
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setDone(false);
        this.setAssignedTasks(new ArrayList<>());
    }

    /**
     * Constructor for creation of project fetched from database
     *
     * @param id                    unique id of project
     * @param title                 title of the project
     * @param dueDate               must be given as YYYYMMDD
     * @param done                  true for finished projects, false for unfinished
     */
    public Project(String id, String title, String dueDate, boolean done) {
        this.setId(id);
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setDone(done);
        this.setAssignedTasks(new ArrayList<>());
    }

    /**
     * Getters for this class
     */
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public boolean ifDone() { return done; }
    public ArrayList<String> getAssignedTasks() { return assignedTasks; }

    /**
     * Setters for this class
     */
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setDone(boolean done) { this.done = done; }
    public void setAssignedTasks(ArrayList<String> assignedTasks) { this.assignedTasks = assignedTasks; }

}
