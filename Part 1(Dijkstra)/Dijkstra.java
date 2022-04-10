import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

// Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DijkstraSP.java.html
/**
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Dijkstra {
    private static double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private static DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private static IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public Dijkstra(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

        // check optimality conditions
        assert check(G, s);
    }

    public static void DijkstraVoid(EdgeWeightedDigraph G, int s) {
    	
    	distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
    	
    }
    
    // relax edge e and update pq if changed
    private static void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public static double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public static boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public static Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
    private static boolean check(EdgeWeightedDigraph G, int s) {

        // check that edge weights are non-negative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private static void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 * @throws Exception 
 ******************************************************************************/

    public static void main(String[] args) throws Exception {

    	fileReader files = new fileReader();
    	ArrayList<stops> stopsList = files.stopsList;
    	HashMap<Integer, stops> stopsHashMap = files.stopsHashMap;
    	ArrayList<stopTimes> stopTimesList = files.stopTimesList;
    	ArrayList<transfers> transfersList = files.transfersList;
    	
    	int largestStopID = 0;
    	
    	for (transfers iterator : transfersList) {
    		
    		if(iterator.from_stop_id > largestStopID) {
    			
    			largestStopID = iterator.from_stop_id;
    			
    		}
    		
    		if(iterator.to_stop_id > largestStopID) {
    			
    			largestStopID = iterator.to_stop_id;
    			
    		}
    		
    	}
    	
    	EdgeWeightedDigraph graph = new EdgeWeightedDigraph(largestStopID+1);
    	
    	int from_stop_id;
    	int to_stop_id;
    	int transfer_type;
    	int min_transfer_time;
    	double cost;
    	
		for(transfers iterator : transfersList){
			
			from_stop_id = iterator.from_stop_id;
			to_stop_id = iterator.to_stop_id;
			transfer_type = iterator.transfer_type;
			min_transfer_time = iterator.min_transfer_time;
			cost = iterator.cost;
			DirectedEdge edge = new DirectedEdge(iterator.from_stop_id, iterator.to_stop_id,iterator.cost);
			graph.addEdge(edge);
			
		}
		
		for(int i = 0; i+1 < stopTimesList.size(); i++) {
			
			if(stopTimesList.get(i).trip_id == stopTimesList.get(i+1).trip_id) {
				
				DirectedEdge sameTripEdge = new DirectedEdge(stopTimesList.get(i).stop_id, stopTimesList.get(i+1).stop_id, 1);
				graph.addEdge(sameTripEdge);
				
			}
			
		}
    	
    	Scanner s = new Scanner(System.in);
    	boolean quit = false;
    	
    	while(!quit) {
    		
    		System.out.println("What bus stop do you want to start at (or type the word 'quit' to leave the system):");
    		
    		if(s.hasNext("quit")) {
    			
    			quit = true;
    			break;
    			
    		}
    		
    		int startingStop = s.nextInt();
    		
    		System.out.println("What bus stop you you like to end at (or type the word 'quit' to leave the system):");
    		
    		if(s.hasNext("quit")) {
    			
    			quit = true;
    			break;
    			
    		}
    		
    		int endingStop = s.nextInt();
    		
    		double distance = 0;

    		DijkstraVoid(graph, startingStop);
    		distance = distTo(endingStop);
    		pathTo(startingStop);
    		
   			Iterable<DirectedEdge> totalPathTo = pathTo(endingStop);
   			
   			if(totalPathTo == null) {
   				
   				System.out.println("Invalid path, please enter another route");
   				
   			}
   			
   			else {
	   			
	   			for(DirectedEdge startStop: totalPathTo) {
	   				
	   					System.out.println("From - " + startStop.from() + ", to - "+ startStop.to() + ", cost - "+ startStop.weight());
	   			
	   			}
	
	   			System.out.println("OVERALL: From - " + startingStop + ", To - " + endingStop + ", Cost - "+  distance);
	   			
   			}
   			
    	}
    	
    }
    
}

