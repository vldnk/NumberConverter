package by.jazztime.algoritm.run;

import by.jazztime.algoritm.converternumber.ConvertNumberToString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by vova on 10.10.16.
 */
public class ConverterNumberFrame {
    private final JFrame frame = new JFrame("Конвертер чисел");
    private final JTextField textIn = new JTextField(44);
    private final JTextArea textLog = new JTextArea(20, 40);
    private String log = "";

    public ConverterNumberFrame() {
        Color exampleRed = new Color(180, 100, 20);
        Color base = new Color(200, 200, 200);
        textIn.setBackground(exampleRed);
        textLog.setBackground(base);
        Container panelFrame = frame.getContentPane();
        panelFrame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton saveButton = new JButton("Сохранить");
        JButton clearButton = new JButton("Очистить");
        saveButton.setBackground(base);
        clearButton.setBackground(base);

        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(180, 100, 20));
        panelButton.setLayout(new FlowLayout());

        clearButton.addActionListener(e -> {
            log = "";
            textLog.setText(log);
        });
        saveButton.addActionListener(e -> saveLog());

        panelButton.add(saveButton);
        panelButton.add(clearButton);

        textIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    saveLog();
            }
        });
        textLog.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textLog);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelFrame.add(textIn, BorderLayout.NORTH);
        panelFrame.add(scrollPane, BorderLayout.CENTER);
        panelFrame.add(panelButton, BorderLayout.SOUTH);

        frame.pack();
        centeringFrame(frame.getWidth(), frame.getHeight());
        frame.setVisible(true);
    }

    private void saveLog() {
        String numberInString = textIn.getText();
        long numberInLong;
        try {
            numberInLong = Long.valueOf(numberInString).longValue();
            log = (numberInString + ": "
                    + ConvertNumberToString.convert(numberInLong)) + "\n" + log;
            textLog.setText(log);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    ConvertNumberToString.isNumber(numberInString)
                            ? "Ошибка. Слишком большое число для типа long"
                            : "Ошибка. Используйте символы от 0 до 9");
        }
    }

    private void centeringFrame(int sizeWidth, int sizeHeight) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }

    public static void main(String[] str) {
        new ConverterNumberFrame();
    }
}
