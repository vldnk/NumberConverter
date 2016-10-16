package by.jazztime.algoritm.controller.towords;

import by.jazztime.algoritm.model.Words;

import java.util.List;
import java.util.Map;

/**
 * Created by vova on 7.10.16.
 */
public class ClassNumber extends Word {
    public final static Integer THE_NUMBER_OF_CLASS_VALUE = 3;

    private final int ONE_THOUSAND = 0;
    private final List<Word> wordList;
    private final Map<Integer, String> numberOfClass;
    private final Map<Integer, String> ok;

    public ClassNumber(Words words, int number) {
        this.numberOfClass = words.getNumberOfClass();
        this.wordList = words.getWordsList();
        this.ok = words.getOtherWords();
        setNumber(number);
    }

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        final int sizeWords = wordList.size();
        // 0 YYY
        if (wordList.get(index + 1).getNumber() == 0) {
            // X0 YYY
            if (index + 2 < sizeWords) {
                // 00 YYY
                if (wordList.get(index + 2).getNumber() == 0) {
                    // X00 YYY
                    if (index + 3 < sizeWords) {
                        // 000 YYY
                        if (wordList.get(index + 3).getNumber() == 0)
                            return "";
                    }
                    // 00 YYY
                    else
                        return "";
                }
                // 0 YYY
            } else
                return "";
        }
        // для тысяч
        if (number == ONE_THOUSAND) {
            // X YYY
            Word first = wordList.get(index + 1);
            // XX YYY
            if (index + 2 < sizeWords) {
                Word second = wordList.get(index + 2);
                // 1X YYY
                if (second.getNumber() == 1)
                    return numberOfClass.get(ONE_THOUSAND);
            }
            switch (first.getNumber()) {
                // 0 YYY
                case 0:
                    return numberOfClass.get(ONE_THOUSAND);
                // 1 YYY
                case 1:
                    return numberOfClass.get(ONE_THOUSAND) + ok.get(3);
                case 2:
                case 3:
                case 4:
                    return numberOfClass.get(ONE_THOUSAND) + "и";
                default:
                    return numberOfClass.get(ONE_THOUSAND);
            }
            // миллионы, и т.д.
        } else {
            Word first = wordList.get(index + 1);
            if (index + 2 < sizeWords) {
                Word second = wordList.get(index + 2);
                if (second.getNumber() == 1)
                    return numberOfClass.get(number) + "ов";
            }
            switch (first.getNumber()) {
                case 0:
                    return numberOfClass.get(number) + "ов";
                case 1:
                    return numberOfClass.get(number);
                case 2:
                case 3:
                case 4:
                    return numberOfClass.get(number) + "а";
                default:
                    return numberOfClass.get(number) + "ов";
            }
        }
    }
}
