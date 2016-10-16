package by.jazztime.algoritm.model;

import by.jazztime.algoritm.controller.towords.Word;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Created by vova on 15.10.16.
 */
public class Words extends Observable implements WordsGet, WordsSet {
    private String numberFirst;
    private String numberSecond;
    private List<Word> wordsList;
    private String log = "";
    private CodeWarning codeError;
    private final Map<Integer, String> numberFirstDigit = new HashMap<>();
    private final Map<Integer, String> numberCombinate = new HashMap<>();
    private final Map<Integer, String> numberSecondDigit = new HashMap<>();
    private final Map<Integer, String> numberHanded = new HashMap<>();
    private final Map<Integer, String> otherWords = new HashMap<>();
    private final Map<Integer, String> numberOfClass = new HashMap<>();

    public Words() {
        otherWords.put(0, "одна");
        otherWords.put(1, "две");
        otherWords.put(2, "и");
        otherWords.put(3, "a");
        otherWords.put(4, "ов");
    }

    @Override
    public void setNumberFirst(String numberFirst) {
        this.numberFirst = numberFirst;
        setChanged();
        notifyObservers(ConverterNumberObserver.SAVE_NUMBER_FIRST);
    }

    @Override
    public void setNumberSecond(String numberSecond) {
        this.numberSecond = numberSecond;
        setChanged();
        notifyObservers(ConverterNumberObserver.SAVE_NUMBER_SECOND);
    }

    @Override
    public String getNumberFirst() {
        return numberFirst;
    }

    @Override
    public String getNumberSecond() {
        return numberSecond;
    }

    public void setWordsList(List<Word> wordsList) {
        this.wordsList = wordsList;
    }

    @Override
    public List<Word> getWordsList() {
        return wordsList;
    }

    @Override
    public Map<Integer, String> getNumberFirstDigit() {
        return numberFirstDigit;
    }

    @Override
    public Map<Integer, String> getNumberCombinate() {
        return numberCombinate;
    }

    @Override
    public Map<Integer, String> getNumberSecondDigit() {
        return numberSecondDigit;
    }

    @Override
    public Map<Integer, String> getNumberHanded() {
        return numberHanded;
    }

    @Override
    public Map<Integer, String> getOtherWords() {
        return otherWords;
    }

    @Override
    public Map<Integer, String> getNumberOfClass() {
        return numberOfClass;
    }

    @Override
    public void setLog(String log) {
        this.log = log;
        setChanged();
        notifyObservers(ConverterNumberObserver.SAVE_LOG);
    }

    @Override
    public String getLog() {
        return log;
    }

    @Override
    public void setCodeError(CodeWarning codeError) {
        this.codeError = codeError;
        setChanged();
        notifyObservers(ConverterNumberObserver.WARNING);
    }

    @Override
    public CodeWarning getCodeError() {
        return codeError;
    }

    @Override
    @Nullable
    @Contract(pure = true)
    public Map<Integer, String> getMap(Integer index) {
        switch (index) {
            case 0:
                return numberOfClass;
            case 1:
                return numberFirstDigit;
            case 2:
                return numberCombinate;
            case 3:
                return numberSecondDigit;
            case 4:
                return numberHanded;
            default:
                return null;
        }
    }
}