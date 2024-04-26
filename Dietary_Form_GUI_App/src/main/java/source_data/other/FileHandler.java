package com.app;

import javax.swing.*;
import java.io.*;
import java.util.List;

/**
 * Created by vladproduction on 24-Apr-24
 */

public class FileHandler {

    private static final File SURVEY_FILE = new File("survey_results.csv"); //Constants for file name;
    //1 place to specify name of headers
    public static final List<String> HEADERS = List.of("DateTime", "FirstName", "LastName", "PhoneNumber", "Email", "Sex", "Water", "Meals", "Wheat", "Sugar", "Dairy", "Miles", "Weight");
    public static final String DEFAULT_CSV_DELIMITER = ",";

    //Constructor (should overwrite any existing file)
    public FileHandler() {
        //todo
        /*1)create a csv file called survey_results.csv;
          2)create a header for the csv file (header:
        * DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight)*/
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(SURVEY_FILE))) {
            //create 1 String from list of headers
            StringBuilder headerLine = new StringBuilder();
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

    //todo:consider adding error handling for invalid user input in the CustomJFrame class before calling writeResults.

    //writing data to file method
    public void writeResults(String surveyData) {
        //simply ignore data writing in case of failed validation.
        //show message dialog with WARN in case of invalid data
        if (isValidCsvData(surveyData)) {
            //todo
            //append to the output file; it should not overwrite the existing data
            //All input and output streams must be closed properly and must be surrounded by a try catch
            /*append the surveyData string to the survey_results.csv file*/
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
            JOptionPane.showMessageDialog(null,
                    surveyData,
                    "Provided data is invalid",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //idea is to make a simple validation with ',' as delimiter
    //also more complicated cases like data with spaces and columns inside value ("data, value") are ignored
    private boolean isValidCsvData(String surveyData) {
        if (surveyData != null && !surveyData.trim().isEmpty()) {
            String[] csvData = surveyData.trim().split(DEFAULT_CSV_DELIMITER);
            if (csvData != null) {
                return csvData.length == HEADERS.size();
            }
        }
        return false;
    }


    /*
    We would like to show error to user.
    For this aim we publish stacktrace to buffer and then show it on UI.
    This code might be useful in many places, so it is better to create method for reuse.
     */
    private void showError(Exception ex) {
        try (ByteArrayOutputStream buff = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(buff)) {
            ex.printStackTrace(ps);
            byte[] content = buff.toByteArray();
            String errorMessage = new String(content);

            JOptionPane.showMessageDialog(null,
                    errorMessage,
                    "Failed to create csv file with predefined headers",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
