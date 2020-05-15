package bbm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author bbm
 * @date 2020/5/14
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> handleControllerException(Throwable ex) {
        log.error("Exception happen", ex);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR.getReasonPhrase(), INTERNAL_SERVER_ERROR);
    }
}
