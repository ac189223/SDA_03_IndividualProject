package IP_07.Interface;

import IP_07.Model.Project;
import IP_07.Model.Task;

import java.util.ArrayList;

/**
 * Represents a dataLists used as a format of data fetched from MySQL database
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public final class DataLists {
    private final ArrayList<Task> tasks;
    private final ArrayList<Project> projects;

    /**
     * Constructor of a ready to work with dataList containing empty ArrayLists
     */
    public DataLists() {
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    /**
     * Constructor of a dataList containing provided ArrayLists
     */
    public DataLists(ArrayList<Task> tasks, ArrayList<Project> projects) {
        this.tasks = tasks;
        this.projects = projects;
    }

    /**
     * Getters for this class
     */
    public ArrayList<Task> getTasks() { return tasks; }
    public ArrayList<Project> getProjects() { return projects; }
}
