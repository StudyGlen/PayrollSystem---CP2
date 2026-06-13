public class EmployeeManager {

    public static String[][] employees = {

        {"1001", "Glen Romero", "IT", "50000"},
        {"1002", "Raymond Ballaran", "HR", "45000"},
        {"1003", "Ainy Julienne Manuel", "Finance", "42000"},
        {"1004", "Dianna Cathlene De Leon", "Finance", "30000"},
        {"1005", "Dianna Nikko Pangilinan", "Finance", "30000"}

    };
    
    public static void addEmployee(
        String id,
        String name,
        String dept,
        String salary) {

    String[][] temp =
            new String[employees.length + 1][4];

    for(int i = 0; i < employees.length; i++) {

        temp[i] = employees[i];
    }

    temp[employees.length] =
            new String[]{id, name, dept, salary};

    employees = temp;
}
    
    public static void deleteEmployee(String empID) {

    String[][] temp =
            new String[employees.length - 1][4];

    int index = 0;

    for(int i = 0; i < employees.length; i++) {

        if(!employees[i][0].equals(empID)) {

            temp[index] = employees[i];
            index++;
        }
    }

    employees = temp;
}
}

