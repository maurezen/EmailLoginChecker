package com.maurezen.loginchecker.ui;

import com.maurezen.loginchecker.email.Email;
import com.maurezen.loginchecker.email.EmailService;

import javax.mail.Session;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 *
 */
public class MailerFrame extends JFrame {

    public MailerFrame() {
        super("Mailer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(50, 50, 640, 480);
        setMinimumSize(new Dimension(920, 620));
        setPreferredSize(new Dimension(920, 620));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        final JTextArea toBeTested = new JTextArea(30, 40);
        JScrollPane toBeTestedScroll = new JScrollPane(toBeTested, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        toBeTested.setText("Paste credentials here");
        toBeTested.setEditable(true);
        toBeTested.setToolTipText("Credential list");

        final JTextArea good = new JTextArea(30, 40);
        JScrollPane goodScroll = new JScrollPane(good, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        good.setText("Good credentials");
        good.setEditable(true);
        good.setToolTipText("Good credentials will be displayed here");

        final JTextField timeField = new JTextField("1000", 7);
        timeField.setToolTipText("Check interval (ms)");

        final JButton sendButton = new JButton("Check");
        sendButton.addActionListener(
            e -> {
                sendButton.setEnabled(false);

                good.setText(checkCredentials(toBeTested.getText(), 1000));

                sendButton.setEnabled(true);
            }
        );

        contentPane.setLayout(new BorderLayout());
        JPanel pane = new JPanel(new BorderLayout());
        pane.add(toBeTestedScroll, BorderLayout.WEST);
        pane.add(goodScroll, BorderLayout.EAST);
        contentPane.add(pane, BorderLayout.CENTER);
        contentPane.add(sendButton, BorderLayout.SOUTH);

        pack();
    }

    private String checkCredentials(String text, int i) {
        Scanner scanner = new Scanner(text);
        scanner.useDelimiter("(,|\\Z|\\t|\\n|\\r| )");

        StringBuilder good = new StringBuilder();

        while (scanner.hasNext()) {
            String credentials = scanner.next().trim();
            if (!credentials.isEmpty()) {
                System.out.println("Checking: " + credentials);
            }
            int index = credentials.indexOf('/');
            if (index > -1) {
                String login = credentials.substring(0, index);
                String username = login.substring(0, login.indexOf('@'));
                String password = credentials.substring(index + 1);

                Email email = EmailService.create(login);
                Session session = email.login(username, password);

                if (email.test(session)) {
                    good.append(credentials).append("\n");
                }
            }
        }

        return good.toString();
    }
}
