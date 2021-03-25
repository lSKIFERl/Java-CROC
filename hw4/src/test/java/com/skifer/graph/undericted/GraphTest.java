package com.skifer.graph.undericted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    /**
     * Создаём вершины от 0 до 5
     */
    void addVertexes () {
        for (int i = 0; i < 6; i++) {
         graph.addVertex(i, i*i);
        }
    }

    @Test
    void testGetOutputComponents() {
        addVertexes();
        for (int i = 1; i < 6; i++) {
            graph.addEdge(0, i); // связываем вершины так, чтобы получилась "звезда"
        }
        graph.removeEdge(0, 1);

        for (SortedSet<Integer> i : graph.getOutputComponents()) {
            System.out.println(Arrays.toString(i.toArray()));
        }

        assertEquals(1, graph.getOutputComponents().get(0).first());
        assertTrue(graph.getOutputComponents().get(1).containsAll(Arrays.asList(0, 2, 3, 4, 5)));

    }

    @Test
    void testGetBoundComponentCount() {
        // Тест на количество компонент, при ещё несозданных вершинах
        assertEquals(0,  graph.getBoundComponentCount());
        addVertexes();
        assertEquals(6, graph.getBoundComponentCount()); // Каждая отдельная вершина - сама по себе компонента
        for (int i = 1; i < 6; i++) {
            graph.addEdge(0, i); // связываем вершины так, чтобы получилась "звезда"
        }
        assertEquals(1, graph.getBoundComponentCount()); // Звезда получилась, это 1 компонента
        graph.removeVertex(2); // удалив пару вершин, мы не удалим всю компоненту
        graph.removeVertex(4);
        assertEquals(1, graph.getBoundComponentCount());
        graph.addVertex(6, 36); // добавляем ещё вершину, теперь компоненты две
        assertEquals(2, graph.getBoundComponentCount());
        graph.addEdge(1, 6); // Связываем вершину из существующих компонент, они образуют 1 новую
        assertEquals(1, graph.getBoundComponentCount());
        graph.removeEdge(0, 1); // удалим ребро, создав две компоненты связности
        assertEquals(2, graph.getBoundComponentCount());
        graph.removeEdge(0, 1); // попробуем ещё раз тоже самое, ничего не изменилось
        assertEquals(2, graph.getBoundComponentCount());
        graph.removeEdge(1, 6);
        assertEquals(3, graph.getBoundComponentCount());
    }

    @Test
    void testAddVertex() {
        addVertexes();
        assertFalse(graph.addVertex(0, 55)); //Тест на попытку перезаписи
        assertFalse(graph.addVertex(null, null)); //Тест на попытку использовать null как id
        assertTrue(graph.addVertex(6, null)); //Тест на создание пустой вершины
    }

    @Test
    void testRemoveVertex() {
        addVertexes();
        assertTrue(graph.removeVertex(0)); //Тест на удаление вершины
        assertFalse(graph.removeVertex(6)); //Тест на удаление несуществующей вершины
        assertFalse(graph.removeVertex(null)); //Тест на использование null
    }

    @Test
    void testAddEdge() {
        addVertexes();
        assertTrue(graph.addEdge(0, 1)); //Тест на добавление ребра
        assertFalse(graph.addEdge(0, 6)); //Тест на добавление ребра между существующим и не существующим элементом
        assertFalse(graph.addEdge(-1, -2)); //Тест на связывание несуществующих вершин
        assertFalse(graph.addEdge(null, null)); //Тест на null
        assertFalse(graph.addEdge(0, null)); //Ещё 1 тест на null
    }

    @Test
    void testRemoveEdge() {
        addVertexes();
        assertTrue(graph.removeEdge(0, 0));
        graph.addEdge(0,5);
        assertTrue(graph.removeEdge(0,5));
        assertFalse(graph.removeEdge(0, 6));
        assertFalse(graph.removeEdge(null, 2));
    }
}