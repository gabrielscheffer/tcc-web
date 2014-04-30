package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import java.util.Date;

public class Bem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 692266294937161334L;
	Dispositivo dispositivo;
	Integer		cd_bem;
	String		ds_bem;

	

	public Bem(Dispositivo dispositivo, Integer cd_bem, String ds_bem) {
		super();
		this.dispositivo = dispositivo;
		this.cd_bem = cd_bem;
		this.ds_bem = ds_bem;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Integer getCd_bem() {
		return cd_bem;
	}

	public void setCd_bem(Integer cd_bem) {
		this.cd_bem = cd_bem;
	}

	public String getDs_bem() {
		return ds_bem;
	}

	public void setDs_bem(String ds_bem) {
		this.ds_bem = ds_bem;
	}

}
