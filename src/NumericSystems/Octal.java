package NumericSystems;

public class Octal implements NumericSystem{
    public static final int BASE = 8;
    public final String BASE_SUB = "₈";
    public static final char[] ALLOWED_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '-'};
    private String value;

    //constructors
    public Octal(){
        this.value = "0";
    }

    public Octal(String value){
        if (isOctal(value)){
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

        if (value != null && !value.isEmpty() && !isOctal(value)){
            this.value = value;
            confirm = true;
        }

        return confirm;
    }

    //methods
    public boolean isOctal(String v){
        boolean check = true;
        char[] digits = v.toCharArray();

        for (char digit : digits){
            if (Character.getNumericValue(digit) > Character.getNumericValue('7')){
                check = false;
                break;
            }
        }

        return check;
    }

    @Override
    public String toString() {
        return value + BASE_SUB;
    }
}
