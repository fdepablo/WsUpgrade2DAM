package main.persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import modelo.entidad.Coche;
import modelo.entidad.Persona;

public class MainJPABuscar {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entity manager creado");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Persona p = em.find(Persona.class, 1);
		em.clear();
		p = em.find(Persona.class, 1);
		p = em.find(Persona.class, 1);
		em.clear();
		//em.remove(p);
		p = em.merge(p);
		em.remove(p);
		System.out.println(p);
		tx.commit();
		em.close();
	}
}
