package L2_Medium.Maximum_Area_of_a_Piece_of_Cake;

import java.util.Arrays;

/**
 * Thu, 03 Jun 2021, 11:52 PM
 * Description:
 * https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 **/
public class Maximum_Area_of_a_Piece_of_Cake {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int m = horizontalCuts.length;
        int n = verticalCuts.length;

        long maxHeight = Math.max(horizontalCuts[0], h - horizontalCuts[m - 1]);
        long maxWidth = Math.max(verticalCuts[0], w - verticalCuts[n - 1]);

        for (int i = 1; i < m; i++) {
            long height = horizontalCuts[i] - horizontalCuts[i - 1];
            maxHeight = Math.max(maxHeight, height);
        }

        for (int i = 1; i < n; i++) {
            long width = verticalCuts[i] - verticalCuts[i - 1];
            maxWidth = Math.max(maxWidth, width);
        }

        return (int) ((maxHeight * maxWidth) % (1e9 + 7));
    }
}
