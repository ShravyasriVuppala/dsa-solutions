import java.util.*;
class Solution {
    public static class Edge {
        String node;
        double weight;
        Edge(String _node, double _weight){
            this.node = _node;
            this.weight = _weight;
        }
    };
    public double dfs(String current, String target, Map<String, List<Edge>> map, double product, Set<String> visited){
        //found target
        if(current.equals(target))
            return product;
        visited.add(current);
        //relax neighbors
        for(Edge edge : map.get(current)){
            if(visited.contains(edge.node))
                continue;

            double newProduct = product * edge.weight;
            double result = dfs(edge.node, target, map, newProduct, visited);

            if(result != -1.0)
                return result;
        }
        return -1.0; //if target is not in this path
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //adj map : operand -> <neighbor, value>
        Map<String, List<Edge>> map = new HashMap<>();
        int n = values.length;
        for(int i = 0; i < n; i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];
            //Add original edge
            map.computeIfAbsent(a, v -> new ArrayList<>()).add(new Edge(b, value));
            //Add reverse edge
            map.computeIfAbsent(b, v -> new ArrayList<>()).add(new Edge(a, 1.0 / value));
        }
        //For each query, DFS from operand 1 to operand 2
        int q = queries.size();
        double[] result = new double[q];
        for(int i = 0; i < q; i++){
            //if operands are not valid, set -1
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(!map.containsKey(a) || !map.containsKey(b)){
                result[i] = -1.0;
                continue;
            }
            //Start dfs
            Set<String> visited = new HashSet<>();
            result[i] = dfs(a, b, map, 1.0, visited);
        }
        return result;
    }
}