/*
 * Alunos: Heitor Silveira e Otávio Coelho
 */
public class ExterminadorDeCoordenadores extends Thread {

	/*
	 * Define o tempo para matar o coordenador atual
	 */
	private long tempoParaMatar = 60000;

	/*
	 * Algoritmo para desativar o coordenador atual a cada minuto.
	 */
	public void run() {
		try {
			while (true) {
				Thread.sleep(tempoParaMatar);
				Main.coordenador.setAtivo(false);
				System.out.println(
						"\nO coordenador (Processo " + Main.coordenador.getIdentificador() + ") foi desativado!");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}