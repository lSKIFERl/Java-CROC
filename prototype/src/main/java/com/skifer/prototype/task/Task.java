package com.skifer.prototype.task;

import com.skifer.prototype.exceptions.IllegalInteraptException;
import com.skifer.prototype.exceptions.TooLongStringException;

import java.io.*;
import java.util.InputMismatchException;

/**
 * Класс, описывающий задачу
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Идентификатор задачи */
    private Integer id;

    /** Название задачи */
    private String taskName;

    /** Описание к задаче */
    private String taskDescription;

    /** Исполнитель */
    private String executor;

    /** Статус задачи */
    private Status status;

    /**
     * Класс задачи, принимает как аргументы название, описание и идентификатор
     * @param taskName Название задачи
     * @param taskDescription Описание
     * @param id Идентификатор
     * @throws TooLongStringException Исключение выбрасывается, если описание или название слишком длинные (300 и 15)
     * @throws InputMismatchException Выбрасывается, если вместо числа вводят любые другие символы
     */
    public Task(String taskName, String taskDescription, Integer id) throws TooLongStringException, IllegalInteraptException,
            InputMismatchException {
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        this.id = id;
        this.status = Status.UNFULFILLED;
        this.executor = "";
    }

    /**
     * Устанавливает название задаче
     * @param taskName Название задачи
     * @throws TooLongStringException Исключение выбрасывается, если название слишком длинное (15)
     * @throws IllegalInteraptException Выбрасывается при попытке изменить задачу в процессе или завершённую, просто...
     * почему бы и нет
     */
    public void setTaskName(String taskName) throws TooLongStringException, IllegalInteraptException {
        if(taskName.length() > 15) throw new TooLongStringException();
        else if (status != Status.UNFULFILLED && status != null) throw new IllegalInteraptException();
        else {
            this.taskName = taskName;
        }
    }

    /**
     * Установит описание
     * @param taskDescription Описание
     * @throws TooLongStringException Исключение выбрасывается, если описание слишком длинное (300)
     * @throws IllegalInteraptException Выбрасывается при попытке изменить задачу в процессе или завершённую
     */
    public void setTaskDescription(String taskDescription) throws TooLongStringException, IllegalInteraptException{
        if(taskDescription.length() > 300) throw new TooLongStringException();
        else if (status != Status.UNFULFILLED && status != null) throw new IllegalInteraptException();
        else {
            this.taskDescription = taskDescription;
        }
    }

    /**
     * Установит исполнителя
     * @param executor исполнитель
     * @throws TooLongStringException Исключение выбрасывается, если имя исполнителя слишком длинное (15)
     * @throws IllegalInteraptException Выбрасывается при попытке изменить задачу в процессе или завершённую
     */
    public void setExecutor(String executor) throws TooLongStringException, IllegalInteraptException{
        if (executor.length() > 15) throw new TooLongStringException();
        else if (status != Status.UNFULFILLED && status != null) throw new IllegalInteraptException();
        else {
            this.executor = executor;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", \nНазвание = '" + taskName + "'" +
                ", \nОписание='" + taskDescription + "'" +
                ", \nИсполнитель='" + executor + "'" +
                ", \nСтатус=" + status;
    }

}