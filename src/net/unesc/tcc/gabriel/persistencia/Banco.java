package net.unesc.tcc.gabriel.persistencia;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.BemLog;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.model.DispositivoLog;

public class Banco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private EntityManager em = EntityManagerUtil.getEM();

	public Banco() {
	}

	@PreDestroy
	public void closebd() {
		tramitabd();
		em.close();
	}

	public List<Bem> recuperartodosBens() {
		tramitabd();
		System.out.println("Recuperando lista de bens do banco...");
		List<Bem> lista = em.createQuery(
				"SELECT b FROM Bem b JOIN b.dispositivo d order by b.cdBemId",
				Bem.class).getResultList();
		return lista;
	}

	public List<Dispositivo> recuperartodosDispositivos() {
		tramitabd();
		System.out.println("Recuperando lista de dispositivos do banco...");
		List<Dispositivo> lista = em.createQuery(
				"SELECT d FROM Dispositivo d order by d.cdDispositivoId",
				Dispositivo.class).getResultList();
		return lista;
	}

	public Bem recuperaBem(Bem b) {
		if (b == null) {
			return null;
		}
		return recuperaBem(b.getCdBemId());
	}

	public Bem recuperaBem(Long cdBemId) {
		if (cdBemId == null) {
			return null;
		}
		tramitabd();
		Bem b = em.find(Bem.class, cdBemId);
		return b;
	}

	public void salvar(Object o) {
		if (o == null) {
			return;
		}
		tramitabd();
		System.out.println("Objeto para salvar: " + o.hashCode());
		em.getTransaction().begin();
		if (o.getClass().equals(Bem.class)) {
			Bem b = (Bem) o;
			b = em.merge(b);
			if (b != null) {
				em.merge(geralog(b));
			}
		} else if (o.getClass().equals(Dispositivo.class)) {
			Dispositivo d = (Dispositivo) o;
			d = em.merge(d);
			if (d != null) {
				em.merge(geralog(d));
			}
		} else {
			System.out.println("Classe >> " + o.getClass());

		}
		tramitabd();
	}

	public void excluir(Bem b) {
		tramitabd();

		Bem b1 = em.find(Bem.class, b.getCdBemId());
		if (b1 == null) {
			System.out.println("Não há Bem " + b.getCdBemId());
			em.clear();
		}
		em.getTransaction().begin();
		em.remove(b1);
		System.out.println("Bem " + b.getCdBemId() + " excluído!");
		tramitabd();
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

	private void tramitabd() {
		if (em.getTransaction().isActive()) {
			System.out.println("Transacao: " + em.getTransaction().hashCode() + " ativa e commitada.");
			em.flush();
			em.getTransaction().commit();
		}
		em.clear();
	}

	public void startbd() throws ParseException {
		// REGISTROS FICTICIOS
		// 3
		Dispositivo d = new Dispositivo((long) 4, "-28.6757785,-49.3650867", true);
		salvar(d);
		Bem b = new Bem(d, (long) 2, "BOI02");
		salvar(b);
		// 4
		d = new Dispositivo((long) 3, "-28.677191,-49.367789", false);
		salvar(d);
		b = new Bem(d, (long) 3, "BOI03");
		salvar(b);
		// 5
		d = new Dispositivo((long) 5, "-28.6777746,-49.3684448", true);
		salvar(d);
		b = new Bem(d, (long) 4, "BOI04");
		salvar(b);
		// 6
		d = new Dispositivo((long) 6, "-28.6778136,-49.367487", false);
		salvar(d);
		b = new Bem(d, (long) 5, "BOI05");
		salvar(b);
		// 7
		d = new Dispositivo((long) 7, "-28.6774144,-49.3663017", true);
		salvar(d);
		b = new Bem(d, (long) 6, "BOI06");
		salvar(b);
		// 8
		d = new Dispositivo((long) 8, "-28.6768261,-49.3657773", false);
		salvar(d);
		b = new Bem(d, (long) 7, "BOI07");
		salvar(b);
		// 9
		d = new Dispositivo((long) 9, "-28.6762637,-49.3656794", false);
		salvar(d);
		b = new Bem(d, (long) 8, "BOI08");
		salvar(b);
		// 10
		d = new Dispositivo((long) 10, "-28.675846,-49.3655587", false);
		salvar(d);
		b = new Bem(d, (long) 9, "BOI09");
		salvar(b);
	}

}
