package com.vadim.exceptions;

/**
 * Created by vadim on 09.04.2017.
 */
public class DublicateWordException extends Exception {

    public DublicateWordException(String word) {
        super("Найдено повторяющееся слово: " + word);
    }
}
