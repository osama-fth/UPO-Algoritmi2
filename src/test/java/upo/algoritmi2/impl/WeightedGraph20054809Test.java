package upo.algoritmi2.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import upo.graph.base.Edge;
import java.util.NoSuchElementException;

class WeightedGraph20054809Test {

    WeightedGraph20054809 grafo;
    
    @BeforeEach
    void setUp() {
        grafo = new WeightedGraph20054809();
    }

    @Test
    @DisplayName("Test del costruttore")
    void testCostruttore() {
        assertNotNull(grafo.incidMatrix);
        assertEquals(0, grafo.incidMatrix.length);
    }
    
    @Test
    @DisplayName("Test aggiunta arco con peso specifico")
    void testAggiungiArcoConPeso() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge edge = Edge.getEdgeByVertexes(v1, v2);
        double peso = 5.5;
        grafo.addEdge(edge, peso);
        
        assertTrue(grafo.containsEdge(edge));
        assertEquals(peso, grafo.getEdgeWeight(edge), 0.001);
        
        // Test aggiunta arco già esistente
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(edge, 7.2));
        assertEquals("L'arco esiste già nel grafo", exception1.getMessage());
        
        // Test aggiunta arco con vertici inesistenti
        Edge edgeInesistente = Edge.getEdgeByVertexes(v1, 99);
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(edgeInesistente, 5.0));
        assertEquals("Uno o entrambi i vertici non esistono nel grafo", exception2.getMessage());
        
        // Test aggiunta arco null
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.addEdge(null, 5.0));
        assertEquals("L'arco non può essere null", exception3.getMessage());
    }
    
    @Test
    @DisplayName("Test aggiunta arco con peso predefinito")
    void testAggiungiArcoConPesoPredefinito() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge edge = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(edge);
        
        assertTrue(grafo.containsEdge(edge));
        assertEquals(1.0, grafo.getEdgeWeight(edge), 0.001);
    }
    
    @Test
    @DisplayName("Test modifica peso di un arco")
    void testImpostaPesoArco() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge edge = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(edge);
        
        // Test modifica peso
        double newWeight = 10.5;
        grafo.setEdgeWeight(edge, newWeight);
        assertEquals(newWeight, grafo.getEdgeWeight(edge), 0.001);
        
        // Test per arco inesistente
        Edge nonExistentEdge = Edge.getEdgeByVertexes(v1, 99);
        
        NoSuchElementException exception1 = assertThrows(NoSuchElementException.class, 
                () -> grafo.getEdgeWeight(nonExistentEdge));
        assertEquals("L'arco non esiste nel grafo", exception1.getMessage());
        
        NoSuchElementException exception2 = assertThrows(NoSuchElementException.class, 
                () -> grafo.setEdgeWeight(nonExistentEdge, 5.0));
        assertEquals("L'arco non esiste nel grafo", exception2.getMessage());
        
        // Test per arco null
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.getEdgeWeight(null));
        assertEquals("L'arco non può essere null", exception3.getMessage());
        
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.setEdgeWeight(null, 5.0));
        assertEquals("L'arco non può essere null", exception4.getMessage());
    }
    
    @Test
    @DisplayName("Test rimozione di un arco")
    void testRimuoviArco() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        
        Edge edge = Edge.getEdgeByVertexes(v1, v2);
        grafo.addEdge(edge, 4.2);
        grafo.removeEdge(edge);
        
        assertFalse(grafo.containsEdge(edge));
        NoSuchElementException exception1 = assertThrows(NoSuchElementException.class, 
                () -> grafo.getEdgeWeight(edge));
        assertEquals("L'arco non esiste nel grafo", exception1.getMessage());
        
        // Test rimozione arco inesistente
        Edge edgeInesistente = Edge.getEdgeByVertexes(v1, v2);
        NoSuchElementException exception2 = assertThrows(NoSuchElementException.class, 
                () -> grafo.removeEdge(edgeInesistente));
        assertEquals("L'arco non esiste nel grafo", exception2.getMessage());
        
        // Test rimozione arco null
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.removeEdge(null));
        assertEquals("L'arco non può essere null", exception3.getMessage());
    }
    
    @Test
    @DisplayName("Test rimozione di un vertice")
    void testRimuoviVertice() {
        int v1 = grafo.addVertex();
        int v2 = grafo.addVertex();
        int v3 = grafo.addVertex();
        
        Edge e1 = Edge.getEdgeByVertexes(v1, v2);
        Edge e2 = Edge.getEdgeByVertexes(v1, v3);
        Edge e3 = Edge.getEdgeByVertexes(v2, v3);
        grafo.addEdge(e1, 5.0);
        grafo.addEdge(e2, 7.5);
        grafo.addEdge(e3, 10.0);
        
        // Rimuovi un vertice collegato a due archi
        grafo.removeVertex(v1);
        assertFalse(grafo.containsVertex(v1));
        
        // Verifica che gli archi collegati siano stati rimossi
        assertFalse(grafo.containsEdge(e1));
        assertFalse(grafo.containsEdge(e2));
        
        // Test rimozione vertice non esistente
        NoSuchElementException exception1 = assertThrows(NoSuchElementException.class, 
                () -> grafo.removeVertex(99));
        assertEquals("Il vertice non esiste nel grafo", exception1.getMessage());
        
        // Test rimozione vertice null
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, 
                () -> grafo.removeVertex(null));
        assertEquals("Il vertice non può essere null", exception2.getMessage());
    }
}
