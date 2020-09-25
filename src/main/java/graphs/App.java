/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package graphs;

import java.util.HashSet;
import java.util.Set;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new App().getGreeting());
        
        Graph friends = new Graph();
        friends.addNode("Nicholas");
        friends.addNode("Ginger");
        friends.addNode("Snowdrop");
        friends.addNode("Rufus");

        friends.addEdge("Nicholas", "Ginger");
        friends.addEdge("Nicholas", "Snowdrop");
        friends.addEdge("Nicholas", "Rufus");
        friends.addEdge("Rufus", "Ginger");

        System.out.println(friends);

        Graph cliques = new Graph();
        cliques.addNode("Gretchen Weiners");
        cliques.addNode("Karen Smith");
        cliques.addNode("Regina George");
        cliques.addNode("Katy Heron");

        cliques.addEdge("Gretchen Weiners", "Karen Smith");
        cliques.addEdge("Gretchen Weiners", "Regina George");
        cliques.addEdge("Karen Smith", "Regina George");

        System.out.println(cliques);



    }
    
    public static class Graph {
        public HashSet<Node> vertexes = new HashSet<>();
        
        public Graph(){}

        public String toString(){
            StringBuilder output = new StringBuilder();
            // Nicholas | Ginger, Snowdrop, Rufus \n
            for(Node vertex : vertexes){
                output.append(vertex.value);
                output.append(" | ");
                for(Node neighbor : vertex.getNeighbors()){
                    output.append(String.format("%s, ", neighbor.value));
                }
                output.append("\n");
            }
            return output.toString();
            // Snowdrop | Nicholas
            // Ginger | Nicholas, Rufus
        }
        
        public int addNode(String value){ // data structure's public methods should not talk about Nodes or require them
            Node newNode = new Node(value);
            vertexes.add(newNode);
            return vertexes.size();
        }

        public void addEdge(String one, String two) throws Exception {
            Node oneNode = null;
            Node twoNode = null;
            for(Node node : vertexes){
                if(node.value.equals(one)){
                    oneNode = node;
                }
                if(node.value.equals(two)){
                    twoNode = node;
                }
            }

            if(oneNode == null || twoNode == null){
                throw new Exception("that node is not there");
            }

            Edge edge = new Edge(oneNode, twoNode);
            oneNode.addEdge(edge); // hey Vertex one, you need to keep track of who you know
            twoNode.addEdge(edge); // samesies vertex two


        }
        
        
        
    }
    
    public static class Node {
        public String value;
        HashSet<Edge> neighbors = new HashSet<>();
        
        public Node(String value){
            this.value = value;
        }

        public void addEdge(Edge edge){
            this.neighbors.add(edge);
        }

        public HashSet<Node> getNeighbors(){
            HashSet<Node> allMyUniqueNeighbors = new HashSet<>();
            for(Edge edge : neighbors){
                if(!edge.firstNode.equals(this)){ // If the node stored in this edge is not me, it is my neighbor
                    allMyUniqueNeighbors.add(edge.firstNode);
                }
                if(!edge.secondNode.equals(this)){
                    // samesies, because every edge has me and another node, but I don;t know whether the first or second is actually me
                    allMyUniqueNeighbors.add(edge.secondNode);
                }
            }
            return allMyUniqueNeighbors;
        }
    }
    
    public static class Edge {
        Node firstNode;
        Node secondNode;

        public Edge(Node a, Node b) {
            this.firstNode = a;
            this.secondNode = b;
        }
    }
}


