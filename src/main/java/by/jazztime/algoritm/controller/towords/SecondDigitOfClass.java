package by.jazztime.algoritm.controller.towords;

import by.jazztime.algoritm.model.Data;
import by.jazztime.algoritm.model.Words;

import java.util.Map;

/**
 * Created by vova on 7.10.16.
 */
public class SecondDigitOfClass extends Word {
    private final Map<Integer, String> numberSecondDigit;

    public SecondDigitOfClass(Data words) {
        this.numberSecondDigit = words.getNumberSecondDigit();
    }

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        // 0[Y], 1[Y];  2[Y], ... 9[Y]
        return (number == 0 || number == 1) ? "" : numberSecondDigit.get(number - 2);
    }
}
