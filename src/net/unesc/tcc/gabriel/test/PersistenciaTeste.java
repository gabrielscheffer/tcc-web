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
		List<Bem> lista = bd.recuperartodosBens();
		for(Bem b: lista){
			System.out.println("-------------------------------------");
			System.out.println("Bem: " + b);
			System.out.println("count: " + Collections.frequency(lista, b));
			System.out.println("cd_bem: " + b.getCdBemId());
			System.out.println("ds_bem: " + b.getDescricaoBem());
			Dispositivo d = b.getDispositivo();
			System.out.println("\nDispositivo: " + d);
			System.out.println("cd_dispositivo: " + d.getCdDispositivoId());
			System.out.println("ds_dispositivo: " + d.getDsDispositivo());
			System.out.println("Marca: " + d.getMarca());
			System.out.println("Firmware: " + d.getFirmware());
			System.out.println("ds_coordenadas: " + d.getDsCoordenadas());
			System.out.println("******************************");
			System.out.println("List de Logs de Bem:" + b.getBemLogs());
			System.out.println("List de Logs de Dispositivo desse Bem:" + b.getDispositivo().getDispositivoLogs());
			System.out.println("******************************");
			
		}

	}

}
