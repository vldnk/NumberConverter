package by.jazztime.algoritm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vova on 7.10.16.
 */
public class SecondDigitOfClass extends Word {
    public static final List<String> NUMBER_SECOND_DIGIT = new ArrayList<>();

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        // 0[Y], 1[Y];  2[Y], ... 9[Y]
        return (number == 0 || number == 1) ? "" : NUMBER_SECOND_DIGIT.get(number - 2);
    }
}
