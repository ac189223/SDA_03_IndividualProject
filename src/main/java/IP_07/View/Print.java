package IP_07.View;

import javax.swing.*;

/**
 * Represents a print used to throw popup windows on the screen
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Print {
    private final String TITLE = "ToDoLy    =-_-=";

    /* =================    =================    Methods    =================   ================= */

    /**
     * Printing popup window with choices and buttons to choose one of them
     *
     * @param frame             used JFrame
     * @param message           message, that will appear in popup window
     * @param list              list of choices
     * @return                  integer pointing on chosen element
     */
    public int showOptionDialog(JFrame frame, String message, Object[] list) {
        return JOptionPane.showOptionDialog(frame,
                message,
                TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                list,
                list[0]);
    }

    /**
     * Printing popup window with dropdown list of choices to choose from
     *
     * @param frame             used JFrame
     * @param message           message, that will appear in popup window
     * @param list              list of choices visible in dropdown
     * @return                  chosen element from the list in string format
     */
    public String showInputDialog(JFrame frame, String message, Object[] list) {
        return (String) JOptionPane.showInputDialog(frame, message, TITLE,
                JOptionPane.PLAIN_MESSAGE, null, list, list[0]);
    }

    /**
     * Printing popup window with input field
     *
     * @param frame             used JFrame
     * @param message           message, that will appear in popup window
     * @return                  input from user in string format
     */
    public String inputLine(JFrame frame, String message) {
        return JOptionPane.showInputDialog(frame, message, TITLE, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Printing popup window with message or information
     *
     * @param frame             used JFrame
     * @param message           message, that will appear in popup window
     */
    public void showMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, TITLE, JOptionPane.PLAIN_MESSAGE);
    }
}
