package com.skifer.graph.undericted;

import java.util.*;

/**
 * Класс неориентированного графа, принимающий любые значения
 */
public class Graph<T> {

    /** Количество компонент связности */
    private Integer boundComponentCount;

    /** Мапа компонент связности.
     *  Идентификатором здесь считается наименьшей идентификатор вершины
     */
    private SortedMap<Integer, SortedSet<Integer>> boundComponentTree;

    /** Мапа вершин */
    private Map<Integer, T> vertexMap;

    /** Сет рёбер */
    private Set<List<Integer>> edgeList;

    /** Отсортированный список компонент связности */
    private SortedSet<SortedSet<Integer>> outputComponents;

    /**
     * Класс неориентированного графа, принимающий любые значения
     */
    public Graph() {
        boundComponentCount = 0;
        boundComponentTree = new TreeMap<>();
        vertexMap = new HashMap<>();
        edgeList = new HashSet<>();
        outputComponents = new TreeSet<>(new Comparator<SortedSet<Integer>>() {
            @Override
            public int compare(SortedSet<Integer> o1, SortedSet<Integer> o2) {
                if (o1 != null & o2 != null) {
                    if (o1.size() >= o2.size())
                        return 1;
                    else
                        return -1;
                }
                return 0;
            }
        });
    }

    /**
     * Вернёт отсортированный список компонет связности
     * @return список компонент
     */
    public List<SortedSet<Integer>> getOutputComponents() {
        outputComponents = new TreeSet<>(new Comparator<SortedSet<Integer>>() {
            @Override
            public int compare(SortedSet<Integer> o1, SortedSet<Integer> o2) {
                if (o1 != null & o2 != null) {
                    if (o1.size() >= o2.size())
                        return 1;
                    else
                        return -1;
                }
                return 0;
            }
        });
        outputComponents.addAll(boundComponentTree.values());
        return new ArrayList<>(outputComponents);
    }

    /**
     * Вернёт число копомнент связности
     * @return количество компонент связности
     */
    public Integer getBoundComponentCount() {
        return boundComponentCount;
    }

    /**
     * Добавит вершину с идентификатором и любыми данными
     * при успешности операции вернёт истинну, иначе ложь
     * @param id идентификатор вершины
     * @param data данные
     * @return успешность операции
     */
    public boolean addVertex(Integer id, T data) {
        if (id != null & !vertexMap.containsKey(id)) {
            vertexMap.put(id, data);
            addEdge(id, id);
            return true;
        }
        return false;
    }

    /**
     * Удалит вершину по идентификатору
     * @param id идентификатор вершины
     * @return успешность операции
     */
    public boolean removeVertex(Integer id) {
        if(id != null & vertexMap.containsKey(id)) {
            Set<List<Integer>> set = new HashSet<>(edgeList);
            for (List<Integer> edge : set) {
                if (edge.contains(id))
                    removeEdge(edge.get(0), edge.get(1));
            }
            vertexMap.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Вернёт созданное ребро между двумя вершинами, порядок вводимых значений не важен
     * @param from идентификатор вершины от
     * @param to идентификатор вершины до
     * @return ребро
     */
    private List<Integer> createEdge(Integer from, Integer to) {

        List<Integer> edge = new ArrayList<>();

        /*
        Выбор того, какая вершина будет первой, а какая второй существенен, т.к.
        от этого будет зависеть id компоненты связности, который устанавливается
        как наименьший id вершины, содержащейся в компоненте
         */
        if (from <= to) {
            edge.add(from);
            edge.add(to);
        } else {
            edge.add(to);
            edge.add(from);
        }
        return edge;
    }

    /**
     * Вернёт сет компонент, содержащих вершины, соединённых ребром edge
     * @param edge ребро
     * @return сет компонент
     */
    private SortedSet<Integer> componentsWithVertex(List<Integer> edge) {

        /*
        Т.к. вершины могут принадлежать разным компонентам, их нужно записать
        а т.к. таких компонент будет максимум две, для простоты выберем сортированный сет
        чтобы можно было обращаться к последнему и первому элементам сета, а не гонять его
        через итератор. А сет нужен всего лишь для того чтобы не добавлять одну и ту же компоненту два раза,
        если вершины принадлежат только одной компоненте
         */
        SortedSet<Integer> componentsWithVertex = new TreeSet<>();

        /*
        Перебираем всё дерево компонент связности, перебирая
        для каждой компоненты все вершины и выискиваем компоненты,
        содержащие 1 из вершин
         */
        for (SortedSet<Integer> i: boundComponentTree.values()) {
            if (i.contains(edge.get(0)) || i.contains(edge.get(1))) {
                componentsWithVertex.add(i.first());
            }
        }
        return componentsWithVertex;
    }

    /**
     * Добавит ребро между двумя вершинами
     * @param from начало
     * @param to конец
     * @return успешность операции
     */
    public boolean addEdge(Integer from, Integer to) {

        // Сначала создаётся список, содержащий в себе 2 вершины, это ребро
        if (vertexMap.containsKey(from) & vertexMap.containsKey(to)) {
            List<Integer> edge = createEdge(from, to);
            edgeList.add(edge);

        /*
        Теперь нужно найти компоненты связности, в которых есть наши вершины
        Это нужно на тот случай, если ребро связало вершины из разных компонент, а значит и сами компоненты
         */
                SortedSet<Integer> componentsWithVertex = componentsWithVertex(edge);

        /*
        Если нет компонент, содержащих одну из вершин, то создаём новую компоненту и добавлем туда обе вершины
        Если есть, то добавляем в неё наше вершины. Если их было несколько, то объединяем их в одну и в неё вершины
         */
                if (componentsWithVertex.isEmpty()) {
                    SortedSet<Integer> boundComponents = new TreeSet<>();
                    boundComponents.add(from);
                    boundComponents.add(to);
                    boundComponentTree.put(boundComponents.first(), boundComponents);
                    boundComponentCount = boundComponentTree.size();
                } else {
                    if (!componentsWithVertex.first().equals(componentsWithVertex.last())) {
                        boundComponentTree.get(componentsWithVertex.first())
                                .addAll(boundComponentTree.get(componentsWithVertex.last()));
                        boundComponentTree.remove(componentsWithVertex.last());
                        boundComponentCount = boundComponentTree.size();
                    }
                    boundComponentTree.get(componentsWithVertex.first()).add(from);
                    boundComponentTree.get(componentsWithVertex.first()).add(to);
                }
                return true;
            }
        return false;
    }

    /**
     * Удаляет ребро между двумя вершинами
     * @param from начало ребра
     * @param to конец ребра
     * @return успешность операции
     */
    public boolean removeEdge(Integer from, Integer to) {

        if (vertexMap.containsKey(from) & vertexMap.containsKey(to)) {

            List<Integer> edge = createEdge(from, to);

            // Проверяем есть ли вообще такое ребро
            if (edgeList.contains(edge)) {

                    SortedSet<Integer> componentsWithVertex = componentsWithVertex(edge);

                    if (!componentsWithVertex.isEmpty()) {

                        Set<Integer> fromSet = new HashSet<>(), toSet = new HashSet<>();

                        // Собираем все рёбра, которые присоединены к обеим вершинам
                        for (List<Integer> j : edgeList) {
                            if (j.contains(from) && !j.contains(to)) {
                                fromSet.addAll(j);
                            }
                            if (j.contains(to) && !j.contains(from)) {
                                toSet.addAll(j);
                            }
                        }

                /*
                Проверяем на наличие вершину, которая всё ещё связывает компоненту,
                то есть есть в обеих разбиениях компоненты
                 */
                        boolean contain = false;
                        if (fromSet.size() > toSet.size()) {
                            for (Integer i : toSet) {
                                if (fromSet.contains(i)) {
                                    contain = true;
                                    break;
                                }
                            }
                        } else {
                            for (Integer i : fromSet) {
                                if (toSet.contains(i)) {
                                    contain = true;
                                    break;
                                }
                            }
                        }

                /*
                Если два разбиения ничего не связывает, то удаляем начальную компоненту
                и из этих двух разбиений строим две новые компоненты
                 */
                        if (!contain && !from.equals(to)) {
                            boundComponentTree.remove(componentsWithVertex.first());
                            for (Integer i : fromSet) {
                                addEdge(from, i);
                            }
                            for (Integer i : toSet) {
                                addEdge(to, i);
                            }
                        }
                    }
                // Удаляем ребро из списка рёбер
                edgeList.remove(edge);
                return true;
            }
        }
        return false;
    }
}
