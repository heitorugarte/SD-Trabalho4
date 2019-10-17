/*
 * Alunos: Heitor Silveira e Otávio Coelho
 */
public class GeradorDeProcessos extends Thread {

	/*
	 * Define o tempo para criar um novo processo.
	 */
	private long tempoParaGerar = 40000;

	/*
	 * Algoritmo para criação e inserção de um novo projeto na lista. Cria um novo
	 * processo e verifica se ele é o primeiro na lista. Caso seja, seu sucessor é
	 * ele mesmo. Casonão,
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