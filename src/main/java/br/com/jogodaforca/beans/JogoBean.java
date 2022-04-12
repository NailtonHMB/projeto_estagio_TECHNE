package br.com.jogodaforca.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import br.com.jogodaforca.daos.UsuarioDao;
import br.com.jogodaforca.game.PalavraEmJogo;
import br.com.jogodaforca.models.Usuario;

@Model
public class JogoBean {

	@Inject
	private HttpServletRequest request;

//--------------------------------------------------
	
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private PalavraEmJogo emJogo;
	
//--------------------------------------------------	
	private Character letraConv;
	private String letra;
	private String msgVitoriaDerrota="";
	private String ranking = "";
	private String palavraArriscada="";
	private String mensagemPalavraErrada="";
	
	/*-------------------INICIA OUTRA PARTIDA-----------------------*/
	
	@Transactional
	public void reiniciar() {
		emJogo.setPalavra(null);
		emJogo.getLetras().clear();
		emJogo.getLetrasJogadas().clear();
		emJogo.getBoneco().setIndice(-1);
		emJogo.getBoneco().modificarFigura();
		System.out.println(emJogo.getBoneco().getIndice());
		emJogo.getCronometroView().setContador(0);
		emJogo.getCronometroView().ligarDesligarCronometro(true);
		msgVitoriaDerrota = "";
		ranking="";
		this.sortearPalavra();
	}
	
	/*-------------------SORTEIA UMA PALAVRA----------------*/
	/*
	 * verifica se palavra já foi escolhida em partida anteriores e chama 
	 * sortearPalavra() de PalavraEmJogo
	 */
	
	public void sortearPalavra() {
		if(getEmJogo().palavraFoiescolhida()) {
		}
		else{
			getEmJogo().sortearPalavra();
		}
	}
	
	/*-------------------ANALISA SE PLAYER PERDEU OU GANHOU----------------*/
	/*
	 * altera a mensagem de fim de jogo da tela
	 * utiliza funções derrota() e vitoria() de PalavraEmJogo
	 */
	
	private boolean vitoriaOuDerrota() {
		if(emJogo.derrota()) {
			msgVitoriaDerrota = "Que pena, você perdeu.";
			return true;
		}else if(emJogo.vitoria()){
			msgVitoriaDerrota = "Parabéns, você venceu!";
			return true;
		}else {
			return false;
		}
	}
	
	/*-------------------VERIFICA SE CARACTERE É LETRA----------------*/
	/*
	 * 
	 */
	
	private boolean NaoeLetra() {
		if((!letra.matches("[a-z]|[A-Z]"))) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/*-------------------VERIFICA E INSERE LETRA NOS ESPAÇOS----------------*/
	/*
	 * Transactional pois atualiza a pontuação do player
	 */
	
	@Transactional
	public void verificaLetra() {
		/* converte Letra de String para Caractere */
		letraConv = Character.valueOf(letra.toLowerCase().charAt(0));
		/*
		 * testa se partida já acabou, se o caractere inserido é letra ou não e se a
		 * letra inserida já foi dita
		 */
		if(!(this.vitoriaOuDerrota() || NaoeLetra() || this.letraJaSaiu(letraConv))) {
			if(emJogo.verificaLetra(letraConv)) {
				emJogo.adicionarLetra(letraConv, emJogo.getPalavra().getPalavra());
				emJogo.getLetrasJogadas().add(letraConv);
			}else {
				emJogo.acrescentarParteBoneco();
				emJogo.getLetrasJogadas().add(letraConv);
			}
			/* onde se calcula e atualiza a pontuação do player e constrói o ranking */
		}if(this.vitoriaOuDerrota()) {
			emJogo.getCronometroView().ligarDesligarCronometro(false);
			this.calcularPontuacao();
			this.mostrarRanking();
		}
	}
	
	/*-------------------TESTA PALAVRA ARRISCADA----------------*/
	/*
	 */
	@Transactional
	public void tentarPalavra() {
		if(!this.vitoriaOuDerrota()) {
			if(palavraArriscada.equalsIgnoreCase(emJogo.getPalavra().getPalavra())) {
				mensagemPalavraErrada="você acertou!";
				emJogo.preencherEspacos();
				this.verificaLetra();
			}else {
				mensagemPalavraErrada="palavra Errada!";
			}
		}else {
			this.verificaLetra();
		}
	}
	
	/*-------------------RETORNA O BONECO DA FORCA----------------*/
	/*
	 */
	
	public String retornaBoneco() {
		System.out.println(emJogo.getBoneco().getFigura());
		return emJogo.getBoneco().getFigura();
	}
	
	
	/*-------------------CONSTRÓI O RANKING COM OS 3 PRIMEIROS LUGARES----------------*/
	/*
	 */
	
	public void mostrarRanking() {
		List<Usuario> primeiros = new ArrayList<>();
		primeiros=usuarioDao.buscarTresPrimeiros();
		ranking = "MELHORES PONTUAÇÕES:\n";
		for (Usuario usuario : primeiros) {
			ranking = ranking + 
					usuario.getNome()+" ::: "+usuario.getPontuacao()+"\n\n";
		}

	}
	
	/*-------------------VERIFICA SE LETRA JÁ FOI DITA----------------*/
	/*
	 */
	
	public boolean letraJaSaiu(Character letra) {
		if(emJogo.getLetrasJogadas().contains(letra)) return true;
		
		return false;
	}
	
	/*-------------------CALCULA PONTUAÇÃO DO PLAYER----------------*/
	/*
	 */
	
	private void calcularPontuacao() {
		Long tamanhoPalavra=Integer.toUnsignedLong(emJogo.getPalavra().getPalavra().length());
		Long tempoDecorrido = Integer.toUnsignedLong(emJogo.getCronometroView().getContador());
		Double pontuacao = (10.0*tamanhoPalavra)/tempoDecorrido;
		String email = request.getUserPrincipal().getName();
		usuarioDao.alterarPontuacao(pontuacao, email);
	}
	
	/*-------------------GETTERS E SETTERS-----------------------*/
	
	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public PalavraEmJogo getEmJogo() {
		return emJogo;
	}

	public void setEmJogo(PalavraEmJogo emJogo) {
		this.emJogo = emJogo;
	}

	public String getMsgVitoriaDerrota() {
		return msgVitoriaDerrota;
	}

	public void setMsgVitoriaDerrota(String msgVitoriaDerrota) {
		this.msgVitoriaDerrota = msgVitoriaDerrota;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getPalavraArriscada() {
		return palavraArriscada;
	}

	public void setPalavraArriscada(String palavraArriscada) {
		this.palavraArriscada = palavraArriscada;
	}

	public String getMensagemPalavraErrada() {
		return mensagemPalavraErrada;
	}

	public void setMensagemPalavraErrada(String mensagemPalavraErrada) {
		this.mensagemPalavraErrada = mensagemPalavraErrada;
	}

}