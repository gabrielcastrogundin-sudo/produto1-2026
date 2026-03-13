package br.ifmg.produto1_2026.resources.exception;

public class databaseException extends RuntimeException{
    public databaseException(String mensage){
        super(mensage);
    }
    public databaseException(){}
}
