import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String CSV_FILE = "employee_data.csv";
    
    // We keep Glen's exact public array variable so MainMenuGUI doesn't break!
    public static String[][] employees = new String[0][4];

    // STATIC INITIALIZER: Runs automatically the exact millisecond the app launches
    static {
        loadEmployees();
    }

    public static void loadEmployees() {
        List<String[]> list = new ArrayList<>();
        File file = new File(CSV_FILE);

        // FAIL-SAFE: If the professor forgets to put the CSV in the folder, the app creates it!
        if (!file.exists()) {
            createSeedCSV();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { 
                    isHeader = false; 
                    continue; 
                } // Skip the header row
                
                String[] values = line.split(",");
                if (values.length >= 4) {
                    list.add(new String[]{
                        values[0].trim(), 
                        values[1].trim(), 
                        values[2].trim(), 
                        values[3].trim()
                    });
                }
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Error loading CSV: " + e.getMessage());
        }
        
        employees = list.toArray(new String[0][]);
    }

    public static void addEmployee(String id, String name, String dept, String salary) {
        try (FileWriter fw = new FileWriter(CSV_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(id + "," + name + "," + dept + "," + salary);

        } catch (IOException e) {
            System.out.println("QA Warning - Error writing to CSV: " + e.getMessage());
        }
        loadEmployees(); // Instantly re-syncs the RAM array with the disk file
    }

    public static void deleteEmployee(String empID) {
        List<String> remainingLines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Keep the line if it's the Header, OR if the ID does not match the target
                if (values.length > 0 && (line.startsWith("Employee ID") || !values[0].trim().equals(empID))) {
                    remainingLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Error reading CSV for purge: " + e.getMessage());
        }

        // Overwrite the file with the surviving records
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (String survivingLine : remainingLines) {
                pw.println(survivingLine);
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Error overwriting CSV: " + e.getMessage());
        }
        loadEmployees();
    }

    // FALLBACK GENERATOR: An un-crashable self-healing mechanism 
    private static void createSeedCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            pw.println("Employee ID,Employee Name,Department,Salary");
            pw.println("1001,Glen Romero,IT,50000");
            pw.println("1002,Raymond Ballaran,HR,45000");
            pw.println("1003,Ainy Julienne Manuel,Finance,42000");
            pw.println("1004,Dianna Cathlene De Leon,Finance,30000");
            pw.println("1005,Nikko Pangilinan,Finance,30000");
        } catch (IOException e) {
            System.out.println("Fatal: Could not self-generate seed CSV.");
        }
    }
}