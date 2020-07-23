import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShortestPath {

	private HashMap<String,Node> nodes;
	
	public ShortestPath() {
		nodes = new HashMap<>();
	}
	
	public String nodesInShortestPath(String start, String end) {
		// question 5
		
		Node s = nodes.get(start);
		Node e = nodes.get(end);

		ArrayList<String> nodesInPath = new ArrayList<String>();

		int distance = e.getShortestDistance();

		Node currentNode = e;
		nodesInPath.add(currentNode.getLabel());

		while (distance != 0) {
			for (int i = 1; i < currentNode.getEdges().length() + 1; i++) {
				Edge currentEdge = currentNode.getEdges().get(i);

				int currentEdgeDistance = currentEdge.getDistance();
				int currentEdgeEndShortestDistance = currentEdge.getEndNode().getShortestDistance();
				if ((currentEdgeDistance + currentEdgeEndShortestDistance) == distance){
					distance -= currentEdgeDistance;
					currentNode = currentEdge.getEndNode();
					nodesInPath.add(currentNode.getLabel());
					break;
				}
			}
		}

		String result= "[";
		for (int i = nodesInPath.size() - 1; i >= 0; i--){
			result += nodesInPath.get(i);
		}
		result += "]";

		System.out.println(result);

		return result;
	}
	
	public int shortestPath(String start, String end) {
		// question 4

		Node s = nodes.get(start);
		Node e = nodes.get(end);
		PriorityQueue queue = new PriorityQueue();

		s.setAsStartNode();
		queue.add(0, s);

		while (queue.hasNext()) {
			Node currentNode = queue.getNextHighestPriorityNode();

			if (currentNode == e) {
				return e.getShortestDistance();
			}

			else {
				for (int i = 1; i < currentNode.getEdges().length() + 1; i++){
					Edge currentEdge = currentNode.getEdges().get(i);

					Node n = currentEdge.getEndNode();

					if (!n.hasBeenVisited()){
						int distance = currentEdge.getDistance();
						int currentDistance = currentNode.getShortestDistance();
						int currentShortestDistance = distance + currentDistance;

						if (currentShortestDistance < n.getShortestDistance()){
							n.updateShortestDistance(new Edge(distance, currentEdge.getStartNode(), currentEdge.getEndNode()));
						}

						queue.add(n.getShortestDistance(), n);
					}
				}
				currentNode.setVisited();
			}
		}
		
		return 0;
	}
	
	public void readInNodes(String file) {
		try {
			File f = new File(file);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = "";
			while((s=br.readLine()) !=null) {
				String[] info = s.split(" ");
				if(!nodes.containsKey(info[0])) {
					nodes.put(info[0], new Node(info[0]));
				}
				if(!nodes.containsKey(info[1])) {
					nodes.put(info[1], new Node(info[1]));
				}
				Node n1 = nodes.get(info[0]);
				Node n2 = nodes.get(info[1]);
				int i = Integer.valueOf(info[2]);
				Edge e = new Edge(i,n1,n2);
				nodes.get(info[0]).addEdge(e);
				Edge e2 = new Edge(i,n2,n1);
				nodes.get(info[1]).addEdge(e2);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printGraph() {
		System.out.println("Graph");
		for(String k : nodes.keySet()) {
			System.out.println(nodes.get(k));
		}
	}
}
