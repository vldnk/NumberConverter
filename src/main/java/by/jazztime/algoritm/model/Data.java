package by.jazztime.algoritm.model;

import by.jazztime.algoritm.controller.towords.Word;

import java.util.List;
import java.util.Map;

/**
 * Created by vova on 16.10.16.
 */
public interface Data {
    List<Word> getWordsList();

    Map<Integer, String> getNumberFirstDigit();

    Map<Integer, String> getNumberCombinate();

    Map<Integer, String> getNumberSecondDigit();

    Map<Integer, String> getNumberHanded();

    Map<Integer, String> getOtherWords();

    Map<Integer, String> getNumberOfClass();
}
