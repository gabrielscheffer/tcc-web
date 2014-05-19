package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the dispositivo database table.
 * 
 */
@Entity
@Table(name = "dispositivo")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_dispositivo_id", unique = true, nullable = false)
	private Long cdDispositivoId;

	@Column(name = "ds_coordenadas")
	private String dsCoordenadas;

	@Column(name = "ds_dispositivo")
	private String dsDispositivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_ultima_online")
	private Date dtUltimaOnline;

	private String firmware = "ROUTER";

	private String marca = "DIGI";

	private boolean online;

	// bi-directional one-to-one association to Bem
	@OneToOne(mappedBy = "dispositivo")
	private Bem bem;

	// bi-directional many-to-one association to DispositivoLog
	@OneToMany(mappedBy = "dispositivo")
	private List<DispositivoLog> dispositivoLogs;

	public Dispositivo() {
	}

	public Dispositivo(Long cdDispositivoId, String dsCoordenadas, boolean online) {
		this.cdDispositivoId = cdDispositivoId;
		this.dsDispositivo = "BEE" + String.format("%02d", cdDispositivoId);
		this.dsCoordenadas = dsCoordenadas;
		this.online = online;
		if (online){
			this.dtUltimaOnline = new Date(0);
		}
	}

	public Long getCdDispositivoId() {
		return this.cdDispositivoId;
	}

	public void setCdDispositivoId(Long cdDispositivoId) {
		this.cdDispositivoId = cdDispositivoId;
	}

	public String getDsCoordenadas() {
		return this.dsCoordenadas;
	}

	public void setDsCoordenadas(String dsCoordenadas) {
		this.dsCoordenadas = dsCoordenadas;
	}

	public String getDsDispositivo() {
		return this.dsDispositivo;
	}

	public void setDsDispositivo(String dsDispositivo) {
		this.dsDispositivo = dsDispositivo;
	}

	public Date getDtUltimaOnline() {
		return this.dtUltimaOnline;
	}

	public void setDtUltimaOnline(Date dtUltimaOnline) {
		this.dtUltimaOnline = dtUltimaOnline;
	}

	public String getFirmware() {
		return this.firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Bem getBem() {
		return this.bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public List<DispositivoLog> getDispositivoLogs() {
		return this.dispositivoLogs;
	}

	public void setDispositivoLogs(List<DispositivoLog> dispositivoLogs) {
		this.dispositivoLogs = dispositivoLogs;
	}

	public DispositivoLog addDispositivoLog(DispositivoLog dispositivoLog) {
		getDispositivoLogs().add(dispositivoLog);
		dispositivoLog.setDispositivo(this);

		return dispositivoLog;
	}

	public DispositivoLog removeDispositivoLog(DispositivoLog dispositivoLog) {
		getDispositivoLogs().remove(dispositivoLog);
		dispositivoLog.setDispositivo(null);

		return dispositivoLog;
	}

}