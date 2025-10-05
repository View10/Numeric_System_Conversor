package NumericSystems;

public class Binary implements NumericSystem{
    public static final int BASE = 2;
    public final String BASE_SUB = "₂";
    public static final char[] ALLOWED_DIGITS = {'0', '1'};
    public boolean isNegative;
    private String value;

    //constructors
    public Binary(){
        this.value = "0";
        this.isNegative = false;
    }

    public Binary(String value, boolean isNegative){
        if (isBinary(value)){
            this.value = value;
        }else {
            this.value = "0";
        }
        this.isNegative = isNegative;
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

        if (value != null && !value.isEmpty() && !isBinary(value)){
            this.value = value;
            confirm = true;
        }

        return confirm;
    }

    public boolean isNegative(){
        return isNegative;
    }

    //methods
    public static int requiredBits(int value, boolean isNegative){
        int requiredBits = 8;
        int range;
        boolean fits = true;

        if (isNegative){
            range =(int) ((Math.pow(BASE, requiredBits)/2)*-1);
        }else {
            range =(int) Math.pow(BASE, requiredBits);
        }

        if (range > value){
            fits = false;
        }

        if (!fits){
            while (range >= value){
                requiredBits += requiredBits;
                range *= 2;
            }
        }

        return requiredBits;
    }

    private boolean isBinary(String v){
        boolean check = true;
        char[] digits = v.toCharArray();

        for (char digit : digits){
            if (digit != ALLOWED_DIGITS[0] && digit != ALLOWED_DIGITS[1]){
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
