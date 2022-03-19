package algorithm;

import java.io.*;
import java.util.*;

public class BJ1800Internet {
    static ArrayList<Pair>[] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int P = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        int left = 0;
        int right = 0;
        int ans = -1;

        map = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            map[i] = new ArrayList<>();

        for(int i=0; i<P; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            map[start].add(new Pair(end, cost));
            map[end].add(new Pair(start, cost));
            right = Math.max(right, cost);
        }

        // 이분탐색 - 일정 가중치 이하인 간선들로 연결할 수 있는가, 아니면 K개까지만 추가
        while(left<=right) {
            int mid = (left+right)/2;

            // 가능하다면 가중치 줄이기
            if(dijkstra(N, K, mid)) {
                right = mid-1;
                ans = mid;
            }

            // 불가능하다면 가중치 늘이기
            else
                left = mid+1;
        }

        System.out.println(ans);
    }

    // 다익스트라
    public static boolean dijkstra(int N, int K, int max) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        for(int i=1; i<=N; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[1] = 0;
        pq.add(new Pair(1, 0));

        while(!pq.isEmpty()) {
            Pair temp = pq.poll();

            for (int i=0; i<map[temp.end].size(); i++) {
                Pair next = map[temp.end].get(i);

                if (dist[next.end] > temp.cost+(next.cost > max ? 1 : 0)) {
                    dist[next.end] = temp.cost+(next.cost > max ? 1 : 0);
                    pq.add(new Pair(next.end, dist[next.end]));
                }
            }
        }

        // 가중치를 넘는게 K개 넘어가면 X
        return dist[N]<=K;
    }

    public static class Pair implements Comparable<Pair>{
        int end;
        int cost;

        public Pair(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Pair p) {

            return this.cost > p.cost ? 1 : -1;
        }
    }
}
