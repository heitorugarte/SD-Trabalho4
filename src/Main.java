import java.util.ArrayList;

public class Main {

	public static ArrayList<Processo> processos;

	public static Processo coordenador;

	public static Recurso recurso;

	public static void main(String[] args) {

		// Recurso que será compartilhado (Singleton)
		recurso = new Recurso();
		
		System.out.println("Recurso instanciado. Processo detentor: " + recurso.getProcessoDetentor() + "\n");

		// Lista de processos
		processos = new ArrayList<Processo>();

		// Processo inicial que será coordenador
		coordenador = new Processo(true);
		processos.add(coordenador);

		// Inicia o gerador de processos
		GeradorDeProcessos gerador = new GeradorDeProcessos();
		gerador.start();

		ExterminadorDeCoordenadores exterminador = new ExterminadorDeCoordenadores();
		//exterminador.start();
	}
}