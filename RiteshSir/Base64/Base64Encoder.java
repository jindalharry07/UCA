
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

// https://leetcode.com/problems/encode-and-decode-tinyurl/description/

public class Base64Encoder {
  // Base64 Table (Index -> Character)
  private static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

  public static String encode(String input) {
    /*
    byte[] bytes = input.getBytes(); // Step 1: String -> ASCII Bytes

    // String = "ABC"
    // Length = 3
    // Character ASCII Value Binary (8-bit)
    // A 65 01000001
    // B 66 01000010
    // C 67 01000011
    // bytes[0] = 65
    // bytes[1] = 66
    // bytes[2] = 67

    StringBuilder res = new StringBuilder();

    int paddingCnt = (3 - (bytes.length % 3)) % 3; // required padding
    // 3 bytes = 24 bits = exact 4 Base64 characters --- 6x4=24

    int fullLen = bytes.length + paddingCnt;
    byte[] padded = new byte[fullLen];
    
    // System.arraycopy(bytes, 0, padded, 0, bytes.length);
    for (int i = 0; i < bytes.length; i++) {
      padded[i] = bytes[i];
    }
    */
      
    int paddingCnt = (3 - (input.length() % 3)) % 3;
    int fullLen = input.length() + paddingCnt;
    StringBuilder res = new StringBuilder();


    /*
      for (int i = 0; i < padded.length; i += 3) {
        int combined = ((padded[i] & 255) << 16) | ((padded[i + 1] & 255) << 8) | ((padded[i + 2] & 255)); // 0xFF = 255 in bits

        // this is combined binary int

        res.append(BASE64_TABLE.charAt((combined >> 18) & 63)); // 0X3F = 63
        res.append(BASE64_TABLE.charAt((combined >> 12) & 63));
        res.append(BASE64_TABLE.charAt((combined >> 6) & 63));
        res.append(BASE64_TABLE.charAt(combined & 63));

      }

      if (paddingCnt > 0) { // That removes the last paddingCnt characters ("A") from res
        res.setLength(res.length() - paddingCnt);
        for (int i = 0; i < paddingCnt; i++) { // append paddingCnt times "="
          res.append('=');
        }
      }
    */

    for(int i = 0; i < fullLen; i+=3) {
      int c1 = input.charAt(i) & 0xFF; // always safe

      int c2 = (i + 1 < input.length()) ? (input.charAt(i + 1) & 0xFF) : 0;
      int c3 = (i + 2 < input.length()) ? (input.charAt(i + 2) & 0xFF) : 0;

      int combined = (c1 << 16) | (c2 << 8) | c3;

      res.append(BASE64_TABLE.charAt((combined >> 18) & 63));
      res.append(BASE64_TABLE.charAt((combined >> 12) & 63));
      res.append(BASE64_TABLE.charAt((combined >> 6) & 63));
      res.append(BASE64_TABLE.charAt(combined & 63));
    }

    if (paddingCnt > 0) { // That removes the last paddingCnt characters ("A") from res
      res.setLength(res.length() - paddingCnt);
      for (int i = 0; i < paddingCnt; i++) { // append paddingCnt times "="
        res.append('=');
      }
    }
    return res.toString();
  }

  private static int getValue(char c) {
    if (c == '='){
      return 0;
    }
    return BASE64_TABLE.indexOf(c);
  }

  private static String decode(String base64) {
    // Remove padding count
    // int padding = 0;
    // if (base64.endsWith("==")) {
    //   padding = 2;
    // }
    // else if (base64.endsWith("=")) {
    //   padding = 1;
    // }

    int length = base64.length();// Work on only real characters
    // int groups = length / 4;

    // byte[] bytes = new byte[groups * 3 - padding]; // groups * 3 - padding size of original string
    // int byteIndex = 0;
    StringBuilder res = new StringBuilder();

    for(int i = 0; i < length; i+=4) {
      int c1 = getValue(base64.charAt(i));
      int c2 = getValue(base64.charAt(i + 1));
      int c3 = getValue(base64.charAt(i + 2));
      int c4 = getValue(base64.charAt(i + 3));

      int val = (c1 << 18) | (c2 << 12) | (c3 << 6) | (c4);

      res.append((char) ((val >> 16) & 255));

      // Append second character only if not padding
      if (base64.charAt(i + 2) != '=') {
        res.append((char) ((val >> 8) & 0xFF));
      }

      // Append third character only if not padding
      if (base64.charAt(i + 3) != '=') {
        res.append((char) (val & 0xFF));
      }
    }
    return res.toString();

    /*
    for (int i = 0; i < length; i += 4) {

      
      // first 6 bits → bits 23..18 (<<18)
      // second → bits 17..12 (<<12)
      // third → bits 11..6 (<<6)
      // fourth → bits 5..0 (no shift)
       

      int val = ((getValue(base64.charAt(i)) << 18) | (getValue(base64.charAt(i + 1)) << 12) |
          (getValue(base64.charAt(i + 2)) << 6) | (getValue(base64.charAt(i + 3))));

    
      // Example "QUJD" (Q=16, U=20, J=9, D=3):
      
      // val = (16<<18) | (20<<12) | (9<<6) | 3
      
      // 16<<18 = 4,194,304
      // 20<<12 = 81,920
      // 9<<6 = 576
      // 3 = 3
      
      // val = 4,194,304 + 81,920 + 576 + 3 = 4,276,803
      // Binary (24 bits): 01000001 01000010 01000011 → which is bytes [65,66,67].
      
      

      bytes[byteIndex++] = (byte) ((val >> 16) & 255); // first byte

      if (base64.charAt(i + 2) != '=') {
        bytes[byteIndex++] = (byte) ((val >> 8) & 255); // second byte
      }
      if (base64.charAt(i + 3) != '=') {
        bytes[byteIndex++] = (byte) (val & 255); // third byte
      }
    }
    return new String(bytes);
    */
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter text to encode: ");
    String input = sc.nextLine();

    String encoded = encode(input);
    System.out.println("Base64 Encoded: " + encoded);
    String decoded = decode(encoded);
    System.out.println("Decoded Encoded: " + decoded);

    sc.close();
  }
}

/*
 * /*
 * ========================
 * Base64 ENCODE — Step-by-Step
 * ========================
 *
 * Example 1 — Input = "ABC" (no padding needed)
 *
 * Step 1: Convert input string to bytes
 * "ABC" → [65, 66, 67]
 * A = 65 (01000001)
 * B = 66 (01000010)
 * C = 67 (01000011)
 *
 * Step 2: Process in 3-byte groups (3 bytes = 24 bits)
 * Combine 3 bytes into one 24-bit integer:
 * combined = (65 << 16) | (66 << 8) | 67
 * = binary: 01000001 01000010 01000011
 * = decimal: 4,276,803
 *
 * Step 3: Split 24-bit into four 6-bit groups
 * (combined >> 18) & 63 → 010000 = 16
 * (combined >> 12) & 63 → 010100 = 20
 * (combined >> 6) & 63 → 001001 = 9
 * combined & 63 → 000011 = 3
 *
 * Step 4: Map each 6-bit value to Base64 table
 * 16='Q', 20='U', 9='J', 3='D'
 * Result: "QUJD"
 *
 *
 * Example 2 — Input = "M" (needs padding)
 *
 * Step 1: Convert input to bytes
 * "M" → [77] → binary: 01001101
 *
 * Step 2: Calculate padding
 * byte count = 1 → (3 - 1) % 3 = 2 → add two zero bytes → [77, 0, 0]
 *
 * Step 3: Combine into 24-bit
 * combined = (77 << 16) | (0 << 8) | 0
 * = binary: 01001101 00000000 00000000
 * = decimal: 5,046,272
 *
 * Step 4: Split into 6-bit groups
 * (combined >> 18) & 63 → 010011 = 19
 * (combined >> 12) & 63 → 010000 = 16
 * (combined >> 6) & 63 → 000000 = 0
 * combined & 63 → 000000 = 0
 *
 * Step 5: Map to Base64 chars
 * 19='T', 16='Q', 0='A', 0='A' → "TQAA"
 *
 * Step 6: Replace last 2 chars with '=' (because padding=2)
 * Final result: "TQ=="
 *
 *
 * Key Points (Encoding):
 * - Take 3 bytes → make 24-bit number → split into 4 × 6-bit groups.
 * - If input length not multiple of 3, pad with zero bytes then replace
 * last 1 or 2 output chars with '='.
 * - Output length is always multiple of 4.
 */

/*
 * ========================
 * Base64 DECODE — Step-by-Step
 * ========================
 *
 * Example 1 — Input = "QUJD" (no padding)
 *
 * Step 1: Find padding count
 * "QUJD" ends with no "=" → padding = 0.
 *
 * Step 2: Process 4-character groups (4 chars = 24 bits)
 * Characters: Q U J D
 * Indexes in Base64 Table:
 * Q = 16 (010000)
 * U = 20 (010100)
 * J = 9 (001001)
 * D = 3 (000011)
 *
 * Step 3: Recombine into 24-bit integer
 * val = (16 << 18) | (20 << 12) | (9 << 6) | 3
 * val = 4,276,803
 * Binary: 01000001 01000010 01000011
 *
 * Step 4: Extract original bytes (8-bit groups)
 * byte1 = (val >> 16) & 255 → 01000001 → 65 ('A')
 * byte2 = (val >> 8) & 255 → 01000010 → 66 ('B')
 * byte3 = val & 255 → 01000011 → 67 ('C')
 *
 * Output: "ABC"
 *
 *
 * Example 2 — Input = "TQ==" (with padding)
 *
 * Step 1: Padding count
 * "TQ==" ends with "==" → padding = 2.
 *
 * Step 2: Characters: T Q = =
 * Indexes:
 * T = 19 (010011)
 * Q = 16 (010000)
 * '=' → treated as 0
 *
 * Step 3: Combine
 * val = (19 << 18) | (16 << 12) | (0 << 6) | 0
 * val = 5,046,272
 * Binary: 01001101 00000000 00000000
 *
 * Step 4: Extract bytes
 * byte1 = (val >> 16)255 → 01001101 → 77 ('M')
 * byte2 and byte3 skipped (because chars 3 and 4 were '=')
 *
 * Output: "M"
 *
 *
 * Key Points (Decoding):
 * - 4 Base64 chars always give up to 3 bytes.
 * - '=' tells decoder how many trailing bytes to ignore:
 * '=' → ignore last 1 byte
 * '==' → ignore last 2 bytes
 * - & 255 keeps only 8 bits when extracting bytes.
 */
