package IP_07.Controller;

import IP_07.Model.Project;
import IP_07.Model.Register;

import java.util.List;

/**
 * Represents an extension of popUpsBuilder made for projects to prepare popup windows
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class PopUpsBuilderProjects extends PopUpsBuilder {
    private MessageBuilderProjects messageBuilderProjects = new MessageBuilderProjects();

    /**
     * Getter for this class
     */
    public MessageBuilderProjects getMessageBuilderProjects() { return messageBuilderProjects; }

    /* =================    =================    Methods for projects    =================    ================= */

    /**
     * Preparing popup with choices for main menu for projects
     *
     * @param register                      register with tasks and projects
     * @return                              integer value corresponding with choice what to do
     */
    public int chooseOptionForProject(Register register) {
        Object[] options = {5, 4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseOptionForProject(register), options);
    }

    /**
     * Preparing popup with choice of projects to edit
     *
     * @param register                      register with tasks and projects
     * @return                              Id of chosen project
     */
    public String chooseProjectToEdit(Register register) {
        Object[] choices = register.getProjectsIds().toArray();
        return getPrint().showInputDialog(getFrame(), getMessageBuilderProjects().chooseTask(), choices);
    }

    /**
     * Preparing popup with choices of activities for chosen project
     *
     * @param register                      register with tasks and projects
     * @param chosenProject                 Id of the chosen project
     * @return                              integer value corresponding with chosen activity
     */
    public int chooseActivityForProject(Register register, String chosenProject) {
        Object[] activities = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseActivityForProject(register, chosenProject), activities);
    }

    /**
     * Preparing popup with choices of fields for chosen project
     *
     * @param register                      register with tasks and projects
     * @param chosenProject                 Id of the chosen project
     * @return                              integer value corresponding with chosen field
     */
    public int chooseProjectFieldToEdit(Register register, String chosenProject) {
        Object[] fields = {5, 4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseProjectField(register, chosenProject), fields);
    }

    /**
     * Preparing popup with choice of project status
     *
     * @return                              integer value corresponding with chosen status
     */
    public int chooseProjectStatus() {
        Object[] statusChoices = {2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseStatus(), statusChoices);
    }

    /**
     * Preparing popup with choice of tasks to assign to project
     *
     * @param register                      register with tasks and projects
     * @return                              Id of chosen task
     */
    public String chooseTaskToAddToTheProject(Register register) {
        Object[] taskChoices = register.getTasksIds().toArray();
        return getPrint().showInputDialog(getFrame(), getMessageBuilderProjects().chooseTask(), taskChoices);
    }

    /**
     * Preparing popup with choices if user want to add next task to the project
     *
     * @return                              integer value corresponding with choice
     */
    public int ifAddNext() {
        Object[] statusChoices = {2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().ifAddNextTask(), statusChoices);
    }

    /**
     * Preparing popup with choices of sorting for projects
     *
     * @return                              integer value corresponding with sorting type
     */
    public int chooseSortingForProjects() {
        Object[] sortingChoices = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseProjectsSorting(), sortingChoices);
    }

    /**
     * Preparing popup with choices of filtering for projects
     *
     * @return                              integer value corresponding with filtering type
     */
    public int chooseFilteringForProjects() {
        Object[] filteringChoices = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilderProjects().chooseFiltering(), filteringChoices);
    }

    /**
     * Preparing popup for different inputs entered by the user
     *
     * @return                              entered value in string format (date must follow YYYYMMDD)
     */
    public String changeProjectDueDate() { return getPrint().inputLine(getFrame(), getMessageBuilderProjects().chooseDueDate()); }
    public String chooseNewTitleForProject() { return getPrint().inputLine(getFrame(), getMessageBuilderProjects().chooseTitle()); }
    public String enterNewTitleForProject() { return getPrint().inputLine(getFrame(), getMessageBuilderProjects().enterProjectTitle()); }
    public String enterNewDueDateForProject() { return getPrint().inputLine(getFrame(), getMessageBuilderProjects().enterDueDate()); }

    /**
     * Preparing popups with different confirmations of actions
     */
    public void projectRemovalConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderProjects().removeProjectConfirmation()); }
    public void projectMarkedAsDoneConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderProjects().markProjectAsDoneConfirmation()); }
    public void fixProjectStatusConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderProjects().fixProjectStatusConfirmation()); }
    public void changeProjectDueDateConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderProjects().changedProjectDueDateConfirmation()); }
    public void changedProjectTitleConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilderProjects().changedProjectTitleConfirmation()); }

    /**
     * Preparing popup with confirmation of adding project
     *
     * @param register                  register with tasks and projects
     */
    public void addedNewProjectConfirmation(Register register) { getPrint().showMessage(getFrame(), getMessageBuilderProjects().addedProjectConfirmation(register)); }

    /**
     * Preparing popup with list of sorted projects
     *
     * @param sortedProjects            list of sorted projects
     */
    public void printSortedProjects(List<Project> sortedProjects) {
        getPrint().showMessage(getFrame(), getMessageBuilderProjects().listOfProjects(sortedProjects));
    }

    /**
     * Preparing popup with list of filtered projects
     *
     * @param filteredProjects          list of filtered projects
     */
    public void printFilteredProjects(List<Project> filteredProjects) {
        getPrint().showMessage(getFrame(), getMessageBuilderProjects().listOfProjects(filteredProjects));
    }
}
