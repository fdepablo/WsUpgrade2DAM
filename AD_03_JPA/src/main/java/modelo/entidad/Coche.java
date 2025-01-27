package modelo.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coches")
public class Coche {
	@Id
	private int id;
	private String marca;
	private String modelo;
	private long km;
}
