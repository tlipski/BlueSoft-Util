package pl.net.bluesoft.util.lang;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pl.net.bluesoft.util.lang.exception.UtilityInvocationException;

public abstract class CryptoUtil {
    private CryptoUtil() {}

    public static String digest(String algorithm, String... chunks) {
        try {
            MessageDigest m = MessageDigest.getInstance(algorithm);
            for (String add : chunks) {
                if (add == null) {
                    continue;
                }
                m.update(add.getBytes());
            }
            
            String result = new BigInteger(1, m.digest()).toString(16);
            while(result.length() < 32)
            {
            	result = "0" + result;
            }
            return result.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new UtilityInvocationException(e);
        }
    }

}
