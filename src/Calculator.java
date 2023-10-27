import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// \b(X{1}|IX{1}|VIII{1}|VII{1}|VI{1}|V{1}|IV{1}|I{1,3})\b
// \s*(\d{1,2}|\D{1,2})\s*[\-\+\*\/]\s*(\d{1,2}|\D{1,2})\s*
public class Calculator extends ConvertorArabicRoman {
    public static void main(String[] args) throws ScannerExceptions {
//        String operator;
        String firstOperand;
        String secondOperand;

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\s*(\\w+)\\s*[\\-\\+\\*\\/]\\s*(\\w+)\\s*"); //обработка исключения, если введен один операнд или больше двух
        Matcher matcher = pattern.matcher(inputLine);
        if (!(matcher.matches())) {
            throw new ScannerExceptions("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

//        if (inputLine.contains("+"))
//            operator = "+";
//        else if (inputLine.contains("-"))
//            operator = "-";
//        else if (inputLine.contains("*"))
//            operator = "*";
//        else
//            operator = "/";


        firstOperand = (inputLine.substring(0, inputLine.indexOf(findOperator(inputLine))).trim()); // ищем с строке первый операнд
        secondOperand = (inputLine.substring(inputLine.indexOf(findOperator(inputLine)) + 1).trim()); //ищем второй операнд
//        System.out.println(firstOperand);
//        System.out.println(secondOperand);
//        System.out.println(validCorrectNumber(firstOperand));
//        System.out.println(validCorrectNumber(secondOperand));
        // Валидируем операнды на правильность написания, сравниваем между собой римские и арабские цифры
        if (validCorrectNumber(firstOperand).equals("ARABIC")&&validCorrectNumber(secondOperand).equals("ARABIC")){
            System.out.println(calculator((Integer.parseInt(firstOperand)),(Integer.parseInt(secondOperand)),findOperator(inputLine)));
        }
        else if (validCorrectNumber(firstOperand).equals("ROMAN")&&validCorrectNumber(secondOperand).equals("ROMAN")){
            System.out.println(convertToRoman(calculator(convertToArabic(firstOperand),convertToArabic(secondOperand),findOperator(inputLine))));
        }
        else throw new ScannerExceptions("Одновременно производится математическое действие с римским и с арабским числами");

    }
    public static String findOperator(String inputLine){ //поиск одного из 4х операторов в строке
        String operator;
        if (inputLine.contains("+"))
            operator = "+";
        else if (inputLine.contains("-"))
            operator = "-";
        else if (inputLine.contains("*"))
            operator = "*";
        else
            operator = "/";
        return operator;
    }

    public static String validCorrectNumber(String operand) throws ScannerExceptions { // метод валидации операнда (соглавно условиям задачи)
        Pattern arabic = Pattern.compile("\\b([1-9]|10)\\b");
        Matcher matcher = arabic.matcher(operand);
        Pattern roman = Pattern.compile("\\b(X|IX|VIII|VII|VI|V|IV|I{1,3})\\b");
        Matcher matcher1 = roman.matcher(operand);
        if (matcher.matches())
            operand = "ARABIC";

        else if (matcher1.matches())
            operand = "ROMAN";

        else throw new ScannerExceptions("Один из операндов либо не является числом, либо числом вне диапазона (1-10,I-X)");
        return operand;
    }

    public static int calculator(int firstOperand, int secondOperand, String operator) { //калькулятор
        int result = 0;
        switch (operator) {

            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
        }
        return result;
    }


}


