package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the dispositivo_log database table.
 * 
 */
@Entity
@Table(name = "dispositivo_log")
public class DispositivoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_dispositivo_log", updatable = false, unique = true, nullable = false)
	private Long cdDispositivoLog;

	@Column(name = "ds_coordenadas", nullable = false, updatable = false)
	private String dsCoordenadas;

	@Column(name = "ds_dispositivo", nullable = false, updatable = false)
	private String dsDispositivo;

	@Column(nullable = false, updatable = false)
	private String firmware;

	@Column(nullable = false, updatable = false)
	private boolean online;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ultima_alteracao", nullable = false, insertable = false, updatable = false, columnDefinition = "datetime default Now()")
	private Date ultimaAlteracao;

	// bi-directional many-to-one association to Dispositivo
	@ManyToOne
	@JoinColumn(name = "cd_dispositivo_id", nullable = false, updatable = false)
	private Dispositivo dispositivo;

	public DispositivoLog() {
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

	public String getFirmware() {
		return this.firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}