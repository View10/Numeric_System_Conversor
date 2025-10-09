package NumericSystems;

public class Hexadecimal implements NumericSystem{
    public static final int BASE = 16;
    public final String BASE_SUB = "₁₆";
    public static final char[] ALLOWED_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'a', 'B', 'b',
    'C','c', 'D', 'd', 'E', 'e', 'F', 'f', '-'};
    private String value;

    //constructors
    public Hexadecimal(){
        this.value = "0";
    }

    public Hexadecimal(String value){
        if (isHexadecimal(value)){
            this.value = value;
        }else {
            this.value = "0";
        }
    }

    //getters and setters
    public int getBase() {
        return BASE;
    }

    public char[] getAllowedDigits() {
        return ALLOWED_DIGITS;
    }

    public String getValue() {
        return value;
    }

    public boolean setValue(String value) {
        boolean confirm = false;

        if (value != null && !value.isEmpty() && !isHexadecimal(value)){
            this.value = value;
            confirm = true;
        }

        return confirm;
    }

    //methods
    public boolean isHexadecimal(String v){
        boolean check = true;
        char[] digits = v.toCharArray();

        for (char digit : digits){
            if (digit < '0' || digit > '9'){
                if (digit < 'A' || digit > 'F'){
                    if (digit != '-'){
                        check = false;
                        break;
                    }
                }
            }
        }

        return check;
    }

    @Override
    public String toString() {
        return value + BASE_SUB;
    }
}
