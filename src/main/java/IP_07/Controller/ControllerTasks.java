package IP_07.Controller;

import IP_07.Model.Register;
import IP_07.Model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a controller to control the flow of tasks part of application
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class ControllerTasks {
    private PopUpsBuilderTasks popUpsBuilderTasks;

    /**
     * Constructor of a ready to work with controller containing popUpsBuilder
     */
    public ControllerTasks() { this.setPopUpsBuilderTasks(new PopUpsBuilderTasks()); }

    /**
     * Getter for this class
     */
    public PopUpsBuilderTasks getPopUpsBuilderTasks() { return popUpsBuilderTasks; }

    /**
     * Setter for this class
     */
    public void setPopUpsBuilderTasks(PopUpsBuilderTasks popUpsBuilderTasks) {
        this.popUpsBuilderTasks = popUpsBuilderTasks;
    }

    /* =================    =================    Methods for tasks    =================   ================= */

    /**
     * Printing main menu for tasks
     *
     * @param register          register with tasks and projects
     */
    public void optionChoice(Register register) {
        int optionChosen = -1;
        while (optionChosen < 0 || optionChosen > 4)
            optionChosen = getPopUpsBuilderTasks().chooseOptionForTask(register);

        switch (optionChosen) {
            case 0:                                     // Back to main menu of application
                break;
            case 1:                                     // Edit task
                editTaskChosen(register);
                break;
            case 2:                                     // Add new task
                addNewTaskChosen(register);
                break;
            case 3:                                     // Print out sorted tasks
                printOutSortedChosen(register);
                break;
            case 4:                                     // Print out filtered tasks
                printOutFilteredOptionChosen(register);
                break;
        }
    }

    /**
     * Printing menu of options for edition for tasks
     *
     * @param register          register with tasks and projects
     */
    private void editTaskChosen(Register register) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderTasks().noTasksInfo();                                // Inform if there are no tasks
        } else {
            String chosenTask = chooseTaskToEdit(register);                       // Choose task to work with
            int chosenActivity = chooseActivityForTask(register, chosenTask);     // Choose what to do

            switch (chosenActivity) {
                case 0:                                                 // Back to main menu of application
                    break;
                case 1:                                                 // Remove task
                    removeTaskChosen(register, chosenTask);
                    break;
                case 2:                                                 // Mark task as done
                    markTaskAsDone(register, chosenTask);
                    break;
                case 3:                                                 // Edit one of fields of chosen task
                    chooseTaskFieldToEdit(register, chosenTask);
                    break;
            }
        }
    }

    /**
     * Choosing task to work with
     *
     * @param register          register with tasks and projects
     */
    private String chooseTaskToEdit(Register register) {
        String chosenTask;
        do {
            chosenTask = getPopUpsBuilderTasks().chooseTaskToEdit(register);            // Print popup with choices
        } while (!register.getTasksIds().contains(chosenTask));                         // Until task is chosen
        return chosenTask;
    }

    /**
     * Choosing what to do with chosen task
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that we will work with
     * @return                  integer value representing chosen activity
     */
    private int chooseActivityForTask(Register register, String chosenTask) {
        int chosenActivity = -1;
        while (chosenActivity < 0 || chosenActivity > 3) {                              // Print popup with choices
            chosenActivity = getPopUpsBuilderTasks().chooseActivityForTask(register, chosenTask);
        }
        return chosenActivity;
    }

    /**
     * Removing task from register
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will be removed
     */
    private void removeTaskChosen(Register register, String chosenTask) {
        register.removeTask(chosenTask);                                                // Remove task
        getPopUpsBuilderTasks().taskRemovalConfirmation();                              // Print confirmation
    }

    /**
     * Marking task as finished
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will be removed
     */
    private void markTaskAsDone(Register register, String chosenTask) {
        register.markTaskAsDone(chosenTask);                                            // Mark project as done
        getPopUpsBuilderTasks().taskMarkedAsDoneConfirmation();                         // Print confirmation
    }

    /**
     * Choosing field of the task, which will be edited
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will have field edited
     */
    private void chooseTaskFieldToEdit(Register register, String chosenTask) {
        int chosenField = -1;
        while (chosenField < 0 || chosenField > 4) {                            // Print popup with choices
            chosenField = getPopUpsBuilderTasks().chooseTaskFieldToEdit(register, chosenTask);

            switch (chosenField) {
                case 0:                                                         // Back to main menu of application
                    break;
                case 1:                                                         // Change status of chosen task
                    changeTaskStatus(register, chosenTask);
                    break;
                case 2:                                                         // Reassign task to project
                    assignToProject(register, chosenTask);
                    break;
                case 3:                                                         // Change due date of chosen task
                    changeTaskDueDate(register, chosenTask);
                    break;
                case 4:                                                         // Change title of chosen task
                    changeTaskTitle(register, chosenTask);
                    break;
            }
        }
    }

    /**
     * Changing status of chosen task
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will be removed
     */
    private void changeTaskStatus(Register register, String chosenTask) {
        int chosenStatus = chooseTaskStatus();                                  // Choose new status
        register.setTaskStatus(chosenTask, chosenStatus);                       // Change status of chosen task
        getPopUpsBuilderTasks().fixTaskStatusConfirmation();                    // Print confirmation
    }

    /**
     * Choosing new status for chosen task
     */
    private int chooseTaskStatus() {
        int chosenStatus = -1;
        while (chosenStatus < 0 || chosenStatus > 1) {
            chosenStatus = getPopUpsBuilderTasks().chooseTaskStatus();           // Print popup with choices
        }
        return chosenStatus;
    }

    /**
     * Assigning task to a project
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will be assigned
     */
    private void assignToProject(Register register, String chosenTask) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderTasks().noProjectsInfo();                                   // Inform if there are no projects
        } else {
            String chosenProjectToAssignTo = chooseProjectToAssignTo(register);         // Choose project to assign to
            assignTaskToTheProject(register, chosenTask, chosenProjectToAssignTo);      // Assign task to chosen project
        }
    }

    /**
     * Choosing project to assign task to it
     *
     * @param register          register with tasks and projects
     */
    private String chooseProjectToAssignTo(Register register) {
        String chosenProjectToAssignTo;
        do {
            do {                                                              // Print popup with choices
                chosenProjectToAssignTo = getPopUpsBuilderTasks().chooseProjectToAssignTo(register);
            } while (chosenProjectToAssignTo == null);
        } while (!register.getProjectsIds().contains(chosenProjectToAssignTo));
        return chosenProjectToAssignTo;
    }

    /**
     * Assigning task to the chosen project
     *
     * @param register                      register containing tasks and projects
     * @param chosenTask                    Id of the task, that will be assigned
     * @param chosenProjectToAssignTo       Id of the project, that the above task will be assigned to
     */
    private void assignTaskToTheProject(Register register, String chosenTask, String chosenProjectToAssignTo) {
        if (!register.findTask(chosenTask).getAssignedToProject().equals(chosenProjectToAssignTo)) {
                                                                            // Assign task if it was not assigned
            register.findTask(chosenTask).setAssignedToProject(chosenProjectToAssignTo);                    // Mark in task
            register.addTaskToProject(chosenTask, chosenProjectToAssignTo);                                 // List in project
            getPopUpsBuilderTasks().addedTaskToProjectConfirmation(chosenTask, chosenProjectToAssignTo);    // Confirm
        } else {                                                            // Inform that it was assigned
            getPopUpsBuilderTasks().taskAlreadyInProjectInformation(chosenTask, chosenProjectToAssignTo);
        }
    }

    /**
     * Changing due date of chosen task
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will get new new date
     */
    private void changeTaskDueDate(Register register, String chosenTask) {
        String chosenDueDate = "";
        DateValidator dateValidator = new DateValidator();
        do {
            do {
                do {
                    chosenDueDate = getPopUpsBuilderTasks().changeTaskDueDate();            // Ask for due date input
                } while (chosenDueDate == null);
            } while (chosenDueDate.equals(""));
        } while (!dateValidator.isThisDateValid(chosenDueDate, "yyyyMMdd"));     // Check if date is valid

        register.setTaskDueDate(chosenTask, chosenDueDate);                                 // Change due date of chosen task
        getPopUpsBuilderTasks().changeTaskDueDateConfirmation();                            // Print confirmation
    }

    /**
     * Changing title of chosen task
     *
     * @param register          register with tasks and projects
     * @param chosenTask        Id of the task, that will be renamed
     */
    private void changeTaskTitle(Register register, String chosenTask) {
        String chosenTitle = chooseNewTitleForTask();                               // Get new title
        register.setTaskTitle(chosenTask, chosenTitle);                             // Change title of chosen task
        getPopUpsBuilderTasks().changedTaskTitleConfirmation();                     // Print confirmation
    }

    /**
     * Getting a new title for the task
     *
     * @return                  String value entered by the user
     */
    private String chooseNewTitleForTask() {
        String chosenTitle = "";
        do {
            do {
                chosenTitle = getPopUpsBuilderTasks().chooseNewTitleForTask();     // Ask for title input
            } while (chosenTitle == null);
        } while (chosenTitle.equals(""));
        return chosenTitle;
    }

    /**
     * Adding new task to the register
     *
     * @param register          register with tasks and projects
     */
    private void addNewTaskChosen(Register register) {
        String newTitle = enterNewTitleForTask();                               // Get title
        String newDueDate = enterNewDueDateForTask();                           // Get due date
        register.addTask(new Task(newTitle, newDueDate));                       // Add new task to register
        getPopUpsBuilderTasks().addedNewTaskConfirmation(register);             // Print confirmation
    }

    /**
     * Getting a due date for a new task
     *
     * @return                  String value entered by the user, must be in format YYYYMMDD
     */
    private String enterNewDueDateForTask() {
        String newDueDate = "";
        DateValidator dateValidator = new DateValidator();
        do {
            do {
                do {
                    newDueDate = getPopUpsBuilderTasks().enterNewDueDateForTask();         // Ask for due date input
                } while (newDueDate == null);
            } while (newDueDate.equals(""));
        } while (!dateValidator.isThisDateValid(newDueDate, "yyyyMMdd"));       // Check if date is valid
        return newDueDate;
    }

    /**
     * Getting a title for a new task
     *
     * @return                  String value entered by the user
     */
    private String enterNewTitleForTask() {
        String newTitle = "";
        do {
            do {
                newTitle = getPopUpsBuilderTasks().enterNewTitleForTask();                // Ask for title input
            } while (newTitle == null);
        } while (newTitle.equals(""));
        return newTitle;
    }

    /**
     * Printing out list of tasks (sorted)
     *
     * @param register          register with tasks and projects
     */
    private void printOutSortedChosen(Register register) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderTasks().noTasksInfo();                                      // Inform if there are no tasks
        } else {
            int chosenSorting = chooseSortingForTasks();                                // Choose sorting

            List<Task> sortedTasks = null;
            switch (chosenSorting) {
                case 0:
                    sortedTasks = register.getTasks().stream()                          // Sort by project
                            .filter(task -> !task.getAssignedToProject().equals(""))
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList())
                            .stream()
                            .sorted(Comparator.comparing(Task::getAssignedToProject)).collect(Collectors.toList());
                    break;

                case 1:
                    sortedTasks = register.getTasks().stream()                        // Sort by due date
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList())
                            .stream()
                            .sorted(Comparator.comparing(Task::getDueDate)).collect(Collectors.toList());
                    break;

                case 2:
                    sortedTasks = register.getTasks().stream()                        // Sort by Id
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
                    break;

                case 3:
                    sortedTasks = register.getTasks().stream()                        // Sort by title
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList())
                            .stream()
                            .sorted(Comparator.comparing(Task::getTitle)).collect(Collectors.toList());
                    break;
            }
            getPopUpsBuilderTasks().printSortedTasks(sortedTasks);                    // Print out sorted list
        }
    }

    /**
     * Choosing of sorting method for tasks
     *
     * @return                  int value representing method of sorting
     */
    private int chooseSortingForTasks() {
        int chosenSorting = -1;
        while (chosenSorting < 0 || chosenSorting > 3) {
            chosenSorting = getPopUpsBuilderTasks().chooseSortingForTasks();        // Print popup with choices
        }
        return chosenSorting;
    }

    /**
     * Printing out filtered list of tasks (sorted)
     *
     * @param register          register with tasks and projects
     */
    private void printOutFilteredOptionChosen(Register register) {
        if (register.getTasks().size() == 0) {
            getPopUpsBuilderTasks().noTasksInfo();                                  // Inform if there are no tasks
        } else {
            int chosenFiltering = chooseFilteringForTasks();                        // Choose filtering

            List<Task> filteredTasks = null;
            switch (chosenFiltering) {
                case 0:
                    filteredTasks = register.getTasks().stream()                    // Filter unfinished
                            .filter(task -> !task.ifDone())
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
                break;

                case 1:
                    filteredTasks = register.getTasks().stream()                    // Filter finished
                            .filter(Task::ifDone)
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
                    break;

                case 2:
                    filteredTasks = register.getTasks().stream()                    // Filter not assigned
                            .filter(task -> task.getAssignedToProject().equals(""))
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
                    break;

                case 3:
                    String chosenProject = "";                                      // Choose project
                    if (register.getProjects().size() != 0) {
                        do {
                            do {                                                    // Print popup with choices
                                chosenProject = getPopUpsBuilderTasks().chooseProjectForFiltering(register);
                            } while (chosenProject == null);
                        } while (!register.getProjectsIds().contains(chosenProject));

                    } else {
                        getPopUpsBuilderTasks().noProjectsInfo();                   // Inform if there are no projects
                    }

                    String finalChosenProject = chosenProject;
                    filteredTasks = register.getTasks().stream()                    // Filter by project assignment
                            .filter(task -> task.getAssignedToProject().equals(finalChosenProject))
                            .sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
                    break;
            }
            getPopUpsBuilderTasks().printFilteredTasks(filteredTasks);              // Print out filtered list
        }
    }

    /**
     * Choosing of filtering method for tasks
     *
     * @return                  int value representing method of filtering
     */
    private int chooseFilteringForTasks() {
        int chosenFiltering = -1;
        while (chosenFiltering < 0 || chosenFiltering > 3) {
            chosenFiltering = getPopUpsBuilderTasks().chooseFilteringForTasks();    // Print popup with choices
        }
        return chosenFiltering;
    }
}
