package br.com.jogodaforca.game;

public class Boneco {
	
	private Integer indice=0;
	private String figura = "../imagens/forca0.png";

	public void modificarFigura() {
		setIndice(getIndice()+1);
		if(getIndice()>=7) {
			setIndice(6);
		}
		figura = "../imagens/forca"+getIndice().toString()+".png";
	}
	
	public String getFigura() {
		return figura;
	}

	public void setFigura(String figura) {
		this.figura = figura;
	}
	
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

}
