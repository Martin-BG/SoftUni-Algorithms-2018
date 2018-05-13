package _13_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Pr03TerranPredefined {

    private static BigInteger[] factorials;

    static {
        factorials = new BigInteger[31];
        factorials[1] = new BigInteger("1");
        factorials[2] = new BigInteger("2");
        factorials[3] = new BigInteger("6");
        factorials[4] = new BigInteger("24");
        factorials[5] = new BigInteger("120");
        factorials[6] = new BigInteger("720");
        factorials[7] = new BigInteger("5040");
        factorials[8] = new BigInteger("40320");
        factorials[9] = new BigInteger("362880");
        factorials[10] = new BigInteger("3628800");
        factorials[11] = new BigInteger("39916800");
        factorials[12] = new BigInteger("479001600");
        factorials[13] = new BigInteger("6227020800");
        factorials[14] = new BigInteger("87178291200");
        factorials[15] = new BigInteger("1307674368000");
        factorials[16] = new BigInteger("20922789888000");
        factorials[17] = new BigInteger("355687428096000");
        factorials[18] = new BigInteger("6402373705728000");
        factorials[19] = new BigInteger("121645100408832000");
        factorials[20] = new BigInteger("2432902008176640000");
        factorials[21] = new BigInteger("51090942171709440000");
        factorials[22] = new BigInteger("1124000727777607680000");
        factorials[23] = new BigInteger("25852016738884976640000");
        factorials[24] = new BigInteger("620448401733239439360000");
        factorials[25] = new BigInteger("15511210043330985984000000");
        factorials[26] = new BigInteger("403291461126605635584000000");
        factorials[27] = new BigInteger("10888869450418352160768000000");
        factorials[28] = new BigInteger("304888344611713860501504000000");
        factorials[29] = new BigInteger("8841761993739701954543616000000");
        factorials[30] = new BigInteger("265252859812191058636308480000000");
    }

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        char[] elements = reader.readLine().trim().toCharArray();

        Map<Character, Integer> distinct = new HashMap<>();
        for (final char element : elements) {
            distinct.putIfAbsent(element, 0);
            distinct.replace(element, distinct.get(element) + 1);
        }
        BigInteger divisor = BigInteger.ONE;
        for (final Integer integer : distinct.values()) {
            if (integer > 1) {
                divisor = divisor.multiply(factorials[integer]);
            }
        }
        System.out.println(factorials[elements.length].divide(divisor));
    }
}
