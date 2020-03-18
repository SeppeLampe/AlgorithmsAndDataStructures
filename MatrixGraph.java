/* This class represents a graph via an n*n Matrix.
 * Each Node of the Graph has a Vector containing all the information of that node in relation to other nodes.
 * This method is quite memory-heavy since for 10 nodes, no matter how many edges, it will consist of 10 Vectors of length 10.
 * Author: Seppe Lampe
 */
public class MatrixGraph 
{
	private Matrix data;
	
	public MatrixGraph(int nrNodes)
	{
		data = new Matrix(nrNodes);
	}
	
	// Adds an edge from a certain node to another node, with a certain weight to the Matrix.
	public void addEdge(int from, int to, double w)
	{
		data.set(from, to, w);
	}
	
	// Retrieves the information of an edge between two nodes in the Matrix.
	public double getEdge(int from, int to)
	{
		return (Double)data.get(from, to);
	}
}