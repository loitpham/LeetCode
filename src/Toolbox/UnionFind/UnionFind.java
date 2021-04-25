package Toolbox.UnionFind;

/**
 * Sat, 24 Apr 2021, 11:10 PM
 * Description:
 **/
public class UnionFind {
    int[] parent;
    int[] size; // only sizes of root elements are
    // guaranteed to be correct
    int numComponents;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        numComponents = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findRecursive(int x) {
        if (parent[x] != x) {
            parent[x] = findRecursive(parent[x]);
        }
        return parent[x];
    }

    public int find(int p) {
        int root = p;

        // Note that in every iteration root is updated, and
        // so is parent of it.
        while (root != parent[root]) {
            root = parent[root];
        }

        // Path compression
        // Make successive parents point directly to root
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }

        return root;
    }

    public void unionUnweighted(int x, int y) {
        parent[find(x)] = find(y);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller component point to the larger component
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        numComponents--;
    }
}
