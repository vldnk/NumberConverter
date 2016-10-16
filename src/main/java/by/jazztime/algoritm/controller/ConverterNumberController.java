package by.jazztime.algoritm.controller;

import by.jazztime.algoritm.model.CodeWarning;
import by.jazztime.algoritm.model.Words;
import by.jazztime.algoritm.model.WordsSet;
import by.jazztime.algoritm.view.ConverterNumberFrame;

import java.util.Observer;

/**
 * Created by vova on 5.10.16.
 */
public class ConverterNumberController {
    private final WordsSet wordsData;
    private final ConverterNumberAlgoritms converterNumberAlgoritms;

    public ConverterNumberController(Words wordsData) {
        this.wordsData = wordsData;
        PreliminaryProcessing.getInstance().readDataXML(wordsData::getMap);
        this.converterNumberAlgoritms = new ConverterNumberAlgoritms(wordsData);
    }

    public void createView(Words wordsData) {
        Observer observerView = new ConverterNumberFrame(wordsData, this);
        wordsData.addObserver(observerView);
    }

    public void toWords(String number) {
        converterNumberAlgoritms.toWords(number);
    }

    public void saveLog(String log, String first, String second) {
        wordsData.setLog(first + ": " + second + "\n" + log);
    }

    public void saveData(String text) {
        wordsData.setNumberFirst(text);
    }

    public void clearLog() {
        wordsData.setLog("");
    }

    public boolean isCorrect(String numberInString) {
        if ("".equals(numberInString))
            wordsData.setCodeError(CodeWarning.NUMBER_NOT);
        else if (numberInString.length() > 66)
            wordsData.setCodeError(CodeWarning.NUMBER_MAX);
        else if (!PreliminaryProcessing.getInstance().isNumber(numberInString))
            wordsData.setCodeError(CodeWarning.NUMBER_TEXT);
        else
            return true;
        return false;
    }
}