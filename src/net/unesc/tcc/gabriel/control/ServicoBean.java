/**
 * 
 */
package net.unesc.tcc.gabriel.control;

import java.util.ArrayList;
import java.util.Date;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.persistencia.Banco;

import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.zigbee.ZBNodeDiscover;

/**
 * @author Gabriel
 * 
 */
public class ServicoBean {

	private ArrayList<Dispositivo> listadispositivos = null;
	private ArrayList<Bem> listabens = null;

	
	public void consultaDispositivos() {
		XbeeControl xbee = new XbeeControl();
		try {
			ArrayList<ZBNodeDiscover> noslist = xbee.descobreNos();
			if (noslist != null && noslist.size() > 0) {
				if (listadispositivos == null) {
					listadispositivos = new ArrayList<Dispositivo>();
				}
				for (ZBNodeDiscover no : noslist) {
					Dispositivo disp = new Dispositivo();
					disp.setCd_dispositivo(noslist.indexOf(no)+1000);
					disp.setDs_dispositivo(no.getNodeIdentifier());
					disp.setDt_ultima_consulta(new Date());
					disp.setFirmware(no.getDeviceType().name());
					disp.setOnline(true);
					disp.setDs_coordenadas("-28.6749125,-49.3630053,16z");
					listadispositivos.add(disp);
				}
				Banco.salvarD(listadispositivos);
			}

		} catch (XBeeException e) {
			// TRATAR EXCEÇÃO DO XBEE
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TRATAR EXCEÇÃO DE PROBLEMA COM A API
			e.printStackTrace();
		}
	}

	

	public void consultaBens() {
		consultaDispositivos();
		if (listadispositivos == null || listadispositivos.size() < 1) {
			return;
		}
		if (listabens == null) {
			listabens = new ArrayList<Bem>();
		}
		// CRUZA AS DUAS LISTAS E SALVA NA TABELA DE REGISTROS DE BENS DO BANCO MYSQL
		for (Dispositivo d : listadispositivos) {
			Bem b = new Bem(d, 1, "BOI NOVO");
			listabens.add(b);
		}
		Banco.salvarB(listabens);
	}
	
	public ArrayList<Bem> consultaTemporaria() {
		consultaDispositivos();
		if (listadispositivos == null || listadispositivos.size() < 1) {
			return null;
		}
		if (listabens == null) {
			listabens = new ArrayList<Bem>();
		}
		// CRUZA AS DUAS LISTAS E SALVA NA TABELA DE REGISTROS DE BENS DO BANCO MYSQL
		for (Dispositivo d : listadispositivos) {
			Bem b = new Bem(d, 1, "BOI NOVO");
			listabens.add(b);
		}
		return listabens;
	}
}
