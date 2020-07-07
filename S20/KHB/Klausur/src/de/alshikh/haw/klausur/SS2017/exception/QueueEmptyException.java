package de.alshikh.haw.klausur.SS2017.exception;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String message) {
        super(message);
    }
}
