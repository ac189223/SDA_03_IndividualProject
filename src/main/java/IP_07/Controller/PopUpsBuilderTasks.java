package IP_07.Controller;

import IP_07.Model.Register;
import IP_07.Model.Task;

import java.util.List;

/**
 * Represents an extension of popUpsBuilder made for tasks to prepare popup windows
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class PopUpsBuilderTasks extends PopUpsBuilder {
    private MessageBuilderTasks messageBuilderTasks = new MessageBuilderTasks();

    /**
     * Getter for this class
     */
    public MessageBuilderTasks getMessageBuilderTasks() { return messageBuilderTasks; }

    /* =================    =================    Methods for tasks    =================    ================= */

    /**
     * Preparing popup with choices for main menu for tasks
     *
     * @param register                      register with tasks and projects
     * @return                              integer value corresponding with choice what to do
     */
    public int chooseOptionForTask(Register register) {
        Object[] options = {5, 4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseOptionForTask(register), options);
    }

    /**
     * Preparing popup with choice of task to edit
     *
     * @param register                      register with tasks and projects
     * @return                              Id of chosen task
     */
    public String chooseTaskToEdit(Register register) {
        Object[] choices = register.getTasksIds().toArray();
        return getPrint().showInputDialog(getFrame(), getMessageBuilderTasks().chooseTask(), choices);
    }

    /**
     * Preparing popup with choices of activities for chosen task
     *
     * @param register                      register with tasks and projects
     * @param chosenTask                    Id of the chosen task
     * @return                              integer value corresponding with chosen activity
     */
    public int chooseActivityForTask(Register register, String chosenTask) {
        Object[] activities = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseActivityForTask(register, chosenTask), activities);
    }

    /**
     * Preparing popup with choices of fields for chosen task
     *
     * @param register                      register with tasks and projects
     * @param chosenTask                    Id of the chosen task
     * @return                              integer value corresponding with chosen field
     */
    public int chooseTaskFieldToEdit(Register register, String chosenTask) {
        Object[] fields = {5, 4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseTaskField(register, chosenTask), fields);
    }

    /**
     * Preparing popup with choice of task status
     *
     * @return                              integer value corresponding with chosen status
     */
    public int chooseTaskStatus() {
        Object[] statusChoices = {2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseStatus(), statusChoices);
    }

    /**
     * Preparing popup with choice of projects to assign task to
     *
     * @param register                      register with tasks and projects
     * @return                              Id of chosen project
     */
    public String chooseProjectToAssignTo(Register register) {
        Object[] projectChoices = register.getProjectsIds().toArray();
        return getPrint().showInputDialog(getFrame(), getMessageBuilderTasks().chooseProject(), projectChoices);
    }

    /**
     * Preparing popup with choice of projects for filtering
     *
     * @param register                      register with tasks and projects
     * @return                              Id of chosen project
     */
    public String chooseProjectForFiltering(Register register) {
        Object[] choices = register.getProjectsIds().toArray();
        return getPrint().showInputDialog(getFrame(), getMessageBuilderTasks().chooseProject(), choices);
    }

    /**
     * Preparing popup with choices of sorting for tasks
     *
     * @return                              integer value corresponding with sorting type
     */
    public int chooseSortingForTasks() {
        Object[] sortingChoices = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseTasksSorting(), sortingChoices);
    }

    /**
     * Preparing popup with choices of filtering for tasks
     *
     * @return                              integer value corresponding with filtering type
     */
    public int chooseFilteringForTasks() {
        Object[] filteringChoices = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderTasks().chooseFiltering(), filteringChoices);
    }

    /**
     * Preparing popup for different inputs entered by the user
     *
     * @return                              entered value in string format (date must follow YYYYMMDD)
     */
    public String changeTaskDueDate() { return getPrint().inputLine(getFrame(), getMessageBuilderTasks().chooseDueDate()); }
    public String chooseNewTitleForTask() { return getPrint().inputLine(getFrame(), getMessageBuilderTasks().chooseTitle()); }
    public String enterNewTitleForTask() { return getPrint().inputLine(getFrame(), getMessageBuilderTasks().enterTaskTitle()); }
    public String enterNewDueDateForTask() { return getPrint().inputLine(getFrame(), getMessageBuilderTasks().enterDueDate()); }

    /**
     * Preparing popups with different confirmations of actions
     */
    public void taskRemovalConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderTasks().removeTaskConfirmation()); }
    public void taskMarkedAsDoneConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderTasks().markTaskAsDoneConfirmation()); }
    public void fixTaskStatusConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderTasks().fixTaskStatusConfirmation()); }
    public void changeTaskDueDateConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderTasks().changedTaskDueDateConfirmation()); }
    public void changedTaskTitleConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderTasks().changedTaskTitleConfirmation()); }

    /**
     * Preparing popup with confirmation of adding task
     *
     * @param register                  register with tasks and projects
     */
    public void addedNewTaskConfirmation(Register register) { getPrint().showMessage(getFrame(), getMessageBuilderTasks().addedTaskConfirmation(register)); }

    /**
     * Preparing popup with list of sorted tasks
     *
     * @param sortedTasks               list of sorted tasks
     */
    public void printSortedTasks(List<Task> sortedTasks) {
        getPrint().showMessage(getFrame(), getMessageBuilderTasks().listOfTasks(sortedTasks));
    }

    /**
     * Preparing popup with list of filtered tasks
     *
     * @param filteredTasks             list of filtered tasks
     */
    public void printFilteredTasks(List<Task> filteredTasks) {
        getPrint().showMessage(getFrame(), getMessageBuilderTasks().listOfTasks(filteredTasks));
    }
}
