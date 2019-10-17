package IP_07.Controller;

import IP_07.Model.Project;
import IP_07.Model.Register;

import java.util.List;

/**
 * Represents an extension of messageBuilder done for projects to build messages, that will be shown in popups
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MessageBuilderProjects extends MessageBuilder {

    /* =================    =================    Methods for projects    =================   ================= */

    /**
     * Building message for main menu for projects
     *
     * @param register                  register with tasks and projects
     * @return                          message in String format
     */
    public String chooseOptionForProject(Register register) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("You have ")
                .append(amountOfProjectsToDo(register)).append(" project");         // Amount of unfinished projects
        if (amountOfProjectsToDo(register) != 1)
            builtMessage.append("s");
        builtMessage.append(" to do and ")
                .append(amountOfProjectsDone(register)).append(" project");         // Amount of finished projects
        if (amountOfProjectsDone(register) != 1)
            builtMessage.append("s");
        builtMessage.append(" finished").append("\n\nChoose an option")             // Main choice for projects
                .append("\n\n(1) Filter projects (by assignment or status)")
                .append("\n(2) Show projects (sorted by title, Id, due date, amount of tasks)")
                .append("\n(3) Add new project")
                .append("\n(4) Edit project (update, mark as finished, remove)")
                .append("\n(5) Back to main menu");
        return String.valueOf(builtMessage);
    }

    /**
     * Building message for menu of edit activities for projects
     *
     * @param register                  register with tasks and projects
     * @param chosenProject             Id of the chosen project
     * @return                          message in String format
     */
    public String chooseActivityForProject(Register register, String chosenProject) {
        String builtMessage = "Choose activity for " + chosenProject + " (" +
                register.findProject(chosenProject).getTitle() + ")" +
                "\n\n(1) Update (title, due date, assign tasks, status)" +
                "\n(2) Mark as finished" +
                "\n(3) Remove" +
                "\n(4) Back to main menu";
        return builtMessage;
    }

    /**
     * Building message for menu of possible fields to be update for project (with actual values)
     *
     * @param register                  register with tasks and projects
     * @param chosenProject             Id of the chosen project
     * @return                          message in String format
     */
    public String chooseProjectField(Register register, String chosenProject) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("Choose field to update for ").append(chosenProject)
                .append("\n\n(1) Title (").append(register.findProject(chosenProject).getTitle()).append(")")
                .append("\n(2) Due date (").append(register.findProject(chosenProject).getDueDate()).append(")")
                .append("\n(3) Assign tasks")
                .append("\n(4) Status");
        if (register.findProject(chosenProject).ifDone())
            builtMessage.append(" (finished)");
        else
            builtMessage.append(" (unfinished)");
        builtMessage.append("\n(5) Back to main menu");
        return String.valueOf(builtMessage);
    }

    /**
     * Building messages for different popups with choices for projects
     *
     * @return                          message in String format
     */
    public String ifAddNextTask() {
        return "Choose an option" + "\n\n(1) Add next task" + "\n(2) Go to main menu";
    }
    public String chooseProjectsSorting() {
        return  "Print projects sorted by \n\n(1) Title \n(2) Id \n(3) Due date \n(4) Amount of tasks";
    }

    /**
     * Building messages for input popup to enter project title
     *
     * @return                          message in String format
     */
    public String enterProjectTitle() { return  "Enter project title"; }

    /**
     * Building messages for popups with confirmations
     *
     * @return                          message in String format
     */
    public String removeProjectConfirmation() { return "Project was removed from the register"; }
    public String markProjectAsDoneConfirmation() { return "Project was marked as finished"; }
    public String fixProjectStatusConfirmation() { return  "Project status was fixed"; }
    public String changedProjectDueDateConfirmation() { return  "Project due date was changed"; }
    public String changedProjectTitleConfirmation() { return  "Project title was changed"; }

    /**
     * Building messages for popups with confirmations of adding project to the register
     *
     * @param register                  register with tasks and projects
     * @return                          message in String format
     */
    public String addedProjectConfirmation(Register register) {
        return "New project was added as " + register.getProjects().get(register.getProjects().size() - 1).getId();
    }

    /**
     * Building messages for popup with list of filtered or sorted projects
     *
     * @param projects                  list of projects
     * @return                          filtered or sorted list in String format
     */
    public String listOfProjects(List<Project> projects) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("Projects\n");
        projects.forEach(project -> builtMessage.append(addProjectToListForMain(project)));   // Add projects to list
        return String.valueOf(builtMessage);
    }
}
