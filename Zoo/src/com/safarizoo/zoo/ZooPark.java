package com.safarizoo.zoo;

import com.safarizoo.animal.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZooPark {

    /**
     * Список животных
     */
    private List<Animal> animalList;

    /**
     * Список сотрудников
     */
    private List<Employee> employeeList;

    /**
     * Список вальеров
     */
    private List<Aviary> aviaryList;


    /**
     * Конструктор зоопарка
     */
    public ZooPark() {
        animalList = new ArrayList<>();
        employeeList = new ArrayList<>();
        aviaryList = new ArrayList<>();
    }

    /**
     * Получить животное
     * @param index индекс
     * @return животное
     */
    public Animal getAnimal(int index) {
        return animalList.get(index);
    }

    /**
     * Получить сотрудника
     * @param index индекс
     * @return сотрудник
     */
    public Employee getEmployee(int index) {
        return employeeList.get(index);
    }

    /**
     * Получить вольер
     * @param index индекс
     * @return вольер
     */
    public Aviary getAviary(int index) {
        return aviaryList.get(index);
    }

    public void cleanAviary(int index) {
        aviaryList.get(index).addCleaningStory(new Date());
    }

    /**
     * Добавляем сотрудника в зоопарк
     *
     * @param employee сотрудник
     */
    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    /**
     * Добавляем вольер в зоопарк
     *
     * @param aviary вольер
     */
    public void addAviary(Aviary aviary) {
        this.aviaryList.add(aviary);
    }

    /**
     * Добавление животного в зоопарк с привязкой сотрудника и вольера
     *
     * @param animal   животное
     * @param employee сотрудник
     * @param aviary   вольер
     */
    public void addAnimal(Animal animal, Employee employee, Aviary aviary) {
        if (employee.getAnimal() == null) {
            employee.setAnimal(animal);
        } else {
            System.out.println("Сотрудник " + employee + " занят");
        }
        if (aviary.getAnimal() == null) {
            aviary.setAnimal(animal);
        } else {
            System.out.println("Вольер " + aviary.getAviaryNumber() + " занят");
        }
        this.animalList.add(animal);
    }

    /**
     * Удаление животного и освобождение вальера и сотрудника
     *
     * @param animal   животное
     * @param employee сотрудник
     * @param aviary   вольер
     */
    public void deleteAnimal(int animal, int employee, int aviary) {
        try {
            if (employeeList.get(employee).getAnimal() == animalList.get(animal)) {
                employeeList.get(employee).unboundAnimal();
            } else {
                System.out.println(employeeList.get(employee).toString() + "\nНе привзяан к" +
                        animalList.get(animal).getName());
            }
        } catch (Exception e) {
            System.err.println("Такого сотрудника не существует");
        }
        try {

            if (aviaryList.get(aviary).getAnimal() == animalList.get(animal)) {
                aviaryList.get(aviary).unboundAnimal();
            } else {
                System.out.println(aviaryList.get(aviary).toString() + "\nНе привзяан к" + animalList.get(animal).getName());
            }
            this.animalList.remove(animal);
        } catch (Exception e) {
            System.err.println("Такого вольера не существует");
        }
    }

    public void deleteEmployee(int index) {
        this.employeeList.remove(index);
    }

    /**
     * Добавляем запись о кормёжке
     *
     * @param animal   животное
     * @param employee кормилец
     */
    public void feedTheAnimal(Animal animal, Employee employee) {
        animal.addFeedStory(new Date(), employee);
    }

    /**
     * Добавляем запись о болезни
     *
     * @param animal животное
     * @param ill    чем заболело
     */
    public void illAnimal(Animal animal, String ill) {
        animal.addIllStory(new Date(), ill);
    }
}