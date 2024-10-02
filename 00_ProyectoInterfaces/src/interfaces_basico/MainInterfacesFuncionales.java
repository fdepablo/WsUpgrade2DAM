package interfaces_basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainInterfacesFuncionales {

	public static void main(String[] args) {
		//Existen predefinidas interfaces funcinales
		Supplier<Integer> numeroAleatorio100 = () -> {
			Random rd = new Random();
			int numero = rd.nextInt(1, 100);
			return numero;
		};

		System.out.println(numeroAleatorio100.get());
		
		numeroAleatorio100 = new Supplier<Integer>() {
			
			@Override
			public Integer get() {
				Random rd = new Random();
				int numero = rd.nextInt(1, 100);
				return numero;
			}
		};
		
		Consumer<String> log = (v) -> {
			Date date = new Date();
			System.out.println("*** " + date + " - " + v);
		};
		log.accept("NullPointerException! Eres un muñon");
		log.accept("Datos recibidos");
		
		Function<Double, Double> raizCuadrada = (v) -> {
			double raiz = Math.sqrt(v);
			return raiz;
		};
		
		System.out.println(raizCuadrada.apply(9.0));
		System.out.println(raizCuadrada.apply(25.0));
		
		List<Integer> listaEnteros = new ArrayList<Integer>();
		listaEnteros.add(1);
		listaEnteros.add(2);
		listaEnteros.add(3);
		
	
		//Recorrero con un for clasico
		for(Integer i : listaEnteros) {
			System.out.println(i);
		}
		
		System.out.println("--------lambda---------");
		Consumer<Integer> recorrer = new Consumer<Integer>() {
			
			@Override
			public void accept(Integer t) {
				System.out.println(t);
				
			}
		};
		listaEnteros.forEach(recorrer);
		
		Consumer<Integer> recorrerLambda = (v) -> {
			System.out.println(v);
		};
		listaEnteros.forEach(recorrerLambda);
		
		//Si solo tiene un parametro de entrada se pueden quitar los parentisis
		listaEnteros.forEach(v -> System.out.println(v));
	}

}
