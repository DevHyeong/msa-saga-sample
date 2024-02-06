package org.example.demo.orderservice.exception;

public class InvalidMenuItemIdException extends RuntimeException {
    public InvalidMenuItemIdException(String menuItemId) {
        super(menuItemId + "is invalid menu id");
    }
}
