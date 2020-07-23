
public class PriorityQueue {
	private QNode root;

	public PriorityQueue() {
		root = null;
	}

	public void add(int priority, Node n) {
		// question 3
		if (hasNext()) {
			root.addQNode(new QNode(priority, n));
		}
		else {
			root = new QNode(priority, n);
		}
	}

	public Node getNextHighestPriorityNode() {
		// question 3
		Node temp = root.getNode();
		root = root.getNext();

		return temp;
	}

	public boolean hasNext() {
		return (root!=null);
	}

	public String toString() {
		if(root == null) {
			return "(empty)";
		}
		return root.toString();
	}
}