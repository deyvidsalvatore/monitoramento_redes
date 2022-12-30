package io.github.deyvidsantos.monitoramento_redes.resource;

import io.github.deyvidsantos.monitoramento_redes.enumeration.Status;
import io.github.deyvidsantos.monitoramento_redes.models.Response;
import io.github.deyvidsantos.monitoramento_redes.models.Servidor;
import io.github.deyvidsantos.monitoramento_redes.service.implementation.ServidorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/servidor")
@RequiredArgsConstructor
public class ServidorResource {
    private final ServidorServiceImpl servidorService;

    /**
     * O método getServidores() adiciona a hora/data, 30 servidores são retornados em cada página
     * Mostrando uma mensagem de servidor recuperado com status 200
     * @return Todos os servidores criados através do ResponseEntity
     */
    @GetMapping("/list")
    public ResponseEntity<Response> getServidores(){
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .dado(Map.of("servidores", servidorService.list(30)))
                    .mensagem("Servidor recuperado")
                    .status(OK)
                    .statusCodigo(OK.value())
                    .build()
        );
    }

    /**
     *
     * @param enderecoIp
     * O método pingServidores testa a conexão de ponto a ponto, adicionando data/hora, uma mensagem sucedida ou falhada
     * com status 200
     * @return Uma mensagem sucedida ou falhada de comunicação do servidor
     * @throws IOException
     */
    @GetMapping("/ping/{enderecoIp}")
    public ResponseEntity<Response> pingServidores(@PathVariable("enderecoIp") String enderecoIp) throws IOException {
        Servidor servidor = servidorService.ping(enderecoIp);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dado(Map.of("servidor", servidor))
                        .mensagem(servidor.getStatus() == Status.SERVER_UP ? "Ping sucedido" : "Ping falhado")
                        .status(OK)
                        .statusCodigo(OK.value())
                        .build()
        );
    }

    /**
     *
     * @param servidor
     * Método POST para salvar um servidor com seu endereço IP, nome, memória e afins com status de 201.
     * @return a criação do servidor com os parâmetros passado
     */
    @PostMapping("/save")
    public ResponseEntity<Response> pingServidores(@RequestBody @Valid Servidor servidor) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dado(Map.of("servidor", servidorService.create(servidor)))
                        .mensagem("Servidor foi criado")
                        .status(CREATED)
                        .statusCodigo(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServidor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dado(Map.of("servidor", servidorService.get(id)))
                        .mensagem("Servidor retornado")
                        .status(OK)
                        .statusCodigo(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServidor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .dado(Map.of("apagado", servidorService.delete(id)))
                        .mensagem("Servidor apagado")
                        .status(OK)
                        .statusCodigo(OK.value())
                        .build()
        );
    }

    @GetMapping(value = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
    }
}
