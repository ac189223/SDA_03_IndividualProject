package IP_07.Controller;

import IP_07.Model.Project;
import IP_07.Model.Register;
import IP_07.Model.Task;

/**
 * Represents a messageBuilder to build messages, that will be shown in popups
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MessageBuilder {

    /* =================    =================    Methods    =================   ================= */

    /**
     * Building message for main menu
     *
     * @param register                  register containing tasks and projects
     * @return                          message in String format
     */
    public String chooseMain(Register register) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("You have ").
                append(amountOfTasks(register)).append(" task");                // Amount of tasks
        if ((amountOfTasks(register)) != 1)
            builtMessage.append("s");
        builtMessage.append(" and ").
                append(amountOfProjects(register)).append(" project");          // Amount of projects
        if ((amountOfProjects(register)) != 1)
            builtMessage.append("s")
                    .append("\n\nChoose an option")                             // Main choice
                    .append("\n\n(1) Print all tasks and projects")
                    .append("\n(2) Work with tasks")
                    .append("\n(3) Work with projects")
                    .append("\n(4) Quit application");
        return String.valueOf(builtMessage);
    }

    /**
     * Building messages for different popups with choices
     *
     * @return                          message in String format
     */
    public String chooseTask() { return "Choose a task from a list"; }
    public String chooseProject() { return "Choose project from a list"; }
    public String chooseStatus() { return "Choose status from below \n\n(1) Finished \n(2) Unfinished"; }
    public String chooseFiltering() { return "Print \n\n(1) Assigned \n(2) Not assigned \n(3) Finished \n(4) Unfinished"; }

    /**
     * Building messages for different input popups to enter values
     *
     * @return                          message in String format
     */
    public String chooseDueDate() { return "Enter new due date (yyyyMMdd)"; }
    public String chooseTitle() { return "Enter new title"; }
    public String enterDueDate() { return "Enter due date (yyyyMMdd)"; }

    /**
     * Building messages for popup with confirmation of quiting application
     *
     * @return                          message in String format
     */
    public String quitConfirmation() { return "Thank you for using"; }

    /**
     * Building messages for popup with confirmation of adding task to the project
     *
     * @param chosenTask                Id of the task, that was added
     * @param chosenProject             Id of the project, that above task was added to
     * @return                          message in String format
     */
    public String addedTaskToProjectConfirmation(String chosenTask, String chosenProject) {
        return "Task " + chosenTask + " was added to project " + chosenProject;
    }

    /**
     * Building messages for different popups with information
     *
     * @return                          message in String format
     */
    public String noProjectsInfo() { return "There are no projects stored"; }
    public String noTasksInfo() { return "There are no tasks stored"; }
    public String noTasksNoProjects() { return "There are no tasks and no projects stored"; }

    /**
     * Building messages for popup with information, that task was already added to the project
     *
     * @param chosenTask                Id of the task, that was added
     * @param chosenProject             Id of the project, that above task was added to
     * @return                          message in String format
     */
    public String taskAlreadyInProjectInfo(String chosenTask, String chosenProject) {
        return "Task " + chosenTask + " was already added to project " + chosenProject;
    }

    /**
     * Printing out all tasks and projects from the main menu
     *
     * @param register                  register containing tasks and projects
     * @return                          list in String format
     */
    public String listForMain(Register register) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("Projects and assigned tasks\n");
        for (Project project: register.getProjects()) {                                         // For every project
            builtMessage.append("\n")
                    .append(String.valueOf(addProjectToListForMain(project)).substring(5));     // Add project
            for (String taskId : project.getAssignedTasks())                                    // For all dependent tasks
                builtMessage.append(addTaskToListForMain(register.findTask(taskId)));           // Add tasks
        }
        builtMessage.append("\n\nNot assigned tasks\n");
        for (Task task: register.getTasks()) {
            if (task.getAssignedToProject().equals("")) {                                       // Filter unassigned tasks
                builtMessage.append("\n")
                        .append(String.valueOf(addTaskToListForMain(task)).substring(5));       // Add them also
            }
        }
        return String.valueOf(builtMessage);
    }

    /**
     * Adding project to list printed from main menu
     *
     * @param project               project to be added
     * @return                      appendix to the list created until that moment, containing data of one project
     */
    public Appendable addProjectToListForMain(Project project) {
        StringBuilder appendix = new StringBuilder();
        appendix.append("\n    ").append(project.getId());
        if (project.getAssignedTasks().size() != 0)  {
            appendix.append(" with ").append(project.getAssignedTasks().size()).append(" task");
            if (project.getAssignedTasks().size() != 1)
                appendix.append("s");
        }
        appendix.append(" - named \"").append(project.getTitle())
                .append("\", with due date ").append(project.getDueDate());
        if (project.ifDone())
            appendix.append(" - finished");
        else
            appendix.append(" - unfinished");
        return appendix;
    }

    /**
     * Adding task to list printed from main menu
     *
     * @param task                  task to be added
     * @return                      appendix to the list created until that moment, containing data of one task
     */
    public Appendable addTaskToListForMain(Task task) {
        StringBuilder appendix = new StringBuilder();
        appendix.append("\n    ").append(task.getId());
        if (!task.getAssignedToProject().equals(""))
            appendix.append(" assigned to ").append(task.getAssignedToProject());
        appendix.append(" - named \"").append(task.getTitle())
                .append("\", with due date ").append(task.getDueDate());
        if (task.ifDone())
            appendix.append(" - finished");
        else
            appendix.append(" - unfinished");
        return appendix;
    }

    /**
     * Counting amount of tasks or projects, finished or unfinished
     *
     * @param register              register of tasks and projects
     * @return                      amount, that was requested
     */
    int amountOfTasks(Register register) { return amountOfTasksDone(register) + amountOfTasksToDo(register); }
    int amountOfTasksDone(Register register) { return (int) register.getTasks().stream().filter(Task::ifDone).count(); }
    int amountOfTasksToDo(Register register) { return (int) register.getTasks().stream().filter(task -> !task.ifDone()).count(); }
    int amountOfProjects(Register register) { return amountOfProjectsDone(register) + amountOfProjectsToDo(register); }
    int amountOfProjectsDone(Register register) { return (int) register.getProjects().stream().filter(Project::ifDone).count(); }
    int amountOfProjectsToDo(Register register) { return (int) register.getProjects().stream().filter(project -> !project.ifDone()).count(); }
}
