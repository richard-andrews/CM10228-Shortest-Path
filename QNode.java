
public class QNode{
	private int priority;
	private QNode next;
	private QNode previous;
	private Node node;
	
	public QNode(int priority, Node n) {
		this.priority = priority;
		this.node = n;
		next = null;
		previous = null;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void addQNode(QNode n) {
		// question 2
		QNode nodeWithHigherPriority = this;
		boolean addedAtEnd = false;

		while (nodeWithHigherPriority.getPriority() <= n.getPriority()){
			if (nodeWithHigherPriority.getNext() == null){
				addedAtEnd = true;

				n.setNext(null);
				n.setPrevious(nodeWithHigherPriority);
				nodeWithHigherPriority.setNext(n);

				break;
			}

			nodeWithHigherPriority = nodeWithHigherPriority.getNext();
		}

		if (!addedAtEnd){
			n.setNext(nodeWithHigherPriority);

			QNode prevNode = nodeWithHigherPriority.getPrevious();
			prevNode.setNext(n);
			n.setPrevious(prevNode);

			nodeWithHigherPriority.setPrevious(n);
		}
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPrevious(QNode p) {
		previous = p;
	}
	
	public QNode getPrevious() {
		return previous;
	}
	
	public void setNext(QNode n) {
		next = n;
	}
	
	public QNode getNext() {
		return next;
	}
	
	public String toString() {
		String s = node.getLabel() + " (" + priority + ") : ";
		if (next!=null) {
			s += next.toString();
		}
		return s;
	}
	
}

