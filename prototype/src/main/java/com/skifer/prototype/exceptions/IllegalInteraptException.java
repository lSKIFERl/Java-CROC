package com.skifer.prototype.exceptions;

/**
 * Возникает при попытке изменить задачу "в процессе" или "завершённую"
 */
public class IllegalInteraptException extends Exception{
    public String getMessage() {
        return "Невозможно изменить задачу";
    }
}
