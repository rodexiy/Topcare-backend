package net.weg.topcare.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Produto não encontrado!");
    }
}
