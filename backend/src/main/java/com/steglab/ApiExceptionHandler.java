package com.steglab;
import java.util.Map;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class ApiExceptionHandler{@ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("detail",e.getMessage()));}@ExceptionHandler(Exception.class) ResponseEntity<Map<String,String>> fail(Exception e){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("detail","The operation could not be completed safely."));}}
