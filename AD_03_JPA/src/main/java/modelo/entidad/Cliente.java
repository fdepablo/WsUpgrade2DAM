package modelo.entidad;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String telefono;
	
	//Donde pongais la anotación @OneToOne, estará la FK
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private CuentaBancaria cuentaBancaria;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Tarea> listaTareas;
}
