
import java.util.*;

public class BuildingRoads {

    static int[] ds;
    static List<int[]> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // Number of nodes
        int M = sc.nextInt(); // Number of edges

        ds = new int[N + 1];
        Arrays.fill(ds, -1); // Initialize each node as its own set

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            merge(a, b);
        }

        for (int i = 1; i < N; i++) {
            if (merge(i, i + 1)) {
                ans.add(new int[]{i, i + 1});
            }
        }

        System.out.println(ans.size());
        for (int[] edge : ans) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    static int find(int u) {
        if (ds[u] < 0) return u;
        return ds[u] = find(ds[u]);
    }

    static boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;

        if (ds[u] < ds[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        ds[v] += ds[u];
        ds[u] = v;
        return true;
    }
}
