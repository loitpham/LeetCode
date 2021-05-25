package L2_Medium.Accounts_Merge;

import java.util.*;

/**
 * Sat, 24 Apr 2021, 8:30 PM
 * Description:
 * https://leetcode.com/problems/accounts-merge/
 **/
public class Accounts_Merge {
    static class UnionFind {
        int[] parent;
        int[] size; // only sizes of root elements are
                    // guaranteed to be correct

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
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
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // N = total number of emails in all accounts
        // Assuming UnionFind data structures uses union-by-rank
        // (weighted quick union) and path compression

        // Time: O(N alpha(N)) ~ O(N),
        // where alpha(N) = inverse-Ackermann function <= 5 for all
        // practical purposes

        // Space: O(N)
        UnionFind uf = new UnionFind(10000);
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;

        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, account.get(0));
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                // Union the first email with the rest
                uf.union(emailToID.get(account.get(1)),
                        emailToID.get(email));
            }
        }

        // Key = id of root element of each connected component
        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email : emailToName.keySet()) {
            // Get id of each email
            // Find the id of root element of the connected component
            // that the current email belongs to
            int idx = uf.find(emailToID.get(email));

            // Create a list of emails for every connected component
            if (!ans.containsKey(idx)) {
                ans.put(idx, new ArrayList<>());
            }
            ans.get(idx).add(email);
        }

        for (List<String> component : ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList<>(ans.values());
    }
}
