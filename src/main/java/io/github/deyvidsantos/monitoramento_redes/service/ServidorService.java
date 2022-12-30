package io.github.deyvidsantos.monitoramento_redes.service;

import io.github.deyvidsantos.monitoramento_redes.models.Servidor;

import java.io.IOException;
import java.util.Collection;

public interface ServidorService {
    Servidor create(Servidor servidor);
    Servidor ping(String enderecoIp) throws IOException;
    Collection<Servidor> list(int limit);
    Servidor get(Long id);
    Servidor update(Servidor servidor);
    Boolean delete(Long id);
}
