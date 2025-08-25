package com.jotaempresas.curso.entity.execption;

//404 - Recurso não encontrado
public class ResourceNotFoundException extends RuntimeException {
 private static final long serialVersionUID = 1L;
 
 // Dessa forma o constr
 public ResourceNotFoundException(String msg, Object id) {
     super(msg +""+ id);
 }
}
