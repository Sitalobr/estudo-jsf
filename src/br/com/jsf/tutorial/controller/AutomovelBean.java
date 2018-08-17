package br.com.jsf.tutorial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import br.com.jsf.tutorial.dao.AutomovelDao;
import br.com.jsf.tutorial.dao.MarcaDao;
import br.com.jsf.tutorial.entity.Automovel;
import br.com.jsf.tutorial.entity.Marca;

@ManagedBean
@ViewScoped
public class AutomovelBean implements Serializable {
	private static final long serialVersionUID = -4984005212339976346L;
	
	private AutomovelDao automovelDao = new AutomovelDao();
	private MarcaDao marcaDao = new MarcaDao();
	
	private Automovel automovel;
	private List<Automovel> automoveis;
	private List<Marca> marcas;
	
	public AutomovelBean() {
		this.setAutomovel(new Automovel());
		this.setAutomoveis(new ArrayList<Automovel>());
	}
	
	@PostConstruct
	public void init() {
		this.setAutomoveis(this.automovelDao.listaTodos());
		this.setMarcas(this.marcaDao.listaTodos());
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	
	public List<Automovel> getAutomoveis() {
		return this.automoveis;
	}
	
	public void setAutomoveis(List<Automovel> automoveis) {
		this.automoveis = automoveis;
	}
	
	public List<Marca> getMarcas() {
		return this.marcas;
	}
	
	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public void limpaCampos() {
		this.setAutomovel(new Automovel());
		this.init();
	}
	
	public void salvar(ActionEvent actionEvent) {
		this.automovelDao.adiciona(automovel);
		this.setAutomoveis(this.automovelDao.listaTodos());
		this.limpaCampos();
        addMessage("Automóvel registrado com sucesso!", FacesMessage.SEVERITY_INFO);
    }
	
	public void excluir(Automovel automovel) {
		this.automovelDao.remove(automovel);
		this.setAutomoveis(this.automovelDao.listaTodos());
        addMessage("Automóvel removido com sucesso!", FacesMessage.SEVERITY_INFO);
    }
    
    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
