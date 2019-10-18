package IP_07.Controller;

import IP_07.Interface.DataLists;
import IP_07.Interface.MySQLController;
import IP_07.Model.Register;

/**
 * Represents a controller to update data from MySQL database and control the flow of application after main menu choice
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Controller {
    private ControllerProjects controllerProjects;
    private ControllerTasks controllerTasks;
    private PopUpsBuilder popUpsBuilder;
    private Register register;
    private MySQLController mySQLController;

    /**
     * Constructor of a ready to work with controller containing dependent controllers, popUpsBuilder,
     * register for data and controller for database
     */
    public Controller() {
        this.setControllerProjects(new ControllerProjects());
        this.setControllerTasks(new ControllerTasks());
        this.setPopUpsBuilder(new PopUpsBuilder());
        this.setRegister(new Register());
        this.setMySQLController(new MySQLController());
    }

    /**
     * Getters for this class
     */
    public ControllerProjects getControllerProjects() { return controllerProjects; }
    public ControllerTasks getControllerTasks() { return controllerTasks; }
    public PopUpsBuilder getPopUpsBuilder() { return popUpsBuilder; }
    public Register getRegister() { return register; }
    public MySQLController getMySQLController() { return mySQLController; }

    /**
     * Setters for this class
     */
    public void setControllerProjects(ControllerProjects controllerProjects) { this.controllerProjects = controllerProjects; }
    public void setControllerTasks(ControllerTasks controllerTasks) { this.controllerTasks = controllerTasks; }
    public void setPopUpsBuilder(PopUpsBuilder popUpsBuilder) { this.popUpsBuilder = popUpsBuilder; }
    public void setRegister(Register register) { this.register = register; }
    public void setMySQLController(MySQLController mySQLController) { this.mySQLController = mySQLController; }

    /* =================    =================    Methods    =================   ================= */

    /**
     * Main method
     * Uploading data from MySQL database
     * Presenting first choice to the user (as many times as he wants, until he will select to quit)
     */
    public void run() {
        uploadData();                                   // Upload data from MySQL database
        getRegister().printStatus();                    // Check if successful

        while (true) { mainChoice(); }                  // Start with popups and get back to main menu every time
    }

    /**
     * Printing main menu, that is containing values for the first choice
     */
    private void mainChoice() {
        int mainChosen = -1;
        while (mainChosen < 0 || mainChosen > 3)
            mainChosen = getPopUpsBuilder().mainChoice(getRegister());

        switch (mainChosen) {
            case 0:                         // Quit
                QuitChosen();
            case 1:                         // Work with projects
                projectsChosen();
                break;
            case 2:                         // Work with tasks
                tasksChosen();
                break;
            case 3:                         // Print out all
                printAllChosen();
                break;
        }
    }

    /**
     * Uploading data from MySQL database into lists stored in the register
     */
    private void uploadData() {
        DataLists dataLists = getMySQLController().readData();                          // Retrieve data in DataLists format
                                                                                        // And populate registers lists
        getRegister().setTasks(dataLists.getTasks());
        getRegister().getTasks().forEach(task -> getRegister().getTasksIds().add(task.getId()));
        getRegister().setProjects(dataLists.getProjects());
        getRegister().getProjects().forEach(project -> getRegister().getProjectsIds().add(project.getId()));
                                                                                        // Create tasks assignations in projects
        getRegister().getTasks().stream().filter(task -> !task.getAssignedToProject().equals(""))
                .forEach(task -> {
                    getRegister().addTaskToProject(task.getId(), task.getAssignedToProject());          // In register
                    getMySQLController().addTaskToProject(task.getId(), task.getAssignedToProject());   // In database
                });
    }

    /**
     * Quiting application with printing good bye message
     */
    private void QuitChosen() {
        getPopUpsBuilder().quitConfirmation();                          // Print confirmation
        System.exit(0);                                          // Close application
    }

    /**
     * Starting dependent controller to work with projects
     */
    private void projectsChosen() {
        getControllerProjects().optionChoice(getRegister());                   // Run projects register
    }

    /**
     * Starting dependent controller to work with tasks
     */
    private void tasksChosen() {
        getControllerTasks().optionChoice(getRegister());                      // Run tasks register
    }

    /**
     * Printing out all stored projects (sorted by Id) with assigned to them tasks (sorted by Id)
     * Printing all tasks without assignation (sorted by Id) under above list
     */
    private void printAllChosen() {
        if (getRegister().getTasks().size() == 0 && getRegister().getProjects().size() == 0) {
            getPopUpsBuilder().noTasksNoProjectsInfo();                     // Inform if database is empty

        } else {
            getPopUpsBuilder().mainTasksAndProjectsList(getRegister());     // Print out all tasks and projects
        }
    }
}
