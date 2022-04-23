package algorithm;

import java.io.*;
import java.util.*;

public class SW2477Car {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			st = new StringTokenizer(br.readLine(), " ");
			int[] as = new int[N];
			for (int i = 0; i < N; i++) {
				as[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			int[] bs = new int[M];
			for (int i = 0; i < M; i++) {
				bs[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			int[] ts = new int[K];
			for (int i = 0; i < K; i++) {
				ts[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] ALine = new int[N];
			int[] BLine = new int[M];
			Queue<Customer> waiting = new LinkedList<Customer>();
			int answer = 0;
			int time = 0;
			int idx = 0;
			ArrayList<Customer> customers = new ArrayList<>();
			while(true) {
				boolean finish = true;
				if(customers.size() == 0)
					finish = false;
				for (Customer customer : customers) {
					if(customer.B == -1) {
						finish = false;
						break;
					}
				}
				if(finish)
					break;
				
				for (int i = 0; i < N; i++) {
					if(ALine[i] == 0)
						continue;
					Customer customer = customers.get(ALine[i] - 1);
					if(customer.ATime + as[i] == time) {
						ALine[customer.A] = 0;
						waiting.offer(customer);
					}
				}
				
				for (int i = 0; i < M; i++) {
					if(BLine[i] == 0)
						continue;
					Customer customer = customers.get(BLine[i] - 1);
					if(customer.BTime + bs[i] == time)
						BLine[i] = 0;
				}
				
				while(!waiting.isEmpty()) {
					boolean success = false;
					for (int i = 0; i < M; i++) {
						if(BLine[i] == 0) {
							Customer customer = waiting.poll();
							BLine[i] = customer.num;
							customer.B = i;
							customer.BTime = time;
							if(customer.A == A && customer.B == B)
								answer += customer.num;
							success = true;
							break;
						}
					}
					if(!success)
						break;
				}
				
				for (int i = idx; i < K; i++) {
					if(time == ts[idx])
						customers.add(new Customer(++idx));
					else
						break;
				}
				
				for (int i = 0; i < customers.size(); i++) {
					Customer customer = customers.get(i);
					if(customer.A != -1)
						continue;
					boolean success = false;
					for (int j = 0; j < N; j++) {
						if(ALine[j] != 0)
							continue;
						ALine[j] = customer.num;
						customer.A = j;
						customer.ATime = time;
						success = true;
						break;
					}
					if(!success)
						break;
				}
				
				
				time++;
			}
			
			sb.append(answer == 0? -1 : answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Customer {
		int A = -1;
		int B = -1;
		int ATime = -1;
		int BTime = -1;
		int num;
		public Customer(int num) {
			this.num = num;
		}
	}
}
