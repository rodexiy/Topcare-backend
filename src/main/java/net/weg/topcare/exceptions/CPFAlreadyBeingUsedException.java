package net.weg.topcare.exceptions;

public class CPFAlreadyBeingUsedException extends Exception {
    public CPFAlreadyBeingUsedException(){
        super("CPF já está sendo utilizado!");
    }
}
