package nl.newnexus.vieropeenrij;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "It's not your turn!")
public class NotYourTurnException extends RuntimeException {
}
