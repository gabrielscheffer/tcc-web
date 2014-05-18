package net.unesc.tcc.gabriel.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the bem_log database table.
 * 
 */
@Entity
@Table(name = "bem_log")
public class BemLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_bem_log", updatable = false, unique = true, nullable = false)
	private Long cdBemLog;

	@Column(name = "cd_dispositivo_id", nullable = false, updatable = false)
	private Long cdDispositivoId;

	@Column(name = "descricao_bem", nullable = false, updatable = false)
	private String descricaoBem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ultima_alteracao", nullable = false, insertable = false, updatable = false, columnDefinition = "datetime default Now()")
	private Date ultimaAlteracao;

	// bi-directional many-to-one association to Bem
	@ManyToOne
	@JoinColumn(name = "cd_bem_id", nullable = false, updatable = false)
	private Bem bem;

	public BemLog() {
	}

	public Long getCdDispositivoId() {
		return this.cdDispositivoId;
	}

	public void setCdDispositivoId(Long cdDispositivoId) {
		this.cdDispositivoId = cdDispositivoId;
	}

	public String getDescricaoBem() {
		return this.descricaoBem;
	}

	public void setDescricaoBem(String descricaoBem) {
		this.descricaoBem = descricaoBem;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Bem getBem() {
		return this.bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

}