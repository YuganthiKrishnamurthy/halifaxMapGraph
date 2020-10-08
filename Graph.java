import java.util.ArrayList;
import java.util.List;

// A user define class to represent a graph.
class Graph {

    //list to store all vertexes in the graph
     List<Vertex> vertexes=new ArrayList();

    //function to add a new vertex
    void addVertex(Vertex vertex)
    {
        //add the vertex to the list
        vertexes.add(vertex);
    }

    //function to add a new edge
    void addEdge(Vertex vertex1,Vertex vertex2)
    {
        //Since a road is bidirectional add the second vertex to the adjacency list of the first vertex and vice versa
        vertex1.adjList.add(vertex2);
        vertex2.adjList.add(vertex1);
    }
}
