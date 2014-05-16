/**
 * 
 */
package net.unesc.tcc.gabriel.control;

/**
 * @author Gabriel
 *
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.unesc.tcc.gabriel.model.Bem;
import net.unesc.tcc.gabriel.persistencia.Banco;

@ManagedBean
@ViewScoped
public class TabelaBensBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2521222924861033L;

	// Objetos da lista online
	private List<Bem> filteredBensOn;
	private List<Bem> benslistOn;
	private Bem selectedBemOn;
	// Objetos da lista offline
	private List<Bem> filteredBensOff;
	private List<Bem> benslistOff;
	private Bem selectedBemOff;

	public TabelaBensBean() {
		benslistOn = null;
		benslistOn = new ArrayList<Bem>();
		popularBens();
	}

	public Bem getSelectedBemOn() {
		return selectedBemOn;
	}

	public void setSelectedBemOn(Bem selectedBem) {
		this.selectedBemOn = selectedBem;
	}

	public List<Bem> getFilteredBensOn() {
		return filteredBensOn;
	}

	public void setFilteredBensOn(List<Bem> filteredBens) {
		this.filteredBensOn = filteredBens;
	}

	public List<Bem> getBenslistOn() {
		return benslistOn;
	}

	public List<Bem> getFilteredBensOff() {
		return filteredBensOff;
	}

	public void setFilteredBensOff(List<Bem> filteredBensOff) {
		this.filteredBensOff = filteredBensOff;
	}

	public Bem getSelectedBemOff() {
		return selectedBemOff;
	}

	public void setSelectedBemOff(Bem selectedBemOff) {
		this.selectedBemOff = selectedBemOff;
	}

	public List<Bem> getBenslistOff() {
		return benslistOff;
	}

	public void forcardescnos() {

	}

	public void bdrefresh() {
		Banco banco = new Banco();
		List<Bem> novalista = banco.recuperartodos();
		if (novalista == null) {
			System.out.println("Lista nula!");
			return;
		}
		System.out.println("Retornado: " + novalista.size());
		List<Bem> online = new ArrayList<Bem>(), offline = new ArrayList<Bem>();
		for (Bem b : novalista) {
			if (b.getDispositivo().isOnline()) {
				online.add(b);
			} else {
				offline.add(b);
			}
		}
		System.out.println("Online: " + online.size());
		System.out.println("Offline: " + offline.size());
		this.benslistOn = online;
		this.benslistOff = offline;
	}

	private void popularBens() {
		ServicoBean servico = new ServicoBean();
		// benslist.addAll(servico.consultaTemporaria());
		bdrefresh();
	}

}
