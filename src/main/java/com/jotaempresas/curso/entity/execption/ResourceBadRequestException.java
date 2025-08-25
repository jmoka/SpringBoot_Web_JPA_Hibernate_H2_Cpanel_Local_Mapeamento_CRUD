package com.jotaempresas.curso.entity.execption;
//400 - Requisição inválida
public class ResourceBadRequestException extends RuntimeException {
 private static final long serialVersionUID = 1L;
 public ResourceBadRequestException(String msg) {
     super(msg);
 }
}
