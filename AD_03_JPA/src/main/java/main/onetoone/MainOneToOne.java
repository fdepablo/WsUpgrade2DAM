package main.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modelo.entidad.Cliente;
import modelo.entidad.CuentaBancaria;

public class MainOneToOne {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		CuentaBancaria cb = new CuentaBancaria();
		cb.setNumero("167898F");
		cb.setEntidad("Caixa");
		
		Cliente c = new Cliente();
		c.setNombre("Bart Simpson");
		c.setTelefono("913567890");
		c.setCuentaBancaria(cb);
		
		em.persist(c);
		//em.persist(cb);
		et.commit();
		
		Cliente c2 = em.find(Cliente.class, 2);
		System.out.println(c2);
	}
}
