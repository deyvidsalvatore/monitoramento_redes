package io.github.deyvidsantos.monitoramento_redes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCodigo;
    protected HttpStatus status;
    protected String motivo;
    protected String mensagem;
    protected String developerMensagem;
    protected Map<?, ?> dado;

}
