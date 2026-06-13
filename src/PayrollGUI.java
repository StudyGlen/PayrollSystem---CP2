import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PayrollGUI extends JFrame {

    JLabel lblName, lblSalary;
    JTextField txtName, txtSalary;

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
        lblName = new JLabel("Employee Name:");

        lblName.setBounds(40, 70, 120, 25);

        panel.add(lblName);

        lblSalary = new JLabel("Monthly Salary:");

        lblSalary.setBounds(40, 110, 120, 25);

        panel.add(lblSalary);

        // TEXTFIELDS
        txtName = new JTextField();

        txtName.setBounds(180, 70, 250, 25);

        panel.add(txtName);

        txtSalary = new JTextField();

        txtSalary.setBounds(180, 110, 250, 25);

        panel.add(txtSalary);

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

        // TEXT AREA
        txtResult = new JTextArea();

        txtResult.setEditable(false);

        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(txtResult);

        scrollPane.setBounds(40, 240, 500, 220);

        panel.add(scrollPane);

        // COMPUTE BUTTON ACTION
        btnCompute.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(txtName.getText().isEmpty()
                            || txtSalary.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please complete all fields.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

String name = txtName.getText().trim();
String salaryText = txtSalary.getText().trim();

// NAME VALIDATION
if(!name.matches("[a-zA-Z ]+")) {

    JOptionPane.showMessageDialog(
            null,
            "Employee Name must contain letters and spaces only.",
            "INVALID NAME",
            JOptionPane.ERROR_MESSAGE
    );

    return;
}

double salary =
        Double.parseDouble(salaryText);

// SALARY VALIDATION
if(salary <= 0) {

    JOptionPane.showMessageDialog(
            null,
            "Salary must be greater than zero.",
            "INVALID SALARY",
            JOptionPane.ERROR_MESSAGE
    );

    return;
}
            double hourlyRate = salary / 168;

            PayrollCalculator calculator =
                    new PayrollCalculator();

            double sss =
                    calculator.compSSS(salary);

            double philhealth =
                    calculator.compPhil(salary);
            
            double pagibig = 200;

            double tax =
                    calculator.compTax(salary);

            double deductions =
                            sss + philhealth + pagibig + tax;

            double netPay = salary - deductions;

                    txtResult.setText(
                            "========== PAYROLL SUMMARY ==========\n\n"
                            + "Employee Name: " + name + "\n"
                            + "Monthly Salary: ₱"
                            + String.format("%.2f", salary) + "\n"
                            + "Hourly Rate: ₱"
                            + String.format("%.2f", hourlyRate)
                            + "\n\n"

                            + "------------- DEDUCTIONS -------------\n"

                            + "SSS: ₱"
                            + String.format("%.2f", sss) + "\n"

                            + "PhilHealth: ₱"
                            + String.format("%.2f", philhealth) + "\n"

                            + "Pag-IBIG: ₱"
                            + String.format("%.2f", pagibig) + "\n"

                            + "Tax: ₱"
                            + String.format("%.2f", tax) + "\n\n"

                            + "Total Deductions: ₱"
                            + String.format("%.2f", deductions)
                            + "\n\n"

                            + "NET PAY: ₱"
                            + String.format("%.2f", netPay)
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            "Payroll Computed Successfully!"
                    );

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter valid salary input.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // CLEAR BUTTON ACTION
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                txtName.setText("");

                txtSalary.setText("");

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