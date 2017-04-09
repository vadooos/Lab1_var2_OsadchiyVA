package com.vadim.exceptions;

/**
 * Created by vadim on 08.04.2017.
 */
public class IncorrectCharException extends Exception{

    public IncorrectCharException(int c) {
        super(String.format("Ошибка! Недопустимый символ: \'" + (char) c + "\'" + "   \\u" + c));
    }
}
