package IP_07.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a dateValidator to check the format of date entered by the user
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class DateValidator {

    /* =================    =================    Methods    =================   ================= */

    /**
     * Checking if the format of date is valid
     *
     * @param dateToValidate            entered by user date that must be validated
     * @param dateFromat                proper format of date
     * @return                          true if entered date matches the format, false otherwise
     */
    public boolean isThisDateValid(String dateToValidate, String dateFromat){

        if (dateToValidate == null) { return false; }                   // Empty date is not valid

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);        // Setting valid format according to input
        sdf.setLenient(false);                                          // To avoid transformation of input like 20191575

        try {
            Date date = sdf.parse(dateToValidate);                      // Try to fit date into selected format
        } catch (ParseException e) {
            return false;                                               // Return false if does not fit
        }
        return true;                                                    // Return true if it is ok
    }
}