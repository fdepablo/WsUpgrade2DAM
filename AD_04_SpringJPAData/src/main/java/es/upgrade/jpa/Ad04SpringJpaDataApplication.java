package es.upgrade.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import es.upgrade.jpa.modelo.entidad.Munion;
import es.upgrade.jpa.modelo.persistencia.MunionDao;

@SpringBootApplication
public class Ad04SpringJpaDataApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ad04SpringJpaDataApplication.class, args);
		
		MunionDao mDao = context.getBean("munionDao",MunionDao.class);
		
		Munion m = new Munion();
		m.setNombre("Bulbasur");
		m.setPuntosVida(20);
		m.setTipo("Planta");
		
		mDao.save(m);
		
		m = new Munion();
		m.setNombre("Pikachu");
		m.setPuntosVida(20);
		m.setTipo("Electrico");
		
		mDao.save(m);
		
		Optional<Munion> optional = mDao.findById(1);
		if(optional.isPresent()) {
			Munion m2 = optional.get();//Con get desenvolvemos el objeto real
			System.out.println("Primer" + m2);
		}
		
		m = mDao.findByNombreContaining("Pika");
		System.out.println("SEgun" + m);
		
		List<Munion> lista = mDao.findByPuntosVida(20);
		System.out.println("Tercer" + lista);
	}

}
