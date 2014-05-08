/**
 * 
 */
package net.unesc.tcc.gabriel.test;

import java.util.List;

import javax.persistence.EntityManager;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.persistencia.EntityManagerUtil;



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
		EntityManager em = EntityManagerUtil.getEM();
		
		List<Bem> lista =
                em.createQuery("select b from Bem b", Bem.class)
                .getResultList();
		em.clear();
		em.close();

	}

}
