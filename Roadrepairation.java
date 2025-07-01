import java.io.*;
import java.util.*;

public class Roadrepairation {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int[] ds;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ds = new int[N + 1];
        Arrays.fill(ds, -1);

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges);

        long totalWeight = 0;
        int connectedComponents = N;

        for (Edge e : edges) {
            if (merge(e.u, e.v)) {
                totalWeight += e.weight;
                connectedComponents--;
            }
        }

        if (connectedComponents == 1) {
            out.println(totalWeight);
        } else {
            out.println("IMPOSSIBLE");
        }

        out.flush();
    }
}
