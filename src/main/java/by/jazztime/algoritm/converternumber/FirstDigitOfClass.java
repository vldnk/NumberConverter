package by.jazztime.algoritm.converternumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 6.10.16.
 */
public class FirstDigitOfClass extends Word {
    public static final List<String> NUMBER_FIRST_DIGIT = new ArrayList<>();
    public static final List<String> NUMBER_COMBINATE = new ArrayList<>();

    // X - следующий, Y - предыдущий
    // [] - число может быть, а может и не быть
    @Override
    public String toString() {
        if (index + 1 < ConvertNumberToString.SIZE) {
            Word second = ConvertNumberToString.WORDS.get(index + 1);
            // X0
            if (number == 0) {
                // 10
                if (second.getNumber() == 1)
                    return NUMBER_COMBINATE.get(0);
                    // 20, 30, ..., 90, 00
                else
                    return "";
            }
            // X1, ..., X9
            else {
                // 01, ..., 09
                if (second.getNumber() == 0) {
                    // 01, 02
                    if (number == 1 || number == 2) {
                        // 01YYY, 02YYY
                        if (index > 0) {
                            if (ConvertNumberToString.WORDS.get(index - 1).getNumber() == 0)
                                return ConvertNumberToString.OK.get(number - 1);
                        }
                    }
                    // 01, ..., 09
                    return NUMBER_FIRST_DIGIT.get(number);

                }
                // 11, 12, ..., 19
                else if (second.getNumber() == 1)
                    return NUMBER_COMBINATE.get(number);
                // 21, ... 29, 31, ... 39, 91, ... 99
                else {
                    // 2..9 1, 2..9 2
                    if (number == 1 || number == 2) {
                        // [2..9] 1 YYY, [2..9] 2 YYY
                        if (index > 0) {
                            if (ConvertNumberToString.WORDS.get(index - 1).getNumber() == 0)
                                return ConvertNumberToString.OK.get(number - 1);
                        }
                    }
                    // 2..9 1..9 [YYY]
                    return NUMBER_FIRST_DIGIT.get(number);
                }
            }
        } else {
            // 1 [YYY], 2 [YYY]
            if (number == 1 || number == 2) {
                // 1YYY, 2YYY
                if (index > 0) {
                    if (ConvertNumberToString.WORDS.get(index - 1).getNumber() == 0)
                        return ConvertNumberToString.OK.get(number - 1);
                }
                // 1, 2
                return NUMBER_FIRST_DIGIT.get(number);
            }
            // 0, 3, ... 9
            else {
                // 3 [YYY], ... 9 [YYY]
                if (number != 0)
                    return NUMBER_FIRST_DIGIT.get(number);
                // 0; 0 YYY; 0
                return (index > 0) ? "" : "ноль";
            }
        }
    }
}
