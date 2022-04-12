package br.com.jogodaforca.game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.jogodaforca.daos.PalavraDao;
import br.com.jogodaforca.models.Palavra;

@SessionScoped
public class PalavraEmJogo implements Serializable{

	private static final long serialVersionUID = -704964303008018209L;

	@Inject
	private PalavraDao palavraDao;
//---------------------------------------------------
	private Boneco boneco = new Boneco();
	private Palavra palavra;
	private CronometroView cronometroView = new CronometroView();
//---------------------------------------------------		
	private Set<Character> letrasJogadas = new HashSet<>();
	private Set<Integer> palavrasJogadas = new HashSet<>();
	private Random random = new Random();
	private List<Character> letras = new LinkedList<>();
		
	/*--------------INÍCIO DE PARTIDA---------------*/
	
	private void converterParaLista(Palavra palavra) {
		String s = palavra.getPalavra();
		for (Character character : s.toCharArray()) {
			if(Character.isSpaceChar(character))
				getLetras().add(' ');
			else
				getLetras().add('_');
		}
	}
	
	public void sortearPalavra() {
		Integer palavraEscolhida;
		do {
			palavraEscolhida = random.nextInt(palavraDao.quantidadeDePalavras());
		}while(palavrasJogadas.contains(palavraEscolhida)||palavraEscolhida==0);
		
		palavrasJogadas.add(palavraEscolhida);
		this.setPalavra(palavraDao.buscarPorId(palavraEscolhida));
		this.converterParaLista(palavra);
		
	}
	
	public boolean palavraFoiescolhida() {
		if(getPalavra()==null) {
			return false;
		}else {
			return true;
		}
	}
	
	/*--------------VERIFICAR VITÓRIA OU DERROTA NA PARTIDA---------------*/
	
	public boolean derrota() {
		if(boneco.getIndice()==6) return true;
		
		return false;
	}
	public boolean vitoria() {
		if(letras.contains('_')) return false;
		
		return true;
	}
	
	/*--------------VERIFICAÇÃO DE LETRA ARRISCADA E INSERÇÃO DE LETRA ---------------*/

	public boolean verificaLetra(Character c) {
		String nome = getPalavra().getPalavra();
		if(nome.contains(c.toString()))
			return true;
		
		return false;
	}
	
	public void adicionarLetra(Character c, String nome) {
		int i = 0;
		for (Character character : nome.toCharArray()) {
			if(character==c) {
				letras.set(i, c);
			}
			i+=1;
		}
	}
	
	/*--------------PREENCHER OS ESPAÇOS EM BRANCO QUANDO ACERTAR PALAVRA---------------*/
	
	public void preencherEspacos() {
		String nome = getPalavra().getPalavra();
		for (Character character : nome.toCharArray()) {
			this.adicionarLetra(character, nome);
		}
		
	}
	
	/*--------------ALTERA O BONECO---------------*/
	
	public void acrescentarParteBoneco() {
		boneco.modificarFigura();
	}
	
	/*-------------------GETTERS E SETTERS-----------------------*/
	
	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
	}

	public List<Character> getLetras() {
		return letras;
	}

	public void setLetras(List<Character> letras) {
		this.letras = letras;
	}

	public Boneco getBoneco() {
		return boneco;
	}

	public void setBoneco(Boneco boneco) {
		this.boneco = boneco;
	}



	public CronometroView getCronometroView() {
		return cronometroView;
	}

	public void setCronometroView(CronometroView cronometroView) {
		this.cronometroView = cronometroView;
	}

	public Set<Character> getLetrasJogadas() {
		return letrasJogadas;
	}

	public void setLetrasJogadas(Set<Character> letrasJogadas) {
		this.letrasJogadas = letrasJogadas;
	}

}
