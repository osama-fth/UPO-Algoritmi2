package upo.algoritmi2.impl;

import upo.graph.base.Edge;
import upo.graph.base.Graph;
import upo.graph.base.VisitResult;

import java.util.*;

public class Graph20054809 implements Graph {
    
    protected Boolean[][] incidMatrix;                  // Matrice di incidenza
    protected int nextVertexId;                         // Prossimo ID da assegnare
    protected Map<Integer, Integer> vertexMap;          // Mappa da ID vertice a indice matrice
    protected Map<Integer, Integer> reverseVertexMap;   // Mappa da indice matrice a ID vertice
    protected List<Edge> edgeList;                      // Lista degli archi

    public Graph20054809() {
        this.incidMatrix = new Boolean[0][0];
        this.nextVertexId = 0;
        this.vertexMap = new HashMap<>();
        this.reverseVertexMap = new HashMap<>();
        this.edgeList = new ArrayList<>();
    }
    
    @Override
    public int addVertex() {
        int vertexId = nextVertexId++;
        int newSize = incidMatrix.length + 1;
        
        Boolean[][] newMatrix = new Boolean[newSize][edgeList.size()];
      
        for (int i = 0; i < incidMatrix.length; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                newMatrix[i][j] = incidMatrix[i][j];
            }
        }
        
        for (int j = 0; j < edgeList.size(); j++) {
            newMatrix[newSize-1][j] = false;
        }
        
        incidMatrix = newMatrix;
        
        vertexMap.put(vertexId, newSize-1);
        reverseVertexMap.put(newSize-1, vertexId);
        
        return vertexId;
    }
    
    @Override
    public boolean containsVertex(Integer vertex) {
        return vertex != null && vertexMap.containsKey(vertex);
    }
    
    @Override
    public void removeVertex(Integer vertex) throws NoSuchElementException {
        if (!containsVertex(vertex)) {
            throw new NoSuchElementException("Il vertice non esiste nel grafo");
        }
        
        int vertexIndex = vertexMap.get(vertex);
        
        List<Edge> edgesToRemove = new ArrayList<>();
        for (int j = 0; j < edgeList.size(); j++) {
            if (incidMatrix[vertexIndex][j]) {
                edgesToRemove.add(edgeList.get(j));
            }
        }
        
        for (Edge edge : edgesToRemove) {
            removeEdge(edge);
        }
        
        Boolean[][] newMatrix = new Boolean[incidMatrix.length - 1][edgeList.size()];
        int newRow = 0;
        
        for (int i = 0; i < incidMatrix.length; i++) {
            if (i != vertexIndex) {
                for (int j = 0; j < edgeList.size(); j++) {
                    newMatrix[newRow][j] = incidMatrix[i][j];
                }
                
                Integer vId = reverseVertexMap.get(i);
                if (vId != null) {
                    vertexMap.put(vId, newRow);
                    reverseVertexMap.put(newRow, vId);
                }
                
                newRow++;
            }
        }
        
        vertexMap.remove(vertex);
        reverseVertexMap.remove(vertexIndex);
        incidMatrix = newMatrix;
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        Integer source = edge.getSource();
        Integer target = edge.getTarget();
        
        if (source.equals(target)) {
            throw new IllegalArgumentException("Non sono ammessi cappi nel grafo");
        }
        
        if (!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Uno o entrambi i vertici non esistono nel grafo");
        }

        if (containsEdge(edge)) {
            throw new IllegalArgumentException("L'arco esiste già nel grafo");
        }
        
        Boolean[][] newMatrix = new Boolean[incidMatrix.length][edgeList.size() + 1];
        
        for (int i = 0; i < incidMatrix.length; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                newMatrix[i][j] = incidMatrix[i][j];
            }
            newMatrix[i][edgeList.size()] = false;
        }
        
        int sourceIndex = vertexMap.get(source);
        int targetIndex = vertexMap.get(target);
        newMatrix[sourceIndex][edgeList.size()] = true;
        newMatrix[targetIndex][edgeList.size()] = true;
        
        incidMatrix = newMatrix;
        edgeList.add(edge);
    }
    
    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        return edgeList.contains(edge);
    }
    
    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if (edge == null) {
            throw new IllegalArgumentException("L'arco non può essere null");
        }
        
        int edgeIndex = edgeList.indexOf(edge);
        if (edgeIndex == -1) {
            throw new NoSuchElementException("L'arco non esiste nel grafo");
        }
        
        Boolean[][] newMatrix = new Boolean[incidMatrix.length][edgeList.size() - 1];
        
        for (int i = 0; i < incidMatrix.length; i++) {
            int newCol = 0;
            for (int j = 0; j < edgeList.size(); j++) {
                if (j != edgeIndex) {
                    newMatrix[i][newCol++] = incidMatrix[i][j];
                }
            }
        }
        
        incidMatrix = newMatrix;
        edgeList.remove(edgeIndex);
    }
    
    @Override
    public Set<Integer> getAdjacent(Integer vertex) throws NoSuchElementException {
        if (!containsVertex(vertex)) {
            throw new NoSuchElementException("Il vertice non esiste nel grafo");
        }
        
        Set<Integer> adjacent = new HashSet<>();
        int vertexIndex = vertexMap.get(vertex);
        
        for (int j = 0; j < edgeList.size(); j++) {
            if (incidMatrix[vertexIndex][j]) {
                Edge edge = edgeList.get(j);
                if (edge.getSource().equals(vertex)) {
                    adjacent.add(edge.getTarget());
                } else {
                    adjacent.add(edge.getSource());
                }
            }
        }
        
        return adjacent;
    }
    
    @Override
    public boolean isAdjacent(Integer v1, Integer v2) throws IllegalArgumentException {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("I vertici non possono essere null");
        }
        
        if (!containsVertex(v1) || !containsVertex(v2)) {
            throw new IllegalArgumentException("Uno o entrambi i vertici non esistono nel grafo");
        }
        
        int index1 = vertexMap.get(v1);
        int index2 = vertexMap.get(v2);
        
        for (int j = 0; j < edgeList.size(); j++) {
            if (incidMatrix[index1][j] && incidMatrix[index2][j]) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public Set<Integer> getVertices() {
        return new HashSet<>(vertexMap.keySet());
    }
    
    @Override
    public Set<Edge> getEdges() {
        return new HashSet<>(edgeList);
    }
    
    @Override
    public int size() {
        return vertexMap.size();
    }
    
    @Override
    public boolean isDirected() {
        return false;
    }
    
    @Override
    public boolean isCyclic() {
        Set<Integer> visited = new HashSet<>();
        
        for (Integer vertex : getVertices()) {
            if (!visited.contains(vertex)) {
                if (isCyclicImpl(vertex, visited, null)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean isCyclicImpl(Integer vertex, Set<Integer> visited, Integer parent) {
        visited.add(vertex);
        
        for (Integer neighbor : getAdjacent(vertex)) {
            if (!visited.contains(neighbor)) {
                if (isCyclicImpl(neighbor, visited, vertex)) {
                    return true;
                }
            } else if (!neighbor.equals(parent)) {
                return true; 
            }
        }
        
        return false;
    }
    
    @Override
    public boolean isDAG() {
        return false;
    }
    
    @Override
    public Set<Set<Integer>> connectedComponents() {
        Set<Set<Integer>> components = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        
        for (Integer vertex : getVertices()) {
            if (!visited.contains(vertex)) {
                Set<Integer> component = new HashSet<>();
                dfsVisit(vertex, visited, component);
                components.add(component);
            }
        }
        
        return components;
    }
    
    private void dfsVisit(Integer vertex, Set<Integer> visited, Set<Integer> component) {
        visited.add(vertex);
        component.add(vertex);
        
        for (Integer neighbor : getAdjacent(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsVisit(neighbor, visited, component);
            }
        }
    }
    
    @Override
    public VisitResult getBFSTree(Integer startVertex) throws IllegalArgumentException {
        if (startVertex == null) {
            throw new IllegalArgumentException("Il vertice di partenza non può essere null");
        }
        
        if (!containsVertex(startVertex)) {
            throw new IllegalArgumentException("Il vertice di partenza non esiste nel grafo");
        }
        
        VisitResult result = new VisitResult(this);
        Queue<Integer> queue = new LinkedList<>();
        
        result.setColor(startVertex, VisitResult.Color.GRAY);
        result.setDistance(startVertex, 0.0);
        queue.add(startVertex);
        
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            
            for (Integer adj : getAdjacent(current)) {
                if (result.getColor(adj) == VisitResult.Color.WHITE) {
                    result.setColor(adj, VisitResult.Color.GRAY);
                    result.setParent(adj, current);
                    result.setDistance(adj, result.getDistance(current) + 1);
                    queue.add(adj);
                }
            }
            result.setColor(current, VisitResult.Color.BLACK);
        }
        
        return result;
    }
    
    @Override
    public VisitResult getDFSTree(Integer startVertex) throws IllegalArgumentException {
        if (startVertex == null) {
            throw new IllegalArgumentException("Il vertice di partenza non può essere null");
        }
        
        if (!containsVertex(startVertex)) {
            throw new IllegalArgumentException("Il vertice di partenza non esiste nel grafo");
        }
        
        VisitResult result = new VisitResult(this);
        int[] time = {0};
        
        dfsTreeVisit(startVertex, null, 0, time, result);
        
        return result;
    }
    
    private void dfsTreeVisit(Integer vertex, Integer parent, double distance, int[] time, VisitResult result) {

        time[0]++;
        result.setStartTime(vertex, time[0]);
        result.setColor(vertex, VisitResult.Color.GRAY);
        result.setDistance(vertex, distance);
        
        if (parent != null) {
            result.setParent(vertex, parent);
        }
        
        for (Integer adj : getAdjacent(vertex)) {
            if (result.getColor(adj) == VisitResult.Color.WHITE) {
                dfsTreeVisit(adj, vertex, distance + 1, time, result);
            }
        }
        result.setColor(vertex, VisitResult.Color.BLACK);
        time[0]++;
        result.setEndTime(vertex, time[0]);
    }
    
    @Override
    public VisitResult getDFSTOTForest(Integer startVertex) {
        throw new UnsupportedOperationException("Non supportato per grafi non orientati");
    }
    
    @Override
    public VisitResult getDFSTOTForest(Integer[] vertices) {
        throw new UnsupportedOperationException("Non supportato per grafi non orientati");
    }
    
    @Override
    public Integer[] topologicalSort() {
        throw new UnsupportedOperationException("Non supportato per grafi non orientati");
    }
    
    @Override
    public Set<Set<Integer>> stronglyConnectedComponents() {
        throw new UnsupportedOperationException("Non supportato per grafi non orientati");
    }
}
