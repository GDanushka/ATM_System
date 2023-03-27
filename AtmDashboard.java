import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AtmDashboard extends JPanel {

    public AtmDashboard() {
        this.setLayout(null);
        this.setBackground(new Color(128, 128, 128));
        JButton DepositBut = new JButton("Deposit");
        DepositBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        DepositBut.setBounds(200, 40, 200, 50);

        JButton WithdrawBut = new JButton("Withdraw");
        WithdrawBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        WithdrawBut.setBounds(200, 110, 200, 50);

        JButton AccBalanceBut = new JButton("Account Balance");
        AccBalanceBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        AccBalanceBut.setBounds(175, 200, 250, 50);

        JButton ExitBut = new JButton("EXIT");
        ExitBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        ExitBut.setBounds(250, 300, 100, 30);

        ExitBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        DepositBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                ATMDeposit atmDashboard = new ATMDeposit();
                atmDashboard.setSize(getSize());
                add(atmDashboard);
                repaint();
                revalidate();
            }
        });
        WithdrawBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                ATMWithdraw atmDashboard = new ATMWithdraw();
                atmDashboard.setSize(getSize());
                add(atmDashboard);
                repaint();
                revalidate();
            }
        });
        AccBalanceBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                ATMAccountBalance atmDashboard = new ATMAccountBalance();
                atmDashboard.setSize(getSize());
                add(atmDashboard);
                repaint();
                revalidate();
            }
        });
        add(AccBalanceBut);
        add(WithdrawBut);
        add(DepositBut);
        add(ExitBut);
    }
}
