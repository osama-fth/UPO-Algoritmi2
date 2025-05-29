package upo.algoritmi2.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import upo.graph.base.Edge;
import upo.graph.base.VisitResult;
import java.util.NoSuchElementException;
import java.util.Set;

class Graph20054809Test {

    Graph20054809 grafo;
    
    @BeforeEach
    void setUp() {
        grafo = new Graph20054809();
    }

    @Test
    @DisplayName("Test del costruttore")
    void testCostruttore() {
        assertNotNull(grafo.incidMatrix);
        assertEquals(0, grafo.incidMatrix.length);
    }
    
    @Test
    @DisplayName("Test aggiunta di vertici")
    void testAggiungiVertice() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        assertEquals(0, v1);
        assertEquals(1, v2);
        assertEquals(2, grafo.size());
    }
    
    @Test
    @DisplayName("Test verifica esistenza di un vertice")
    void testContieneVertice() {
        int v1 = grafo.addVertex();
        assertTrue(grafo.containsVertex(v1));
        assertFalse(grafo.containsVertex(99));
        
        // Test containsEdge
        int v2 = grafo.addVertex();
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(e1);
        
        assertTrue(grafo.containsEdge(e1));
        
        Edge e2 = Edge.getEdgeByVertexes(v1, 99);
        assertFalse(grafo.containsEdge(e2));
        
        // Test containsEdge con arco null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
                () -> grafo.containsEdge(null));
        assertEquals("L'arco non può essere null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test grafo orientato")
    void testEOrientato() {
        assertFalse(grafo.isDirected());
    }
    
    @Test
    @DisplayName("Test aggiunta di un arco")
    void testAggiungiArco() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(e1);
        
        assertTrue(grafo.containsEdge(e1));
        
        // Test aggiunta arco già esistente
        IllegalArgumentException exception0 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(e1));
        assertEquals("L'arco esiste già nel grafo", exception0.getMessage());
        
        // Test cappio
        Edge e2 = Edge.getEdgeByVertexes(v1, v1);
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(e2));
        assertEquals("Non sono ammessi cappi nel grafo", exception1.getMessage());
        
        // Test arco con vertice inesistente
        Edge e3 = Edge.getEdgeByVertexes(v1, 99);
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(e3));
        assertEquals("Uno o entrambi i vertici non esistono nel grafo", exception2.getMessage());
        
        // Test arco null
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(null));
        assertEquals("L'arco non può essere null", exception3.getMessage());
    }
    
    @Test
    @DisplayName("Test verifica adiacenza tra vertici")
    void testSonoAdiacenti() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(e1);
        
        assertTrue(grafo.isAdjacent(v1, v2));
        assertTrue(grafo.isAdjacent(v2, v1)); 
        
        // Test con vertici inesistenti
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.isAdjacent(v1, 99));
        assertEquals("Uno o entrambi i vertici non esistono nel grafo", exception1.getMessage());
        
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.isAdjacent(99, v1));
        assertEquals("Uno o entrambi i vertici non esistono nel grafo", exception2.getMessage());
        
        // Test con vertici null
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.isAdjacent(null, v1));
        assertEquals("I vertici non possono essere null", exception3.getMessage());
        
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.isAdjacent(v1, null));
        assertEquals("I vertici non possono essere null", exception4.getMessage());
    }
    
    @Test
    @DisplayName("Test rimozione di un arco")
    void testRimuoviArco() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(e1);
        grafo.removeEdge(e1);
        
        assertFalse(grafo.containsEdge(e1));
        assertFalse(grafo.isAdjacent(v1, v2));
        
        // Test rimozione arco inesistente
        Edge e2 = Edge.getEdgeByVertexes(v1, v2);
        NoSuchElementException exception1 = assertThrows(NoSuchElementException.class, 
                () -> grafo.removeEdge(e2));
        assertEquals("L'arco non esiste nel grafo", exception1.getMessage());
        
        // Test rimozione arco null
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.removeEdge(null));
        assertEquals("L'arco non può essere null", exception2.getMessage());
    }
    
    @Test
    @DisplayName("Test recupero di tutti gli archi")
    void testOttieniArchi() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        Edge e2 = Edge.getEdgeByVertexes(v2, v3);
        
        grafo.addEdge(e1);
        grafo.addEdge(e2);
        
        Set<Edge> edges = grafo.getEdges();
        assertEquals(2, edges.size());
        assertTrue(edges.contains(e1));
        assertTrue(edges.contains(e2));
    }
    
    @Test
    @DisplayName("Test rimozione di un vertice")
    void testRimuoviVertice() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        
        Edge e12 = Edge.getEdgeByVertexes(v1, v2);
        Edge e23 = Edge.getEdgeByVertexes(v2, v3);
        
        grafo.addEdge(e12);
        grafo.addEdge(e23);
        
        grafo.removeVertex(v2);
        
        assertFalse(grafo.containsVertex(v2));
        assertEquals(2, grafo.size());
        assertFalse(grafo.containsEdge(e12));
        assertFalse(grafo.containsEdge(e23));
        
        // Test per rimozione vertice inesistente
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, 
                () -> grafo.removeVertex(99));
        assertEquals("Il vertice non esiste nel grafo", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test ottenimento vertici")
    void testGetVertices() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        
        Set<Integer> vertices = grafo.getVertices();
        assertEquals(3, vertices.size());
        assertTrue(vertices.contains(v1));
        assertTrue(vertices.contains(v2));
        assertTrue(vertices.contains(v3));
        
        grafo.removeVertex(v2);
        vertices = grafo.getVertices();
        assertEquals(2, vertices.size());
        assertFalse(vertices.contains(v2));
    }

    @Test
    @DisplayName("Test rilevamento cicli")
    void testIsCyclic() {
        // Grafo senza cicli: v1 -- v2 -- v3
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v2));
        grafo.addEdge(Edge.getEdgeByVertexes(v2, v3));
        
        assertFalse(grafo.isCyclic());
        
        // Aggiungo un arco v1 -- v3
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v3));
        assertTrue(grafo.isCyclic());
    }

    @Test
    @DisplayName("Test isDAG")
    void testIsDAG() {
        assertFalse(grafo.isDAG());
    }

    @Test
    @DisplayName("Test componenti connesse")
    void testConnectedComponents() {
        // Componente 1: v1 -- v2 -- v3
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v2));
        grafo.addEdge(Edge.getEdgeByVertexes(v2, v3));

        // Componente 2: v4 -- v5
        int v4 = grafo.addVertex();
        int v5 = grafo.addVertex();
        grafo.addEdge(Edge.getEdgeByVertexes(v4, v5));
        
        Set<Set<Integer>> components = grafo.connectedComponents();
        assertEquals(2, components.size());
    }
 
    @Test
    @DisplayName("Test visita BFS")
    void testBFS() {
        //      v0
        //     /  \
        //    v1---v2
        //    |
        //    v3

        int v0 = grafo.addVertex();
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        grafo.addEdge(Edge.getEdgeByVertexes(v0, v1));
        grafo.addEdge(Edge.getEdgeByVertexes(v0, v2));
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v2)); 
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v3));
        
        VisitResult result = grafo.getBFSTree(v0);
        assertEquals(0.0, result.getDistance(v0));
        assertEquals(1.0, result.getDistance(v1));
        assertEquals(1.0, result.getDistance(v2));
        assertEquals(2.0, result.getDistance(v3));
        
        // Verifica i parent
        assertNull(result.getPartent(v0));
        assertEquals(v0, result.getPartent(v1));
        assertEquals(v0, result.getPartent(v2));
        assertEquals(v1, result.getPartent(v3));
        
        // Verifica i colori finali
        assertEquals(VisitResult.Color.BLACK, result.getColor(v0));
        assertEquals(VisitResult.Color.BLACK, result.getColor(v1));
        assertEquals(VisitResult.Color.BLACK, result.getColor(v2));
        assertEquals(VisitResult.Color.BLACK, result.getColor(v3));
        
        // Test con vertice inesistente
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.getBFSTree(99));
        assertEquals("Il vertice di partenza non esiste nel grafo", exception1.getMessage());
        
        // Test con vertice null
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.getBFSTree(null));
        assertEquals("Il vertice di partenza non può essere null", exception2.getMessage());
    }

    @Test
    @DisplayName("Test visita DFS")
    void testDFS() {
        //      v0
        //     /  \
        //    v1   v2
        //    |
        //    v3

        int v0 = grafo.addVertex();
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        grafo.addEdge(Edge.getEdgeByVertexes(v0, v1));
        grafo.addEdge(Edge.getEdgeByVertexes(v0, v2));
        grafo.addEdge(Edge.getEdgeByVertexes(v1, v3));
        
        VisitResult result = grafo.getDFSTree(v0);
        for (int i = v0; i <= v3; i++) {
            assertEquals(VisitResult.Color.BLACK, result.getColor(i));
        }
        
        // Verifico i parent
        assertNull(result.getPartent(v0));
        assertEquals(v0, result.getPartent(v1));
        assertEquals(v0, result.getPartent(v2));
        assertEquals(v1, result.getPartent(v3));
        
        // Verifico le distanze
        assertEquals(0.0, result.getDistance(v0));
        assertEquals(1.0, result.getDistance(v1));
        assertEquals(1.0, result.getDistance(v2));
        assertEquals(2.0, result.getDistance(v3));
        
        // Verifico i tempi
        for (int i = v0; i <= v3; i++) {
            assertTrue(result.getStartTime(i) < result.getEndTime(i));
        }
        
        // Test con vertice inesistente
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.getDFSTree(99));
        assertEquals("Il vertice di partenza non esiste nel grafo", exception1.getMessage());
        
        // Test con vertice null
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.getDFSTree(null));
        assertEquals("Il vertice di partenza non può essere null", exception2.getMessage());
    }
}
