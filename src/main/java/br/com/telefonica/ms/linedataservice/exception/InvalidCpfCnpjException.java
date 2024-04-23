package br.com.telefonica.ms.linedataservice.exception;

public class InvalidCpfCnpjException extends RuntimeException{
    public InvalidCpfCnpjException(String s){
        super(s);
    }
}
