import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    final static ArrayList<Edge> edges = new ArrayList<>();
    final static ArrayList<Edge> minimumSpaningTree = new ArrayList<>();
    static int[] parents;
    public static void sortEdge(){
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge o1, Edge o2) {
                return String.valueOf(o1.getWeight()).compareTo(String.valueOf(o2.getWeight()));
            }
        });
    }

    public static int findRoot(int weight) {
        while(weight != parents[weight]){
            weight = parents[weight];
        }
        int root = weight;
        return root;
    }

    public static void initParents() {
        parents = new int[edges.size()];
        for(int i=0; i<edges.size(); i++) {
            parents[i] = i;
        }
    }

    public static void union(int root1, int root2) {
        parents[root1] = root2;
    }

    public static void main(String[] args) {
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 0, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(0, 3, 3));
        edges.add(new Edge(3, 4, 5));
        edges.add(new Edge(4, 6, 6));
        edges.add(new Edge(4, 5, 7));
        edges.add(new Edge(0, 6, 4));

        System.out.println("Danh sach canh ban dau:");
        for(Edge edge: edges){
            System.out.println(edge.toString());
        }

        sortEdge();
        System.out.println("Danh sach canh da sap xep:");
        for(Edge edge: edges){
            System.out.println(edge.toString());
        }

        initParents();
        for(int i=0; i<edges.size(); i++){
            Edge currentEdge = edges.get(i);
            int root1 = findRoot(currentEdge.getVertex1());
            int root2 = findRoot(currentEdge.getVertex2());
            if(root1 != root2){
                minimumSpaningTree.add(currentEdge);
                union(root1, root2);
            }
        }
        System.out.println("Result:");
        int travelLength = 0;
        for(Edge edge: minimumSpaningTree){
            travelLength += edge.getWeight();
            System.out.println(edge.toString());
        }
        System.out.println("Travel length:" + travelLength);
    }
}