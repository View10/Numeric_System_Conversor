import NumericSystems.Binary;
import NumericSystems.Hexadecimal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number;

        System.out.print("Enter a decimal number:");
        number = scanner.next();

        /*System.out.println(NumericSystemConversor.hexadecimalToDecimal(new Hexadecimal(number)));

        System.out.println(NumericSystemConversor.decimalToBinary(NumericSystemConversor.hexadecimalToDecimal(new Hexadecimal(number))+""));

        System.out.println(NumericSystemConversor.decimalToHexadecimal(number));

        System.out.print("Enter a decimal number:");
        number = scanner.next();*/

        Binary bin = NumericSystemConversor.decimalToBinary(number);

        System.out.println(number + "₁₀ in binary is " + bin);
        System.out.println(bin.isNegative);

        /*System.out.println(NumericSystemConversor.binaryToDecimal(bin));

        System.out.print("Enter a binary number:");
        number = scanner.next();

        System.out.println(NumericSystemConversor.decimalToHexadecimal(NumericSystemConversor.binaryToDecimal(new Binary(number, false))+""));*/
    }
}
