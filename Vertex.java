import java.util.ArrayList;
import java.util.List;

// A user define class to represent a vertex.
class Vertex {

        //variables to store x and y coordinates
        int x,y;

        //list to store adjacent vertices
        List<Vertex> adjList=new ArrayList();

        //list to store path from source vertex
        List<Vertex> pathFromSourceList=new ArrayList();

        //constructor to define x and y coordinates
        Vertex(int x,int y)
        {
            this.x=x;
            this.y=y;
        }

}
