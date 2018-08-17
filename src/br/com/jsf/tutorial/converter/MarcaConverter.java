package br.com.jsf.tutorial.converter;

import javax.faces.convert.FacesConverter;

import br.com.jsf.tutorial.dao.Dao;
import br.com.jsf.tutorial.dao.MarcaDao;
import br.com.jsf.tutorial.entity.Marca;

@FacesConverter("marcaConverter")
public class MarcaConverter extends ConversorGenerico<Marca> {
	private static final long serialVersionUID = -4485426500266219232L;
	
	private MarcaDao dao = new MarcaDao();

	@Override
	public Dao<Marca> getDao() {
		return dao;
	}
}
