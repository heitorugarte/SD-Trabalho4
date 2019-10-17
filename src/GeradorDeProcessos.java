/*
 * Alunos: Heitor Silveira e Ot�vio Coelho
 */
public class GeradorDeProcessos extends Thread {

	/*
	 * Define o tempo para criar um novo processo.
	 */
	private long tempoParaGerar = 40000;

	/*
	 * Algoritmo para cria��o e inser��o de um novo projeto na lista. Cria um novo
	 * processo e verifica se ele � o primeiro na lista. Caso seja, seu sucessor �
	 * ele mesmo. Cason�o,
	 */
	public void run() {
		int id = 0;
		while (true) {
			Processo novo = new Processo();
			Main.processos.add(novo);
			try {
				Thread.sleep(tempoParaGerar);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}