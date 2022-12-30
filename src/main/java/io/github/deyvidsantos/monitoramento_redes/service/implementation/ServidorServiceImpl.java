package io.github.deyvidsantos.monitoramento_redes.service.implementation;

import io.github.deyvidsantos.monitoramento_redes.models.Servidor;
import io.github.deyvidsantos.monitoramento_redes.repository.ServidorRepository;
import io.github.deyvidsantos.monitoramento_redes.service.ServidorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static io.github.deyvidsantos.monitoramento_redes.enumeration.Status.SERVER_DOWN;
import static io.github.deyvidsantos.monitoramento_redes.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServidorServiceImpl implements ServidorService {

    @Autowired
    private final ServidorRepository servidorRepository;

    @Override
    public Servidor create(Servidor servidor) {
        log.info("Salvando novo servidor: {}", servidor.getNome());
        servidor.setImageUrl(setServidorImageUrl());
        return servidorRepository.save(servidor);
    }


    @Override
    public Servidor ping(String enderecoIp) throws IOException {
        log.info("Pingando servidor IP: {}", enderecoIp);
        Servidor servidor = servidorRepository.findByEnderecoIp(enderecoIp);
        InetAddress endereco = InetAddress.getByName(enderecoIp);
        /* Se o endereço é comunicavél/alcançavel no tempo de mostre o estado Up (ON), se não o estado de Down (OFF)*/
        servidor.setStatus(endereco.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        servidorRepository.save(servidor);
        return servidor;
    }

    @Override
    public Collection<Servidor> list(int limit) {
        log.info("Buscando todos os servidores");
        return servidorRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Servidor get(Long id) {
        log.info("Buscando servidor por id: {}", id);
        return servidorRepository.findById(id).get();
    }

    @Override
    public Servidor update(Servidor servidor) {
        log.info("Atualizando servidor: {}", servidor.getNome());
        return servidorRepository.save(servidor);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Apagando servidor por ID: {}", id);
        servidorRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServidorImageUrl() {
        String[] imageNomes = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" +
                imageNomes[new Random().nextInt(4)]).toUriString();
    }
}
