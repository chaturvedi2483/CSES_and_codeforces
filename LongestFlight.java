import java.util.*;

public class LongestFlight {
    static final int MAXN = 100_005;
    static boolean[] vis = new boolean[MAXN];
    static int[] in = new int[MAXN];
    static int[] parent = new int[MAXN];
    static int[] length = new int[MAXN];
    static int[] ans = new int[MAXN];
    static ArrayList<Integer>[] graph = new ArrayList[MAXN];
    static int N, M;

    public static void dfs(int u, int par) {
        vis[u] = true;
        for (int v : graph[u]) {
            if (v != par && !vis[v]) {
                dfs(v, u);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            in[i] = 0;
            length[i] = -1;
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            in[b]++;
        }

        dfs(1, 0);

        if (!vis[N]) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        length[1] = 0;

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (length[u] != -1 && length[v] < length[u] + 1) {
                    length[v] = length[u] + 1;
                    parent[v] = u;
                }
                in[v]--;
                if (in[v] == 0) {
                    q.add(v);
                }
            }
        }

        int k = length[N];
        System.out.println(k + 1);
        int[] path = new int[k + 1];
        int u = N;
        for (int i = k; i >= 0; i--) {
            path[i] = u;
            u = parent[u];
        }

        for (int i = 0; i <= k; i++) {
            System.out.print(path[i]);
            System.out.print(i == k ? "\n" : " ");
        }
    }
}
