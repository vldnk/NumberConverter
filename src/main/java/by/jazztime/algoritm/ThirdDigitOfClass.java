package by.jazztime.algoritm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 7.10.16.
 */
public class ThirdDigitOfClass extends Word {
    public static final List<String> NUMBER_HANDED = new ArrayList<>();

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        // 0, 1..9
        return (number == 0) ? "" : NUMBER_HANDED.get(number - 1);
    }
}
