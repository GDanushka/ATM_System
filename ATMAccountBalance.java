import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ATMAccountBalance extends JPanel {

    public ATMAccountBalance() {
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);

        JLabel LbHeader = new JLabel("Saving Account");
        LbHeader.setBounds(200, 50, 300, 90);
        LbHeader.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        LbHeader.setForeground(Color.WHITE);

        JLabel LbAcoBalance = new JLabel("Your Account Balance : Rs.  ");
        LbAcoBalance.setBounds(120, 150, 800, 20);
        LbAcoBalance.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JLabel LbWithdraw = new JLabel("Withdrawable Balance : Rs.  ");
        LbWithdraw.setBounds(120, 180, 800, 20);
        LbWithdraw.setFont(new Font("Abril Fatface", Font.BOLD, 20));


        JButton BackBut = new JButton("BACK");
        BackBut.setBounds(250, 300, 100, 30);
        BackBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        BackBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                AtmDashboard atmDashboard = new AtmDashboard();
                atmDashboard.setSize(getSize());
                add(atmDashboard);
                repaint();
                revalidate();
            }
        });

        try {
            try (BufferedReader brAccountBalance = new BufferedReader(new FileReader("DepositAmount.txt"))) {
                double accountBalance = Double.parseDouble(brAccountBalance.readLine());
                LbAcoBalance.setText(LbAcoBalance.getText() + accountBalance);
                LbWithdraw.setText(LbWithdraw.getText() + (accountBalance - 500));
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ATMAccountBalance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ATMAccountBalance.class.getName()).log(Level.SEVERE, null, ex);
        }

        add(LbHeader);
        add(LbWithdraw);
        add(LbAcoBalance);
        add(BackBut);
    }
}
