package org.agendafx.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Jean
 */
public class Person {

	private final StringProperty nombre;
	private final StringProperty apellido;
	private final StringProperty calle;
	private final IntegerProperty codpost;
	private final StringProperty ciudad;
	private final ObjectProperty<LocalDate> cump;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null,null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param nombre
	 * @param apellido
	 */
	public Person(String nombre, String apellido) {
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		
		// Some initial dummy data, just for convenient testing.
		this.calle = new SimpleStringProperty("Alguna calle");
		this.codpost = new SimpleIntegerProperty(1234);
		this.ciudad = new SimpleStringProperty("Alguna ciudad");
		this.cump = new SimpleObjectProperty<LocalDate>(LocalDate.of(2023, 5, 21));
	}
	
	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public StringProperty NombreProperty() {
		return nombre;
	}

	public String getApellido() {
		return apellido.get();
	}

	public void setApellido(String apellido) {
		this.apellido.set(apellido);
	}
	
	public StringProperty ApellidoProperty() {
		return apellido;
	}

	public String getCalle() {
		return calle.get();
	}

	public void setCalle(String calle) {
		this.calle.set(calle);
	}
	
	public StringProperty CalleProperty() {
		return calle;
	}

	public int getCodPost() {
		return codpost.get();
	}

	public void setCodPost(int codpost) {
		this.codpost.set(codpost);
	}
	
	public IntegerProperty CodPostProperty() {
		return codpost;
	}

	public String getCiudad() {
		return ciudad.get();
	}

	public void setCiudad(String ciudad) {
		this.ciudad.set(ciudad);
	}
	
	public StringProperty CiudadProperty() {
		return ciudad;
	}

	public LocalDate getCump() {
		return cump.get();
	}

	public void setCump(LocalDate cump) {
		this.cump.set(cump);
	}
	
	public ObjectProperty<LocalDate> CumpProperty() {
		return cump;
	}
}
