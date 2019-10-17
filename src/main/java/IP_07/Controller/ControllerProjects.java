package IP_07.Controller;

import IP_07.Model.Project;
import IP_07.Model.Register;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a controller to control the flow of projects part of application
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class ControllerProjects {
    private PopUpsBuilderProjects popUpsBuilderProjects;

    /**
     * Constructor of a ready to work with controller containing popUpsBuilder
     */
    public ControllerProjects() {
        this.setPopUpsBuilderProjects(new PopUpsBuilderProjects());
    }

    /**
     * Getter for this class
     */
    public PopUpsBuilderProjects getPopUpsBuilderProjects() { return popUpsBuilderProjects; }

    /**
     * Setter for this class
     */
    public void setPopUpsBuilderProjects(PopUpsBuilderProjects popUpsBuilderProjects) {
        this.popUpsBuilderProjects = popUpsBuilderProjects;
    }

    /* =================    =================    Methods for projects    =================   ================= */

    /**
     * Printing main menu for projects
     *
     * @param register          register with tasks and projects
     */
    public void optionChoice(Register register) {
        int optionChosen = -1;
        while (optionChosen < 0 || optionChosen > 4)
            optionChosen = getPopUpsBuilderProjects().chooseOptionForProject(register);

        switch (optionChosen) {
            case 0:                                     // Back to main menu of application
                break;
            case 1:                                     // Edit project
                editProjectChosen(register);
                break;
            case 2:                                     // Add new project
                addNewProjectChosen(register);
                break;
            case 3:                                     // Print out sorted projects
                printOutSortedChosen(register);
                break;
            case 4:                                     // Print out filtered projects
                printOutFilteredOptionChosen(register);
                break;
        }
    }

    /**
     * Printing menu of options for edition for projects
     *
     * @param register          register with tasks and projects
     */
    private void editProjectChosen(Register register) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderProjects().noProjectsInfo();                                // Inform if there are no projects
        } else {
            String chosenProject = chooseProjectToEdit(register);                       // Choose project to work with
            int chosenActivity = chooseActivityForProject(register, chosenProject);     // Choose what to do

            switch (chosenActivity) {
                case 0:                                                     // Back to main menu
                    break;
                case 1:                                                     // Remove project
                    removeProjectChosen(register, chosenProject);
                    break;
                case 2:                                                     // Mark project as done
                    markProjectAsDone(register, chosenProject);
                    break;
                case 3:                                                     // Edit one of fields of chosen project
                    chooseProjectFieldToEdit(register, chosenProject);
                    break;
            }
        }
    }

    /**
     * Choosing project to work with
     *
     * @param register          register with tasks and projects
     */
    private String chooseProjectToEdit(Register register) {
        String chosenProject;
        do {
            chosenProject = getPopUpsBuilderProjects().chooseProjectToEdit(register);      // Print popup with choices
        } while (!register.getProjectsIds().contains(chosenProject));                      // Until project is chosen
        return chosenProject;
    }

    /**
     * Choosing what to do with chosen projects
     *
     * @param register          register with tasks and projects
     * @param chosenProject     project, that we will work with
     * @return                  integer value representing chosen activity
     */
    private int chooseActivityForProject(Register register, String chosenProject) {
        int chosenActivity = -1;
        while (chosenActivity < 0 || chosenActivity > 3) {                                // Print popup with choices
            chosenActivity = getPopUpsBuilderProjects().chooseActivityForProject(register, chosenProject);
        }
        return chosenActivity;
    }

    /**
     * Removing project from register
     *
     * @param register          register with tasks and projects
     * @param chosenProject     project, that will be removed
     */
    private void removeProjectChosen(Register register, String chosenProject) {
        register.removeProjectAlways(chosenProject);                                    // Remove project
        getPopUpsBuilderProjects().projectRemovalConfirmation();                        // Print confirmation
    }

    /**
     * Marking project as finished
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the project, that will be removed
     */
    private void markProjectAsDone(Register register, String chosenProject) {
        register.markProjectAsDoneAlways(chosenProject);                                // Mark project as done
        getPopUpsBuilderProjects().projectMarkedAsDoneConfirmation();                   // Print confirmation
    }

    /**
     * Choosing field of the project, which will be edited
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the project, that will have field edited
     */
    private void chooseProjectFieldToEdit(Register register, String chosenProject) {
        int chosenField = -1;
        while (chosenField < 0 || chosenField > 4) {                                    // Print popup with choices
            chosenField = getPopUpsBuilderProjects().chooseProjectFieldToEdit(register, chosenProject);

            switch (chosenField) {
                case 0:                                                     // Back to main menu of application
                    break;
                case 1:                                                     // Change status of chosen project
                    changeProjectStatus(register, chosenProject);
                    break;
                case 2:                                                     // Assign tasks to chosen project
                    assignNewTasksToProject(register, chosenProject);
                    break;
                case 3:                                                     // Change due date of chosen project
                    changeProjectDueDate(register, chosenProject);
                    break;
                case 4:                                                     // Change title of chosen project
                    changeProjectTitle(register, chosenProject);
                    break;
            }
        }
    }

    /**
     * Changing status of chosen project
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the project, that will be removed
     */
    private void changeProjectStatus(Register register, String chosenProject) {
        int chosenStatus = chooseProjectStatus();                           // Choose new status
        register.setProjectStatus(chosenProject, chosenStatus);             // Change status of chosen project
        getPopUpsBuilderProjects().fixProjectStatusConfirmation();          // Print confirmation
    }

    /**
     * Choosing new status for chosen project
     */
    private int chooseProjectStatus() {
        int chosenStatus = -1;
        while (chosenStatus < 0 || chosenStatus > 1) {
            chosenStatus = getPopUpsBuilderProjects().chooseProjectStatus();                // Print popup with choices
        }
        return chosenStatus;
    }

    /**
     * Assigning tasks to the chosen project
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the projects, that tasks will be assigned to
     */
    private void assignNewTasksToProject(Register register, String chosenProject) {
        if (register.getTasks().size() == 0) {
            getPopUpsBuilderProjects().noTasksInfo();                                       // Inform if there are no tasks
        } else {
            boolean addNextTask = true;
            do {
                String chosenTaskToAddToProject = chooseTaskToAddToTheProject(register);    // Choose task to add
                addTaskToTheProject(register, chosenProject, chosenTaskToAddToProject);     // Assign task to chosen project

                addNextTask = ifAddNextTaskToProject(addNextTask);                          // Ask if want to add next task

            } while (addNextTask);                                                          // Repeat while want to add next
        }
    }

    /**
     * Choosing task to add to chosen project
     *
     * @param register          register containing tasks
     */
    private String chooseTaskToAddToTheProject(Register register) {
        String chosenTaskToAddToProject;
        do {
            do {                                                                            // Print popup with choices
                chosenTaskToAddToProject = getPopUpsBuilderProjects().chooseTaskToAddToTheProject(register);
            } while (chosenTaskToAddToProject == null);
        } while (!register.getTasksIds().contains(chosenTaskToAddToProject));
        return chosenTaskToAddToProject;
    }

    /**
     * Assigning task to the project
     *
     * @param register                      register containing tasks and projects
     * @param chosenTaskToAddToProject      Id of the task, that will be assigned
     * @param chosenProject                 Id of the project, that the above task will be assigned to
     */
    private void addTaskToTheProject(Register register, String chosenProject, String chosenTaskToAddToProject) {
        if (!register.findTask(chosenTaskToAddToProject).getAssignedToProject().equals(chosenProject)) {
                                                                                        // Assign task if it was not assigned
            register.findTask(chosenTaskToAddToProject).setAssignedToProject(chosenProject);                      // Mark in task
            register.addTaskToProject(chosenTaskToAddToProject, chosenProject);                                   // List in project
            getPopUpsBuilderProjects().addedTaskToProjectConfirmation(chosenTaskToAddToProject, chosenProject);   // Confirm
        } else {                                                                        // Inform that it was assigned
            getPopUpsBuilderProjects().taskAlreadyInProjectInformation(chosenTaskToAddToProject, chosenProject);
        }
    }

    /**
     * Asking if user want to assign next task to the project
     *
     * @param addNextTask               true value, as before user wanted to assign a task
     * @return                          choice of the user - true to assign next, false to go back to main menu
     */
    private boolean ifAddNextTaskToProject(boolean addNextTask) {
        int ifAddNext = -1;
        while (ifAddNext < 0 || ifAddNext > 1) {
            ifAddNext = getPopUpsBuilderProjects().ifAddNext();                             // Print popup with choices
        }
        if (ifAddNext == 0)                                                                 // If No
            addNextTask = false;
        return addNextTask;                                                                 // If Yes
    }

    /**
     * Changing due date of chosen project
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the project, that will get new new date
     */
    private void changeProjectDueDate(Register register, String chosenProject) {
        String chosenDueDate = "";
        DateValidator dateValidator = new DateValidator();
        do {
            do {
                do {
                    chosenDueDate = getPopUpsBuilderProjects().changeProjectDueDate();      // Ask for due date input
                } while (chosenDueDate == null);
            } while (chosenDueDate.equals(""));
        } while (!dateValidator.isThisDateValid(chosenDueDate, "yyyyMMdd"));     // Check if date is valid

        register.setProjectDueDate(chosenProject, chosenDueDate);                           // Change due date of chosen project
        getPopUpsBuilderProjects().changeProjectDueDateConfirmation();                      // Print confirmation
    }

    /**
     * Changing title of chosen project
     *
     * @param register          register with tasks and projects
     * @param chosenProject     Id of the project, that will be renamed
     */
    private void changeProjectTitle(Register register, String chosenProject) {
        String chosenTitle = chooseNewTitleForProject();                                // Get new title
        register.setProjectTitle(chosenProject, chosenTitle);                           // Change title of chosen project
        getPopUpsBuilderProjects().changedProjectTitleConfirmation();                   // Print confirmation
    }

    /**
     * Getting a new title for the project
     *
     * @return                  String value entered by the user
     */
    private String chooseNewTitleForProject() {
        String chosenTitle = "";
        do {
            do {
                chosenTitle = getPopUpsBuilderProjects().chooseNewTitleForProject();     // Ask for title input
            } while (chosenTitle == null);
        } while (chosenTitle.equals(""));
        return chosenTitle;
    }

    /**
     * Adding new project to the register
     *
     * @param register          register with tasks and projects
     */
    private void addNewProjectChosen(Register register) {
        String newTitle = enterNewTitleForProject();                                    // Get title
        String newDueDate = enterNewDueDateForProject();                                // Get due date
        register.addProject(new Project(newTitle, newDueDate));                         // Add new project to register
        getPopUpsBuilderProjects().addedNewProjectConfirmation(register);               // Print confirmation
    }

    /**
     * Getting a title for a new project
     *
     * @return                  String value entered by the user
     */
    private String enterNewTitleForProject() {
        String newTitle = "";
        do {
            do {
                newTitle = getPopUpsBuilderProjects().enterNewTitleForProject();          // Ask for title input
            } while (newTitle == null);
        } while (newTitle.equals(""));
        return newTitle;
    }

    /**
     * Getting a due date for a new project
     *
     * @return                  String value entered by the user, must be in format YYYYMMDD
     */
    private String enterNewDueDateForProject() {
        String newDueDate = "";
        DateValidator dateValidator = new DateValidator();
        do {
            do {
                do {
                    newDueDate = getPopUpsBuilderProjects().enterNewDueDateForProject();    // Ask for due date input
                } while (newDueDate == null);
            } while (newDueDate.equals(""));
        } while (!dateValidator.isThisDateValid(newDueDate, "yyyyMMdd"));       // Check if date is valid
        return newDueDate;
    }

    /**
     * Printing out list of projects (sorted)
     *
     * @param register          register with tasks and projects
     */
    private void printOutSortedChosen(Register register) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderProjects().noProjectsInfo();                                // Inform if there are no projects
        } else {
            int chosenSorting = chooseSortingForProjects();                             // Choose sorting

            List<Project> sortedProjects = null;
            switch (chosenSorting) {
                case 0:                                                                 // Sort by number of tasks
                    sortedProjects = register.getProjects();
                    for (int i = 0; i < sortedProjects.size(); i++) {                   // Use bubble sort
                        for (int j = 0; j < sortedProjects.size() - i - 1; j++) {
                            if((sortedProjects.get(j)).getAssignedTasks().size() >
                                    (sortedProjects.get(j + 1)).getAssignedTasks().size()) {
                                Collections.swap(sortedProjects, j, j + 1);
                            }
                        }
                    }
                    break;

                case 1:                                                                 // Sort by due date
                    sortedProjects = register.getProjects().stream()
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList())
                            .stream()
                            .sorted(Comparator.comparing(Project::getDueDate)).collect(Collectors.toList());
                    break;

                case 2:                                                                // Sort by Id
                    sortedProjects = register.getProjects().stream()
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList());
                    break;

                case 3:
                    sortedProjects = register.getProjects().stream()                   // Sort by title
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList())
                            .stream()
                            .sorted(Comparator.comparing(Project::getTitle)).collect(Collectors.toList());
                    break;
            }
            getPopUpsBuilderProjects().printSortedProjects(sortedProjects);            // Print out sorted list
        }
    }

    /**
     * Choosing of sorting method for projects
     *
     * @return                  int value representing method of sorting
     */
    private int chooseSortingForProjects() {
        int chosenSorting = -1;
        while (chosenSorting < 0 || chosenSorting > 3) {
            chosenSorting = getPopUpsBuilderProjects().chooseSortingForProjects();    // Print popup with choices
        }
        return chosenSorting;
    }

    /**
     * Printing out filtered list of projects (sorted)
     *
     * @param register          register with tasks and projects
     */
    private void printOutFilteredOptionChosen(Register register) {
        if (register.getProjects().size() == 0) {
            getPopUpsBuilderProjects().noProjectsInfo();                            // Inform if there are no projects
        } else {
            int chosenFiltering = chooseFilteringForProjects();                     // Choose filtering

            List<Project> filteredProjects = null;
            switch (chosenFiltering) {
                case 0:
                    filteredProjects = register.getProjects().stream()              // Filter unfinished
                            .filter(task -> !task.ifDone())
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList());
                    break;

                case 1:
                    filteredProjects = register.getProjects().stream()              // Filter finished
                            .filter(Project::ifDone)
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList());
                    break;

                case 2:
                    filteredProjects = register.getProjects().stream()              // Filter without assigned tasks
                            .filter(project -> project.getAssignedTasks().size() == 0)
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList());
                    break;

                case 3:
                    filteredProjects = register.getProjects().stream()              // Filter with assigned tasks
                            .filter(project -> project.getAssignedTasks().size() != 0)
                            .sorted(Comparator.comparing(Project::getId)).collect(Collectors.toList());
                    break;
            }
            getPopUpsBuilderProjects().printFilteredProjects(filteredProjects);     // Print out filtered list
        }
    }

    /**
     * Choosing of filtering method for projects
     *
     * @return                  int value representing method of filtering
     */
    private int chooseFilteringForProjects() {
        int chosenFiltering = -1;
        while (chosenFiltering < 0 || chosenFiltering > 3) {
            chosenFiltering = getPopUpsBuilderProjects().chooseFilteringForProjects();     // Print popup with choices
        }
        return chosenFiltering;
    }
}
