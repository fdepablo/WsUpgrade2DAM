package modelo.entidad;

import modelo.negocio.GestorCorreo;

public class Consumidor extends Thread{

	public String nombre;
	public GestorCorreo cola;
	
	public Consumidor(String nombre, GestorCorreo cola){
		super();
		this.nombre = nombre;
		this.cola = cola;
	}
	
	public void run(){
		//Podemos sustituir el 'while' por el 'for' si queremos que manda X mensajes
		while(true) {
			Correo mensaje = cola.getMensaje();
			System.out.println(nombre + " ha consumido el mensaje: " + mensaje);
		}
	}

}