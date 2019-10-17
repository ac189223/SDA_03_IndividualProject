package IP_07.Model;

/**
 * Represents a task
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Task {
    private String id;
    private String title;
    private String dueDate;
    private boolean done;
    private String assignedToProject;


    /**
     * Constructor for creation of a new task with defined name and due date
     *
     * @param title                 title of the task
     * @param dueDate               due date of the task (must be given as YYYYMMDD)
     */
    public Task(String title, String dueDate) {
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setDone(false);
        this.setAssignedToProject("");
    }

    /**
     * Constructor for creation of unassigned task fetched from database
     *
     * @param id                    unique id of task
     * @param title                 title of the task
     * @param dueDate               must be given as YYYYMMDD
     * @param done                  true for finished tasks, false for unfinished
     */
    public Task(String id, String title, String dueDate, boolean done) {
        this.setId(id);
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setDone(done);
        this.setAssignedToProject("");
    }

    /**
     * Constructor for creation of task (assigned to existing project) fetched from database
     *
     * @param id                    unique id of task
     * @param title                 title of the task
     * @param dueDate               must be given as YYYYMMDD
     * @param done                  true for finished tasks, false for unfinished
     * @param assignedToProject     id of the project, that task is assigned to
     */
    public Task(String id, String title, String dueDate, boolean done, String assignedToProject) {
        this.setId(id);
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setDone(done);
        this.setAssignedToProject(assignedToProject);
    }

    /**
     * Getters for this class
     */
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public boolean ifDone() { return done; }
    public String getAssignedToProject() { return assignedToProject; }

    /**
     * Setters for this class
     */
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setDone(boolean done) { this.done = done; }
    public void setAssignedToProject(String assignedToProject) { this.assignedToProject = assignedToProject; }
}
