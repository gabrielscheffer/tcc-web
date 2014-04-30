/**
 * 
 */
package net.unesc.tcc.gabriel.control;

/**
 * @author Gabriel
 *
 */
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.model.Dispositivo;
import net.unesc.tcc.gabriel.persistencia.Banco;

@ManagedBean
@ViewScoped
public class TabelaBensBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2521222924861033L;

	private List<Bem> filteredBens;

	private List<Bem> benslist;

	private Bem selectedBem;

	public TabelaBensBean() {
		benslist = null;
		benslist = new ArrayList<Bem>();
		popularBens();
	}

	public Bem getSelectedBem() {
		return selectedBem;
	}

	public void setSelectedBem(Bem selectedBem) {
		this.selectedBem = selectedBem;
	}
	
	public List<Bem> getFilteredBens() {
		return filteredBens;
	}

	public void setFilteredBens(List<Bem> filteredBens) {
		this.filteredBens = filteredBens;
	}

	public List<Bem> getBenslist() {
		return benslist;
	}

	private void popularBens() {
		ServicoBean servico = new ServicoBean();
		try {
			//benslist.addAll(servico.consultaTemporaria());
			//benslist.add(new Bem(new Dispositivo(2, "-28.6749125,-49.3630053,16z", "24/04/2014 20:54", true), 1,"BOI01"));
			benslist.add(new Bem(new Dispositivo(3, "-28.6757785,-49.3650867,16z", "24/04/2014 20:54", true), 2,"BOI02"));
			benslist.add(new Bem(new Dispositivo(1, "-28.677191,-49.367789,20z", "24/04/2014 20:54", false), 3,"BOI03"));
			benslist.add(new Bem(new Dispositivo(4, "-28.6777746,-49.3684448,20z", "24/04/2014 20:54", true), 4,"BOI04"));
			benslist.add(new Bem(new Dispositivo(5, "-28.6778136,-49.367487,20z", "24/04/2014 20:54", false), 5,"BOI05"));
			benslist.add(new Bem(new Dispositivo(6, "-28.6774144,-49.3663017,20z", "24/04/2014 20:54", true), 6,"BOI06"));
			benslist.add(new Bem(new Dispositivo(7, "-28.6768261,-49.3657773,20z", "24/04/2014 20:54", false), 7,"BOI07"));
			benslist.add(new Bem(new Dispositivo(8, "-28.6762637,-49.3656794,20z", "24/04/2014 20:54", false), 8,"BOI08"));
			benslist.add(new Bem(new Dispositivo(9, "-28.675846,-49.3655587,20z", "24/04/2014 20:54", false), 9,"BOI09"));
			benslist.add(new Bem(new Dispositivo(10, "-28.6760519,-49.3650089,20z", "24/04/2014 20:54", true), 10,"BOI10"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	



}
