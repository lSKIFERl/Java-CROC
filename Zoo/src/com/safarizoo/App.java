package com.safarizoo;

import com.safarizoo.animal.Animal;
import com.safarizoo.zoo.Aviary;
import com.safarizoo.zoo.Employee;
import com.safarizoo.zoo.ZooPark;

import java.util.Date;

public class App {

    public static void main(String[] args) {

        /*
        Сначала создаются списки животных, работников и вальеров, которые сразу же заполняются
        Списки взял чтобы не создавать 9 переменных
         */

        ZooPark zooPark = new ZooPark();

        zooPark.addEmployee(new Employee("Николай", "Дроздов", 10));
        zooPark.addEmployee(new Employee("Василий", "Фамилиевич", 17));
        zooPark.addEmployee(new Employee("Джон", "Арбакл", 26));

        zooPark.addAviary(new Aviary(13));
        zooPark.addAviary(new Aviary(8));
        zooPark.addAviary(new Aviary(19));

        zooPark.addAnimal(new Animal("Тигр", "Раджа", 12), zooPark.getEmployee(0), zooPark.getAviary(0));
        zooPark.addAnimal(new Animal("Папуг", "Яга", 5), zooPark.getEmployee(1), zooPark.getAviary(1));
        zooPark.addAnimal(new Animal("Кот", "Горфилд", 8), zooPark.getEmployee(2), zooPark.getAviary(2) );

        /*
        Выводим все элементы, что у нас есть
         */
        for (int i = 0; i < 3; i++) {
            System.out.println("На содержании " + zooPark.getAnimal(i).getName());
            System.out.println(zooPark.getAviary(i).toString());
            System.out.println(zooPark.getEmployee(i).toString());
        }

        zooPark.feedTheAnimal(zooPark.getAnimal(0), zooPark.getEmployee(0)); //Кормим Раджу
        zooPark.cleanAviary(1); // Чистим клетку Яги
        zooPark.illAnimal(zooPark.getAnimal(1), "Птичий грипп"); // Замечаем, что Яга заболел
        zooPark.feedTheAnimal(zooPark.getAnimal(1), zooPark.getEmployee(0)); // Кормим Ягу, чтобы не болел
        zooPark.cleanAviary(2); // Чистим вольер Гарфилда
        zooPark.feedTheAnimal(zooPark.getAnimal(2), zooPark.getEmployee(2)); // Кормим Гарфилда
        zooPark.feedTheAnimal(zooPark.getAnimal(2), zooPark.getEmployee(2)); // Кормим Гарфилда
        zooPark.feedTheAnimal(zooPark.getAnimal(2), zooPark.getEmployee(2)); // I'm sorry Jon
        zooPark.deleteEmployee(2); // Гарфилд сожрал Джона
        Animal garfield = zooPark.getAnimal(2);
        zooPark.deleteAnimal(2, 2, 2); // Отдаём Гарфилда в надёжные руки

        ZooPark area3 = new ZooPark(); // создаём надёжные руки

        area3.addEmployee(new Employee("Джим", "Дэвис", 1989)); // добавляем сотрудника

        area3.addAviary(new Aviary(3166)); //Добавляем камеру содержания для объекта 3166

        // Помещаем Гарфилда в зону содержания 3, Назначаем ответственным Джима, помещаем в камеру SCP-3166
        area3.addAnimal(garfield, area3.getEmployee(0), area3.getAviary(0));
        area3.cleanAviary(0); // Чистим камеру содержания
        area3.feedTheAnimal(area3.getAnimal(0), area3.getEmployee(0)); // It's been too long since my last meat
        area3.deleteEmployee(0);

        System.out.println("Вольер " + zooPark.getAviary(0).getAviaryNumber() + " Очищен " +
                zooPark.getAviary(1).getCleaningStory());

        System.out.println(zooPark.getAnimal(1).getName() + " Заболел " +
                zooPark.getAnimal(1).getIllStory().getIllName() +
                " " + zooPark.getAnimal(1).getIllStory().getIllDate());

        System.out.println(area3.getAnimal(0).getName() + " покормлен " +
                area3.getAnimal(0).getFeedStory().getFeeder().getFirstName());

    }
}
