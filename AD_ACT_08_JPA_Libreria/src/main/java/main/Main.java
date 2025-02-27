package main;

import java.util.List;

import com.google.protobuf.TextFormat.ParseException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.entity.Author;
import model.entity.Book;
import model.entity.Library;
import model.entity.PublisherHouse;

public class Main {

	/*
	 * Errores que me encontré:
	 * 
	 * Exception in thread "main" java.lang.NullPointerException: Cannot invoke
	 * "org.hibernate.mapping.ToOne.getReferencedEntityName()" because "toOne" is
	 * null
	 * 
	 * 1. Estaba usando el @Column(name="") en listas de relaciones. Hibernate no
	 * permite esto, porque solo es para tipos básicos
	 * 
	 * 2. Faltaba el @JoinTable en la relación @ManyToMany
	 * 
	 * @JoinTable( name = "books_libraries", joinColumns = @JoinColumn(name =
	 * "book_id"), inverseJoinColumns = @JoinColumn(name = "library_id"))
	 * 
	 * 3. Cuando ya había definido el @JoinTable, me dio error otra vez, porque
	 * donde estaba esta anotación, también tenía un mappedBy, pero donde esté esta
	 * no tiene que estar el mappedBy
	 * 
	 * 4. Al hacer los Select, si las entidades tienen en el toString() las
	 * referencias de sus relaciones, se produce un bucle infinito: Si tienes
	 * relaciones bidireccionales (por ejemplo, un Book tiene un PublisherHouse y un
	 * PublisherHouse tiene una lista de Book), al imprimir una de estas entidades,
	 * Hibernate intentará seguir la referencia y puede entrar en un ciclo infinito.
	 */

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibrary");
		EntityManager em = emf.createEntityManager();

		persistEntities(em);

		selectAllBooksWithPublisherAndAuthor(em);
		System.out.println();
		selectAllAuthorsWithBooks(em);
		System.out.println();
		selectAllLibrariesJustWithBooks(em);
		System.out.println();
		selectAllBooksAndLibrary(em);

	}

	private static void persistEntities(EntityManager em) {

		EntityTransaction et = em.getTransaction();
		et.begin();

		Author salinger = new Author();
		Author orwell = new Author();
		Author woolf = new Author();

		Book catcherInTheRye = new Book();
		Book nineStories = new Book();
		Book frannyAndZooey = new Book();
		Book nineteenEightyFour = new Book();
		Book animalFarm = new Book();
		Book downAndOutInParisAndLondon = new Book();
		Book mrsDalloway = new Book();
		Book toTheLighthouse = new Book();

		PublisherHouse hachette = new PublisherHouse();
		PublisherHouse penguin = new PublisherHouse();

		Library library1 = new Library();
		Library library2 = new Library();

		assignHachette(catcherInTheRye, nineStories, animalFarm, downAndOutInParisAndLondon, mrsDalloway, hachette);
		assignPenguin(frannyAndZooey, nineteenEightyFour, toTheLighthouse, penguin);
		assignNineStories(salinger, nineStories, hachette, library1, library2);
		assignFrannyAndZooey(salinger, frannyAndZooey, penguin, library2);
		assign1984(orwell, nineteenEightyFour, penguin, library1, library2);
		assignAnimalFarm(orwell, animalFarm, hachette, library2);
		assignDownAndOutInParisAndLondon(orwell, downAndOutInParisAndLondon, hachette, library1, library2);
		assignMrsDalloway(woolf, mrsDalloway, hachette, library1, library2);
		assignToTheLighthouse(woolf, toTheLighthouse, penguin, library1);
		assignCatcherInTheRye(salinger, catcherInTheRye, hachette, library2);
		assignOrwell(orwell, nineteenEightyFour, animalFarm, downAndOutInParisAndLondon);
		assignWoolf(woolf, mrsDalloway, toTheLighthouse);
		assignSalinger(salinger, catcherInTheRye, nineStories, frannyAndZooey);
		assignLibrary1(nineStories, nineteenEightyFour, downAndOutInParisAndLondon, mrsDalloway, toTheLighthouse,
				library1);
		assignLibrary2(catcherInTheRye, nineStories, frannyAndZooey, nineteenEightyFour, animalFarm,
				downAndOutInParisAndLondon, mrsDalloway, library2);

		em.persist(library1);
		em.persist(library2);

		et.commit();
	}

	private static void assignLibrary2(Book catcherInTheRye, Book nineStories, Book frannyAndZooey,
			Book nineteenEightyFour, Book animalFarm, Book downAndOutInParisAndLondon, Book mrsDalloway,
			Library library2) {
		library2.setAddress("Beautiful Avenue, 78");
		library2.setName("OMG Library");
		library2.setOwnerName("Kendrick Lamar");
		library2.setListOfBooks(List.of(catcherInTheRye, mrsDalloway, downAndOutInParisAndLondon, nineteenEightyFour,
				animalFarm, nineStories, frannyAndZooey));
	}

	private static void assignLibrary1(Book nineStories, Book nineteenEightyFour, Book downAndOutInParisAndLondon,
			Book mrsDalloway, Book toTheLighthouse, Library library1) {
		library1.setAddress("Fake Street, 18");
		library1.setName("Fake Library");
		library1.setOwnerName("Fakest Smith");
		library1.setListOfBooks(
				List.of(toTheLighthouse, mrsDalloway, downAndOutInParisAndLondon, nineteenEightyFour, nineStories));
	}

	private static void assignSalinger(Author salinger, Book catcherInTheRye, Book nineStories, Book frannyAndZooey) {
		try {
			salinger.setBirthdate("1919-01-01");
		} catch (ParseException | java.text.ParseException e) {
			e.printStackTrace();
		}
		salinger.setName("J.D. Salinger");
		salinger.setListOfBooks(List.of(catcherInTheRye, frannyAndZooey, nineStories));
	}

	private static void assignWoolf(Author woolf, Book mrsDalloway, Book toTheLighthouse) {
		try {
			woolf.setBirthdate("1882-01-25");
		} catch (ParseException | java.text.ParseException e) {
			e.printStackTrace();
		}
		woolf.setName("Virginia Woolf");
		woolf.setListOfBooks(List.of(toTheLighthouse, mrsDalloway));
	}

	private static void assignOrwell(Author orwell, Book nineteenEightyFour, Book animalFarm,
			Book downAndOutInParisAndLondon) {
		try {
			orwell.setBirthdate("1903-06-25");
		} catch (ParseException | java.text.ParseException e) {
			e.printStackTrace();
		}
		orwell.setName("George Orwell");
		orwell.setListOfBooks(List.of(downAndOutInParisAndLondon, animalFarm, nineteenEightyFour));
	}

	private static void assignCatcherInTheRye(Author salinger, Book catcherInTheRye, PublisherHouse hachette,
			Library library2) {
		catcherInTheRye.setAuthor(salinger);
		catcherInTheRye.setPrice(15);
		catcherInTheRye.setPublisher(hachette);
		catcherInTheRye.setListOfLibrariesWhereAvailable(List.of(library2));
		catcherInTheRye.setTitle("The Catcher in the Rye");
	}

	private static void assignToTheLighthouse(Author woolf, Book toTheLighthouse, PublisherHouse penguin,
			Library library1) {
		toTheLighthouse.setAuthor(woolf);
		toTheLighthouse.setPrice(22);
		toTheLighthouse.setPublisher(penguin);
		toTheLighthouse.setListOfLibrariesWhereAvailable(List.of(library1));
		toTheLighthouse.setTitle("To the Lighthouse");
	}

	private static void assignMrsDalloway(Author woolf, Book mrsDalloway, PublisherHouse hachette, Library library1,
			Library library2) {
		mrsDalloway.setAuthor(woolf);
		mrsDalloway.setPrice(20);
		mrsDalloway.setPublisher(hachette);
		mrsDalloway.setListOfLibrariesWhereAvailable(List.of(library1, library2));
		mrsDalloway.setTitle("Mrs Dalloway");
	}

	private static void assignDownAndOutInParisAndLondon(Author orwell, Book downAndOutInParisAndLondon,
			PublisherHouse hachette, Library library1, Library library2) {
		downAndOutInParisAndLondon.setAuthor(orwell);
		downAndOutInParisAndLondon.setPrice(16);
		downAndOutInParisAndLondon.setPublisher(hachette);
		downAndOutInParisAndLondon.setListOfLibrariesWhereAvailable(List.of(library1, library2));
		downAndOutInParisAndLondon.setTitle("Down and Out in Paris and London");
	}

	private static void assignAnimalFarm(Author orwell, Book animalFarm, PublisherHouse hachette, Library library2) {
		animalFarm.setAuthor(orwell);
		animalFarm.setPrice(10);
		animalFarm.setPublisher(hachette);
		animalFarm.setListOfLibrariesWhereAvailable(List.of(library2));
		animalFarm.setTitle("Animal Farm");
	}

	private static void assign1984(Author orwell, Book nineteenEightyFour, PublisherHouse penguin, Library library1,
			Library library2) {
		nineteenEightyFour.setAuthor(orwell);
		nineteenEightyFour.setPrice(18);
		nineteenEightyFour.setPublisher(penguin);
		nineteenEightyFour.setListOfLibrariesWhereAvailable(List.of(library1, library2));
		nineteenEightyFour.setTitle("1984");
	}

	private static void assignFrannyAndZooey(Author salinger, Book frannyAndZooey, PublisherHouse penguin,
			Library library2) {
		frannyAndZooey.setAuthor(salinger);
		frannyAndZooey.setPrice(14);
		frannyAndZooey.setPublisher(penguin);
		frannyAndZooey.setListOfLibrariesWhereAvailable(List.of(library2));
		frannyAndZooey.setTitle("Franny and Zooey");
	}

	private static void assignNineStories(Author salinger, Book nineStories, PublisherHouse hachette, Library library1,
			Library library2) {
		nineStories.setAuthor(salinger);
		nineStories.setPrice(12);
		nineStories.setPublisher(hachette);
		nineStories.setListOfLibrariesWhereAvailable(List.of(library1, library2));
		nineStories.setTitle("Nine Stories");
	}

	private static void assignPenguin(Book frannyAndZooey, Book nineteenEightyFour, Book toTheLighthouse,
			PublisherHouse penguin) {
		penguin.setAddress("Castelao Street, 50");
		penguin.setName("Penguin House");
		penguin.setListOfBooks(List.of(frannyAndZooey, nineteenEightyFour, toTheLighthouse));
	}

	private static void assignHachette(Book catcherInTheRye, Book nineStories, Book animalFarm,
			Book downAndOutInParisAndLondon, Book mrsDalloway, PublisherHouse hachette) {
		hachette.setAddress("New Boulevard, 812");
		hachette.setName("Hachette Publishing");
		hachette.setListOfBooks(
				List.of(nineStories, animalFarm, downAndOutInParisAndLondon, mrsDalloway, catcherInTheRye));
	}

	private static void selectAllBooksAndLibrary(EntityManager em) {
		String jpql = "SELECT b FROM Book b";
		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
		List<Book> listOfBooks = query.getResultList();

		listOfBooks.forEach(book -> {
			System.out.println("--------------------------------------------------");
			System.out.print("Book: ");
			System.out.println(book.getTitle());
			System.out.println("Libraries available: ");
			book.getListOfLibrariesWhereAvailable().forEach(library -> {
				System.out.print("\t- Library " + library.getName());
				System.out.println(" (" + library.getAddress() + ")");
			});
		});
	}

	private static void selectAllLibrariesJustWithBooks(EntityManager em) {
		String jpql = "SELECT l FROM Library l";
		TypedQuery<Library> query = em.createQuery(jpql, Library.class);
		List<Library> listOfLibraries = query.getResultList();

		listOfLibraries.forEach(library -> {
			System.out.println("--------------------------------------------------");
			System.out.print("Library: ");
			System.out.println(library.getName());
			System.out.println("Books available: ");
			System.out.println();
			library.getListOfBooks().forEach(book -> {
				System.out.println("\t- " + book.getTitle());
			});
		});

	}

	private static void selectAllAuthorsWithBooks(EntityManager em) {
		String jpql = "SELECT a FROM Author a";
		TypedQuery<Author> query = em.createQuery(jpql, Author.class);
		List<Author> listOfAuthors = query.getResultList();

		listOfAuthors.forEach(author -> {
			System.out.println("--------------------------------------------------");
			System.out.print("Author: ");
			System.out.println(author.getName());
			System.out.println("Books written: ");
			System.out.println();
			author.getListOfBooks().forEach(book -> {
				System.out.println("\t- " + book.getTitle());
			});
		});

	}

	private static void selectAllBooksWithPublisherAndAuthor(EntityManager em) {
		String jpql = "SELECT b FROM Book b";
		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
		List<Book> listOfBooks = query.getResultList();

		listOfBooks.forEach(book -> {
			System.out.println("--------------------------------------------------");
			System.out.print("Book: ");
			System.out.println(book.getTitle());
			System.out.print("Publisher: ");
			System.out.println(book.getPublisher().getName());
			System.out.print("Author: ");
			System.out.println(book.getAuthor().getName());
		});

	}
}
