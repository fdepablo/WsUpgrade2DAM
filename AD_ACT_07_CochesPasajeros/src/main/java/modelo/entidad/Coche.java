package modelo.entidad;

import java.util.List;

public class Coche {
	private int id;
	private String marca;
	private String modelo;
	private int anio;
	private double km;
	
	private List<Pasajero> listaPasajeros;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public List<Pasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(List<Pasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	
	
}
