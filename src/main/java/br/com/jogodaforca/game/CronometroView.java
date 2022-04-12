package br.com.jogodaforca.game;

public class CronometroView{

	private Integer contador=0;
	private boolean onOffCronometro = true;
	private String cronometro = "00:00:00";
	
	public void ligarDesligarCronometro(boolean onOffCronometro) {
		this.onOffCronometro = onOffCronometro;
	}
	
	public void increment() {
		if(onOffCronometro)
			setContador(getContador() + 1);
	}

	public String getCronometro() {
		int segundo = getContador()%60;
		int minuto = getContador()/60;
		int hora = minuto/60;
		cronometro = String.format("%02d:%02d:%02d", hora, minuto, segundo);
		return cronometro;
	}
	public void zerarCronometro() {
		setContador(0);
	}
	
	/*-------------------GETTERS E SETTERS-----------------------*/

	public void setCronometro(String cronometro) {
		this.cronometro = cronometro;
	}
	

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

}
