package by.jazztime.algoritm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by vova on 5.10.16.
 */
public final class ConvertNumberToString {
    public static final List<String> OK = new ArrayList<>(Arrays.asList("одна", "две", "и", "a", "ов"));
    public static final List<Word> WORDS = new LinkedList<>();
    public static int SIZE;
    private static boolean isRead = false;

    private static void setIndex(int index) {
        WORDS.get(index).setIndex(index);
    }

    private ConvertNumberToString() {}

    @NotNull
    public static String convert(long number) {
        if (!isRead) {
            try {
                readXML();
                isRead = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        String numberString = String.valueOf(number);
        final int sizeNumerals = numberString.length();
        List<Integer> intList = new LinkedList<>();
        IntStream.range(0, sizeNumerals).forEach(i ->
            intList.add(Integer.valueOf(String.valueOf(numberString.charAt(i))))
        );
        Collections.reverse(intList);
        IntStream.range(0, sizeNumerals).forEach(index -> {
            Word word = getClassWord(index);
            word.setNumber(intList.get(index));
            WORDS.add(word);
        });
        int indexNumberOfClass = -1;
        int indexClassValue = ClassNumber.THE_NUMBER_OF_CLASS_VALUE;
        while (indexClassValue < WORDS.size()) {
            WORDS.add(indexClassValue, new ClassNumber(++indexNumberOfClass));
            indexClassValue += (ClassNumber.THE_NUMBER_OF_CLASS_VALUE + 1);
        }
        SIZE = WORDS.size();
        IntStream.range(0, SIZE).forEach(ConvertNumberToString::setIndex);

        final String SEPARATOR = " ";
        Iterator<Word> it = WORDS.iterator();
        String result = "";
        String buffer;
        while (it.hasNext()) {
            buffer = it.next().toString();
            result = buffer + ("".equals(buffer) ? result : SEPARATOR + result);
        }
        WORDS.clear();
        return result.trim();
    }

    @Nullable
    private static Word getClassWord(int index) {
        switch (index % 3) {
            case 0:
                return new FirstDigitOfClass();
            case 1:
                return new SecondDigitOfClass();
            case 2:
                return new ThirdDigitOfClass();
            default:
                return null;
        }
    }

    private static boolean isNumber(String string) {
        final int lengthNumberInString = string.length();
        char ch;
        for (int index = 0; index < lengthNumberInString; index++) {
            ch = string.charAt(index);
            if (!(ch >= '0' && ch <= '9'))
                return false;
        }
        return true;
    }

    private static void readXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse("src/main/resources/ClassOfNumbers.xml");
        doc.getDocumentElement().normalize();
        Node wordnumber = doc.getDocumentElement();
        NodeList baseorders = wordnumber.getChildNodes();
        IntStream.range(0, baseorders.getLength()).forEach(index -> {
            Node baseorder = baseorders.item(index);
            NodeList nextelements = baseorder.getChildNodes();
            if (baseorder.getNodeType() != Node.TEXT_NODE) {
                int indexM = Integer.valueOf(baseorder.getAttributes().item(0).getTextContent()).intValue();
                List<String> listEl = getList(indexM);
                IntStream.range(0, nextelements.getLength()).forEach(indexE -> {
                    Node nextelement = nextelements.item(indexE);
                    if (nextelement.getNodeType() != Node.TEXT_NODE) {
                        listEl.add(nextelement.getTextContent());
                    }
                });
            }
        });
    }

    @org.jetbrains.annotations.Nullable
    @org.jetbrains.annotations.Contract(pure = true)
    private static List<String> getList(int index) {
        switch (index) {
            case 0:
                return ClassNumber.NUMBER_OF_CLASS;
            case 1:
                return FirstDigitOfClass.NUMBER_FIRST_DIGIT;
            case 2:
                return FirstDigitOfClass.NUMBER_COMBINATE;
            case 3:
                return SecondDigitOfClass.NUMBER_SECOND_DIGIT;
            case 4:
                return ThirdDigitOfClass.NUMBER_HANDED;
            default:
                return null;
        }
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        final String EXIT = "exit";
        Scanner scanner = new Scanner(System.in);
        String numberInString;
        long numberInLong;
        while (true) {
            System.out.print("Введите число: ");
            numberInString = scanner.next();
            if (numberInString.toLowerCase().equals(EXIT))
                break;
            try {
                numberInLong = Long.valueOf(numberInString).longValue();
                System.out.println(numberInString + ": "
                        + ConvertNumberToString.convert(numberInLong));
            } catch (NumberFormatException e) {
                System.err.println(isNumber(numberInString)
                        ? "Ошибка. Слишком большое число для типа long"
                        : "Ошибка. Используйте символы от 0 до 9");
            }
        }
    }
}