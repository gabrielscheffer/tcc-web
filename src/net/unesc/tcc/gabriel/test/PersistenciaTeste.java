/**
 * 
 */
package net.unesc.tcc.gabriel.test;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.persistencia.Banco;



/**
 * @author Gabriel
 *
 */
public class PersistenciaTeste {
	
	/**
	 * 
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Banco bd = new Banco();
		try {
			bd.startbd();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Bem> lista = bd.recuperartodos();
		for(Bem b: lista){
			System.out.println("-------------------------------------");
			System.out.println("Bem: " + b);
			System.out.println("count: " + Collections.frequency(lista, b));
			System.out.println("cd_bem: " + b.getCd_bem());
			System.out.println("ds_bem: " + b.getDs_bem());
			Dispositivo d = b.getDispositivo();
			System.out.println("\nDispositivo: " + d);
			System.out.println("cd_dispositivo: " + d.getCd_dispositivo());
			System.out.println("ds_dispositivo: " + d.getDs_dispositivo());
			System.out.println("Marca: " + d.getMarca());
			System.out.println("Firmware: " + d.getFirmware());
			System.out.println("ds_coordenadas: " + d.getDs_coordenadas());
			System.out.println("dt_ultima_consulta: " + d.getDt_ultima_consulta());
			
		}

	}

}
