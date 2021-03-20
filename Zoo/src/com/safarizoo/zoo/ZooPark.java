package com.safarizoo.zoo;

import com.safarizoo.animal.*;

import java.util.Arrays;
import java.util.Date;

public class ZooPark {

    /**
     * Список животных
     */
    private Animal[] animalList;

    /**
     * Список сотрудников
     */
    private Employee[] employeeList;

    /**
     * Список вальеров
     */
    private Aviary[] aviaryList;


    /**
     * Конструктор зоопарка
     */
    public ZooPark() {
        animalList = new Animal[1];
        employeeList = new Employee[1];
        aviaryList = new Aviary[1];
    }

    /**
     * Получить животное
     * @param index индекс
     * @return животное
     */
    public Animal getAnimal(int index) {
        return animalList[index];
    }

    /**
     * Получить сотрудника
     * @param index индекс
     * @return сотрудник
     */
    public Employee getEmployee(int index) {
        return employeeList[index];
    }

    /**
     * Получить вольер
     * @param index индекс
     * @return вольер
     */
    public Aviary getAviary(int index) {
        return aviaryList[index];
    }

    public void cleanAviary(int index) {
        aviaryList[index].addCleaningStory(new Date());
    }

    /**
     * Добавляем сотрудника в зоопарк
     *
     * @param employee сотрудник
     */
    public void addEmployee(Employee employee) {
        this.employeeList[employeeList.length-1] = employee;
        employeeList = Arrays.copyOf(employeeList, employeeList.length + 1);
    }

    /**
     * Добавляем вольер в зоопарк
     *
     * @param aviary вольер
     */
    public void addAviary(Aviary aviary) {
        this.aviaryList[aviaryList.length-1] = aviary;
        aviaryList = Arrays.copyOf(aviaryList, aviaryList.length + 1);
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
        this.animalList[animalList.length-1] = animal;
        animalList = Arrays.copyOf(animalList, animalList.length + 1);
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
            if (employeeList[employee].getAnimal() == animalList[animal]) {
                employeeList[employee].unboundAnimal();
            } else {
                System.out.println(employeeList[employee].toString() + "\nНе привзяан к" +
                        animalList[animal].getName());
            }
        } catch (Exception e) {
            System.err.println("Такого сотрудника не существует");
        }
        try {

            if (aviaryList[aviary].getAnimal() == animalList[animal]) {
                aviaryList[aviary].unboundAnimal();
            } else {
                System.out.println(aviaryList[aviary].toString() + "\nНе привзяан к" + animalList[animal].getName());
            }
        } catch (Exception e) {
            System.err.println("Такого вольера не существует");
        }
        this.animalList[animal] = null;
    }

    /**
     * Удаление сотрудника
     * @param index номер в массиве
     */
    public void deleteEmployee(int index) {
        this.employeeList[index] = null;
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