
public class HalifaxMap
{
    //object to access the graph class
    Graph graph = new Graph();


    //function to add a new intersection
    Boolean newIntersection(int x,int y)
    {

            //create a object of class vertex with the user given coordinates
            Vertex vertex = new Vertex(x, y);

            //check if the intersection is already present in the graph
            for (int i = 0; i < graph.vertexes.size(); i++) {
                if (graph.vertexes.get(i).x == vertex.x && graph.vertexes.get(i).y == vertex.y)
                    return false;
            }

            //add the intersection to the graph
            graph.addVertex(vertex);

            return true;

    }

    //function to define a new road
    Boolean defineRoad(int x1,int y1,int x2,int y2)
    {

            Vertex vertex1 = null, vertex2 = null;

            //traverse the vertexes list of the graph and assign the corresponding source and destination vertex object matching the user given coordinates
            for (int i = 0; i < graph.vertexes.size(); i++) {
                if (graph.vertexes.get(i).x == x1 && graph.vertexes.get(i).y == y1) {
                    vertex1 = graph.vertexes.get(i);
                }
                if (graph.vertexes.get(i).x == x2 && graph.vertexes.get(i).y == y2) {
                    vertex2 = graph.vertexes.get(i);
                }
            }

            //if the source or destination vertex is not found or a road is already defined between the two vertices
            if (vertex1 == null || vertex2 == null || vertex1.adjList.contains(vertex2))
                return false;

            else {
                //function call to add a new edge
                graph.addEdge(vertex1, vertex2);
            }


            return true;


    }

    //function used in dijkstra's algorithm which returns the index of the vertex with the minimum distance and not included in the shortest path tree
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize minimum value and minimum index
        int min = Integer.MAX_VALUE, min_index = -1;

        //traverse the graph
        for (int v = 0; v < graph.vertexes.size(); v++)
            //check if the vertex is not present in the minimum spanning tree and distance is less than or equal to minimum value
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

//function to return the shortest path from source to destination vertex
    void navigate(int x1,int y1,int x2,int y2)
    {

            Vertex vertex1, vertex2;

            //construct an array containing the lengths of the roads in the graph
            //initialize the array
            int graphweights[][] = new int[graph.vertexes.size()][graph.vertexes.size()];
            for (int i = 0; i < graph.vertexes.size(); i++) {
                vertex1 = graph.vertexes.get(i);
                for (int j = 0; j < graph.vertexes.size(); j++) {
                    vertex2 = graph.vertexes.get(j);
                    //if there is an edge between vertex1 and vertex2
                    if (i != j && vertex1.adjList.contains(vertex2)) {
                        //calculate the length of the road using the formula square_root( (x2 – x1)^2 + (y2 – y1)^2 )
                        graphweights[i][j] = (int) Math.sqrt(Math.pow((vertex2.x - vertex1.x), 2) + Math.pow((vertex2.y - vertex1.y), 2));
                    } else {
                        graphweights[i][j] = 0;
                    }
                }
            }

            //traverse the vertexes list of the graph and assign the corresponding source and destination vertex object matching the user given coordinates
            vertex1 = null;
            vertex2 = null;
            int src_index = 0, dest_index = 0;
            for (int i = 0; i < graph.vertexes.size(); i++) {
                if (graph.vertexes.get(i).x == x1 && graph.vertexes.get(i).y == y1) {
                    vertex1 = graph.vertexes.get(i);
                    src_index = i;
                }
                if (graph.vertexes.get(i).x == x2 && graph.vertexes.get(i).y == y2) {
                    vertex2 = graph.vertexes.get(i);
                    dest_index = i;
                }
            }

            //if the source or destination vertex is not found
            if (vertex1 == null || vertex2 == null)
                System.out.println("no path");


            else {

                //dijkstra' algorithm
                int V = graph.vertexes.size();

                // The output array. dist[i] will hold the shortest distance from src to i
                int dist[] = new int[V];

                // sptSet[i] will be true if vertex i is included in shortest path tree or shortest distance from src to i is finalized
                Boolean sptSet[] = new Boolean[V];

                // Initialize all distances as INFINITE and stpSet[] as false
                for (int i = 0; i < V; i++) {
                    dist[i] = Integer.MAX_VALUE;
                    sptSet[i] = false;
                }

                // Distance of source vertex from itself is always 0
                dist[src_index] = 0;


                // Find shortest path for all vertices
                for (int count = 0; count < V - 1; count++) {
                    // Pick the minimum distance vertex from the set of vertices not yet processed. u is always equal to src in first iteration.
                    int u = minDistance(dist, sptSet);

                    // Mark the picked vertex as processed
                    sptSet[u] = true;

                    // Update dist value and pathFromSourceList of the adjacent vertices of the picked vertex.
                    for (int v = 0; v < V; v++) {
                        // Update dist[v] and pathFromSourceList only if is not in sptSet, there is an edge from u to v, and total weight of path from src to v through u is smaller than current value of dist[v]
                        if (!sptSet[v] && graphweights[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graphweights[u][v] < dist[v]) {

                            //set the distance value of v
                            dist[v] = dist[u] + graphweights[u][v];

                            //clear the pathFromSourceList of v
                            graph.vertexes.get(v).pathFromSourceList.clear();

                            //add all the vertexes in the pathFromSourceList of u to v
                            for (int i = 0; i < graph.vertexes.get(u).pathFromSourceList.size(); i++)
                                graph.vertexes.get(v).pathFromSourceList.add(graph.vertexes.get(u).pathFromSourceList.get(i));
                            //add u to pathFromSourceList of v
                            graph.vertexes.get(v).pathFromSourceList.add(graph.vertexes.get(u));
                        }
                    }
                }

                //check if the distance value of destination vertex is not INFINITE
                if (dist[dest_index] == Integer.MAX_VALUE)
                    System.out.println("no path");
                else {

                    //print the pathFromSourceList of destination vertex along with the destination vertex
                    for (int i = 0; i < graph.vertexes.get(dest_index).pathFromSourceList.size(); i++)
                        System.out.print(graph.vertexes.get(dest_index).pathFromSourceList.get(i).x + "," + graph.vertexes.get(dest_index).pathFromSourceList.get(i).y + " --> ");
                    System.out.println(graph.vertexes.get(dest_index).x + "," + graph.vertexes.get(dest_index).y);
                }
            }

//clear the pathFromSourceList of all vertexes
for(int i = 0; i < graph.vertexes.size(); i++)
{
    graph.vertexes.get(i).pathFromSourceList.clear();
}

    }

}

