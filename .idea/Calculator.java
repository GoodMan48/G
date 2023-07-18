import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String input = sc.nextLine();
        System.out.println(parse(input));
    }
    public static String parse(String input) throws Exception {
        int num1, num2;
        String operation;
        String result;
        boolean isArabian;
        input = input.replace(" ","");
        String[] operands = input.split("[+\\-*/]");

        if(operands.length >=3) throw new Exception("Должно быть два числа");
        operation = detectOperation(input);

        if(operation == null) throw new Exception("Неподдерживаемая математическая операция");

        if(Roman.isArabian(operands[0]) && Roman.isArabian(operands[1])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isArabian = true;
        }
        else if (!Roman.isArabian(operands[0]) && !Roman.isArabian(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isArabian = false;
        }
        else { throw new Exception("Числа должны быть одного формата");
        }
        if (num1 > 10 || num2 > 10) { throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, operation);
        if(isArabian) {
            if (arabian <= 0) { throw new Exception("Римское число должно быть больше 0");
            }
            result = Roman.convertToArabian(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String expression) {
        if(expression.contains("+")) return "+";
        else if(expression.contains("-")) return "-";
        else if(expression.contains("*")) return "*";
        else if(expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b,String operation){
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
    static class Roman {
        static String[] isRoman = new String[] {"0","I","II","III","IV","V","VI","VII","ViII","IX","X",
                "XI","XII", "XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
                "XXI","XXII","XXIII","XXIV ","XXV","XXVI","XXVII","XXVIII","XXIX","XXX",
                "XXXI","XXXII","XXXIII","XXXIV", "XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
                "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
                "LI","LII","LIII","LIV","LV","LVI","LIVII","LIVIII","LIIX","LX",
                "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
                "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX",
                "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
                "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C",};
        public static boolean isArabian(String val){
            for (String s : isRoman) {
                if (val.equals(s)) {
                    return true;
                }
            }
            return false;

        }
        public static int convertToArabian(String arabian) {
            for(int i = 0; i < isRoman.length; i++){
                if(arabian.equals(isRoman[i])) {
                    return i;
                }
            }
            return -1;
        }
        public static String convertToArabian(int arabian){
            return isRoman[arabian];
        }
    }
}
