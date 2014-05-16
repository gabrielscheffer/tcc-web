package net.unesc.tcc.gabriel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Gabriel
 * 
 */
@Entity
public class Bem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 692266294937161334L;
	
	@Id
	@Column(name = "cd_bem_id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cd_bem;
	@Column(name = "descricao_bem", nullable = false)
	private String ds_bem;
	
	@OneToOne
	@JoinColumn(name="cd_dispositivo_id")
	private Dispositivo dispositivo;
	
	public Bem() {
	}

	public Bem(Dispositivo dispositivo, Long cd_bem, String ds_bem) {
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

	public Long getCd_bem() {
		return cd_bem;
	}

	public void setCd_bem(Long cd_bem) {
		this.cd_bem = cd_bem;
	}

	public String getDs_bem() {
		return ds_bem;
	}

	public void setDs_bem(String ds_bem) {
		this.ds_bem = ds_bem;
	}

}
