package de.alshikh.haw.klausur.SS2017.exception;

public class QueueFullException extends RuntimeException {
    public QueueFullException(String message) {
        super(message);
    }
}
