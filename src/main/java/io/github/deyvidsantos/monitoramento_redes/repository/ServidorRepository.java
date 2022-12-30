package io.github.deyvidsantos.monitoramento_redes.repository;

import io.github.deyvidsantos.monitoramento_redes.models.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    Servidor findByEnderecoIp(String enderecoIp);
}
