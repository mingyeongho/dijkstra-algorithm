package Ex;

public class Main {
    static final int N = 6; // 노드 수
    static int M = Integer.MAX_VALUE;
    static int[][] node = {
            {0,7,9,M,M,14} // 0번 노드
            ,{M,0,10,15,M,M} // 1번 노드
            ,{M,M,0,11,M,2} // 2번 노드
            ,{M,M,M,0,6,M} // 3번 노드
            ,{M,M,M,M,0,M} // 4번 노드
            ,{M,M,M,M,9,0} // 5번 노드
    };
    
    static boolean[] visited = new boolean[N]; 
    static int[] cost = new int[N]; // 비용
    
    public static void main(String[] args) {
        
        dijkstra(0); // 0번 노드가 시작일 때
        for(int i=0; i<N; i++) {
            System.out.println(cost[i]); // 0번 노드가 시작점일 때 각 노드까지의 최소비용
        }
    }
    
    //가장 최소 거리를 가지는 정점을 반환
    static int getSmallIndex() { // 0번 노드에서 i번 노드까지의 최소 비용을 index에 
        int min = Integer.MAX_VALUE;
        int idx = -1;
        for(int i=0; i<N; i++) {
            if( !visited[i] && cost[i] < min) { // 아직 방문하지 않고  비용이 가장 낮은 노드
                min = cost[i]; // min에 0번 노드에서 i번 노드까지의 최소 비용을 대입
                idx = i; 
            }
        }
        return idx;
    }
    
    static void dijkstra(int start) {
        for(int i=0; i<N; i++) {
            //시작점에서 출발했을 때,모든 경로의 cost
            cost[i] = node[start][i]; // 시작 노드와 연결된 노드들 사이의 비용을 저장
        }
        
        //시작점 방문처리 
        visited[start] = true;
        
        for(int i=0; i<N-2; i++) { 
            //현재 방문하지 않은 node 중에서, 빠르게 도착할 수 있는
            //즉 최소 cost를 가지는 index를 가져옴.
            int curr = getSmallIndex();
            //가져온 index를 방문처리 
            visited[curr] = true;
            
            //위 노드와 인접한 노드들을 모두 확인
            for(int j=0; j<N; j++) {
                // 인접 노드 중, 방문하지 않은 곳이 있다면 
                if(!visited[j] && node[curr][j] != M && cost[j]> cost[curr]+node[curr][j]) {
                        cost[j] = cost[curr] + node[curr][j]; // 갱신
                }
            }
        }
    }
}