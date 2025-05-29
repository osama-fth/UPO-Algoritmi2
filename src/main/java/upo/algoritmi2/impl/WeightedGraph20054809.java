package upo.algoritmi2.impl;

import upo.graph.base.Edge;
import upo.graph.base.WeightedGraph;
import java.util.*;

public class WeightedGraph20054809 extends Graph20054809 implements WeightedGraph {
    
    private Map<Edge, Double> weights;
    
    public WeightedGraph20054809() {
        super();
        this.weights = new HashMap<>();
    }
    
    public void addEdge(Edge edge, double weight) {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        if (!containsVertex(edge.getSource()) || !containsVertex(edge.getTarget())) {
            throw new IllegalArgumentException("Uno o entrambi i vertici non esistono nel grafo");
        }
        
        if (containsEdge(edge)) {
            throw new IllegalArgumentException("L'arco esiste già nel grafo");
        }
        
        super.addEdge(edge);
        weights.put(edge, weight);
    }

    @Override
    public void addEdge(Edge edge) {
        addEdge(edge, 1.0);
    }
    
    @Override
    public void removeEdge(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        if (!containsEdge(edge)) {
            throw new NoSuchElementException("L'arco non esiste nel grafo");
        }
        
        super.removeEdge(edge);
        weights.remove(edge);
    }
    
    @Override
    public void removeVertex(Integer vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Il vertice non può essere null");
        }

        super.removeVertex(vertex);
    }
    
    @Override
    public double getEdgeWeight(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        if (!containsEdge(edge)) {
            throw new NoSuchElementException("L'arco non esiste nel grafo");
        }
        
        return weights.getOrDefault(edge, 1.0);
    }
    
    @Override
    public void setEdgeWeight(Edge edge, double weight) {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        if (!containsEdge(edge)) {
            throw new NoSuchElementException("L'arco non esiste nel grafo");
        }
        
        weights.put(edge, weight);
    }
    
    @Override
    public WeightedGraph getBellmanFordShortestPaths(Integer sourceVertex) {
        throw new UnsupportedOperationException("Metodo non implementato");
    }
    
    @Override
    public WeightedGraph getDijkstraShortestPaths(Integer sourceVertex) {
        throw new UnsupportedOperationException("Metodo non implementato");
    }
    
    @Override
    public WeightedGraph getPrimMST(Integer sourceVertex) {
        throw new UnsupportedOperationException("Metodo non implementato");
    }
    
    @Override
    public WeightedGraph getKruskalMST() {
        throw new UnsupportedOperationException("Metodo non implementato");
    }
    
    @Override
    public WeightedGraph getFloydWarshallShortestPaths() {
        throw new UnsupportedOperationException("Metodo non implementato");
    }
}
