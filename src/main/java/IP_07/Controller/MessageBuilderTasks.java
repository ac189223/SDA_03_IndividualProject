package IP_07.Controller;

import IP_07.Model.Register;
import IP_07.Model.Task;

import java.util.List;

/**
 * Represents an extension of messageBuilder done for tasks to build messages, that will be shown in popups
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class MessageBuilderTasks extends MessageBuilder {

    /* =================    =================    Methods for tasks    =================   ================= */

    /**
     * Building message for main menu for the tasks
     *
     * @param register                  register with tasks and projects
     * @return                          message in String format
     */
    public String chooseOptionForTask(Register register) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("You have ")
                .append(amountOfTasksToDo(register)).append(" task");         // Amount of unfinished tasks
        if (amountOfTasksToDo(register) != 1)
            builtMessage.append("s");
        builtMessage.append(" to do and ")
                .append(amountOfTasksDone(register)).append(" task");         // Amount of finished tasks
        if (amountOfTasksDone(register) != 1)
            builtMessage.append("s");
        builtMessage.append(" finished").append("\n\nChoose an option")       // Main choice for tasks
                .append("\n\n(1) Filter tasks (by assignment or status)")
                .append("\n(2) Show tasks (sorted by title, Id, due date, project)")
                .append("\n(3) Add new task")
                .append("\n(4) Edit task (update, mark as finished, remove)")
                .append("\n(5) Back to main menu");
        return String.valueOf(builtMessage);
    }

    /**
     * Building message for menu of edit activities for tasks
     *
     * @param register                  register with tasks and projects
     * @param chosenTask                Id of the chosen task
     * @return                          message in String format
     */
    public String chooseActivityForTask(Register register, String chosenTask) {
        String builtMessage = "Choose activity for " + chosenTask + " (" +
                register.findTask(chosenTask).getTitle() + ")" +
                "\n\n(1) Update (title, due date, project assignment, status)" +
                "\n(2) Mark as finished" +
                "\n(3) Remove" +
                "\n(4) Back to main menu";
        return builtMessage;
    }

    /**
     * Building message for menu of possible fields to be update for tasks (with actual values)
     *
     * @param register                  register with tasks and projects
     * @param chosenTask                Id of the chosen task
     * @return                          message in String format
     */
    public String chooseTaskField(Register register, String chosenTask) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("Choose field to update for ").append(chosenTask)
                .append("\n\n(1) Title (").append(register.findTask(chosenTask).getTitle()).append(")")
                .append("\n(2) Due date (").append(register.findTask(chosenTask).getDueDate()).append(")")
                .append("\n(3) Project assignment");
        if (!register.findTask(chosenTask).getAssignedToProject().equals(""))
            builtMessage.append(" (").append(register.findTask(chosenTask).getAssignedToProject()).append(")");
        builtMessage.append("\n(4) Status");
        if (register.findTask(chosenTask).ifDone())
            builtMessage.append(" (finished)");
        else
            builtMessage.append(" (unfinished)");
        builtMessage.append("\n(5) Back to main menu");
        return String.valueOf(builtMessage);
    }

    /**
     * Building messages for input popup to enter task title
     *
     * @return                          message in String format
     */
    public String enterTaskTitle() { return  "Enter task title"; }

    /**
     * Building message for popup with choices of sorting for tasks
     *
     * @return                          message in String format
     */
    public String chooseTasksSorting() {
        return "Print tasks sorted by \n\n(1) Title \n(2) Id \n(3) Due date \n(4) Project";
    }

    /**
     * Building messages for popups with confirmations
     *
     * @return                          message in String format
     */
    public String removeTaskConfirmation() { return "Task was removed from the register"; }
    public String markTaskAsDoneConfirmation() { return "Task was marked as finished"; }
    public String fixTaskStatusConfirmation() { return  "Task status was fixed"; }
    public String reassignedTaskConfirmation() { return "Task was reassigned"; }
    public String changedTaskDueDateConfirmation() { return  "Task due date was changed"; }
    public String changedTaskTitleConfirmation() { return  "Task title was changed"; }

    /**
     * Building messages for popups with confirmations of adding task to the register
     *
     * @param register                  register with tasks and projects
     * @return                          message in String format
     */
    public String addedTaskConfirmation(Register register) {
        return "New task was added as " + register.getTasks().get(register.getTasks().size() - 1).getId();
    }

    /**
     * Building messages for popup with list of filtered or sorted tasks
     *
     * @param tasks                     list of tasks
     * @return                          filtered or sorted list in String format
     */
    public String listOfTasks(List<Task> tasks) {
        StringBuilder builtMessage = new StringBuilder();
        builtMessage.append("Tasks\n");
        tasks.forEach(task -> builtMessage.append(addTaskToListForMain(task)));   // Add tasks to list
        return String.valueOf(builtMessage);
    }
}
