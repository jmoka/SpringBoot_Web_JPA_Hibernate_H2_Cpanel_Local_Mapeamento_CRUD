package com.jotaempresas.curso.entity.execption;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

/*
 * Se você está fazendo API REST → use @RestControllerAdvice.
	Se você está fazendo MVC com páginas HTML → use @ControllerAdvice.
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExecptionErrorClasse> handleNotFound(ResourceNotFoundException ex,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		ExecptionErrorClasse err = new ExecptionErrorClasse(Instant.now(), status.value(), status.getReasonPhrase(),
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<ExecptionErrorClasse> handleBadRequest(ResourceBadRequestException ex,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExecptionErrorClasse err = new ExecptionErrorClasse(Instant.now(), status.value(), status.getReasonPhrase(),
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceConflictException.class)
	public ResponseEntity<ExecptionErrorClasse> handleConflict(ResourceConflictException ex,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ExecptionErrorClasse err = new ExecptionErrorClasse(Instant.now(), status.value(), status.getReasonPhrase(),
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExecptionErrorClasse> handleGeneric(Exception ex, HttpServletRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	    String msg = "Erro interno: " + ex.getMessage();

	    // 1️⃣ Violação de integridade (FK / UNIQUE)
	    if (ex instanceof org.springframework.dao.DataIntegrityViolationException) {
	        status = HttpStatus.CONFLICT;
	        msg = "Violação de integridade: dados duplicados ou vínculos existentes.";
	    } 
	    // 2️⃣ Restrição SQL direta
	    else if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
	        status = HttpStatus.BAD_REQUEST;
	        msg = "Violação de restrição no banco de dados.";
	    } 
	    // 3️⃣ Argumento inválido
	    else if (ex instanceof IllegalArgumentException) {
	        status = HttpStatus.BAD_REQUEST;
	        msg = "Parâmetro inválido: " + ex.getMessage();
	    } 
	    // 4️⃣ Nulo inesperado
	    else if (ex instanceof NullPointerException) {
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	        msg = "Erro inesperado: valor nulo acessado.";
	    }
	    // 5️⃣ Recurso não encontrado
	    else if (ex instanceof ResourceNotFoundException) {
	        status = HttpStatus.NOT_FOUND;
	        msg = ex.getMessage();
	    }
	    // 6️⃣ Conflito de recurso
	    else if (ex instanceof ResourceConflictException) {
	        status = HttpStatus.CONFLICT;
	        msg = ex.getMessage();
	    }
	    // 7️⃣ Erro de tipo de dados inválido
	    else if (ex instanceof org.springframework.http.converter.HttpMessageNotReadableException) {
	        status = HttpStatus.BAD_REQUEST;
	        msg = "Erro de leitura do corpo da requisição: formato inválido.";
	    }
	    
	    // 9️⃣ Erro de validação de parâmetros (@Valid)
	    else if (ex instanceof org.springframework.web.bind.MethodArgumentNotValidException) {
	        status = HttpStatus.BAD_REQUEST;
	        msg = ((org.springframework.web.bind.MethodArgumentNotValidException) ex)
	                  .getBindingResult()
	                  .getFieldErrors()
	                  .stream()
	                  .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                  .reduce("", (a, b) -> a + "; " + b);
	    }
	    // 🔟 Erro genérico
	    else {
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	        msg = "URL errada {Barra no Final, Faltando Paramentro , URL deve está Errada : " + ex.getMessage();
	    }

	    ExecptionErrorClasse err = new ExecptionErrorClasse(
	            Instant.now(),
	            status.value(),
	            status.getReasonPhrase(),
	            msg,
	            request.getRequestURI()
	    );

	    return ResponseEntity.status(status).body(err);
	}

}
