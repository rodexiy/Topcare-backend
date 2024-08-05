package net.weg.topcare.exceptions;

import net.weg.topcare.entity.ProductSpecification;

public class ProductSpecificationNotFound extends Exception{
    public ProductSpecificationNotFound(){
        super("Especificação de produto não encontrada!");
    }
}
