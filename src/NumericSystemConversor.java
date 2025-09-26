import NumericSystems.*;

import java.util.ArrayList;
import java.util.Collections;

public class NumericSystemConversor {
    //Methods
    public static Binary decimalToBinary(String decimal){
        ArrayList<Integer> finalNumberDigits = new ArrayList<>();
        String finalNumber = "";
        boolean isNegative = false;
        int remainder = 0;
        int divisor = 0;
        int dividend = Binary.BASE;
        int aux = 0;
        int count = 0;

        if (decimal.toCharArray()[0] == '-') {
            String[] splitNum = decimal.split("-");
            isNegative = true;
            divisor = Integer.parseInt(splitNum[1]);
        }else {
            divisor = Integer.parseInt(decimal);
        }

        while (divisor != 0){
            while (aux <= divisor){
                if (aux + dividend > divisor){
                    break;
                }else {
                    count++;
                    aux += dividend;
                }
            }
            remainder = divisor - (dividend*count);
            finalNumberDigits.add(remainder);

            divisor = count;
            aux = 0;
            count = 0;
        }

        Collections.reverse(finalNumberDigits);

        if (isNegative){
            for (int i = 0; i < finalNumberDigits.size(); i++){
                if (finalNumberDigits.get(i) == 0){
                    finalNumberDigits.set(i, 1);
                }else {
                    finalNumberDigits.set(i, 0);
                }
            }

            for (int i = finalNumberDigits.size()-1; i >= 0; i--){
                if (finalNumberDigits.get(i) + 1 > 1){
                    finalNumberDigits.set(i, 0);
                }else {
                    finalNumberDigits.set(i, 1);
                    break;
                }
            }
        }

        for(int digit : finalNumberDigits){
            finalNumber += digit;
        }

        return new Binary(finalNumber, isNegative);
    }

    public static Octal decimalToOctal(String decimal){
        ArrayList<Integer> finalNumberDigits = new ArrayList<>();
        String finalNumber = "";
        int remainder = 0;
        int divisor = Integer.parseInt(decimal);
        int dividend = Octal.BASE;
        int aux = 0;
        int count = 0;

        while (divisor != 0){
            while (aux <= divisor){
                if (aux + dividend > divisor){
                    break;
                }else {
                    count++;
                    aux += dividend;
                }
            }
            remainder = divisor - (dividend*count);
            finalNumberDigits.add(remainder);

            divisor = count;
            aux = 0;
            count = 0;
        }

        Collections.reverse(finalNumberDigits);

        for(int digit : finalNumberDigits){
            finalNumber += digit;
        }

        return new Octal(finalNumber);
    }

    public static Hexadecimal decimalToHexadecimal(String decimal){
        ArrayList<String> finalNumberDigits = new ArrayList<>();
        String finalNumber = "";
        int remainder = 0;
        int divisor = Integer.parseInt(decimal);
        int dividend = Hexadecimal.BASE;
        int aux = 0;
        int count = 0;

        while (divisor != 0){
            while (aux <= divisor){
                if (aux + dividend > divisor){
                    break;
                }else {
                    count++;
                    aux += dividend;
                }
            }
            remainder = divisor - (dividend*count);

            switch (remainder){
                case 10:
                    finalNumberDigits.add("A");
                    break;
                case 11:
                    finalNumberDigits.add("B");
                    break;
                case 12:
                    finalNumberDigits.add("C");
                    break;
                case 13:
                    finalNumberDigits.add("D");
                    break;
                case 14:
                    finalNumberDigits.add("E");
                    break;
                case 15:
                    finalNumberDigits.add("F");
                    break;
                default:
                    finalNumberDigits.add(String.valueOf(remainder));
                    break;
            }

            divisor = count;
            aux = 0;
            count = 0;
        }

        Collections.reverse(finalNumberDigits);

        for(String digit : finalNumberDigits){
            finalNumber += digit;
        }

        return new Hexadecimal(finalNumber);
    }

    public static int binaryToDecimal(Binary bin){
        int finalNumber = 0;
        char[] digits = bin.getValue().toCharArray();
        int count = 0;

        for (int i = digits.length-1; i >= 0; i--, count++){
            if (digits[i] == '1'){
                finalNumber += (int) Math.pow(Binary.BASE, count);
            }
        }
        if (bin.isNegative()){
            finalNumber *= -1;
        }

        return finalNumber;
    }

    public static int octalToDecimal(Octal oct){
        int finalNumber = 0;
        char[] digits = oct.getValue().toCharArray();
        int count = 0;

        for (int i = digits.length-1; i >= 0; i--, count++){
            finalNumber += (int) (Math.pow(Octal.BASE, i) * (digits[count] -'0'));
        }

        return finalNumber;
    }

    public static int hexadecimalToDecimal(Hexadecimal hex){
        int finalNumber = 0;
        char[] digits = hex.getValue().toCharArray();
        int count = 0;
        char charDigit = 'a';
        int intDigit = 0;

        for (int i = digits.length-1; i >= 0; i--, count++){
            charDigit = digits[count];
            switch (charDigit){
                case 'A':
                    intDigit = 10;
                    break;
                case 'B':
                    intDigit = 11;
                    break;
                case 'C':
                    intDigit = 12;
                    break;
                case 'D':
                    intDigit = 13;
                    break;
                case 'E':
                    intDigit = 14;
                    break;
                case 'F':
                    intDigit = 15;
                    break;
                default:
                    intDigit = charDigit - '0';
                    break;
            }

            finalNumber += (int) (Math.pow(Hexadecimal.BASE, i) * intDigit);
        }

        return finalNumber;
    }


}
