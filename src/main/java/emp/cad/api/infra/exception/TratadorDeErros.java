package emp.cad.api.infra.exception;



import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404(){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException exception){

        var erro = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(erro.stream().map(ErrosValidacaoDTO::new).toList());
    }

    private record ErrosValidacaoDTO(String campo, String mensagem){

        public ErrosValidacaoDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}
