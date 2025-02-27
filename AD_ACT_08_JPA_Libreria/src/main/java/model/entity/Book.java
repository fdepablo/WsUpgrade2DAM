package model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private double price;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_id_publisher", referencedColumnName = "id")
	private PublisherHouse publisher;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_id_author", referencedColumnName = "id")
	private Author author;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "books_libraries",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "library_id")
		)
	private List<Library> listOfLibrariesWhereAvailable;

}
