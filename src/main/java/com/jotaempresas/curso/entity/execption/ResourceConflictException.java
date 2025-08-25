package com.jotaempresas.curso.entity.execption;

//409 - Conflito (duplicidade)
public class ResourceConflictException extends RuntimeException {
 private static final long serialVersionUID = 1L;
 public ResourceConflictException(String msg) {
     super(msg);
 }
}
