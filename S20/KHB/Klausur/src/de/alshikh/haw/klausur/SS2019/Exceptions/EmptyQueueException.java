package de.alshikh.haw.klausur.SS2019.Exceptions;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String errorMessage) {
        super(errorMessage);
    }
}
