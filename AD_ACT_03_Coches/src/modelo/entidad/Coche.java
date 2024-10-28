package modelo.entidad;

import java.io.Serializable;
import java.util.Objects;

import modelo.enumerado.TipoMotor;

public class Coche implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6722327725889073305L;
	
	private int id;
	private String marca;
	private String modelo;
	private TipoMotor tipoMotor;
	
	public Coche() {
		super();
	}
	
	public Coche(String marca, String modelo, TipoMotor tipoMotor) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.tipoMotor = tipoMotor;
	}
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
	public TipoMotor getTipoMotor() {
		return tipoMotor;
	}
	public void setTipoMotor(TipoMotor tipoMotor) {
		this.tipoMotor = tipoMotor;
	}
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", tipoMotor=" + tipoMotor + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id;
	}
	
	
}
