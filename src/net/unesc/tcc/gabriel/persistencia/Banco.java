package net.unesc.tcc.gabriel.persistencia;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;

public class Banco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2678650489448917868L;
	
	
	public Banco() {
		
	}

	// BANCO MYSQL, utilizar HIBERNATE ou outro metodo para controlar as
	// insercoes de forma automatica
	public void salvar(ArrayList<Bem> benslist) {
		// SALVAR BENS NA TABELA DE 'REGISTROS DE BENS'
	}

	public List<Bem> recuperartodos() {
		System.out.println("Recuperando lista de bens do banco...");
		EntityManager em = EntityManagerUtil.getEM();
		List<Bem> lista = em.createQuery("SELECT b FROM Bem b JOIN b.dispositivo d order by b.cd_bem", Bem.class).getResultList();		
		return lista;
	}

	public void salvar(Object o) {
		System.out.println("Objeto para salvar: " + o);
		EntityManager em = EntityManagerUtil.getEM();
		EntityTransaction transacao = em.getTransaction();
        transacao.begin();
		if (o.getClass().equals(Bem.class)){
			Bem b = (Bem) o;
			em.merge(b);
		}else if (o.getClass().equals(Dispositivo.class)){
			Dispositivo d = (Dispositivo) o;
			em.merge(d);
		}
        transacao.commit();
        em.clear();
	}
	
	 public void excluir(Bem b){
	        EntityManager em = EntityManagerUtil.getEM();
	        Bem b1 = em.find(Bem.class, b.getCd_bem());
	        if(b1 == null){
	        	System.out.println("Não há Consumidor "+b.getCd_bem());
	        	em.clear();
	        }
	        EntityTransaction transacao = em.getTransaction();
	        transacao.begin();
	        em.remove(b1);
	        transacao.commit();
	        System.out.println("Consumidor "+b.getCd_bem()+" excluído!");
	    }

	public void startbd() throws ParseException {
		//REGISTROS FICTICIOS
		salvar(new Bem(new Dispositivo((long) 4, "-28.6757785,-49.3650867", "24/04/2014 20:54", true), (long) 3,"BOI03"));
		salvar(new Bem(new Dispositivo((long) 3, "-28.677191,-49.367789", "24/04/2014 20:54", false), (long) 4,"BOI04"));
		salvar(new Bem(new Dispositivo((long) 5, "-28.6777746,-49.3684448", "24/04/2014 20:54", true), (long) 5,"BOI05"));
		salvar(new Bem(new Dispositivo((long) 6, "-28.6778136,-49.367487", "24/04/2014 20:54", false), (long) 6,"BOI06"));
		salvar(new Bem(new Dispositivo((long) 7, "-28.6774144,-49.3663017", "24/04/2014 20:54", true), (long) 7,"BOI07"));
		salvar(new Bem(new Dispositivo((long) 8, "-28.6768261,-49.3657773", "24/04/2014 20:54", false), (long) 8,"BOI08"));
		salvar(new Bem(new Dispositivo((long) 9, "-28.6762637,-49.3656794", "24/04/2014 20:54", false), (long) 9,"BOI09"));
		salvar(new Bem(new Dispositivo((long) 10, "-28.675846,-49.3655587", "24/04/2014 20:54", false), (long) 10,"BOI10"));		
	}
	public void closebd(){
		EntityManager em = EntityManagerUtil.getEM();
		em.clear();
		em.close();
	}

}
