package br.com.jsf.tutorial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.jsf.tutorial.dao.MarcaDao;
import br.com.jsf.tutorial.entity.Marca;

@ManagedBean
@RequestScoped
public class MarcaBean implements Serializable {
	private static final long serialVersionUID = -4984005212339976346L;
	
	private MarcaDao marcaDao = new MarcaDao();
	
	private Marca marca;
	private List<Marca> marcas;
	
	public MarcaBean() {
		this.setMarca(new Marca());
		this.setMarcas(new ArrayList<Marca>());
	}
	
	@PostConstruct
	public void init() {
		this.setMarcas(this.marcaDao.listaTodos());
	}
	

	public Marca getMarca() {
		return this.marca;
	}
	

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	public List<Marca> getMarcas() {
		return this.marcas;
	}
	
	
	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
	
	public void limpaCampos() {
		this.setMarca(new Marca());
		this.init();
	}
	
	public void salvar(ActionEvent actionEvent) {
		this.marcaDao.adiciona(marca);
		this.setMarcas(this.marcaDao.listaTodos());
		this.limpaCampos();
        addMessage("Marca registrada com sucesso!", FacesMessage.SEVERITY_INFO);
    }
	
	public void excluir(Marca marca) {
		this.marcaDao.remove(marca);
		this.setMarcas(this.marcaDao.listaTodos());
        addMessage("Marca removida com sucesso!", FacesMessage.SEVERITY_INFO);
    }
     
    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
