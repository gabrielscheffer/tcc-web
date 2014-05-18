package net.unesc.tcc.gabriel.persistencia;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.BemLog;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.model.DispositivoLog;

public class Banco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2678650489448917868L;

	public Banco() {

	}

	public List<Bem> recuperartodosBens() {
		System.out.println("Recuperando lista de bens do banco...");
		EntityManager em = EntityManagerUtil.getEM();
		List<Bem> lista = em.createQuery("SELECT b FROM Bem b JOIN b.dispositivo d order by b.cdBemId",Bem.class).getResultList();
		return lista;
	}
	
	public List<Dispositivo> recuperartodosDispositivos(){
		System.out.println("Recuperando lista de dispositivos do banco...");
		EntityManager em = EntityManagerUtil.getEM();
		List<Dispositivo> lista = em.createQuery("SELECT d FROM Dispositivo d order by d.cdDispositivoId",Dispositivo.class).getResultList();
		return lista;
	}
	
	public Bem recuperaBem(Bem b){
		if (b == null){
			return null;
		}
		return recuperaBem(b.getCdBemId());
	}
	
	public Bem recuperaBem(Long cdBemId){
		if (cdBemId == null){
			return null;
		}
		EntityManager em = EntityManagerUtil.getEM();
		Bem b = em.find(Bem.class, cdBemId);
		em.clear();
		return b;
	}

	public void salvar(Object o) {
		if (o == null){
			return;
		}
		System.out.println("Objeto para salvar: " + o);
		EntityManager em = EntityManagerUtil.getEM();
		EntityTransaction transacao = em.getTransaction();
		transacao.begin();
		if (o.getClass().equals(Bem.class)) {
			Bem b = (Bem) o;
			b = em.merge(b);
			if (b != null){
				em.merge(geralog(b));
			}
		} else if (o.getClass().equals(Dispositivo.class)) {
			Dispositivo d = (Dispositivo) o;
			d = em.merge(d);
			if (d != null){
				em.merge(geralog(d));
			}
		}else{
			System.out.println("Classe >> " + o.getClass());
			
		}
		if (transacao.isActive()){
			transacao.commit();
		}
		em.clear();
	}

	private BemLog geralog(Bem b) {
		Dispositivo d = b.getDispositivo();
		// LOG AUDITORIA BEM
		BemLog bl = new BemLog();
		bl.setBem(b);
		bl.setCdDispositivoId(d.getCdDispositivoId());
		bl.setDescricaoBem(b.getDescricaoBem());

		return bl;
	}
	
	private DispositivoLog geralog(Dispositivo d) {
		// LOG AUDITORIA DISPOSITIVO
		DispositivoLog dlog = new DispositivoLog();
		dlog.setDispositivo(d);
		dlog.setDsCoordenadas(d.getDsCoordenadas());
		dlog.setDsDispositivo(d.getDsDispositivo());
		dlog.setFirmware(d.getFirmware());
		dlog.setOnline(d.isOnline());

		return dlog;
	}

	public void excluir(Bem b) {
		EntityManager em = EntityManagerUtil.getEM();
		Bem b1 = em.find(Bem.class, b.getCdBemId());
		if (b1 == null) {
			System.out.println("Não há Bem " + b.getCdBemId());
			em.clear();
		}
		EntityTransaction transacao = em.getTransaction();
		transacao.begin();
		em.remove(b1);
		transacao.commit();
		System.out.println("Bem " + b.getCdBemId() + " excluído!");
	}

	public void startbd() throws ParseException {
		// REGISTROS FICTICIOS
		//3
		Dispositivo d = new Dispositivo((long) 4, "-28.6757785,-49.3650867",true);
		salvar(d);
		Bem b = new Bem(d, (long) 2, "BOI02"); 
		salvar(b);
		//4
		d = new Dispositivo((long) 3, "-28.677191,-49.367789", false);
		salvar(d);
		b = new Bem(d, (long) 3, "BOI03"); 
		salvar(b);
		//5
		d = new Dispositivo((long) 5, "-28.6777746,-49.3684448", true);
		salvar(d);
		b = new Bem(d, (long) 4, "BOI04"); 
		salvar(b);
		//6
		d = new Dispositivo((long) 6, "-28.6778136,-49.367487", false);
		salvar(d);
		b = new Bem(d, (long) 5, "BOI05"); 
		salvar(b);
		//7
		d = new Dispositivo((long) 7, "-28.6774144,-49.3663017", true);
		salvar(d);
		b = new Bem(d, (long) 6, "BOI06"); 
		salvar(b);
		//8
		d = new Dispositivo((long) 8, "-28.6768261,-49.3657773", false);
		salvar(d);
		b = new Bem(d, (long) 7, "BOI07"); 
		salvar(b);
		//9
		d = new Dispositivo((long) 9, "-28.6762637,-49.3656794", false);
		salvar(d);
		b = new Bem(d, (long) 8, "BOI08"); 
		salvar(b);
		//10
		d = new Dispositivo((long) 10, "-28.675846,-49.3655587", false);
		salvar(d);
		b = new Bem(d, (long) 9, "BOI09"); 
		salvar(b);
	}

	public void closebd() {
		EntityManager em = EntityManagerUtil.getEM();
		em.clear();
		em.close();
	}

}
