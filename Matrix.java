/* This class represents an n*n Matrix.
 * Each node of the matrix has a Vector containing all the information of that node in relation to other nodes.
 * This method is quite memory-heavy since for 10 nodes, no matter how many edges, it will consist of 10 Vectors of length 10.
 * Author: Seppe Lampe
 */
public class Matrix 
{
	private Vector data;
	private int nrNodes;
	
	public Matrix(int nrNodes)
	{
		this.nrNodes = nrNodes;
		data = new Vector(nrNodes*nrNodes);									// allocate an N-by-N matrix where N = nrNodes
		for(int i=0; i<nrNodes; i++) {							//O(n)
			for(int x=0; x<nrNodes; x++) {
				data.set(i*nrNodes+x, 0);									//Set every element to 0 instead of null
			}
		}
	}
	
	public void set(int row, int col, Comparable weight)		//O(1)
	{
		data.set(row*nrNodes + col, weight); 								// store the weight at the given row and column.
	}

	public Comparable get(int row, int col)						//O(1)
	{
		return data.get(row*nrNodes + col);									// return the weight at the given row and column.
	}
	
	// Return a String notation of the matrix
	public String toString() {									//O(n)
		String result = new String();
		for(int i=0; i<nrNodes; i++) {
			for(int x=0; x<nrNodes; x++) {
				result += data.get(i*nrNodes+x).toString() + " ";
			}
			result += "    ";
		}
		return result;
	}
	
	public void print() {										//O(n)
		System.out.println(this.toString());
	}
	
}