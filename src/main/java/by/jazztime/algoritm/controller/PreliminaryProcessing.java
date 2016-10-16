package by.jazztime.algoritm.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by vova on 16.10.16.
 */
public class PreliminaryProcessing {
    private volatile static PreliminaryProcessing processing = null;

    private PreliminaryProcessing() {}

    public static PreliminaryProcessing getInstance() {
        if (processing == null) {
            synchronized (PreliminaryProcessing.class) {
                if (processing == null)
                    processing = new PreliminaryProcessing();
            }
        }
        return processing;
    }

    public boolean isNumber(String string) {
        final int lengthNumberInString = string.length();
        char ch;
        for (int index = 0; index < lengthNumberInString; index++) {
            ch = string.charAt(index);
            if (!(ch >= '0' && ch <= '9'))
                return false;
        }
        return true;
    }

    public void readDataXML(Function<Integer, Map<Integer, String>> maps) {
        DocumentBuilder db = null;
        Document doc = null;
        final String pathData = "src/main/resources/ClassOfNumbers.xml";
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(pathData);
            doc.getDocumentElement().normalize();
            Node wordnumber = doc.getDocumentElement();
            NodeList baseorders = wordnumber.getChildNodes();
            IntStream.range(0, baseorders.getLength()).forEach(index -> {
                Node baseorder = baseorders.item(index);
                NodeList nextelements = baseorder.getChildNodes();
                if (baseorder.getNodeType() != Node.TEXT_NODE) {
                    Integer indexM = Integer.valueOf(baseorder.getAttributes().item(0).getTextContent());
                    Map<Integer, String> mapEl = maps.apply(indexM);
                    Integer key = -1;
                    for (int indexItem = 0; indexItem < nextelements.getLength(); indexItem++) {
                        Node nextelement = nextelements.item(indexItem);
                        if (nextelement.getNodeType() != Node.TEXT_NODE)
                            mapEl.put(++key, nextelement.getTextContent());
                    }
                }
            });
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SAXException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ошибка. Файл данных " + pathData +
                            " не найден",
                    "Сообщение об ошибке",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
