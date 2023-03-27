import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATMWithdraw extends JPanel {

    public ATMWithdraw() {
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);

        JLabel LbHeader = new JLabel("Make A Withdraw");
        LbHeader.setBounds(200, 30, 350, 50);
        LbHeader.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        LbHeader.setForeground(Color.WHITE);

        JLabel LbAmount = new JLabel("Enter the amount : Rs.");
        LbAmount.setBounds(50, 100, 300, 50);
        LbAmount.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JTextField AmountTxt = new JTextField(8);
        AmountTxt.setBounds(310, 112, 180, 25);
        AmountTxt.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JButton BackspaceBut = new JButton('\u232b' + "");
        BackspaceBut.setBounds(480, 112, 70, 25);
        BackspaceBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JLabel LbNote = new JLabel("Note:- The amount must be in multiples of a hundred");
        LbNote.setBounds(15, 150, 640, 20);
        LbNote.setFont(new Font("Serif", Font.BOLD, 20));
        LbNote.setForeground(new Color(204,255,0));

        JLabel LbNote1 = new JLabel("Note:- Withdrawable amount less then Rs.500 in account balance");
        LbNote1.setBounds(10, 180, 780, 20);
        LbNote1.setFont(new Font("Serif", Font.BOLD, 20));
        LbNote1.setForeground(new Color(204,255,0));

        JButton CancelBut = new JButton("Cancel");
        CancelBut.setBounds(100, 250, 140, 40);
        CancelBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JButton WithdrawBut = new JButton("Withdraw");
        WithdrawBut.setBounds(400, 250, 148, 40);
        WithdrawBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        CancelBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                AtmDashboard atmDashboard = new AtmDashboard();
                atmDashboard.setSize(getSize());
                add(atmDashboard);
                repaint();
                revalidate();
            }
        });

        WithdrawBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (AmountTxt.getText().isEmpty() != true) {
                    int withdrawAmount = Integer.parseInt(AmountTxt.getText());
                    if (validateAmount(withdrawAmount)) {
                        try {
                            try (BufferedReader brDeposite = new BufferedReader(new FileReader("DepositAmount.txt"))) {
                                String accountOldBalance;
                                if ((accountOldBalance = brDeposite.readLine()) != null) {
                                    int accountBalance = Integer.parseInt(accountOldBalance);
                                    if ((accountBalance - 500) > withdrawAmount) {
                                        int accountNewBalance = accountBalance - withdrawAmount;
                                        PrintWriter writer = new PrintWriter("DepositAmount.txt");
                                        writer.println(accountNewBalance);
                                        writer.close();
                                        AmountTxt.setText("");
                                        JOptionPane.showMessageDialog(null,
                                                "Withdraw successful !\nWithdraw Amount" + withdrawAmount
                                                        + " \n ###You will recive### \n" + cash(withdrawAmount));
                                        removeAll();
                                        AtmDashboard atmDashboard = new AtmDashboard();
                                        atmDashboard.setSize(getSize());
                                        add(atmDashboard);
                                        repaint();
                                        revalidate();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                                    }
                                }
                            } catch (NumberFormatException | HeadlessException e1) {

                                e1.printStackTrace();
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ATMDeposit.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ATMDeposit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your entered amount isn't a multiple of a hundred");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Amount!\n Please Try Again");
                }
            }
        });

        AmountTxt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (AmountTxt.getText().trim().length() == 8
                        || ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        BackspaceBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String l = AmountTxt.getText();
                if (l.length() > 0) {
                    l = l.substring(0, l.length() - 1);
                }
                AmountTxt.setText(l);
            }
        });
        add(LbNote);
        add(LbNote1);
        add(LbHeader);
        add(LbAmount);
        add(AmountTxt);
        add(WithdrawBut);
        add(BackspaceBut);
        add(CancelBut);

    }

    public String cash(int amount) {
        String text1 = "Rs.5000 notes : " + amount / 5000;
        amount %= 5000;
        String text2 = "Rs.1000 notes : " + amount / 1000;
        amount %= 1000;
        String text3 = "Rs.500 notes : " + amount / 500;
        amount %= 500;
        String text4 = "Rs.100 notes : " + amount / 100;
        amount %= 100;
        return text1 + "\n" + text2 + "\n" + text3 + "\n" + text4 ;
    }

    public Boolean validateAmount(int amount) {
        for (int i = 0; i < 2; i++) {
            if ((amount %= 100) != 0) {
                return false;
            }
        }
        return true;
    }
}
