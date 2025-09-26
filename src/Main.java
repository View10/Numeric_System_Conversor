import NumericSystems.Binary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number;

        System.out.print("Enter a decimal number:");
        number = scanner.next();

        Binary bin = NumericSystemConversor.decimalToBinary(number);

        System.out.println(number + "₁₀ in binary is " + bin);
        System.out.println(bin.isNegative);

        System.out.println(NumericSystemConversor.binaryToDecimal(bin));

        /*System.out.println("Enter a decimal number:");
        number = scanner.next();
        nsc.setNumber(number);

        Binary bin = nsc.decimalToBinary();
        Octal oct = nsc.decimalToOctal();
        Hexadecimal hex = nsc.decimalToHexadecimal();

        System.out.println(number + "₁₀ in binary is " + bin);
        System.out.println(number + "₁₀ in octal is " + oct);
        System.out.println(number + "₁₀ in hexadecimal is " + hex);

        System.out.print("Enter a binary number: ");
        number = scanner.next();
        nsc.setNumber(number);

        int binaryToDecimal = nsc.binaryToDecimal();

        System.out.println(number + "₂ in decimal is " + binaryToDecimal + "₁₀");*/
    }
}
