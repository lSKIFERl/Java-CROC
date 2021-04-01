package menu;

import exceptions.IllegalInteraptException;
import exceptions.TooLongStringException;
import task.Status;
import task.Task;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс менюшки
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Мапа для хранения задач по их id */
    private Map<Integer, Task> taskMap;

    /** Последний Id для присвоения */
    private Integer lastId;

    /**
     * Класс менюшки
     */
    public Menu() {
        this.taskMap = new HashMap<>();
        this.lastId = 0;
    }

    public void callMenu() {

            /*
            Стартовое меню содержит в себе команды из 0 кейса, если выбрать 3 кейс и выбрать задачу, то
            появится ещё одно меню
             */
        int toBeContinued = 0;
        while(toBeContinued != 1) {
            switch (toBeContinued) {
                case 0:
                {
                    System.out.println(
                            "0 - команды\n" +
                            "1 - выход\n" +
                            "2 - добавить задачу\n" +
                            "3 - выбрать задачу\n");
                    break;
                }
                case 1: {
                    System.out.println("Сохраянемся и выходим\n");
                    break;
                }
                case 2: {
                    System.out.println("Введите название и описание");
                    //пытаемся создать новую задачу
                    try {
                        taskMap.put(lastId, new Task(new Scanner(System.in).nextLine(), new Scanner(System.in).nextLine(), lastId));
                        lastId++;
                        System.out.println("Задача успешно создана");
                    } catch (TooLongStringException | IllegalInteraptException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    //если есть задачи, то выводится их список и предлагается выбрать 1 из них дял редактирования
                    if(!taskMap.isEmpty()) {
                        System.out.println("Выберите задачу для редактирования");
                        for (Task task : taskMap.values()) {
                            System.out.println(task.getId() + " : " + task.getTaskName() + '\n');
                        }
                        Task task = null;
                        int taskId = -1;
                        try {
                            taskId = new Scanner(System.in).nextInt();
                            task = taskMap.get(taskId);
                        } catch (InputMismatchException e) {
                            System.err.println("Ожидался ввод числа");
                        }

                        if (task != null) {
                            // та самая, подменюшка редактирования
                            int choice = 0;
                            while (choice != 1) {
                                switch (choice) {
                                    case 0: {
                                        // помощь
                                        System.out.println(
                                                "0 - команды\n" +
                                                        "1 - выход\n" +
                                                        "2 - получить всю информацию\n" +
                                                        "3 - изменить исполнителя\n" +
                                                        "4 - изменить описание\n" +
                                                        "5 - отметить выполненной задачу\n" +
                                                        "6 - изменить название\n" +
                                                        "7 - удалить");
                                        break;
                                    }
                                    case 1: {
                                        // выход из редактирования
                                        System.out.println("выходим из режима редактирования");
                                        break;
                                    }
                                    case 2: {
                                        // вывод всей информации о задаче
                                        System.out.println(task.toString());
                                        break;
                                    }
                                    case 3: {
                                        // ввод нового исполнителя
                                        System.out.println("введите нового исполнителя: ");
                                        try {
                                            task.setExecutor(new Scanner(System.in).nextLine());
                                            task.setStatus(Status.IN_PROGRESS);
                                            System.out.println("успешно!");
                                        } catch (TooLongStringException | IllegalInteraptException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 4: {
                                        // ввод нового описания
                                        System.out.println("введите описание: ");
                                        try {
                                            task.setTaskDescription(new Scanner(System.in).nextLine());
                                            System.out.println("успешно!");
                                        } catch (TooLongStringException | IllegalInteraptException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 5: {
                                        // завершение задачи
                                        System.out.println("задача закрыта");
                                        task.setStatus(Status.DONE);
                                        break;
                                    }
                                    case 6: {
                                        // изменение названия
                                        System.out.println("введите новое название: ");
                                        try {
                                            task.setTaskName(new Scanner(System.in).nextLine());
                                            System.out.println("успешно!");
                                        } catch (TooLongStringException | IllegalInteraptException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 7: {
                                        // удаление задачи
                                        taskMap.remove(taskId);
                                        choice = 1;
                                        System.out.println("Взял, удалил(");
                                        break;
                                    }
                                }
                                if (choice != 1) {
                                    try {
                                        choice = new Scanner(System.in).nextInt();
                                    } catch (InputMismatchException e) {
                                        choice = 0;
                                        System.err.println("Ожидался ввод числа");
                                    }
                                }
                            }
                        } else {
                            System.out.println("такой задачи не существует");
                        }
                    }
                }
            }
            try {
                toBeContinued = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                toBeContinued = 0;
                System.err.println("Ожидался ввод числа");
            }
        }
    }
}
