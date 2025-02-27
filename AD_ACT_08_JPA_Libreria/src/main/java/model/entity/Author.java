package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.protobuf.TextFormat.ParseException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "birth_date")
	@Temporal(value = TemporalType.DATE)
	private Date birthdate;
	@OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
	private List<Book> listOfBooks;
    public void setBirthdate(String birthdateStr) throws ParseException, java.text.ParseException {
        this.birthdate = dateFormat.parse(birthdateStr);
    }
    
}
