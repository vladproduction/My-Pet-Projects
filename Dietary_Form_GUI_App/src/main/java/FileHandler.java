import javax.swing.*;
import java.io.*;
import java.util.List;

/**
 * FileHandler class created to handle data GUI form and with file.
 * Contain 2 methods:
 * -FileHandler() - constructor;
 * -writeResults(String surveyData);
 * */
public class FileHandler {

    //Constant for file
    private static final File SURVEY_FILE = new File("survey_results.csv");

    //Constant for headers of the file
    //1 place to specify name of headers
    public static final List<String> HEADERS = List.of(
                       "DateTime", "FirstName", "LastName",
                                "PhoneNumber", "Email", "Sex",
                                "Water", "Meals", "Wheat",
                                "Sugar", "Dairy", "Miles", "Weight");

    //Constant for delimiter (we split expression by comma)
    public static final String DEFAULT_CSV_DELIMITER = ",";

    /**
     * Constructor method purpose:
     * Aim is to create a csv file called survey_results.csv and create a header for the csv file.
     * FileHandler constructor should overwrite any existing file.
     * */
    public FileHandler() {
        //initialising FileWriter object as a parameter for creating PrintWriter object
        //in try block with resources PrintWriter pasted as resources
        //for FileWriter object as parameter stands File with concrete name and path to be created
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(SURVEY_FILE))) {
            //create 1 String from list of headers (using StringBuilder)
            StringBuilder headerLine = new StringBuilder();//initializing StringBuilder
            for (int i = 0; i < HEADERS.size(); i++) {
                String header = HEADERS.get(i);
                headerLine.append(header);
                if (i != HEADERS.size() - 1) {
                    headerLine.append(DEFAULT_CSV_DELIMITER);
                }
            }
            //write header for csv
            printWriter.println(headerLine);
        } catch (Exception e) {
            //we would like to show error to user.
            //for this aim we publish stacktrace to buffer and then show it on UI
            showError(e);
            //and finally also print error to console
            e.printStackTrace();
        }
    }




    /**
     * Method append data to the file.
     * Not overwrite the existing data.
     * @param surveyData String data has to be saved to the file.
     * */
    public void writeResults(String surveyData) {
        //simply ignore data writing in case of failed validation
        //show message dialog with WARN in case of invalid data
        if (isValidCsvData(surveyData)) {

            //append to the output file
            // it should not overwrite the existing data
            //All input and output streams closed by autocloseable(try-with-resources) and surrounded by try catch
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(SURVEY_FILE, true))) {
                printWriter.append(surveyData);
                printWriter.append("\n");

            } catch (IOException e) {
                //we would like to show error to user.
                //for this aim we publish stacktrace to buffer and then show it on UI
                showError(e);
                //and finally also print error to console
                e.printStackTrace();
            }
        } else {
            //in case of data is invalid showing MessageDialog frame
            JOptionPane.showMessageDialog(null,
                    surveyData,
                    "Provided data is invalid",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Helper method isValidCsvData make a simple validation with ',' (comma) as delimiter.
     * More complicated cases like data with spaces and columns inside value ("data, value") are ignored.
     * @param surveyData String data has to be saved to the file.
     * @return false (boolean statement in case of data is not valid)
     * */
    private boolean isValidCsvData(String surveyData) {
        if (surveyData != null && !surveyData.trim().isEmpty()) {
            surveyData = surveyData.trim();
            String[] csvData = surveyData.trim().split(DEFAULT_CSV_DELIMITER);
            if (csvData != null) {
                if (surveyData.endsWith(",")) {
                    return csvData.length == HEADERS.size() - 1;
                }
                return csvData.length == HEADERS.size();
            }
        }
        return false;
    }


    /**
     * Helper method showError:
     * We would like to show error to user.
     * For this aim we publish stacktrace to buffer and then show it on UI.
     * This code might be useful in many places, so it is better to create method for reuse.
     * @param ex Exception as parameter
     * */
    private void showError(Exception ex) {
        //creating buffer and place it in autocloseable try-catch block
        try (ByteArrayOutputStream buff = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(buff)) {
            ex.printStackTrace(ps); //printing trace
            byte[] content = buff.toByteArray();
            String errorMessage = new String(content);
            //in case of error showing MessageDialog frame
            JOptionPane.showMessageDialog(null,
                    errorMessage,
                    "Failed to create csv file with predefined headers",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
