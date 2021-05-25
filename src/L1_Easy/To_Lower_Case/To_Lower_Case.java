package L1_Easy.To_Lower_Case;

/**
 * Tue, 25 May 2021, 12:32 AM
 * Description:
 * https://leetcode.com/problems/to-lower-case/
 **/
public class To_Lower_Case {
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }
}
