import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuGUI extends JFrame {

    JButton btnCompute, btnClear, btnExit, btnSearch;

    public MainMenuGUI() {

        setTitle("MotorPH Payroll System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 240, 250));

        JLabel lblTitle = new JLabel(
                "MotorPH Payroll Management System");

        lblTitle.setBounds(60, 40, 500, 40);

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 24));

        panel.add(lblTitle);
        
// Update Employee

        JButton btnUpdateEmployee =
                
        new JButton("Update Employee");
        btnUpdateEmployee.setBounds(220, 300, 200, 40);
        panel.add(btnUpdateEmployee);
        
// OPEN BUTTON

        JButton btnOpen = new JButton("Open Payroll System");

        btnOpen.setBounds(220, 120, 200, 40);

        panel.add(btnOpen);

// SEARCH BUTTON

        btnSearch = new JButton("Search Employee");
        
        JButton btnAddEmployee =
        new JButton("Add Employee");
        btnAddEmployee.setBounds(420,250,180,40);

        panel.add(btnAddEmployee);

        btnSearch.setBounds(220, 180, 200, 40);

        btnSearch.setBackground(new Color(0, 153, 76));
        btnSearch.setForeground(Color.BLACK);

        panel.add(btnSearch);

// EXIT BUTTON

        btnExit = new JButton("Exit");

        btnSearch.setBounds(220, 180, 200, 40);

        btnAddEmployee.setBounds(220, 240, 200, 40);

        btnExit.setBounds(220, 360, 200, 40);

        btnExit.setBackground(new Color(200, 0, 0));
        btnExit.setForeground(Color.BLACK);

        panel.add(btnExit);

// OPEN PAYROLL GUI

        btnOpen.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

        PayrollGUI payroll = new PayrollGUI();
        payroll.setVisible(true);

        dispose();
       
            }
        });
        
// ADD EMPLOYEE
        
        btnAddEmployee.addActionListener(
        new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

        JTextField txtID = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtDept = new JTextField();
        JTextField txtSalary = new JTextField();

        Object[] fields = {
    
        "Employee ID:", txtID,
        "Employee Name:", txtName,
        "Department:", txtDept,
        "Salary:", txtSalary
};

        int option = JOptionPane.showConfirmDialog(
        null,
        fields,
        "Add Employee",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
);

        if(option == JOptionPane.OK_OPTION) {

        String id = txtID.getText().trim();
        String name = txtName.getText().trim();
        String dept = txtDept.getText().trim();
        String salary = txtSalary.getText().trim();

// Validation when entering valid Value in the textfeild

        if(id.isEmpty() ||
        name.isEmpty() ||
        dept.isEmpty() ||
        salary.isEmpty()) {

        JOptionPane.showMessageDialog(
                null,
                "Please complete all fields.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }
    
// NAME VALIDATION
            
if(!name.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
            null,
            "Employee Name must contain letters and spaces only.",
            "Invalid Name",
            JOptionPane.ERROR_MESSAGE
    
        );

    return;
            
}
        
// EMPLOYEE ID VALIDATION
            
if(!id.matches("\\d+")) {

            JOptionPane.showMessageDialog(
            null,
            "Employee ID must contain numbers only.",
            "Invalid Employee ID",
            JOptionPane.ERROR_MESSAGE
        );

    return;
}

// NAME VALIDATION
            
if(!name.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
            null,
            "Employee Name must contain letters and spaces only.",
            "Invalid Name",
            JOptionPane.ERROR_MESSAGE
    );

    return;
}

// DEPARTMENT VALIDATION
            
if(!dept.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
            null,
            "Department must contain letters and spaces only.",
            "Invalid Department",
            JOptionPane.ERROR_MESSAGE
    );

    return;
}

// SALARY VALIDATION

try {

double salaryAmount =
            Double.parseDouble(salary);

if(salaryAmount <= 0) {

                JOptionPane.showMessageDialog(
                null,
                "Salary must be greater than zero.",
                "Invalid Salary",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }

} catch(NumberFormatException ex) {

    JOptionPane.showMessageDialog(
            null,
            "Salary must contain numbers only.",
            "Invalid Salary",
            JOptionPane.ERROR_MESSAGE
    );

    return;
}



EmployeeManager.addEmployee(
        id,
        name,
        dept,
        salary
);

JOptionPane.showMessageDialog(
        null,
        "Employee Added Successfully!"
);

}
     
}

}); 
        
// UPDATE EMPLOYEE

btnUpdateEmployee.addActionListener(
    new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            String empID = JOptionPane.showInputDialog(
                    null,
                    "Enter Employee ID to Update:"
            );

            if(empID == null) {
                return;
          
            }
            
            boolean found = false;

            for(String[] employee : EmployeeManager.employees) {

            if(employee[0].equals(empID)) {

        String[] options = {
            "Edit",
            "Delete",
            "Cancel"
};

        int choice = JOptionPane.showOptionDialog(
            null,
            "Employee ID: " + employee[0] + "\n"
            + "Name: " + employee[1] + "\n"
            + "Department: " + employee[2] + "\n"
            + "Salary: ₱" + employee[3] + "\n\n"
            + "Select an action:",
            "Employee Found",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
);
        
// DELETE EMPLOYEE

if(choice == 1) {

            int confirmDelete =
            JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete Employee "
                    + employee[0] + "?",
                    "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

            if(confirmDelete == JOptionPane.YES_OPTION) {

            EmployeeManager.deleteEmployee(
                employee[0]
        );

            JOptionPane.showMessageDialog(
                null,
                "Employee deleted successfully!"
        );

        found = true;
        break;
    }
}
            
if(choice == 0) {

           JTextField txtName = new JTextField(employee[1]);
           JTextField txtDept = new JTextField(employee[2]);
           JTextField txtSalary = new JTextField(employee[3]);

           Object[] fields = {

                "Employee Name:", txtName,
                "Department:", txtDept,
                "Salary:", txtSalary
};

            int updateOption = JOptionPane.showConfirmDialog(
                null,
                fields,
                "Update Employee",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
);
            
            if(updateOption == JOptionPane.OK_OPTION) {

            employee[1] = txtName.getText().trim();
            employee[2] = txtDept.getText().trim();
            employee[3] = txtSalary.getText().trim();

            JOptionPane.showMessageDialog(
            null,
            "Employee Updated Successfully!"
    );
}
        
}
      

            found = true;
            break;
    }
}

if(!found) {

    JOptionPane.showMessageDialog(
            null,
            "Employee not found.",
            "Error",
            JOptionPane.ERROR_MESSAGE
    );
}

        }
    }
);


// SEARCH EMPLOYEE

            btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            String empID = JOptionPane.showInputDialog(
                        null,
                        "Enter Employee ID:"
        );

            if(empID == null) {
                    return;
     }

            boolean found = false;

            for(String[] employee : EmployeeManager.employees) {

            if(employee[0].equals(empID)) {

            JOptionPane.showMessageDialog(
                null,
                "EMPLOYEE FOUND\n\n"
                + "Employee ID: " + employee[0] + "\n"
                + "Name: " + employee[1] + "\n"
                + "Department: " + employee[2] + "\n"
                + "Salary: ₱" + employee[3],
                "Employee Information",
                JOptionPane.PLAIN_MESSAGE
        );

        found = true;
        break;
    }
}

        if(!found) {

            JOptionPane.showMessageDialog(
            null,
            "Employee not found.",
            "Employee Information",
            JOptionPane.ERROR_MESSAGE
    );
}
            }
        });

// EXIT
            btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "EXIT",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                        
    );

                if(confirm == JOptionPane.YES_OPTION) {

                    System.exit(0);
                }
            }
        });

        add(panel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new MainMenuGUI().setVisible(true);
            }
        });
    }
}
