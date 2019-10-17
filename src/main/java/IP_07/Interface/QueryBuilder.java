package IP_07.Interface;

/**
 * Represents a queryBuilder to prepare SQL queries against MySQL database
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class QueryBuilder {

    /* =================    =================    Methods for tasks    =================   ================= */

    /**
     * Preparing SQL query to read tasks data from database
     *
     * @return              SQL query in String format
     */
    public String readTasksSqlString() {
        return "SELECT id, title, due_date, status, project_id FROM tasks WHERE deletion_date IS NULL;";
    }

    /**
     * Preparing SQL query to add new task to database
     *
     * @return              SQL query in String format
     */
    public String addNewTask(String id, String title, String dueDate) {
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO tasks ")
                .append("(id, title, due_date) ")
                .append("VALUES (")
                .append("'").append(id).append("', ")
                .append("'").append(title).append("', ")
                .append("'").append(getDate(dueDate)).append("');");                // Convert date before adding
        return String.valueOf(sqlString);
    }

    /**
     * Preparing SQL query to mark task as finished
     *
     * @return              SQL query in String format
     */
    public String markTaskAsDone(String taskId) {
        return "UPDATE tasks SET status = true WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to mark task as unfinished
     *
     * @return              SQL query in String format
     */
    public String markTaskAsNotDone(String taskId) {
        return "UPDATE tasks SET status = false WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to remove task
     *
     * @return              SQL query in String format
     */
    public String removeTask(String taskId) {
        return "DELETE FROM tasks WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to set task due date
     *
     * @return              SQL query in String format
     */
    public String setTaskDueDate(String taskId, String dueDate) {
        return "UPDATE tasks SET due_date = '" + getDate(dueDate) + "' WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to set task title
     *
     * @return              SQL query in String format
     */
    public String setTaskTitle(String taskId, String title) {
        return "UPDATE tasks SET title = '" + title + "' WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to set log dates for task
     *
     * @return              SQL query in String format
     */
    public String setTaskLogDate(String taskId, String fieldName) {
        return "UPDATE projects SET " + fieldName + "  = CURRENT_TIMESTAMP WHERE id = '" + taskId + "';";
    }

    /* =================    =================    Methods for projects    =================   ================= */

    /**
     * Preparing SQL query to read projects data from database
     *
     * @return              SQL query in String format
     */
    public String readProjectsSqlString() {
        return "SELECT id, title, due_date, status FROM projects WHERE deletion_date IS NULL;";
    }

    /**
     * Preparing SQL query to add new project to database
     *
     * @return              SQL query in String format
     */
    public String addNewProject(String id, String title, String dueDate) {
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO projects ")
                .append("(id, title, due_date) ")
                .append("VALUES (")
                .append("'").append(id).append("', ")
                .append("'").append(title).append("', ")
                .append("'").append(getDate(dueDate)).append("');");                // Convert date before adding
        return String.valueOf(sqlString);
    }

    /**
     * Preparing SQL query to mark project as finished
     *
     * @return              SQL query in String format
     */
    public String markProjectAsDone(String projectId) {
        return "UPDATE projects SET status = true WHERE id = '" + projectId + "';";
    }

    /**
     * Preparing SQL query to mark project as unfinished
     *
     * @return              SQL query in String format
     */
    public String markProjectAsNotDone(String projectId) {
        return "UPDATE projects SET status = false WHERE id = '" + projectId + "';";
    }

    /**
     * Preparing SQL query to assign task to the chosen project
     *
     * @return              SQL query in String format
     */
    public String addTaskToProject(String taskId, String projectId) {
        return "UPDATE tasks SET project_id = '" + projectId +
                "' WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to remove assignation
     *
     * @return              SQL query in String format
     */
    public String setTaskAssignationToNull(String taskId) {
        return "UPDATE tasks SET project_id = NULL WHERE id = '" + taskId + "';";
    }

    /**
     * Preparing SQL query to remove project
     *
     * @return              SQL query in String format
     */
    public String removeProject(String projectId) {
        return "DELETE FROM projects WHERE id = '" + projectId + "';";
    }

    /**
     * Preparing SQL query to set project due date
     *
     * @return              SQL query in String format
     */
    public String setProjectDueDate(String projectId, String dueDate) {
        return "UPDATE projects SET due_date = '" + getDate(dueDate) + "' WHERE id = '" + projectId + "';";
    }

    /**
     * Preparing SQL query to set project title
     *
     * @return              SQL query in String format
     */
    public String setProjectTitle(String projectId, String title) {
        return "UPDATE projects SET title = '" + title + "' WHERE id = '" + projectId + "';";
    }

    /**
     * Preparing SQL query to set log dates for project
     *
     * @return              SQL query in String format
     */
    public String setProjectLogDate(String projectId, String fieldName) {
        return "UPDATE projects SET " + fieldName + "  = CURRENT_TIMESTAMP WHERE id = '" + projectId + "';";
    }

    /* =================    =================    Conversion    =================   ================= */

    /**
     *  Converting due date to be able to insert that date into MySQL database (adding "-")
     *
     * @param dueDate       due date in format YYYYMMDD
     * @return              date after conversion in format YYYY-MM-DD
     */
    private Appendable getDate(String dueDate) {
        StringBuilder date = new StringBuilder();
        date.append(dueDate, 0, 4).append("-")
                .append(dueDate, 4, 6).append("-")
                .append(dueDate, 6, 8);
        return date;
    }
}
