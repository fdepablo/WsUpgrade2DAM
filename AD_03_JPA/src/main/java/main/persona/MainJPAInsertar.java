package main.persona;

import java.util.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import modelo.entidad.Coche;
import modelo.entidad.Persona;

public class MainJPAInsertar {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entity manager creado");
		
		Persona p = new Persona();
		p.setNombre("Vegeta");
		p.setFechaNacimiento(new Date());
		p.setCasado(true);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		em.persist(p);
		p.setNombre("Krilin");
		em.persist(p);
		//no importa cuantas veces usemos persist (porque
		//siempre será el mismo objeto)
		//Si queremos dar de alta otro objeto, tendremos
		//que crearlo
		p = new Persona();
		p.setNombre("Goku");
		em.persist(p);
		
		p = new Persona();
		p.setNombre("Bulma");
		Persona p2 = em.merge(p);//devuleve la refencia del objeto gstionado
		p.setNombre("Mutenroshi");
		//aqui no se cambia nada, por el objeto bulma es diferene al objeto bulma que esta gestionado
		p2.setNombre("Mutenroshi");
		//em.clear();
		//em.detach(p2);
		tx.commit();
		
		em.close();
	}
}
