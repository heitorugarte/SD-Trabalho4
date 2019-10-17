import java.util.LinkedList;

public class Processo extends Thread {

	private static int ids = 0;
	private int identificador;
	private boolean ehCoordenador, ativo, possuiRecurso, estaNaFila;
	private static boolean eleicaoEmAndamento;

	private LinkedList<Processo> listaDeProcessos;

	/*
	 * Construtor de um processo n�o-coordenador
	 */
	public Processo() {
		super();
		this.identificador = ++ids;
		this.possuiRecurso = false;
		System.out.println("\nGerador: Um novo processo foi criado! (Processo " + this.identificador + ")");
		super.start();
	}

	/*
	 * Construtor de um processo coordenador
	 */
	public Processo(boolean isCoordenador) {
		super();
		this.identificador = ++ids;
		this.possuiRecurso = false;
		this.ehCoordenador = true;
		listaDeProcessos = new LinkedList<Processo>();
		System.out.println("O primeiro coordenador foi criado! (Processo " + this.identificador + ")");
		super.start();
	}

	/*
	 * M�todo de execu��o do processo. Se n�o for coordenador, faz uma requisi��o
	 * para controlar o recurso a cada intervalo de 10 a 25 segundos (aleat�rio). Se
	 * for coordenador, gerencia a fila de requisi��es para controlar o recurso e
	 * distribui o controle de acordo
	 */
	@Override
	public void run() {
		this.ativo = true;
		while (true) {
			if (!ehCoordenador) {
				if (!possuiRecurso && !estaNaFila) {
					if (fazerRequisicao(this)) {
						processarRecurso();
					} else {
						estaNaFila = true;
						long tempoParaRefazerRequisicao = nextLongRange(10000, 25000);
						System.out.println("Processo " + this.identificador + ": tentar� denovo em "
								+ (tempoParaRefazerRequisicao / 1000) + " segundos");
						try {
							Thread.sleep(tempoParaRefazerRequisicao);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			super.run();
		}
	}

	private void processarRecurso() {
		long tempoParaProcessarRecurso = nextLongRange(5000, 15000);
		System.out.println("Processo " + this.identificador + ": estar� efetuando o processamento por "
				+ (tempoParaProcessarRecurso / 1000) + " segundos.");
		try {
			Thread.sleep(tempoParaProcessarRecurso);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nProcesso " + this.identificador + ": terminou o processamento do recurso.");
		Main.coordenador.liberarRecurso();
		possuiRecurso = false;
	}

	/*
	 * M�todo para gerar um long entre um limite minimo e maximo
	 * 
	 * @return numero gerado
	 */
	public long nextLongRange(long minimo, long maximo) {
		long resultado = minimo + (long) (Math.random() * (maximo - minimo));
		// System.out.println("\nSorteio de long: " + resultado + " ms");
		return resultado;
	}

	public void virarCoordenador() {
		ehCoordenador = true;
		Main.coordenador = this;
		listaDeProcessos = new LinkedList<Processo>();
	}

	/*
	 * M�todo para fazer requisi��o para controlar o recurso
	 * 
	 * @return booleano indicando se obteve controle do recurso ou n�o
	 */
	public synchronized boolean fazerRequisicao(Processo solicitante) {
		if (Main.coordenador.ativo) {
			return Main.coordenador.checarDisponibilidadeRecurso(solicitante);
		} else {
			// Fazer eleicao do novo coordenador
			Processo.eleicaoEmAndamento = true;
		}
		return false;
	}

	/*
	 * M�todo do algoritmo centralizado Verifica se o recurso est� sendo utilizado
	 * por algum processo. Se n�o estiver, cede o recurso ao processo solicitante.
	 * Se estiver ocupado, insere o solicitante por ultimo na lista de processos em
	 * espera.
	 */
	public boolean checarDisponibilidadeRecurso(Processo solicitante) {
		if (ehCoordenador) {
			if (Main.recurso.getProcessoDetentor() == null) {
				Main.recurso.setProcessoDetentor(solicitante);
				return true;
			} else {
				listaDeProcessos.addLast(solicitante);
				System.out.println("\nCoordenador: O Processo " + solicitante.getIdentificador()
						+ " tentou solicitar acesso ao recurso sem sucesso, e foi adicionado � lista de espera.");
				System.out.println("Coordenador: Lista de espera dos processos: " + listaToString());
			}
		}
		return false;
	}

	public void liberarRecurso() {
		if (listaDeProcessos.size() > 0) {
			Processo proximoProcesso = listaDeProcessos.removeFirst();
			Main.recurso.setProcessoDetentor(proximoProcesso);

			proximoProcesso.possuiRecurso = true;
			proximoProcesso.estaNaFila = false;
			proximoProcesso.processarRecurso();
		} else {
			Main.recurso.setProcessoDetentor(null);
		}
	}

	public int getIdentificador() {
		return this.identificador;
	}

	public boolean isCoordenador() {
		return this.ehCoordenador;
	}

	public void setCoordenador(boolean isCoordenador) {
		this.ehCoordenador = isCoordenador;
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		if (ativo == false) {
			this.ativo = ativo;
			this.ehCoordenador = false;
			this.listaDeProcessos = new LinkedList<Processo>();
		}
	}

	private String listaToString() {
		try {
			String toString = "";
			for (Processo p : listaDeProcessos) {
				toString += p.getIdentificador() + ", ";
			}
			return toString;
		} catch (NullPointerException ex) {
			return ex.getMessage();
		}
	}
}
