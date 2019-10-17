package IP_07.Controller;

import IP_07.Model.Register;
import IP_07.View.Print;

import javax.swing.*;

/**
 * Represents a popUpsBuilder to prepare popup windows for application
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class PopUpsBuilder {
    private MessageBuilder messageBuilder;
    private Print print;
    private JFrame frame;

    /**
     * Constructor of a ready to work with popUpsBuilder containing messageBuilder, printer and suitable JFrame
     */
    public PopUpsBuilder() {
    this.setMessageBuilder(new MessageBuilder());
    this.setPrint(new Print());
    this.setFrame(new JFrame("=-_-="));
    }

    /**
     * Getters for this class
     */
    public MessageBuilder getMessageBuilder() { return messageBuilder; }
    public Print getPrint() { return print; }
    public JFrame getFrame() { return frame; }

    /**
     * Setters for this class
     */
    public void setMessageBuilder(MessageBuilder messageBuilder) { this.messageBuilder = messageBuilder; }
    public void setPrint(Print print) { this.print = print; }
    public void setFrame(JFrame frame) { this.frame = frame; }

    /* =================    =================    Methods    =================   ================= */

    /**
     * Preparing popup with choices for main menu
     *
     * @param register                      register with tasks and projects
     * @return                              integer value corresponding with choice what to do
     */
    public int mainChoice(Register register) {
        Object[] mains = {4, 3, 2, 1};
        return getPrint().showOptionDialog(getFrame(), getMessageBuilder().chooseMain(register), mains);
    }

    /**
     * Preparing popup with confirmation of quiting application
     */
    public void quitConfirmation() { getPrint().showMessage(getFrame(), getMessageBuilder().quitConfirmation()); }

    /**
     * Preparing popup with confirmation of adding task to the project
     *
     * @param chosenTaskToAddToProject      Id of the task, that will be assigned to the project
     * @param chosenProject                 Id of the project, that above task will be assigned to
     */
    public void addedTaskToProjectConfirmation(String chosenTaskToAddToProject, String chosenProject) {
        getPrint().showMessage(getFrame(), getMessageBuilder().addedTaskToProjectConfirmation(chosenTaskToAddToProject, chosenProject));
    }

    /**
     * Preparing popups with information
     */
    public void noProjectsInfo() { getPrint().showMessage(getFrame(), getMessageBuilder().noProjectsInfo()); }
    public void noTasksInfo() { getPrint().showMessage(getFrame(), getMessageBuilder().noProjectsInfo()); }
    public void noTasksNoProjectsInfo() { getPrint().showMessage(getFrame(), getMessageBuilder().noTasksNoProjects()); }

    /**
     * Preparing popup with information that task was already added to the project
     *
     * @param chosenTaskToAddToProject      Id of the task, that was assigned to the project
     * @param chosenProject                 Id of the project, that above task was assigned to
     */
    public void taskAlreadyInProjectInformation(String chosenTaskToAddToProject, String chosenProject) {
        getPrint().showMessage(getFrame(), getMessageBuilder().taskAlreadyInProjectInfo(chosenTaskToAddToProject, chosenProject));
    }

    /**
     * Preparing popup with print out of all tasks and projects from the main menu
     *
     * @param register                      register with tasks and projects
     */
    public void mainTasksAndProjectsList(Register register) { getPrint().showMessage(getFrame(), getMessageBuilder().listForMain(register));
    }
}