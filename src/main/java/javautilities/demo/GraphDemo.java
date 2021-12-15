package javautilities.demo;

import javautilities.util.SymmetricGraph;
import javautilities.util.Graph;

public class GraphDemo {

	public static void main(String[] args) {
		System.out.println("BASIC GRAPH");
		
		SymmetricGraph<Integer> graph = new SymmetricGraph<>();

//		graph.addPoint(1);
//		graph.addPoint(2);
//		graph.addPoint(3);
//		graph.addPoint(5);
		
		graph.addLine(1, 2);
		graph.addLine(1, 2);
		graph.addLine(2, 2);
		graph.addLine(2, 3);
		graph.addLine(7, 1);
		
		graph.removeLine(4, 5);
		graph.removeLine(1, 2);
		
//		System.out.println(graph);
		System.out.println(graph.getPoints());
		System.out.println(graph.getLines());
		System.out.println(graph.getNeighbours(1));
		System.out.println(graph.getNeighbours(2));
		System.out.println(graph.getNeighbours(3));
		System.out.println(graph.getNeighbours(4));
		System.out.println(graph.getNeighbours(5));
		System.out.println(graph.getNeighbours(7));
		System.out.println(graph.containsLine(1, 2));
		System.out.println(graph.containsLine(2, 3));
		System.out.println(graph.containsPoint(29));
		System.out.println(graph.containsPoint(5));
		
		graph.removePoint(2);
		
		System.out.println(graph);
		
		System.out.println();
		System.out.println("GRAPH");
		
		Graph<Integer, Integer> graph2 = new Graph<>();

//		graph.leftAddPoint(1);
//		graph.leftAddPoint(3);
//		graph.rightAddPoint(2);
//		graph.rightAddPoint(4);
		
		graph2.addLineFromLeft(1, 2);
		graph2.addLineFromLeft(3, 4);
		graph2.addLineFromRight(1, 2);

		graph2.addLineFromRight(3, 2);
		graph2.addLineFromLeft(3, 2);
		
		graph2.removeLineFromRight(3, 2);
		graph2.removeLineFromLeft(3, 2);
		
//		System.out.println(graph);
		System.out.println(graph2.getLeftPoints());
		System.out.println(graph2.getRightPoints());
		System.out.println(graph2.getLines());
		
		System.out.println(graph2.getNeighboursOfLeftPoint(1));
		System.out.println(graph2.getNeighboursOfLeftPoint(3));
		System.out.println(graph2.getNeighboursOfRightPoint(2));
		System.out.println(graph2.getNeighboursOfRightPoint(4));
		
		System.out.println(graph2.getNeighboursOfLeftPoint(2));
		System.out.println(graph2.getNeighboursOfRightPoint(1));
		
		System.out.println(graph2.containsLineFromLeft(1, 2));
		System.out.println(graph2.containsLineFromRight(1, 2));
		System.out.println(graph2.containsLineFromRight(3, 2));
		
		System.out.println(graph2.containsLineFromLeft(3, 2));
		System.out.println(graph2.containsLineFromRight(3, 2));
		
	}
	
}
