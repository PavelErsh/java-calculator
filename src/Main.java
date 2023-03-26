import java.util.Scanner;
import java.util.*;

class Main {
    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение:");
            String expression = scanner.nextLine();
            try {
                String result = calc(expression);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        int a = 0;
        int b = 0;

        if (!isMath(parts))
        {
            throw new Exception("строка не является математической операцией");
        }

        if (!isSingle(parts)){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (parts.length > 3)
        {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if ((isRoman(parts[0]) == true && isRoman(parts[2]) == false) || (isRoman(parts[0]) == false && isRoman(parts[2]) == true)) {
            throw new Exception("Оба числа должны быть арабскими или римскими, нельзя использовать арабские и римские символы в одном выражении");
        }

        if (parts.length != 3) {
            throw new Exception("Некорректный формат выражения");
        }
        if (isRoman(parts[0]) == true && isRoman(parts[2]) == true) {
            a = fromRomeToArabic(parts[0]);
            b = fromRomeToArabic(parts[2]);
        }
        if (isRoman(parts[0]) == false && isRoman(parts[2]) == false) {
            a = parseNumber(parts[0]);
            b = parseNumber(parts[2]);
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10 включительно");
        }

        String operator = parts[1];
        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
            throw new Exception("Недопустимая операция: " + operator);
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Недопустимая операция: " + operator);
        }

        if (isRoman(parts[0]) && isRoman(parts[2]) && parts[1] == "/") {
            if (result < 1) {
                throw new Exception("Деление римских чисел нацело невозможно");
            }
            return toRoman(result);
        }
         if (isRoman(parts[0]) == true && isRoman(parts[2]) == true ) {
            if (result < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            return toRoman(result);
        }
         if (!isRoman(parts[0]) && !isRoman(parts[2])) {
            return Integer.toString(result);
        }
        return "";
    }

    private static int parseNumber(String s) throws Exception {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new Exception("Некорректный формат числа: " + s);
        }
    }

    private static boolean isMath(String[] s){
      String [] operators =  {"+", "-", "/", "*"};
      for(int number = 0; number < s.length - 1 ; number++){
          for (int operator = 0; operator < operators.length - 1; operator++){
              if (s[number].equals( operators[operator] )){
                  return true;
              }
          }
      }
      return false;
    }

    private static boolean isSingle(String[] s){
        int counter = 0;
        String [] operators =  {"+", "-", "/", "*"};
        for(int number = 0; number < s.length - 1 ; number++){
            for (int operator = 0; operator < operators.length - 1; operator++){
                if (s[number].equals( operators[operator] )){
                    counter ++;
                }
            }
        }
        if (counter == 1){
            return true;
        }
        else{
            return false;
        }
    }
    private static boolean isRoman(String s) {
        return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") || s.equals("V") || s.equals("VI") || s.equals("VII") || s.equals("VIII") || s.equals("IX") || s.equals("X");
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            return "Ошибка";
        }
        StringBuilder sb = new StringBuilder();
        while (number >= 1000) {
            sb.append("M");
            number -= 1000;
        }
        if (number >= 900) {
            sb.append("CM");
            number -= 900;
        }
        if (number >= 500) {
            sb.append("D");
            number -= 500;
        }
        if (number >= 400) {
            sb.append("CD");
            number -= 400;
        }
        while (number >= 100) {
            sb.append("C");
            number -= 100;
        }
        if (number >= 90) {
            sb.append("XC");
            number -= 90;
        }
        if (number >= 50) {
            sb.append("L");
            number -= 50;
        }
        if (number >= 40) {
            sb.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            sb.append("X");
            number -= 10;
        }
        if (number >= 9) {
            sb.append("IX");
            number -= 9;
        }
        if (number >= 5) {
            sb.append("V");
            number -= 5;
        }
        if (number >= 4) {
            sb.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            sb.append("I");
            number -= 1;
        }
        return sb.toString();
    }
    public static int fromRomeToArabic(String romanNumeral){
        Scanner input = new Scanner(System.in);
        romanNumeral.toUpperCase();


        int[] arabicArray = new int[romanNumeral.length()];
          for (int i = 0; i < romanNumeral.length(); i++) {
            switch (romanNumeral.charAt(i)) {
                case 'I':
                    arabicArray[i] = 1;
                    break;
                case 'V':
                    arabicArray[i] = 5;
                    break;
                case 'X':
                    arabicArray[i] = 10;
                    break;
                case 'L':
                    arabicArray[i] = 50;
                    break;
                case 'C':
                    arabicArray[i] = 100;
                    break;
                case 'D':
                    arabicArray[i] = 500;
                    break;
                case 'M':
                    arabicArray[i] = 1000;
                    break;
            }
    }

    int arabicNumber = 0;
      for (int i = 0; i < arabicArray.length; i++) {
        if (i < arabicArray.length - 1 && arabicArray[i] < arabicArray[i+1]) {
            arabicNumber -= arabicArray[i];
        } else {
            arabicNumber += arabicArray[i];
        }
    }
      return arabicNumber;
    }

}
