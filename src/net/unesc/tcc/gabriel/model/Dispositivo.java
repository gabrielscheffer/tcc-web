/**
 * 
 */
package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gabriel
 * 
 */
public class Dispositivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4351275053454850562L;

	Integer cd_dispositivo;
	String ds_dispositivo, marca = "DIGI", firmware = "ROUTER", ds_coordenadas;
	Date dt_ultima_consulta, dt_ultima_online;
	boolean online;

	public Dispositivo(Integer cd_dispositivo, String ds_coordenadas,
			Date dt_ultima_consulta, boolean online) {
		super();
		this.cd_dispositivo = cd_dispositivo;
		this.ds_dispositivo = "BEE" + String.format("%02d", cd_dispositivo);
		this.ds_coordenadas = ds_coordenadas;
		this.dt_ultima_consulta = dt_ultima_consulta;
		this.online = online;
	}
	public Dispositivo(Integer cd_dispositivo, String ds_coordenadas,
			String dt_ultima_consulta, boolean online) throws ParseException {
		super();
		this.cd_dispositivo = cd_dispositivo;
		this.ds_dispositivo = "BEE" + String.format("%02d", cd_dispositivo);
		this.ds_coordenadas = ds_coordenadas;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		this.dt_ultima_consulta = df.parse(dt_ultima_consulta);
		this.online = online;
	}
	

	public Dispositivo() {
	}
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Integer getCd_dispositivo() {
		return cd_dispositivo;
	}

	public void setCd_dispositivo(Integer cd_dispositivo) {
		this.cd_dispositivo = cd_dispositivo;
	}

	public String getDs_dispositivo() {
		return ds_dispositivo;
	}

	public void setDs_dispositivo(String ds_dispositivo) {
		this.ds_dispositivo = ds_dispositivo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getDs_coordenadas() {
		return ds_coordenadas;
	}

	public void setDs_coordenadas(String ds_coordenadas) {
		this.ds_coordenadas = ds_coordenadas;
	}

	public Date getDt_ultima_consulta() {
		return dt_ultima_consulta;
	}

	public void setDt_ultima_consulta(Date dt_ultima_consulta) {
		this.dt_ultima_consulta = dt_ultima_consulta;
	}

}
