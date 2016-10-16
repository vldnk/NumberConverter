package by.jazztime.algoritm.controller;

import by.jazztime.algoritm.controller.towords.*;
import by.jazztime.algoritm.model.Data;
import by.jazztime.algoritm.model.Words;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by vova on 16.10.16.
 */
public class ConverterNumberAlgoritms {
    private final Words wordsData;

    public ConverterNumberAlgoritms(Words words) {
        this.wordsData = words;
    }

    public void toWords(String numberString) {
        final int sizeNumerals = numberString.length();
        List<Integer> intList = IntStream.range(0, sizeNumerals)
                .map(index -> Integer.valueOf(String.valueOf(numberString.charAt(index))))
                .collect(LinkedList::new, List::add, List::addAll);
        Collections.reverse(intList);
        wordsData.setWordsList(IntStream.range(0, sizeNumerals)
                .mapToObj(index -> {
                    Word word = getClassWord(wordsData, index);
                    word.setNumber(intList.get(index));
                    return word;
                }).collect(LinkedList::new, List::add, List::addAll));
        final List<Word> words = wordsData.getWordsList();
        IntStream.range(0, words.size())
                .filter(i -> i % 3 == 0)
                .forEach(i -> ((FirstDigitOfClass) words.get(i)).setWordList(words));
        int indexNumberOfClass = -1;
        int indexClassValue;
        synchronized (ClassNumber.THE_NUMBER_OF_CLASS_VALUE) {
            indexClassValue = ClassNumber.THE_NUMBER_OF_CLASS_VALUE;
        }
        final int stepForGetIndexNextClass = indexClassValue + 1;
        while (indexClassValue < words.size()) {
            words.add(indexClassValue, new ClassNumber(wordsData, ++indexNumberOfClass));
            indexClassValue += stepForGetIndexNextClass;
        }
        IntStream.range(0, words.size()).forEach(index -> words.get(index).setIndex(index));

        final String SEPARATOR = " ";
        Iterator<Word> it = words.iterator();
        String result = "";
        String buffer;
        while (it.hasNext()) {
            buffer = it.next().toString();
            result = buffer + ("".equals(buffer) ? result : SEPARATOR + result);
        }
        words.clear();
        wordsData.setNumberSecond(result.trim());
    }

    @Nullable
    private Word getClassWord(Data data, int index) {
        switch (index % 3) {
            case 0:
                return new FirstDigitOfClass(data);
            case 1:
                return new SecondDigitOfClass(data);
            case 2:
                return new ThirdDigitOfClass(data);
            default:
                return null;
        }
    }
}
