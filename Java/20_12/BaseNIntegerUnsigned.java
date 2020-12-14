public class BaseNIntegerUnsigned {

    public static final int BASE_MINIMUM = 2;
    public static final int BASE_MAXIMUM = 26;
    public static final char DIGIT_ZERO = 'A';

    /**
     * Magnitude of the integer in String. 'magnitude' should always be valid w.r.t.
     * 'base', and it should never contain unnecessary leading zeros, i.e.,
     * DIGIT_ZEROs.
     */
    private final String magnitude;

    /** Base of the integer. Only valid base values are allowed. */
    private final int base;

    /**
     * Instantiate an NBasedIntegerUnsigned. Throws IllegalArgumentException if
     * 'base' or 'magnitude' is invalid.
     */
    public BaseNIntegerUnsigned(String magnitude, int base) {
        /* implementation omitted */
    }

    /**
     * Return true if 'base' is between BASE_MINIMUM and BASE_MAXIMUM (both
     * inclusive); Otherwise, false.
     */
    public static boolean isValidBase(int base) {
        /* implementation omitted */
    }

    /**
     * Return true if 'base' is valid and 'magnitude' is valid for 'base'.
     * 'magnitude' is valid for 'base' if 1) it is not null, 2) it is not empty, and
     * 3) it contains only digits that are valid for the base.
     *
     * For example, for base 4, valid digits include 'A', 'B', 'C', and 'D'.
     * Therefore, magnitudes like "A", "AABC", and "DAC" are valid for base 4, while
     * magnitudes like "", "EA", and "B3C" are invalid for base 4.
     */
    public static boolean isValidMagnitude(String magnitude, int base) {
        /* implementation omitted */
    }

    /**
     * Return an integer value to indicate the relation between 'this' and 'other'.
     * The value should be 1) positive if 'this' is greater than 'other', 2) zero if
     * 'this' is equal to 'other', and 3) negative if 'this' is smaller than
     * 'other'. Throw IllegalArgumentException if 'other' is null or 'this' and
     * 'other' have different bases.
     *
     * For example, comparing two integers "DA" and "BC" in base 5 should return a
     * positive value; comparing two integers "CA" and "ACA" in base 7 should return
     * 0; comparing two integers "BC" and "DA" in base 6 should return a negative
     * value.
     */
    public int compare(BaseNIntegerUnsigned other) {
        /* implementation omitted */
    }

    /**
     * Return the sum of 'this' and 'other'. The result integer should have the same
     * base as 'this'. Throw IllegalArgumentException if 'other' is null or 'this'
     * and 'other' have different bases.
     *
     * For example, adding two integers "ABCDA" and "DBC" in base 5 should return a
     * new integer "CAEC" in the same base. The decimal values of the three integers
     * are 190, 82, and 272, respectively.
     */
    public BaseNIntegerUnsigned add(BaseNIntegerUnsigned other) {
        /* implementation omitted */
    }

    /**
     * Return the result of subtracting 'other' from 'this'. The result integer
     * should have the same base as 'this'. Throw IllegalArgumentException if 1)
     * 'other' is null, 2) 'this' and 'other' have different bases, or 3) 'this' is
     * smaller than 'other'.
     *
     * For example, subtracting integer "DBC" from "ABCDA", both in base 5, should
     * return a new integer "EBD" in the same base. The decimal values of the three
     * integers are 82, 190, and 108, respectively.
     */
    public BaseNIntegerUnsigned subtract(BaseNIntegerUnsigned other) {
        /* implementation omitted */
    }

    /**
     * Return the result of multiplying 'this' and 'other'. The result integer
     * should have the same base as 'this'. Throw IllegalArgumentException if
     * 'other' is null or 'this' and 'other' have different bases.
     *
     * For example, multiplying two unsigned integer "BCD" and "BC", both in base 5,
     * should return a new integer "CADB" in the same base. The decimal values of
     * the three integers are 38, 7, and 266, respectively.
     */
    public BaseNIntegerUnsigned multiply(BaseNIntegerUnsigned other) {
        /* Add code here to satisfy the requirements */
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if (this.base != other.getBase()) {
            throw new IllegalArgumentException();
        }
        String otherMag = other.getMagnitude();
        int power = 0;
        BaseNIntegerUnsigned ans = new BaseNIntegerUnsigned("A", base);// zero
        for (int i = otherMag.length() - 1; i >= 0; i--) {
            BaseNIntegerUnsigned n = this.multiply(otherMag.charAt(i));
            BaseNIntegerUnsigned nn = n.shiftRight(power++);
            ans.add(nn);
        }
        return ans;
    }

    /**
     * Return the result of multiplying 'this' and 'digit'. The result integer
     * should have the same base as 'this'. Throw IllegalArgumentException if
     * 'digit' is not a valid digit for 'this.base'.
     *
     * For example, multiplying unsigned integer "BCD" and digit 'C', both in base
     * 5, should return a new integer "DAB" in the same base. The decimal values of
     * the two integers are 38 and 76, respectively, and the decimal value of the
     * digit is 2.
     */
    private BaseNIntegerUnsigned multiply(char digit) {
        /* Add code here to satisfy the requirements */
        if (!isValidBase(digit)) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        int decimalDigit = getValueFromDigit(digit, this.getBase());

        int carry = 0;
        for (int i = this.magnitude.length() - 1; i >= 0; i--) {
            int val = this.getValueAtPosition(i);
            int newVal = val * decimalDigit + carry;
            carry = 0;
            if (newVal >= this.base) {
                newVal = newVal % base;
                carry = newVal / base;// ceil
            }
            sb.append(getDigitFromValue(newVal, base));
        }
        if (carry != 0) {
            sb.append(getDigitFromValue(carry, base));
        }
        sb.reverse();
        String newValStr = sb.toString();
        return new BaseNIntegerUnsigned(newValStr, base);
    }

    /**
     * Return the result of shifting the radix point to the right by 'rep' digits.
     * Throw IllegalArgumentException if 'rep' is negative.
     * 
     * For example, shifting the radix point of an integer with magnitude "BCD" to
     * the right by 3 digits should return a new integer with magnitude "BCDAAA" in
     * the same base.
     */
    private BaseNIntegerUnsigned shiftRight(int rep) {
        /* Add code here to satisfy the requirements */
        if (rep < 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder(this.getMagnitude());
        for (int i = 0; i < rep; i++) {
            sb.append("A");
        }
        String newMag = sb.toString();
        return new BaseNIntegerUnsigned(newMag, this.getBase());

    }

    /**
     * Return the digit in 'base' with the specified 'value'. Throw
     * IllegalArgumentException if 'base' is invalid or 'value' cannot be
     * represented using a single digit in 'base'.
     *
     * For example, value 3 is represented using digit 'D' in base 5.
     */
    public static char getDigitFromValue(int value, int base) {
        /* implementation omitted */
    }

    /**
     * Return the value of 'digit' in 'base'. Throw IllegalArgumentException if
     * 'base' is invalid or 'digit' is not a valid digit for 'base'.
     *
     * For example, digit 'C' in base 6 has value 2.
     */
    public static int getValueFromDigit(char digit, int base) {
        /* implementation omitted */
    }

    /**
     * Return the base of the integer.
     */
    public int getBase() {
        /* implementation omitted */
    }

    /**
     * Return the magnitude of the integer.
     */
    public String getMagnitude() {
        /* implementation omitted */
    }

    /**
     * Return the number of positions, which is the same as the number of digits, in
     * the 'magnitude' of 'this'.
     *
     * For example, magnitudes "BA" and "CBA" in base 6 have 2 and 3 positions,
     * respectively.
     */
    private int getNumberOfPositions() {
        /* implementation omitted */
    }

    /**
     * Return the value of the digit at position 'pos'. 'pos' should be
     * non-negative. Return 0 if 'pos' is greater than the maximum position.
     *
     * For example, given magnitude "GECA" in base 9, the digits at positions 1, 3,
     * and 5 are 'C', 'G', and 'A', and their values are 2, 6, and 0, respectively.
     */
    private int getValueAtPosition(int pos) {
        /* implementation omitted */
    }

    /**
     * Return the digit at position 'pos'. 'pos' should be non-negative. Return
     * DIGIT_ZERO if 'pos' is greater than the maximum position.
     *
     * For example, given magnitude "GECA" in base 9, the digits at positions 1, 3,
     * and 5 are 'C', 'G', and 'A', respectively.
     */
    private char getDigitAtPosition(int pos) {
        /* implementation omitted */
    }

}