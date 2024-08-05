package net.weg.topcare.exceptions;

public class BrandNotFoundException extends Exception{
    public BrandNotFoundException(){
        super("Marca não encontrada!");
    }
}
