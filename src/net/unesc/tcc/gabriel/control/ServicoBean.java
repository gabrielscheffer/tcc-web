/**
 * 
 */
package net.unesc.tcc.gabriel.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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

	private Banco banco = new Banco();
	private Date ultima_consulta;

	@PostConstruct
	public void inicializar() {
		System.out.println("Glassfish diz: 'Estou iniciando!'");
		ultima_consulta = null;
		try {
			// POPULA DADOS FICTÍCIOS
			System.out
					.println("/****************************************************************************/");
			System.out.println("POPULANDO REGISTROS FICTICIOS...");
			banco.startbd();
			System.out
					.println("/****************************************************************************/");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void finalizar() {
		System.out.println("Glassfish diz: 'Estou desligando!'");
		banco.closebd();

	}

	private List<Dispositivo> consultaDispositivos() {
		XbeeControl xbee = new XbeeControl();
		List<Dispositivo> listadispositivos = null;
		try {
			ArrayList<ZBNodeDiscover> noslist = xbee.descobreNos();
			if (noslist != null && noslist.size() > 0) {
				listadispositivos = new ArrayList<Dispositivo>();
				for (ZBNodeDiscover no : noslist) {
					Dispositivo disp = new Dispositivo();
					disp.setCdDispositivoId((long) (noslist.indexOf(no) + 1000));
					disp.setDsDispositivo(no.getNodeIdentifier());
					String firmware = "";
					switch (no.getDeviceType()) {
					case DEV_TYPE_COORDINATOR:
						firmware = "COORDINATOR";
						break;
					case DEV_TYPE_END_DEVICE:
						firmware = "END_DEVICE";
						break;
					case DEV_TYPE_ROUTER:
						firmware = "ROUTER";
						break;
					}
					disp.setFirmware(firmware);
					disp.setOnline(true);
					disp.setDtUltimaOnline(ultima_consulta);
					disp.setDsCoordenadas("-28.6749125,-49.3630053");
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
		return listadispositivos;
	}

	@Asynchronous
	public void consultaBens() {
		List<Dispositivo> disponlist = consultaDispositivos(), dispbancolist, dispofflist = new ArrayList<>();

		if (disponlist == null) {
			System.out.println("Lista de Dispositivos do Zigbee nula!");
			disponlist = new ArrayList<>();
		}
		if (disponlist.size() < 1) {
			System.out.println("Lista de Dispositivos do Zigbee vazia!");
		}

		// CRUZA LISTA DO BANCO COM LISTA DE ENCONTRADOS -- MELHORAR ISSO
		dispbancolist = banco.recuperartodosDispositivos();
		if (dispbancolist == null || dispbancolist.size() < 1) {
			System.out.println("Lista de Dispositivos do banco nula ou vazia!");
		}
		for (Dispositivo d : dispbancolist) {
			if (d.isOnline()) {
				boolean encontrado = false;
				for (Dispositivo don : disponlist) {
					if (don.getCdDispositivoId().equals(d.getCdDispositivoId())) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado && !dispofflist.contains(d)) {
					dispofflist.add(d);
				}
			}
		}
		// SETA OFFLINE para os dispositivos não encontrados que estavam ON
		if (dispofflist.size() < 1) {
			System.out.println("--Lista de Dispositivos offline vazia.");
		}
		for (Dispositivo doff : dispofflist) {
			System.out.println("Dispositivo: " + doff.getDsDispositivo() + " ("
					+ doff + ") está OFFLINE!");
			doff.setOnline(false);
			banco.salvar(doff);
		}
		int i = 1;
		// SALVA NOVOS BENS ENCONTRADOS
		for (Dispositivo d : disponlist) {
			banco.salvar(d);
			if (d.getFirmware().compareToIgnoreCase("COORDINATOR") == 0) {
				continue;
			}
			Bem b = new Bem(d, (long) i, "BOI DO MEU VÔ Nº:"
					+ String.format("%02d", i));
			if (banco.recuperaBem((long) i) == null) {
				banco.salvar(b);
			}
			i++;

		}
	}

	@Schedule(second = "0", minute = "*/2", hour = "*", persistent = false)
	public void tarefaAgendada() {
		ultima_consulta = new Date();
		System.out
				.println("**********************************************************");
		System.out.println("Iniciando descoberta de nós (a cada 2 minutos)...");
		consultaBens();
		System.out
				.println("----------------------------------------------------------");
	}

	public Date getUltima_consulta() {
		return ultima_consulta;
	}

}
