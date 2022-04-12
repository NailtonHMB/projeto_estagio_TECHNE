package br.com.jogodaforca.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.jogodaforca.models.Logradouro;

@FacesConverter("logradouroConverter")
public class LogradouroConverter  implements Converter{		
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if(id==null || id.trim().isEmpty()) return null;
		Logradouro logradouro = new Logradouro();
		logradouro.setId(Integer.valueOf(id));
		return logradouro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object logradouroObject) {
		if(logradouroObject==null) return null;
		Logradouro logradouro = (Logradouro) logradouroObject;
		return logradouro.getId().toString();
	}

}
