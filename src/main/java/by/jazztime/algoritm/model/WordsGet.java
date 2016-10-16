package by.jazztime.algoritm.model;

import java.util.Map;

/**
 * Created by vova on 16.10.16.
 */
public interface WordsGet extends Data {
    String getNumberFirst();

    String getNumberSecond();

    String getLog();

    CodeWarning getCodeError();

    Map<Integer, String> getMap(Integer index);
}
