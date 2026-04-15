package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Guest 
{
	private String nombre;
	private String numeroCelular;
	private String genero;
	private Date fechaNacimiento;
	private String direccion;
	private boolean aceptaTerminos;

	@Override
	public String toString() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		return getNombre() + ";" + getNumeroCelular() + ";" + getGenero() + ";" + sdf.format(getFechaNacimiento()) + ";" + getDireccion() + ";" + (getAceptaTerminos() ? "1" : "0");
	}

	public String getNombre() { return nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getNumeroCelular() { return numeroCelular; }

	public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }

	public String getGenero() { return genero; }

	public void setGenero(String genero) { this.genero = genero; }

	public Date getFechaNacimiento() { return fechaNacimiento; }

	public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	public String getDireccion() { return direccion; }

	public void setDireccion(String direccion) { this.direccion = direccion; }

	public boolean getAceptaTerminos() { return aceptaTerminos; }

	public void setAceptaTerminos(boolean aceptaTerminos) { this.aceptaTerminos = aceptaTerminos; }
}