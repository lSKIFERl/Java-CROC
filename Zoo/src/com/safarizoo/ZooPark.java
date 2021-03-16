package com.safarizoo;

import java.util.ArrayList;
import java.util.Date;

public class ZooPark {

    private static ArrayList<Animal> animalList;
    private static ArrayList<Employee> employeeList;
    private static ArrayList<Aviary> aviaryList;

    public static void main(String[] args) {

        /*
        Сначала создаются списки животных, работников и вальеров, которые сразу же заполняются
        Списки взял чтобы не создавать 9 переменных
         */
        animalList = new ArrayList<>();
        employeeList = new ArrayList<>();
        aviaryList = new ArrayList<>();

        animalList.add(new Animal("Черепаха-философ", "Угвэй", 2000));
        animalList.add(new Animal("Малая мудрая панда", "Шифу", 67));
        animalList.add(new Animal("Большая глупая панда", "По", 20));

        employeeList.add(new Employee("Николай", "Дроздов", 10));
        employeeList.add(new Employee("Василий", "Фамилиевич", 17));
        employeeList.add(new Employee("Генадий", "Человеков", 26));

        aviaryList.add(new Aviary(13));
        aviaryList.add(new Aviary(8));
        aviaryList.add(new Aviary(19));

        /*
        Прикрепляем сотрудников и вальеры к животным
        Выводим все элементы, что у нас есть
         */
        for (int i = 0; i < 3; i++) {
            animalList.get(i).setAviary(aviaryList.get(i));
            animalList.get(i).setEmployee(employeeList.get(i));
            System.out.println("На содержании " + animalList.get(i).getName());
            System.out.println("Вальер " + aviaryList.get(i).getAviaryNumber());
            System.out.println("Сотрудник " + employeeList.get(i).getName());
        }

        animalList.remove(0); //удаляем Угвэя
        aviaryList.remove(0); //удаляем его варьер
        aviaryList.get(0).addCleaningStory(new Date()); //производим очистку в вальере Шифу
        animalList.get(1).addFeedStory(new Date(), employeeList.get(1)); //Кормим По печеньками
        animalList.get(0).addIllStory(new Date(), "Простуда"); //*Шифу простудился*
        employeeList.remove(2); // уволняем сотрудника за то, что кормил По печеньками

        //пользуясь прелестями ооп, а именно обращаемся к объектам, которые инициализировали, ранее через другие объекты
        // выводим информацию на терминал
        System.out.println("Вальер " + animalList.get(0).getAviary().getAviaryNumber() + " Очищен " +
                animalList.get(0).getAviary().getCleaningStory());

        System.out.println(animalList.get(0).getName() + " Заболел " + animalList.get(0).getIllStory().getIllName() +
                " " + animalList.get(0).getIllStory().getIllDate());

        System.out.println(animalList.get(1).getName() + " покормлен " +
                animalList.get(1).getFeedStory().getFeeder().getName());

        for (int i = 0; i < 2; i++) {
            System.out.println("На содержании " + animalList.get(i).getName());
            System.out.println("Сотрудник " + employeeList.get(i).getName());
        }
    }
}