package by.jazztime.algoritm.controller.towords;

import by.jazztime.algoritm.model.Data;
import by.jazztime.algoritm.model.Words;

import java.util.Map;

/**
 * Created by vova on 7.10.16.
 */
public class ThirdDigitOfClass extends Word {
    private final Map<Integer, String> numberHanded;

    public ThirdDigitOfClass(Data words) {
        this.numberHanded = words.getNumberHanded();
    }

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        // 0, 1..9
        return (number == 0) ? "" : numberHanded.get(number - 1);
    }
}
