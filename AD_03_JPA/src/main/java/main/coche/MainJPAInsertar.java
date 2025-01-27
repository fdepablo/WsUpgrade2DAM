package main.coche;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import modelo.entidad.Coche;

public class MainJPAInsertar {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entity manager creado");
		
		Coche coche = new Coche();
		coche.setId(1);
		coche.setMarca("Toyota");
		coche.setModelo("Celica");
		coche.setKm(95000);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(coche);
		tx.commit();
		
		em.close();
	}
}
