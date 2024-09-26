package com.curso.estudiantes;

import com.curso.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);
	String nl = System.lineSeparator();
	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada.");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"Ejecutando metodo run de Spring");
	}
}
