package net.unesc.tcc.gabriel.persistencia;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

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

	public ArrayList<Bem> recuperar() {
		
		return null;
	}

	public boolean salvar(Object o) {
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
        
//        Long id = c.getConsumidor_ID();
//        if (id == 0){
//            System.out.println("Consumidor novo salvo!");
//        }else{
//            System.out.println("Consumidor "+c.getConsumidor_ID()+" salvo!");
//        }
//		List<Bem> lista = em.createQuery("select b from Bem b", Bem.class)
//				.getResultList();
//		em.clear();
		//em.close();
		return true;
	}

	public void startbd() throws ParseException {
		//REGISTROS FICTICIOS
		salvar(new Bem(new Dispositivo(4, "-28.6757785,-49.3650867", "24/04/2014 20:54", true), 3,"BOI03"));
		salvar(new Bem(new Dispositivo(3, "-28.677191,-49.367789", "24/04/2014 20:54", false), 4,"BOI04"));
		salvar(new Bem(new Dispositivo(5, "-28.6777746,-49.3684448", "24/04/2014 20:54", true), 5,"BOI05"));
		salvar(new Bem(new Dispositivo(6, "-28.6778136,-49.367487", "24/04/2014 20:54", false), 6,"BOI06"));
		salvar(new Bem(new Dispositivo(7, "-28.6774144,-49.3663017", "24/04/2014 20:54", true), 7,"BOI07"));
		salvar(new Bem(new Dispositivo(8, "-28.6768261,-49.3657773", "24/04/2014 20:54", false), 8,"BOI08"));
		salvar(new Bem(new Dispositivo(9, "-28.6762637,-49.3656794", "24/04/2014 20:54", false), 9,"BOI09"));
		salvar(new Bem(new Dispositivo(10, "-28.675846,-49.3655587", "24/04/2014 20:54", false), 10,"BOI10"));		
	}

}
