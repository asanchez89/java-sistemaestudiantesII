package com.curso.estudiantes;

import com.curso.estudiantes.modelo.Estudiante;
import com.curso.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

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
		var salir = false;
		var consola = new Scanner(System.in);

		while(!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOpciones(consola);
			} catch (Exception e) {
				System.out.println("Ocurrio un erro al ejecutar operacion:" + e.getMessage());
			}
		}
	}

	private boolean ejecutarOpciones(Scanner consola) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 ->{
				logger.info(nl+"Listado de Estudiantes...");
				var estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString()+nl)));
			}
			case 2 -> {
				logger.info(nl+"Introduce el ID del estudiante a buscar:");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null)
					logger.info(nl+"Estudiante encontrado: "+estudiante+nl);
				else
					logger.info(nl+"Estudiante no encontrado: "+idEstudiante+nl);
			}
			case 3 -> {
				logger.info(nl+"Agregar Estudiante: ");
				logger.info(nl+"Nombre: ");
				var nombre = consola.nextLine();
				logger.info(nl+"Apellido: ");
				var apellido = consola.nextLine();
				logger.info(nl+"Telefono: ");
				var tel = consola.nextLine();
				logger.info(nl+"Correo: ");
				var mail = consola.nextLine();
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(tel);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info(nl+"Estudiante agregado: "+estudiante+nl);
			}
			case 4 -> {
				logger.info(nl+"Modificar Estudiante: ");
				logger.info(nl+"Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null) {
					logger.info(nl + "Nombre: ");
					var nombre = consola.nextLine();
					logger.info(nl + "Apellido: ");
					var apellido = consola.nextLine();
					logger.info(nl + "Telefono: ");
					var tel = consola.nextLine();
					logger.info(nl + "Correo: ");
					var mail = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(tel);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info(nl+"Estudiante modificado: "+estudiante+nl);
				} else
					logger.info(nl+"Estudiante no encontrado: "+idEstudiante+nl);
			}
			case 5 -> {
				logger.info(nl+"Eliminar Estudiante: ");
				logger.info(nl+"Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info(nl+"Estudiante eliminado: "+estudiante+nl);
				} else
					logger.info(nl+"Estudiante no encontrado: "+idEstudiante+nl);
			}
			case 6 -> {
				logger.info(nl+"Hasta pronto...");
				salir = true;
			}
			default -> logger.info(nl+"Opcion no reconocida");
		}
		return salir;
	}

	private void mostrarMenu() {
		logger.info(nl);
		logger.info("""
			*** Sistema de Estudiantes ***
			1. Listar Estudiante
			2. Buscar Estudiante
			3. Agregar Estudiante
			4. Modificar Estudiante
			5. Eliminar Estudiante
			6. Salir
			Elige una opcion:
			""");
	}
}
