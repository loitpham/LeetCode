package L1_Easy.Backspace_String_Compare;

import java.util.Stack;

/**
 * Tue, 01 Jun 2021, 9:30 PM
 * Description:
 **/
public class Backspace_String_Compare {
    public void process(Stack<Character> stack, String s) {
        for (char c : s.toCharArray()) {
            if (c == '#' && !stack.isEmpty()) {
                stack.pop();
            } else if (c != '#') {
                stack.push(c);
            }
        }
    }
    public boolean compare(Stack<Character> s1, Stack<Character> s2) {
        if (s1.size() != s2.size()) {
            return false;
        }
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.peek().equals(s2.peek())) {
                s1.pop();
                s2.pop();
            } else {
                return false;
            }
        }
        return s1.isEmpty() && s2.isEmpty();
    }
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        process(s1, s);
        process(s2, t);
        return compare(s1, s2);
    }

    public boolean backspaceCompare2(String s, String t) {
        int p1 = s.length() - 1;
        int p2 = t.length() - 1;
        int count1 = 0;
        int count2 = 0;

        while (p1 >= 0 || p2 >= 0) {
            while (p1 >= 0) {
                if (s.charAt(p1) == '#') {
                    count1++;
                    p1--;
                } else if (count1 > 0) {
                    count1--;
                    p1--;
                } else {
                    break;
                }
            }

            while (p2 >= 0) {
                if (t.charAt(p2) == '#') {
                    count2++;
                    p2--;
                } else if (count2 > 0) {
                    count2--;
                    p2--;
                } else {
                    break;
                }
            }

            if (p1 >= 0 && p2 >= 0 && s.charAt(p1) != t.charAt(p2)) {
                return false;
            }

            if ((p1 >= 0) != (p2 >= 0)) {
                return false;
            }
            p1--;
            p2--;
        }
        return true;
    }
}
