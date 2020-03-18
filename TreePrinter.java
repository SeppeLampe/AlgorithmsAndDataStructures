/* This is a class used to print Trees. It is based on the abstract class TreeAction.
 * This is the only class built not in-line based on TreeAction.
 * Author: Seppe Lampe
 */
public class TreePrinter extends TreeAction{
	public void run(Tree.TreeNode n) {
		System.out.println(n.getValue());
	}
}