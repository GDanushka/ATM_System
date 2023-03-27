import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.JPanel;

public class AtmLogIn {
    public AtmLogIn(String cardNo, String log_Pin) {

        JFrame loginf = new JFrame("ATM LOGIN");// frame title
        loginf.setSize(750, 400);// frame size
        loginf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of application

        JPanel loginp = new JPanel();
        loginp.setBackground(new Color(128, 128, 128));// change color of background
        loginp.setLayout(null);

        JLabel LbHeader = new JLabel("ACCOUNT LOGIN");
        LbHeader.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        LbHeader.setForeground(Color.WHITE);
        LbHeader.setBounds(180, 20, 300, 30);

        JLabel LbCardNo = new JLabel("Enter Your Card No :");
        LbCardNo.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        LbCardNo.setForeground(Color.WHITE);
        LbCardNo.setBounds(18, 100, 280, 30);

        JTextField TxCardNo = new JTextField(25);
        TxCardNo.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        TxCardNo.setBounds(260, 100, 240, 30);

        JLabel LbPin = new JLabel("Enter your PIN :");
        LbPin.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        LbPin.setForeground(Color.WHITE);
        LbPin.setBounds(68, 150, 200, 30);

        JPasswordField pin = new JPasswordField(4);
        pin.setFont(new Font("Abril Fatface", Font.BOLD, 16));
        pin.setBounds(260, 150, 60, 30);

        JButton LogInBut = new JButton("LOGIN");
        LogInBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        LogInBut.setBounds(400, 250, 120, 30);

        JButton ExitBut = new JButton("Exit");
        ExitBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));
        ExitBut.setBounds(200, 250, 100, 30);

        ExitBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        LogInBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = "";
                char[] pass = pin.getPassword();
                for (int i = 0; i < pass.length; i++) {
                    temp += pass[i];
                }
                String card = TxCardNo.getText();
                if (cardNo.compareTo(card) == 0 && log_Pin.compareTo(temp) == 0) {
                    loginp.removeAll();
                    AtmDashboard atmDashboard = new AtmDashboard();
                    atmDashboard.setSize(loginp.getSize());
                    loginf.setTitle("ATM");
                    loginp.add(atmDashboard);
                    loginp.repaint();
                    loginp.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN \n Please Try Again");
                }
            }
        });
        TxCardNo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (TxCardNo.getText().trim().length() == 16
                        || ((a < '0') || (a > '9')) && (a != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        loginp.add(LbCardNo);
        loginp.add(TxCardNo);
        loginp.add(LbHeader);
        loginp.add(LbPin);
        loginp.add(ExitBut);
        loginp.add(LogInBut);
        loginp.add(pin);
        loginf.add(loginp);
        loginf.setVisible(true);

    }

    public static void main(String[] args) {
        new AtmLogIn("1234567812345678", "1234");
    }
}
