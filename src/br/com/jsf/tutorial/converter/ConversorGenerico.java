package br.com.jsf.tutorial.converter;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.jsf.tutorial.dao.Dao;
import br.com.jsf.tutorial.entity.EntidadeGenerica;


public abstract class ConversorGenerico<T> implements Converter, Serializable {
	
	private static final long serialVersionUID = -2115340050601376098L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		}

		T identificavel = null;

		try {
			identificavel = getDao().buscaPorId(Long.parseLong(value));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (identificavel == null) {
			throw new ConverterException(new FacesMessage("Identificação desconhecida:  " + value));
		}

		return identificavel;
	}
	
	

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			EntidadeGenerica i = (EntidadeGenerica) value;
			return String.valueOf(i.getId());
		}
	}
	
	public abstract Dao<T> getDao();
}
