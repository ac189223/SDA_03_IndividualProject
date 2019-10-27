package IP_07.Interface;

import IP_07.Model.Register;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a data writer used to create the JSON archive file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class JSONController {
    private final String fileName;

    /**
     * Constructor of ready to use JSONController with correct path to target file
     */
    public JSONController() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.fileName = "src/main/resources/archive/DataLists_" + timeStamp + ".json";
    }

    /**
     * Getter for this class
     */
    public String getFileName() { return fileName; }

    /**
     * Writing dataLists into JSON file
     *
     * @param register  containing data, that need to be saved
     */
    public void writeDataToJSON(Register register) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Java object to file
        try (FileWriter writer = new FileWriter(getFileName())) {
            gson.toJson(new DataLists(register.getTasks(), register.getProjects()), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
