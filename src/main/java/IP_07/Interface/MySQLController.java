package IP_07.Interface;

import IP_07.Model.Project;
import IP_07.Model.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents a database controller to execute queries against MySQL database
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MySQLController {
    private QueryBuilder queryBuilder;
    private MySQLConnector mySQLConnector;
    private final String CREATE = "creation_date";
    private final String UPDATE = "last_update";
    private final String DELETE = "deletion_date";

    /**
     * Constructor of a ready to work with controller containing queryBuilder and connector to the database
     */
    public MySQLController () {
        this.setQueryBuilder(new QueryBuilder());
        this.setMySQLConnector(new MySQLConnector());
    }

    /**
     * Getters for this class
     */
    public QueryBuilder getQueryBuilder() { return queryBuilder; }
    public MySQLConnector getMySQLConnector() { return mySQLConnector; }
    public String getCREATE() { return CREATE; }
    public String getUPDATE() { return UPDATE; }
    public String getDELETE() { return DELETE; }

    /**
     * Setters for this class
     */
    public void setQueryBuilder(QueryBuilder queryBuilder) { this.queryBuilder = queryBuilder; }
    public void setMySQLConnector(MySQLConnector mySQLConnector) { this.mySQLConnector = mySQLConnector; }

    /* =================    =================    Methods    =================   ================= */

    /**
     * Reading data from database
     *
     * @return              list of tasks and projects in DataLists format or throwing SQLException if unable to read
     */
    public DataLists readData() {
        DataLists dataLists = new DataLists();
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getQueryBuilder().readTasksSqlString());       // Execute select query against tasks table
            createTasks(dataLists, rs);                                                     // Append tasks to result set
            rs = stmt.executeQuery(getQueryBuilder().readProjectsSqlString());              // Execute select query against tasks table
            createProjects(dataLists, rs);                                                  // Append projects to result set
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("READ - CANNOT EXECUTE =(");                                 // Inform if unable to read
        }

        return dataLists;
    }

    /**
     * Appending tasks from database to result set
     *
     * @param dataLists         container for data fetched from database
     * @param rs                resultSet of data fetched from database
     * @throws SQLException     if unable to execute
     */
    private void createTasks(DataLists dataLists, ResultSet rs) throws SQLException {
        while (rs.next())
            try {                                                                       // Add to array tasks assigned to projects
                dataLists.getTasks().add(new Task(rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim().replace("-", ""),
                        rs.getBoolean(4),
                        rs.getString(5).trim()));
            } catch (NullPointerException e) {                                          // Add unassigned tasks to array
                dataLists.getTasks().add(new Task(rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim().replace("-", ""),
                        rs.getBoolean(4)));
            }
    }

    /**
     * Appending projects from database to result set
     *
     * @param dataLists         container for data fetched from database
     * @param rs                resultSet of data fetched from database
     * @throws SQLException     if unable to execute
     */
    private void createProjects(DataLists dataLists, ResultSet rs) throws SQLException {
        while (rs.next())                                                               // Add projects to array
            dataLists.getProjects().add(new Project(rs.getString(1).trim(),
                    rs.getString(2).trim(),
                    rs.getString(3).trim().replace("-", ""),
                    rs.getBoolean(4)));
    }

    /**
     * Assigning task to the project
     *
     * @param taskId            id of the task, that will be assigned to a chosen project
     * @param projectId         id of the project, that above task will be assigned to
     */
    public void addTaskToProject(String taskId, String projectId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().addTaskToProject(taskId, projectId));      // Execute add task to project query
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ADD TASK TO PROJECT - CANNOT EXECUTE =(");                  // Inform if unable to add task to project
        }
    }

    /**
     * Setting task assignation to project to null
     *
     * @param taskId            id of the task, that will be unassigned
     * @param projectId         id of the project, that above task was assigned to
     */
    public void setTaskAssignationToNull(String taskId, String projectId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setTaskAssignationToNull(taskId));         // Execute remove assignation
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("REMOVE PROJECT ASSIGNATION - CANNOT EXECUTE =(");           // Inform if unable to remove
        }
    }

    /* =================    =================    Methods for tasks    =================   ================= */

    /**
     * Adding new task
     *
     * @param id                unique id, that task will be added under
     * @param title             title of the new task
     * @param dueDate           due date of the task (must be given as YYYYMMDD)
     */
    public void addNewTask(String id, String title, String dueDate) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.execute(getQueryBuilder().addNewTask(id, title, dueDate));                 // Execute add task query
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CREATE TASK - CANNOT EXECUTE =(");                          // Inform if unable to create task
        }
    }

    /**
     * Marking task as finished
     *
     * @param taskId         id of the task, that will be marked as finished
     */
    public void markTaskAsDone(String taskId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().markTaskAsDone(taskId));                   // Execute mark task finished query
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MARK TASK AS FINISHED - CANNOT EXECUTE =(");                    // Inform if unable to mark as finished
        }
    }

    /**
     * Marking task as unfinished
     *
     * @param taskId         id of the task, that will be marked as unfinished
     */
    public void markTaskAsNotDone(String taskId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().markTaskAsNotDone(taskId));                // Execute mark task unfinished query
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MARK TASK AS UNFINISHED - CANNOT EXECUTE =(");              // Inform if unable to mark as unfinished
        }
    }

    /**
     * Removing task, by setting date of deletion
     *
     * @param taskId            id of the task, that will have date of deletion set
     */
    public void removeTask(String taskId) {
        updateTaskLogDate(taskId, getDELETE());                                             // Update log date for task
    }

    /**
     * Setting task due date
     *
     * @param taskId            id of the task, that will have due date set
     * @param dueDate           due date that will be set for above task (format YYYYMMDD)
     */
    public void setTaskDueDate(String taskId, String dueDate) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setTaskDueDate(taskId, dueDate));          // Execute set due date for task
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET TASK DUE DATE - CANNOT EXECUTE =(");                    // Inform if unable to set
        }
    }

    /**
     * Setting task title
     *
     * @param taskId            id of the task, that will have title set
     * @param title             title that will be set for above task
     */
    public void setTaskTitle(String taskId, String title) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setTaskTitle(taskId, title));              // Execute set title for task
            updateTaskLogDate(taskId, getUPDATE());                                         // Update log date for task
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET TASK TITLE - CANNOT EXECUTE =(");                       // Inform if unable to set
        }
    }

    /**
     * Setting task date as timestamp for creation_date, last_update or deletion_date
     *
     * @param taskId            id of the task, that will have one of above dates set
     * @param fieldName         chosen date field, that will be set for above task
     */
    public void updateTaskLogDate(String taskId, String fieldName) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setTaskLogDate(taskId, fieldName));        // Execute set date for task
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET TASK LOG DATE - CANNOT EXECUTE =(");                    // Inform if unable to set
        }
    }

    /* =================    =================    Methods for projects    =================   ================= */

    /**
     * Adding new project
     *
     * @param id                unique id, that project will be added under
     * @param title             title of the new project
     * @param dueDate           due date of the project (must be given as YYYYMMDD)
     */
    public void addNewProject(String id, String title, String dueDate) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.execute(getQueryBuilder().addNewProject(id, title, dueDate));              // Execute add project query
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CREATE PROJECT - CANNOT EXECUTE =(");                       // Inform if unable to create project
        }
    }

    /**
     * Marking project as finished
     *
     * @param projectId         id of the project, that will be marked as finished
     */
    public void markProjectAsDone(String projectId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().markProjectAsDone(projectId));             // Execute mark project finished query
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MARK PROJECT AS FINISHED - CANNOT EXECUTE =(");             // Inform if unable to mark as finished
        }
    }

    /**
     * Marking project as unfinished
     *
     * @param projectId         id of the project, that will be marked as unfinished
     */
    public void markProjectAsNotDone(String projectId) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().markProjectAsNotDone(projectId));          // Execute mark project unfinished query
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MARK PROJECT AS UNFINISHED - CANNOT EXECUTE =(");           // Inform if unable to mark as unfinished
        }
    }

    /**
     * Removing project, by setting date of deletion
     *
     * @param projectId         id of the project, that will have date of deletion set
     */
    public void removeProject(String projectId) {
        updateProjectLogDate(projectId, getDELETE());                                       // Update log task for project
    }

    /**
     * Setting project due date
     *
     * @param projectId         id of the project, that will have due date set
     * @param dueDate           due date that will be set for above project (format YYYYMMDD)
     */
    public void setProjectDueDate(String projectId, String dueDate) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setProjectDueDate(projectId, dueDate));    // Execute set due date for project
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET PROJECT DUE DATE - CANNOT EXECUTE =(");                 // Inform if unable to set
        }
    }

    /**
     * Setting project title
     *
     * @param projectId         id of the project, that will have title set
     * @param title             title that will be set for above project
     */
    public void setProjectTitle(String projectId, String title) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setProjectTitle(projectId, title));        // Execute set title for project
            updateProjectLogDate(projectId, getUPDATE());                                   // Update log task for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET PROJECT TITLE - CANNOT EXECUTE =(");                    // Inform if unable to set
        }
    }

    /**
     * Setting project date as timestamp for creation_date, last_update or deletion_date
     *
     * @param projectId         id of the project, that will have one of above dates set
     * @param fieldName         chosen date field, that will be set for above project
     */
    public void updateProjectLogDate(String projectId, String fieldName) {
        try
        {
            Connection conn = (getMySQLConnector().startConnection());                      // Establish connection
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(getQueryBuilder().setProjectLogDate(projectId, fieldName));  // Execute set date for project
            getMySQLConnector().closeConnection(conn);                                      // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SET PROJECT LOG DATE - CANNOT EXECUTE =(");                 // Inform if unable to set
        }
    }
}