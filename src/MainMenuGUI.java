import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuGUI extends JFrame {

    JButton btnCompute, btnClear, btnExit, btnSearch;

    public MainMenuGUI() {

        setTitle("MotorPH Payroll System");
        setSize(650, 400);
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

        // OPEN BUTTON
        JButton btnOpen = new JButton("Open Payroll System");

        btnOpen.setBounds(220, 120, 200, 40);

        panel.add(btnOpen);

        // SEARCH BUTTON
        btnSearch = new JButton("Search Employee");

        btnSearch.setBounds(220, 180, 200, 40);

        btnSearch.setBackground(new Color(0, 153, 76));
        btnSearch.setForeground(Color.BLACK);

        panel.add(btnSearch);

        // EXIT BUTTON
        btnExit = new JButton("Exit");

        btnExit.setBounds(220, 240, 200, 40);

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

                switch(empID) {

                    case "1001":

                        JOptionPane.showMessageDialog(
                                null,
                                "EMPLOYEE FOUND\n\n"
                                + "Employee ID: 1001\n"
                                + "Name: Glen Romero\n"
                                + "Department: IT\n"
                                + "Employee Level: Tier 3\n"
                                + "Salary: ₱50,000\n"
                                + "Birthday: Feb 17\n"
                                + "Work Set-up: WFH\n"
                                + "Location: Manila\n",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        break;

                    case "1002":

                        JOptionPane.showMessageDialog(
                                null,
                                "EMPLOYEE FOUND\n\n"
                                + "Employee ID: 1002\n"
                                + "Name: Raymond Ballaran\n"
                                + "Department: HR\n"
                                + "Employee Level: Tier 2\n"
                                + "Salary: ₱45,000\n"
                                + "Birthday: Feb 17\n"
                                + "Work Set-up: WFH\n"
                                + "Location: Pampanga\n",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        break;

                    case "1003":

                        JOptionPane.showMessageDialog(
                                null,
                                "EMPLOYEE FOUND\n\n"
                                + "Employee ID: 1003\n"
                                + "Name: Ainy Julienne Manuel\n"
                                + "Department: Finance\n"
                                + "Employee Level: Tier 2\n"
                                + "Salary: ₱42,000\n"
                                + "Birthday: Feb 17\n"
                                + "Work Set-up: WFH\n"
                                + "Location: Laguna\n",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        break;
                        
                    case "1004":

                        JOptionPane.showMessageDialog(
                                null,
                                "EMPLOYEE FOUND\n\n"
                                + "Employee ID: 1004\n"
                                + "Name: Dianna Cathlene De Leon\n"
                                + "Department: Finance\n"
                                + "Employee Level: Tier 1\n"
                                + "Salary: ₱30,000\n"
                                + "Birthday: Feb 17\n"
                                + "Work Set-up: WFH\n"
                                + "Location: Manila\n",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                        );
                        
                        break;
                        
                    case "1005":

                        JOptionPane.showMessageDialog(
                                null,
                                "EMPLOYEE FOUND\n\n"
                                + "Employee ID: 1005\n"
                                + "Name: Dianna Nikko Pangilinan\n"
                                + "Department: Finance\n"
                                + "Employee Level: Tier 1\n"
                                + "Salary: ₱30,000\n"
                                + "Birthday: Feb 17\n"
                                + "Work Set-up: WFH\n"
                                + "Location: Bulacan\n",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                        );

                    default:

                        JOptionPane.showMessageDialog(
                                null,
                                "Employee not found.",
                                "Employee Information",
                                JOptionPane.PLAIN_MESSAGE
                                
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