package main;

import modelo.entidad.Consumidor;
import modelo.entidad.Productor;
import modelo.negocio.GestorCorreo;

public class MainColaCorreo {

	public static void main(String[] args) {
		GestorCorreo cola = new GestorCorreo();
		
		Productor p1 = new Productor("Producto 1",cola);
		Productor p2 = new Productor("Producto 2",cola);
		Productor p3 = new Productor("Producto 3",cola);
		
		Consumidor c1 = new Consumidor("Consumidor 1",cola);
		Consumidor c2 = new Consumidor("Consumidor 2",cola);
		
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
		c2.start();
	}

}
