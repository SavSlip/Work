public class ConvertorArabicRoman {


    static int[] ARABIC = {
            100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    static String[] ROMAN = {
            "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public static String convertToRoman(int inputArabicNum) throws ScannerExceptions { // конвертер из арабского числа в римское ( на ввод 1-100)
        if (inputArabicNum <=0){
            throw new ScannerExceptions("Римское число не может быть меньше единицы");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ARABIC.length; i++) {
            while (inputArabicNum >= ARABIC[i]) {
                result.append(ROMAN[i]);
                inputArabicNum -= ARABIC[i];
            }

        }
        return result.toString();
    }

    public static int convertToArabic(String inputRomanNum) { // из римского в арабское ( на ввод I-X)
        int result = 0;
        for (int i = 0; i < ROMAN.length; i++) {
            if (inputRomanNum.length() < ROMAN[i].length())
                continue;
            if (inputRomanNum.length() == 0)
                break;
            while ((inputRomanNum.substring(0, ROMAN[i].length())).contains(ROMAN[i])) {
                result += ARABIC[i];
                inputRomanNum = inputRomanNum.substring(ROMAN[i].length());
                if (inputRomanNum.length() == 0 || inputRomanNum.length() < ROMAN[i].length())
                    break;

            }
        }
        return result;
    }

}


