import java.util.Scanner;

public class CalcString {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение:");
        Solution solution = new Solution(in.nextLine());
        solution.solution();

    }
    static class Solution {
        String operands;
        char action;
        String[] oper;
        String result;
        Solution(String operands){
            this.operands = operands;
        }
        public  void solution() throws Exception {
            if (operands.contains(" + ")) {
                oper = operands.split(" \\+ ");

                action = '+';
            } else if (operands.contains(" - ")) {
                oper = operands.split(" - ");
                action = '-';
            } else if (operands.contains(" * ")) {
                oper = operands.split(" \\* ");
                action = '*';
            } else if (operands.contains(" / ")) {
                oper = operands.split(" / ");
                action = '/';
            } else {
                throw new Exception("Некорректный знак действия");
            }
            if(oper[0].length() > 10 || oper[1].length() > 10) throw new Exception("Длинна сторки не должна привышать 10 символов");

            if(!oper[0].contains("\"")) throw new Exception("Первым аргументом выражения, подаваемого на вход, должна быть строка");

            if (action == '*' || action == '/') {
                if (oper[1].contains("\"")) throw new Exception("Строчку можно делить или умножать только на число");
                if(Integer.parseInt(oper[1]) > 10) throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            }
            for (int i = 0; i < oper.length; i++) {
                oper[i] = oper[i].replace("\"", "");
            }
            if (action == '+') {
                operResult(oper[0] + oper[1]);
            } else if (action == '*') {
                int multiplier = Integer.parseInt(oper[1]);
                String result = "";
                for (int i = 0; i < multiplier; i++) {
                    result += oper[0];
                }
                operResult(result);
            } else if (action == '-') {
                int index = oper[0].indexOf(oper[1]);
                if (index == -1) {
                    operResult(oper[0]);
                } else {
                    String result = oper[0].substring(0, index);
                    result += oper[0].substring(index + oper[1].length());
                    operResult(result);
                }
            } else {
                int newLen = oper[0].length() / Integer.parseInt(oper[1]);
                String result = oper[0].substring(0, newLen);
                operResult(result);
            }
        }
        public String operResult(String result){
            this.result = result;
            if(result.length() > 40){
                result = result.substring(0,40)+"...";}

            System.out.println("\""+result+"\"");
            return result;}
    }
}
