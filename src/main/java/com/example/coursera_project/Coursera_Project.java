package com.example.coursera_project;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class Coursera_Project {

//    Try with the following input:
//    10 2019-01-01 2022-12-31 reports


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArray = input.split(" ");

        int requiredCredit = Integer.parseInt(inputArray[0]);
        String startDate = inputArray[1];
        String endDate = inputArray[2];
        String reportsPath = inputArray[3];
        String outputFormat;
        if (inputArray.length >= 5 && !inputArray[4].isEmpty()) {
            outputFormat = inputArray[4];
        } else {
            outputFormat = "both";
        }


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursera?useSSL=false", "root", "root");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT " +
                    "CONCAT(s.first_name,' ',s.last_name) AS 'Name', " +
                    " c.name AS 'CourseName', " +
                    " c.total_time AS 'Time', " +
                    "c.credit AS 'Credit', " +
                    "CONCAT(i.first_name,' ', i.last_name) AS 'InstructorName' " +
                    "FROM coursera.courses c " +
                    "INNER JOIN coursera.instructors i ON c.instructor_id = i.id " +
                    "INNER JOIN coursera.`students courses xref` scx ON c.id = scx.course_id " +
                    "INNER JOIN coursera.students s ON scx.stundents_pin = s.pin " +
                    "WHERE c.credit >= "
                    + requiredCredit + " AND scx.completion_data BETWEEN '" + startDate + "' AND '" + endDate + "'");

            if (outputFormat.equalsIgnoreCase("csv") || outputFormat.equalsIgnoreCase("both")) {
                generateCsvReport(resultSet, reportsPath);
            }
            if (outputFormat.equalsIgnoreCase("html") || outputFormat.equalsIgnoreCase("both")) {
                generateHtmlReport(resultSet, reportsPath);
            }
            System.out.println("Reports generated successfully");
        } catch (SQLException | IOException e) {
            System.out.println("Error generating reports: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void generateHtmlReport(ResultSet resultSet, String reportsPath) throws Exception {
        // Generate the HTML report
        StringBuilder htmlReport = new StringBuilder("<html>" +
                "<body>" +
                "<table>" +
                "<tr>" +
                "<th>Student Name</th>" +
                "<th>Course Name</th>" +
                "<th>Time</th>" +
                "<th>Credit</th>" +
                "<th>Instructor</th>" +
                "</tr>");
        while (resultSet.next()) {
            String studentName = resultSet.getString("Name");
            String courseName = resultSet.getString("CourseName");
            int time = resultSet.getInt("Time");
            int credits = resultSet.getInt("Credit");
            String instructorName = resultSet.getString("InstructorName");

            htmlReport.append("<tr><td>")
                    .append(studentName).append("</td><td>")
                    .append(courseName).append("</td><td>")
                    .append(time).append("</td><td>")
                    .append(credits).append("</td><td>")
                    .append(instructorName).append("</td></tr>");
        }
        htmlReport.append("</table></body></html>");

        // Save the HTML report to file
        File htmlFile = new File(reportsPath + "/report.html");
        FileWriter htmlWriter = new FileWriter(htmlFile);
        htmlWriter.write(htmlReport.toString());
        htmlWriter.close();
    }

    private static void generateCsvReport(ResultSet resultSet, String reportsPath) throws Exception {
        StringBuilder csvReport = new StringBuilder("Student Name, Course Name, Time, Credits, Instructor\n");
        resultSet.beforeFirst();
        while (resultSet.next()) {
            String studentName = resultSet.getString("Name");
            String courseName = resultSet.getString("CourseName");
            int time = resultSet.getInt("Time");
            int credits = resultSet.getInt("Credit");
            String instructorName = resultSet.getString("InstructorName");

            csvReport.append(studentName).append(",")
                    .append(courseName).append(",")
                    .append(time).append(",")
                    .append(credits).append(",")
                    .append(instructorName).append("\n");
        }

        // Save the CSV report to file
        File csvFile = new File(reportsPath + "/report.csv");
        FileWriter csvWriter = new FileWriter(csvFile);
        csvWriter.write(csvReport.toString());
        csvWriter.close();
    }

}
