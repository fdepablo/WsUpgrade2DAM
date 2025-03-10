package main.coche;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import modelo.entidad.Coche;

public class MainJPABorrar {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entity manager creado");
		
		EntityTransaction et = em.getTransaction();
		et.begin(); 
		Coche coche = em.find(Coche.class, 1);
		System.out.println(coche);
		em.remove(coche);
		coche = em.find(Coche.class, 1);
		System.out.println(coche);
		et.commit();
		
		em.close();
	}
}
