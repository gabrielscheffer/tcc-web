package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the bem database table.
 * 
 */
@Entity
@Table(name = "bem")
public class Bem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_bem_id", updatable = false, unique = true, nullable = false)
	private Long cdBemId;

	@Column(name = "descricao_bem", nullable = false)
	private String descricaoBem;

	// bi-directional one-to-one association to Dispositivo
	@OneToOne
	@JoinColumn(name = "cd_dispositivo_id", nullable = false)
	private Dispositivo dispositivo;

	// bi-directional many-to-one association to BemLog
	@OneToMany(mappedBy = "bem")
	private List<BemLog> bemLogs;

	public Bem() {
	}

	public Bem(Dispositivo dispositivo, Long cdBemId, String descricaoBem) {
		this.dispositivo = dispositivo;
		this.cdBemId = cdBemId;
		this.descricaoBem = descricaoBem;
	}

	public Long getCdBemId() {
		return this.cdBemId;
	}

	public void setCdBemId(Long cdBemId) {
		this.cdBemId = cdBemId;
	}

	public String getDescricaoBem() {
		return this.descricaoBem;
	}

	public void setDescricaoBem(String descricaoBem) {
		this.descricaoBem = descricaoBem;
	}

	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public List<BemLog> getBemLogs() {
		return this.bemLogs;
	}

	public void setBemLogs(List<BemLog> bemLogs) {
		this.bemLogs = bemLogs;
	}

	public BemLog addBemLog(BemLog bemLog) {
		getBemLogs().add(bemLog);
		bemLog.setBem(this);

		return bemLog;
	}

	public BemLog removeBemLog(BemLog bemLog) {
		getBemLogs().remove(bemLog);
		bemLog.setBem(null);

		return bemLog;
	}

}