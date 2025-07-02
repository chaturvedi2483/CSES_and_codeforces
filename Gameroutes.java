
import java.util.*;

public class Gameroutes {
    static final int MAXN = 100001;
    static final long MOD = (long)1e9 + 7;
    static List<Integer>[] graph = new ArrayList[MAXN];
    static int[] inDegree = new int[MAXN];
    static long[] dp = new long[MAXN];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // number of nodes
        int M = sc.nextInt(); // number of edges

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        dp[1] = 1; // There is 1 way to reach node 1 (starting point)

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph[u]) {
                dp[v] = (dp[v] + dp[u]) % MOD;
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        System.out.println(dp[N]); // Number of ways to reach node N
    }
}
