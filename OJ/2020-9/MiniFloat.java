import java.util.ArrayList;

public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";

        int sign = bitSequence.charAt(0) == '0' ? 1 : -1;

        int exponent = Integer.parseInt(bitSequence.substring(2, 5), 2);
        exponent += Math.pow(2, 3) * (bitSequence.charAt(1) == '0' ? 0 : -1); // 2's complement

        float significand = 1;
        String mantissaSeq = bitSequence.substring(5, MINI_FLOAT_SIZE);
        for (int i = 0; i < mantissaSeq.length(); i++) {
            int pow = -(i + 1);
            significand += (Integer.parseInt(mantissaSeq.substring(i, i + 1)) * Math.pow(2, pow));
        }
        float ans = sign * (float) Math.pow(2, exponent) * significand;
        return ans;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // the miniFloats that is integer shall have mantissa be 000.
        ArrayList<String> allIntegral = new ArrayList<String>();
        String[] all = getValidMiniFloatBitSequences();
        for (String str : all) {
            float f = miniFloatFromString(str);
            if (f == (int) f)
                allIntegral.add(str);
        }

        return allIntegral.size();

    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);

        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

}
