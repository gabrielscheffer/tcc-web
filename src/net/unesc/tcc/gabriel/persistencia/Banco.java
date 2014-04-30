/**
 * 
 */
package net.unesc.tcc.gabriel.persistencia;

import java.util.ArrayList;
import java.util.Collection;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;

/**
 * @author Gabriel
 *
 */
public class Banco {
	// BANCO MYSQL, utilizar HIBERNATE ou outro metodo para controlar as insercoes de forma automatica
	public static void salvarD(ArrayList<Dispositivo> displist) {
		// SALVAR DISPOSITIVOS ONLINE NA TABELA DE 'REGISTROS DE DISPOSITIVOS'
	}
	public static void salvarB(ArrayList<Bem> benslist){
		// SALVAR BENS NA TABELA DE 'REGISTROS DE BENS'
	}
	public static ArrayList<Bem> recuperar() {
		return null;
	}

}
