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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATMDeposit extends JPanel {

    public ATMDeposit() {
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);

        JLabel LbHeader = new JLabel("Save for Future");
        LbHeader.setBounds(200, 30, 350, 50);
        LbHeader.setFont(new Font("Abril Fatface", Font.BOLD, 30));
        LbHeader.setForeground(Color.WHITE);

        JLabel LbAmount = new JLabel("Enter the amount : Rs.");
        LbAmount.setBounds(50, 100, 300, 50);
        LbAmount.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JTextField AmountTxt = new JTextField(10);
        AmountTxt.setBounds(310, 112, 180, 25);
        AmountTxt.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JLabel LbNote = new JLabel("Note: The amount must be in multiples of a hundred");
        LbNote.setBounds(15, 150, 640, 20);
        LbNote.setFont(new Font("Serif", Font.BOLD, 20));
        LbNote.setForeground(Color.RED);

        JButton DepositBut = new JButton("Deposit");
        DepositBut.setBounds(400, 250, 148, 40);
        DepositBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JButton BackspaceBut = new JButton('\u232b' + "");
        BackspaceBut.setBounds(480, 112, 70, 25);
        BackspaceBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        JButton CancelBut = new JButton("Cancel");
        CancelBut.setBounds(100, 250, 140, 40);
        CancelBut.setFont(new Font("Abril Fatface", Font.BOLD, 20));

        BackspaceBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String l = AmountTxt.getText();
                if (l.length() > 0) {
                    l = l.substring(0, l.length() - 1);
                }
                AmountTxt.setText(l);
            }
        });
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
        DepositBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (AmountTxt.getText().isEmpty() != true) {
                        int depositeAmount = Integer.parseInt(AmountTxt.getText());
                        try (BufferedReader brDeposite = new BufferedReader(new FileReader("DepositAmount.txt"))) {
                            String accountOldBalance;
                            if ((accountOldBalance = brDeposite.readLine()) != null) {
                                int accountNewBalance = Integer.parseInt(accountOldBalance) + depositeAmount;
                                PrintWriter writer = new PrintWriter("DepositAmount.txt");
                                writer.println(accountNewBalance);
                                writer.close();
                                AmountTxt.setText("");
                                JOptionPane.showMessageDialog(null, "Transaction successful !");
                            }
                        } catch (NumberFormatException | HeadlessException e1) {

                            e1.printStackTrace();
                        }
                        removeAll();
                        AtmDashboard atmDashboard = new AtmDashboard();
                        atmDashboard.setSize(getSize());
                        add(atmDashboard);
                        repaint();
                        revalidate();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Amount!\n Please Try Again");
                    }

                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {

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
        add(LbHeader);
        add(LbAmount);
        add(LbNote);
        add(AmountTxt);
        add(DepositBut);
        add(BackspaceBut);
        add(CancelBut);

    }
}