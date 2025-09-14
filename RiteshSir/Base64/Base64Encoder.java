
import java.util.Base64;
import java.util.Scanner;

// https://leetcode.com/problems/encode-and-decode-tinyurl/description/

public class Base64Encoder {
  // Base64 Table (Index -> Character)
  private static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

  public static String encode(String input) {
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

    for(int i = 0; i < padded.length; i+=3) {
      int combined = ((padded[i] & 0xFF)<<16) | ((padded[i+1] & 0xFF)<< 8) | ((padded[i+2] & 0xFF)); // 0xFF = 255 in b bits

      // this is combined binary int

      res.append(BASE64_TABLE.charAt((combined >> 18) & 0x3F)); // 0X3F = 63
      res.append(BASE64_TABLE.charAt((combined >> 12) & 0x3F));
      res.append(BASE64_TABLE.charAt((combined >> 6) & 0x3F));
      res.append(BASE64_TABLE.charAt(combined & 0x3F));

    }

    if(paddingCnt > 0){ //That removes the last paddingCnt characters ("A") from res
      res.setLength(res.length() - paddingCnt);
      for (int i = 0; i < paddingCnt; i++) { // append paddingCnt times "="
        res.append('=');
      }
    }
    return res.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter text to encode: ");
    String input = sc.nextLine();

    String encoded = encode(input);
    System.out.println("Base64 Encoded: " + encoded);

    sc.close();
  }
}

/*
Example 1 — Input = "ABC" (no padding)

Bytes
A = 65 (01000001)
B = 66 (01000010)
C = 67 (01000011)
So padded = [65,66,67], paddingCount = 0.

Combine 3 bytes → one 24-bit int (combined)

combined = (65 << 16) | (66 << 8) | 67
         = binary: 01000001 01000010 01000011
         = decimal: 4,276,803


Split into 4 groups of 6 bits (we extract them using shifts + mask):

(combined >> 18) & 0x3F => first 6 bits
010000010100001001000011 >> 18 => 000000000000000000010000 => last 6 bits 010000 = 16

(combined >> 12) & 0x3F => second 6 bits => 010100 = 20

(combined >> 6) & 0x3F => third 6 bits => 001001 = 9

combined & 0x3F => fourth 6 bits => 000011 = 3

Groups (decimal): [16, 20, 9, 3]
Map to Base64 table: index 16='Q', 20='U', 9='J', 3='D'

Append characters => "QUJD"
paddingCount=0 so we don't change the result.

Result: "ABC" → "QUJD"



Example 2 — Input = "M" (needs padding)

Bytes
M = 77 (01001101)
bytes.length = 1 → paddingCount = (3 - 1) % 3 = 2
padded = [77, 0, 0] (we add two zero bytes)

Combine

combined = (77 << 16) | (0 << 8) | 0
         = binary: 01001101 00000000 00000000
         = decimal: 5,046,272


6-bit groups

(combined >> 18) & 0x3F => 010011 = 19

(combined >> 12) & 0x3F => 010000 = 16

(combined >> 6) & 0x3F => 000000 = 0

combined & 0x3F => 000000 = 0

Groups: [19,16,0,0] → Base64 chars: T Q A A → intermediate result "TQAA"

Handle padding

Because paddingCount = 2, the code does:

result.setLength(result.length() - paddingCount);

That removes the last 2 characters ("AA") from "TQAA", leaving "TQ".

Then it appends two '=' characters:
"TQ" + "==" → "TQ==".

Result: "M" → "TQ=="
(Those 'A's were placeholders produced by the zero bytes — we replace them with '=' to indicate padding.)
*/