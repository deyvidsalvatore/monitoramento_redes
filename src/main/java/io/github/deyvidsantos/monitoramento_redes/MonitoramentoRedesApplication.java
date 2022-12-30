package io.github.deyvidsantos.monitoramento_redes;

/* import io.github.deyvidsantos.monitoramento_redes.enumeration.Status;
import io.github.deyvidsantos.monitoramento_redes.models.Servidor;
import io.github.deyvidsantos.monitoramento_redes.repository.ServidorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean; */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonitoramentoRedesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoRedesApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner run(ServidorRepository servidorRepository){
		return args -> {
			servidorRepository.save(new Servidor(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			servidorRepository.save(new Servidor(null, "192.168.1.161", "Windows Server", "16 GB", "Servidor WEB", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
			servidorRepository.save(new Servidor(null, "192.168.1.162", "Windows 10", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			servidorRepository.save(new Servidor(null, "192.168.1.163", "Kali Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
		};
	} */

}
