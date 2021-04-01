package com.skifer.prototype.exceptions;

/**
 * Возникает, если строка слишком длинная
 */
public class TooLongStringException extends Throwable {
    public String getMessage() {
        return "Слишком длинная строка!";
    }
}
