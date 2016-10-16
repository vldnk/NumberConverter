package by.jazztime.algoritm.view;

import by.jazztime.algoritm.controller.ConverterNumberController;
import by.jazztime.algoritm.model.CodeWarning;
import by.jazztime.algoritm.model.ConverterNumberObserver;
import by.jazztime.algoritm.model.Words;
import by.jazztime.algoritm.model.WordsGet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by vova on 10.10.16.
 */
public class ConverterNumberFrame implements Observer {
    private final WordsGet words;
    private final ConverterNumberController converter;
    private final JFrame frame = new JFrame("Конвертер чисел");
    private final JTextField textIn = new JTextField(44);
    private final JTextArea textLog = new JTextArea(20, 40);

    public ConverterNumberFrame(WordsGet words, ConverterNumberController converter) {
        this.words = words;
        this.converter = converter;
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
        panelButton.setBackground(exampleRed);
        panelButton.setLayout(new FlowLayout());

        clearButton.addActionListener(e -> converter.clearLog());
        saveButton.addActionListener(e -> converter.saveData(textIn.getText()));

        panelButton.add(saveButton);
        panelButton.add(clearButton);

        textIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    converter.saveData(textIn.getText());
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

    private boolean messageWarning(CodeWarning code) {
        switch (code) {
            case NUMBER_NOT:
                JOptionPane.showMessageDialog(frame, "Ошибка. Введите число");
                break;
            case NUMBER_MAX:
                JOptionPane.showMessageDialog(frame, "Ошибка. Слишком большое число");
                break;
            case NUMBER_TEXT:
                JOptionPane.showMessageDialog(frame, "Ошибка. Используйте символы от 0 до 9");
                break;
            default:
                return false;
        }
        return true;
    }

    private void centeringFrame(int sizeWidth, int sizeHeight) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        final int X = (s.width - sizeWidth) / 2;
        final int Y = (s.height - sizeHeight) / 2;
        frame.setBounds(X, Y, sizeWidth, sizeHeight);
    }

    @Override
    public void update(Observable o, Object arg) {
        ConverterNumberObserver argModel = (ConverterNumberObserver) arg;
        switch (argModel) {
            case SAVE_NUMBER_FIRST:
                if (converter.isCorrect(words.getNumberFirst()))
                    converter.toWords(words.getNumberFirst());
                break;
            case SAVE_NUMBER_SECOND:
                converter.saveLog(
                        words.getLog(),
                        words.getNumberFirst(),
                        words.getNumberSecond());
                break;
            case SAVE_LOG:
                textLog.setText(words.getLog());
                break;
            case WARNING:
                messageWarning(words.getCodeError());
                break;
            default:
                break;
        }
    }

    public static void main(String[] str) {
        Words words = new Words();
        ConverterNumberController converter = new ConverterNumberController(words);
        converter.createView(words);
    }
}
