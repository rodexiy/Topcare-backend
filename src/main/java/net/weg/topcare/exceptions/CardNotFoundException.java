package net.weg.topcare.exceptions;

public class CardNotFoundException extends Exception {
    public CardNotFoundException() {
        super("Card não encontrado");
    }
}
