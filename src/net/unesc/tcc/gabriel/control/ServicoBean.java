/**
 * 
 */
package net.unesc.tcc.gabriel.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.Initialized;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.persistencia.Banco;

import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.zigbee.ZBNodeDiscover;

/**
 * @author Gabriel
 * 
 */
@Startup
@Singleton
public class ServicoBean {

	private ArrayList<Dispositivo> listadispositivos = null;
	private ArrayList<Bem> listabens = null;
	private Banco banco;
	
	@PostConstruct
	public void inicializar() {
		banco = new Banco();
		System.out.println("Glassfish diz: 'Estou iniciando!'");
		try {
			banco.startbd();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PreDestroy
	public void finalizar(){
		System.out.println("Glassfish diz: 'Estou desligando!'");
		banco.closebd();
		
	}
	

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
					disp.setCd_dispositivo((long) (noslist.indexOf(no) + 1000));
					disp.setDs_dispositivo(no.getNodeIdentifier());
					disp.setDt_ultima_consulta(new Date());
					disp.setFirmware(no.getDeviceType().name());
					disp.setOnline(true);
					disp.setDs_coordenadas("-28.6749125,-49.3630053");
					listadispositivos.add(disp);
				}
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
		// CRUZA AS DUAS LISTAS E SALVA NA TABELA DE REGISTROS DE BENS DO BANCO
		// MYSQL
		int i = 1;
		for (Dispositivo d : listadispositivos) {
			Bem b = new Bem(d, (long) i, "BOI DO MEU VÔ");
			i++;
			listabens.add(b);
		}
		banco.salvar(listabens);
	}

	public ArrayList<Bem> consultaTemporaria() {
		consultaDispositivos();
		if (listadispositivos == null || listadispositivos.size() < 1) {
			return null;
		}
		if (listabens == null) {
			listabens = new ArrayList<Bem>();
		}
		// CRUZA AS DUAS LISTAS E SALVA NA TABELA DE REGISTROS DE BENS DO BANCO
		// MYSQL
		int i = 1;
		for (Dispositivo d : listadispositivos) {
			Bem b = new Bem(d, (long) i, "BOI DO MEU VÔ");
			i++;
			listabens.add(b);
		}
		return listabens;
	}
	public Banco getBanco() {
		return banco;
	}
	
}
