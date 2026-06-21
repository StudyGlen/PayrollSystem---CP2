import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PayrollGUI extends JFrame {

    JLabel lblName, lblDaysWorked;
    JComboBox<String> cmbEmployees;
    JTextField txtDaysWorked;

    JButton btnCompute, btnClear, btnBack;

    JTextArea txtResult;

    public PayrollGUI() {

        setTitle("Employee Payroll System");

        setSize(600, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // PANEL
        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(230, 240, 250));

        // TITLE
        JLabel lblTitle =
                new JLabel("MotorPH Payroll Management System");

        lblTitle.setBounds(60, 10, 500, 35);

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 24));

        panel.add(lblTitle);

        // LABELS
        lblName = new JLabel("Select Employee:");

        lblName.setBounds(40, 70, 120, 25);

        panel.add(lblName);

        lblDaysWorked = new JLabel("Days Worked:");

        lblDaysWorked.setBounds(40, 110, 120, 25);

        panel.add(lblDaysWorked);

        // DROPDOWN MENU (Pulls live data from EmployeeManager CSV storage)
        cmbEmployees = new JComboBox<>();
        cmbEmployees.setBounds(180, 70, 250, 25);
        
        // Auto-load active employees into dropdown
        if (EmployeeManager.employees != null) {
            for (String[] emp : EmployeeManager.employees) {
                if (emp.length >= 4) {
                    cmbEmployees.addItem(emp[0] + " - " + emp[1]);
                }
            }
        }
        panel.add(cmbEmployees);

        // TEXTFIELD FOR DAYS WORKED
        txtDaysWorked = new JTextField();

        txtDaysWorked.setBounds(180, 110, 250, 25);

        panel.add(txtDaysWorked);

        // COMPUTE BUTTON
        btnCompute = new JButton("Compute Payroll");

        btnCompute.setBounds(40, 170, 160, 40);

        btnCompute.setBackground(new Color(120, 120, 120));

        btnCompute.setForeground(Color.BLACK);

        btnCompute.setFocusPainted(false);

        panel.add(btnCompute);

        // CLEAR BUTTON
        btnClear = new JButton("Clear");

        btnClear.setBounds(220, 170, 100, 40);

        btnClear.setBackground(Color.GRAY);

        btnClear.setForeground(Color.BLACK);

        btnClear.setFocusPainted(false);

        panel.add(btnClear);

        // BACK BUTTON
        btnBack = new JButton("Back to Main Menu");

        btnBack.setBounds(340, 170, 180, 40);

        btnBack.setBackground(new Color(255, 153, 0));

        btnBack.setForeground(Color.BLACK);

        btnBack.setFocusPainted(false);

        panel.add(btnBack);

        // TEXT AREA (Glen's Pristine Payslip Container)
        txtResult = new JTextArea();

        txtResult.setEditable(false);

        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(txtResult);

        scrollPane.setBounds(40, 240, 500, 220);

        panel.add(scrollPane);

        // COMPUTE BUTTON ACTION (Rubric Feature 3 Integration Engine)
        btnCompute.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(cmbEmployees.getSelectedItem() == null 
                            || txtDaysWorked.getText().trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please select an employee and input days worked.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    // Extract ID and Name from dropdown selection (e.g. "1004 - Dianna De Leon")
                    String selectedItem = cmbEmployees.getSelectedItem().toString();
                    String[] parts = selectedItem.split(" - ");
                    String empID = parts[0];
                    String empName = parts.length > 1 ? parts[1] : selectedItem;

                    // Locate stored monthly base salary from CSV array
                    double storedBaseSalary = 0;
                    for (String[] emp : EmployeeManager.employees) {
                        if (emp.length >= 4 && emp[0].equals(empID)) {
                            storedBaseSalary = Double.parseDouble(emp[3]);
                            break;
                        }
                    }

                    if (storedBaseSalary <= 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error: Employee base salary record is invalid or zero.",
                                "DATA ERROR",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    String daysText = txtDaysWorked.getText().trim();
                    double daysWorked = Double.parseDouble(daysText);

                    // DAYS WORKED VALIDATION
                    if(daysWorked <= 0 || daysWorked > 31) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Active days worked must be between 0.5 and 31.",
                                "INVALID DAYS WORKED",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    // DYNAMIC PAYROLL DERIVATIONS (Assuming 22 standard working days per month)
                    double dailyRate = storedBaseSalary / 22.0;
                    double hourlyRate = dailyRate / 8.0;
                    double grossPay = dailyRate * daysWorked;

                    PayrollCalculator calculator = new PayrollCalculator();

                    // Statutory deductions calculated against earned gross pay
                    double sss = calculator.compSSS(grossPay);
                    double philhealth = calculator.compPhil(grossPay);
                    double pagibig = 200.0; // Preserving Glen's fixed Pag-IBIG contribution
                    double tax = calculator.compTax(grossPay);

                    double deductions = sss + philhealth + pagibig + tax;
                    double netPay = grossPay - deductions;

                    // RENDER GLEN'S EXACT FORMATTED PAYSLIP
                    txtResult.setText(
                            "========== PAYROLL SUMMARY ==========\n\n"
                            + "Employee Name: " + empName + "\n"
                            + "Base Monthly:  ₱" + String.format("%.2f", storedBaseSalary) + "\n"
                            + "Daily Rate:    ₱" + String.format("%.2f", dailyRate) + "\n"
                            + "Days Worked:   " + String.format("%.1f", daysWorked) + "\n"
                            + "GROSS PAY:     ₱" + String.format("%.2f", grossPay) + "\n\n"

                            + "------------- DEDUCTIONS -------------\n"

                            + "SSS: ₱" + String.format("%.2f", sss) + "\n"

                            + "PhilHealth: ₱" + String.format("%.2f", philhealth) + "\n"

                            + "Pag-IBIG: ₱" + String.format("%.2f", pagibig) + "\n"

                            + "Tax: ₱" + String.format("%.2f", tax) + "\n\n"

                            + "Total Deductions: ₱" + String.format("%.2f", deductions) + "\n\n"

                            + "NET PAY: ₱" + String.format("%.2f", netPay)
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            "Payroll Computed Successfully!"
                    );

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid numerical value for days worked (e.g. 14.5).",
                            "INPUT ERROR",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // CLEAR BUTTON ACTION
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (cmbEmployees.getItemCount() > 0) {
                    cmbEmployees.setSelectedIndex(0);
                }

                txtDaysWorked.setText("");

                txtResult.setText("");
            }
        });

        // BACK BUTTON ACTION
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MainMenuGUI menu = new MainMenuGUI();

                menu.setVisible(true);

                dispose();
            }
        });

        add(panel);
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new PayrollGUI().setVisible(true);
            }
        });
    }
}