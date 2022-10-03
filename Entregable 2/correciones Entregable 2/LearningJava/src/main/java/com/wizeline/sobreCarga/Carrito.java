package com.wizeline.sobreCarga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Carrito {
	private List<Producto> lista = new ArrayList<Producto>();

	
	// Sobre carga de constructores 
	
	public Carrito() {
		super();
	}

	public Carrito(List<Producto> lista) {
		super();
		this.lista = lista;
	}

	public void obtenerProducto(Producto p) {
		lista.add(p);
	}

	
	// Sobre carga de Metodos 
	
	public void obtenerProducto(String nombre, double precio) {
		lista.add(new Producto(nombre, precio));
	}

	public void obtenerProductos(List<Producto> productos) {
		lista.addAll(productos);
	}

	public void obtenerProductos(Producto... productos) {
		for (Producto f : productos) {
			lista.add(f);
		}
	}
	
	
	
	
	
}
