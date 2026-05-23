import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PayrollGUI extends JFrame {

    JLabel lblName, lblSalary;
    JTextField txtName, txtSalary;
    JButton btnCompute, btnClear;
    JTextArea txtResult;

    public PayrollGUI() {

        setTitle("Employee Payroll System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // PANEL
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // LABELS
        lblName = new JLabel("Employee Name:");
        lblName.setBounds(30, 30, 120, 25);
        panel.add(lblName);

        lblSalary = new JLabel("Monthly Salary:");
        lblSalary.setBounds(30, 70, 120, 25);
        panel.add(lblSalary);

        // TEXTFIELDS
        txtName = new JTextField();
        txtName.setBounds(160, 30, 200, 25);
        panel.add(txtName);

        txtSalary = new JTextField();
        txtSalary.setBounds(160, 70, 200, 25);
        panel.add(txtSalary);

        // BUTTONS
        btnCompute = new JButton("Compute Payroll");
        btnCompute.setBounds(30, 120, 160, 35);
        panel.add(btnCompute);

        btnClear = new JButton("Clear");
        btnClear.setBounds(210, 120, 100, 35);
        panel.add(btnClear);

        // TEXT AREA
        txtResult = new JTextArea();
        txtResult.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBounds(30, 180, 420, 240);
        panel.add(scrollPane);

        // BUTTON ACTION
        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String name = txtName.getText();
                    double salary = Double.parseDouble(txtSalary.getText());

                    double hourlyRate = salary / 168;

                    double sss = compSSS(salary);
                    double philhealth = compPhil(salary);
                    double pagibig = 200;
                    double tax = compTax(salary);

                    double deductions = sss + philhealth + pagibig + tax;
                    double netPay = salary - deductions;

                    txtResult.setText(
                            "========== PAYROLL SUMMARY ==========\n\n"
                            + "Employee Name: " + name + "\n"
                            + "Monthly Salary: ₱" + salary + "\n"
                            + "Hourly Rate: ₱" + String.format("%.2f", hourlyRate) + "\n\n"
                            + "------------- DEDUCTIONS -------------\n"
                            + "SSS: ₱" + String.format("%.2f", sss) + "\n"
                            + "PhilHealth: ₱" + String.format("%.2f", philhealth) + "\n"
                            + "Pag-IBIG: ₱" + String.format("%.2f", pagibig) + "\n"
                            + "Tax: ₱" + String.format("%.2f", tax) + "\n\n"
                            + "Total Deductions: ₱" + String.format("%.2f", deductions) + "\n\n"
                            + "NET PAY: ₱" + String.format("%.2f", netPay)
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter valid salary input.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // CLEAR BUTTON
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtSalary.setText("");
                txtResult.setText("");
            }
        });

        add(panel);
    }

    // SSS COMPUTATION
    public double compSSS(double salary) {

        if (salary < 3250)
            return 135;
        else if (salary < 3750)
            return 157.50;
        else if (salary < 4250)
            return 180;
        else if (salary < 24750)
            return 1102.50;
        else
            return 1125;
    }

    // PHILHEALTH
    public double compPhil(double salary) {

        if (salary <= 10000)
            return 300;
        else if (salary < 60000)
            return salary * 0.03;
        else
            return 1800;
    }

    // TAX
    public double compTax(double salary) {

        double tax = 0;

        if (salary < 20833) {
            tax = 0;
        } else if (salary < 33333) {
            tax = (salary - 20833) * 0.20;
        } else if (salary < 66667) {
            tax = ((salary - 33333) * 0.25) + 2500;
        } else {
            tax = ((salary - 66667) * 0.30) + 10833;
        }

        return tax;
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
