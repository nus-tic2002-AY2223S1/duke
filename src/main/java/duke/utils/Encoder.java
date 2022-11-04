package duke.utils;

import java.util.Base64;

/**
 * Implementation of string encoder and decoder
 */
public class Encoder {
    /**
     * Encodes a plain string to base64
     *
     * @param s Plain input string
     * @return Encoded string
     */
    public static String encode(String s) {
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(s.getBytes());
    }

    /**
     * Decodes a base64 encoded string
     *
     * @param s Encoded string
     * @return Decoded string
     */
    public static String decode(String s) {
        Base64.Decoder dec = Base64.getDecoder();
        return new String(dec.decode(s));
    }
}
