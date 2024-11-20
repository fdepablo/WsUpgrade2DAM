package modelo.entidad;

import modelo.negocio.GeneradorEmails;
import modelo.negocio.GestorCorreo;

public class Productor extends Thread{

	public String nombre;
	public GestorCorreo cola;
	
	public Productor(String nombre, GestorCorreo cola){
		super();
		this.nombre = nombre;
		this.cola = cola;
	}
	
	//Cada productor produce 10 mensajes
	public void run(){
		for(int i = 1;i <= 10;i++){
			Correo mensaje = GeneradorEmails.getInstance().generarEmail();
			cola.addMensaje(mensaje);
		}
	}

}