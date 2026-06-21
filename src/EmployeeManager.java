import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String CSV_FILE = "employee_data.csv";
    
    // Public array variable kept intact for MainMenuGUI backward compatibility
    public static String[][] employees = new String[0][4];

    // STATIC INITIALIZER: Auto-loads data the exact millisecond the app launches
    static {
        loadEmployees();
    }

    public static void loadEmployees() {
        List<String[]> list = new ArrayList<>();
        File file = new File(CSV_FILE);

        // FAIL-SAFE: Self-generates seed CSV if missing from folder
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
                } // Skip header row
                
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

    // FEATURE 2: Add Employee with Duplicate ID prevention
    public static boolean addEmployee(String id, String name, String dept, String salary) {
        // 1. Check for duplicates
        for (String[] emp : employees) {
            if (emp.length > 0 && emp[0].trim().equals(id.trim())) {
                return false; // Abort save if ID already exists
            }
        }

        // 2. Append unique record to CSV disk
        try (FileWriter fw = new FileWriter(CSV_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(id + "," + name + "," + dept + "," + salary);

        } catch (IOException e) {
            System.out.println("QA Warning - Error writing to CSV: " + e.getMessage());
            return false;
        }
        
        loadEmployees(); // Instantly re-syncs RAM array with disk
        return true; 
    }

    // FEATURE 4: Delete Employee from CSV disk
    public static void deleteEmployee(String empID) {
        List<String> remainingLines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Keep line if it's the Header OR if the ID does not match target
                if (values.length > 0 && (line.startsWith("Employee ID") || !values[0].trim().equals(empID))) {
                    remainingLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Error reading CSV for purge: " + e.getMessage());
        }

        // Overwrite file with surviving records
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (String survivingLine : remainingLines) {
                pw.println(survivingLine);
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Error overwriting CSV: " + e.getMessage());
        }
        loadEmployees();
    }

    // --- FEATURE 4 MISSING PIECE: Hard-Drive Overwrite Engine ---
    public static void saveAllToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            pw.println("Employee ID,Employee Name,Department,Salary"); // Re-write header
            for (String[] emp : employees) {
                if (emp.length >= 4) {
                    pw.println(emp[0] + "," + emp[1] + "," + emp[2] + "," + emp[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("QA Warning - Failed to sync updates to disk: " + e.getMessage());
        }
    }
    // ------------------------------------------------------------

    // FALLBACK GENERATOR: Self-healing seed roster
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