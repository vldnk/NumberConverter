package by.jazztime.algoritm.converternumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 7.10.16.
 */
public class ClassNumber extends Word {
    public static final int THE_NUMBER_OF_CLASS_VALUE = 3;
    public static final int ONE_THOUSAND = 0;
    public static final List<String> NUMBER_OF_CLASS = new ArrayList<>();


    public ClassNumber(int number) {
        setNumber(number);
    }

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        List<Word> wordList = ConvertNumberToString.WORDS;
        // 0 YYY
        if (wordList.get(index + 1).getNumber() == 0) {
            // X0 YYY
            if (index + 2 < ConvertNumberToString.SIZE) {
                // 00 YYY
                if (wordList.get(index + 2).getNumber() == 0) {
                    // X00 YYY
                    if (index + 3 < ConvertNumberToString.SIZE) {
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
            Word first = ConvertNumberToString.WORDS.get(index + 1);
            // XX YYY
            if (index + 2 < ConvertNumberToString.SIZE) {
                Word second = ConvertNumberToString.WORDS.get(index + 2);
                // 1X YYY
                if (second.getNumber() == 1)
                    return NUMBER_OF_CLASS.get(ONE_THOUSAND);
            }
            switch (first.getNumber()) {
                // 0 YYY
                case 0:
                    return NUMBER_OF_CLASS.get(ONE_THOUSAND);
                // 1 YYY
                case 1:
                    return NUMBER_OF_CLASS.get(ONE_THOUSAND) + ConvertNumberToString.OK.get(3);
                case 2:
                case 3:
                case 4:
                    return NUMBER_OF_CLASS.get(ONE_THOUSAND) + "и";
                default:
                    return NUMBER_OF_CLASS.get(ONE_THOUSAND);
            }
        // миллионы, и т.д.
        } else {
            Word first = ConvertNumberToString.WORDS.get(index + 1);
            if (index + 2 < ConvertNumberToString.SIZE) {
                Word second = ConvertNumberToString.WORDS.get(index + 2);
                if (second.getNumber() == 1)
                    return NUMBER_OF_CLASS.get(number) + "ов";
            }
            switch (first.getNumber()) {
                case 0:
                    return NUMBER_OF_CLASS.get(number) + "ов";
                case 1:
                    return NUMBER_OF_CLASS.get(number);
                case 2:
                case 3:
                case 4:
                    return NUMBER_OF_CLASS.get(number) + "а";
                default:
                    return NUMBER_OF_CLASS.get(number) + "ов";
            }
        }
    }
}
