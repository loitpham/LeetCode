package L2_Medium.Max_Area_of_Island;

/**
 * Tue, 01 Jun 2021, 8:56 PM
 * Description:
 **/
public class Max_Area_of_Island {
    boolean[][] visited;
    public int findArea(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length
        || visited[r][c] || grid[r][c] == 0) {
            return 0;
        }
        visited[r][c] = true;
        return 1 + findArea(grid, r - 1, c) +
                findArea(grid, r + 1, c) +
                findArea(grid, r, c - 1) +
                findArea(grid, r, c + 1);
    }
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                max = Math.max(max, findArea(grid, r, c));
            }
        }
        return max;
    }
}
