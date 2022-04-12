package br.com.jogodaforca.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * CALENDAR CONVERTER utilizado para converter a data no formato MM/yy para um que o tipo
 * Calendar aceite.
 * 
 * Chamado sempre que tentar atribuir valores a um objeto tipo Calendar
 *
 */
@FacesConverter(forClass = Calendar.class)
public class CalendarConverter implements Converter{

	private DateTimeConverter converter = new DateTimeConverter();
	
	public CalendarConverter() {
		converter.setPattern("MM/yy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
		if(dataTexto.isEmpty()) {
			/**
			 * erro do tipo Null converter
			 */
			dataTexto="00/00";
			System.out.println(dataTexto);
		}
		Date data = (Date)converter.getAsObject(context, component, dataTexto);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObject) {
		if(dataObject==null) return null;
		Calendar calendar = (Calendar) dataObject;
		return converter.getAsString(context, component, calendar.getTime());
	}

}
