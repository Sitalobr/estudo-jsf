package br.com.jsf.tutorial.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="automovel")
public class Automovel implements EntidadeGenerica, Serializable {
	private static final long serialVersionUID = 8792608018204782973L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="modelo", nullable = false)
	private String modelo;
	@Column(name="ano_fabricacao", nullable = false)
	private String anoFabricacao;
	@Column(name="ano_modelo", nullable = false)
	private String anoModelo;
	@Column(name="observacao")
	private String observacao;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_marca")
	private Marca marca;
	
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	public String getAnoModelo() {
		return anoModelo;
	}
	
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
}
