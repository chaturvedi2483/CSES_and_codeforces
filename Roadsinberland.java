import java.util.*;

class DisjointSetUnion {
    int[] parent, rank;
    int n;

    public DisjointSetUnion(int n) {
        this.n = n;
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        Arrays.fill(rank, 0);
    }

    public int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]); // Path compression
    }

    public void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (rank[a] > rank[b]) {
            parent[b] = a;
            rank[a] = Math.max(rank[a], rank[b] + 1);
        } else {
            parent[a] = b;
            rank[b] = Math.max(rank[b], rank[a] + 1);
        }
    }
}

public class Roadsinberland {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Number of nodes
        DisjointSetUnion dsu = new DisjointSetUnion(n);

        List<int[]> close = new ArrayList<>();

        for (int i = 1; i <= n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (dsu.find(u) != dsu.find(v)) {
                dsu.union(u, v);
            } else {
                close.add(new int[]{u, v});
            }
        }

        // Use TreeSet to get sorted unique components
        Set<Integer> set = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(dsu.find(i));
        }

        // Pair unconnected components to create a connected graph
        List<int[]> open = new ArrayList<>();
        Iterator<Integer> it = set.iterator();
        if (it.hasNext()) {
            int u = it.next();
            while (it.hasNext()) {
                int v = it.next();
                open.add(new int[]{u, v});
                u = v;
            }
        }

        System.out.println(close.size());
        for (int i = 0; i < close.size(); i++) {
            int[] c = close.get(i);
            int[] o = open.get(i);
            System.out.println(c[0] + " " + c[1] + " " + o[0] + " " + o[1]);
        }
    }
}
