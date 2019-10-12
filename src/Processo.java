import java.util.LinkedList;

public class Processo extends Thread {
	private int id;
	private boolean isCoordenador;
	private Recurso recurso;
	
	private LinkedList<Processo> listaDeProcessos;
	
	public Processo(int id, boolean isCoordenador, Recurso recurso) {
		super();
		this.id = id;
		this.isCoordenador = isCoordenador;
		this.recurso = recurso;
		this.listaDeProcessos = new LinkedList<Processo>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	public int getIdentificador() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCoordenador() {
		return isCoordenador;
	}

	public void setCoordenador(boolean isCoordenador) {
		this.isCoordenador = isCoordenador;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}
