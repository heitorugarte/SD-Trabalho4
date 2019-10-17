public final class Recurso {

	private static final Recurso INSTANCE = new Recurso();

	private Processo processoDetentor;

	public Processo getProcessoDetentor() {
		return processoDetentor;
	}

	public void setProcessoDetentor(Processo processoDetentor) {
		this.processoDetentor = processoDetentor;
		if (processoDetentor != null)
			System.out.println(
					"\nRecurso: O novo detentor é o processo " + this.processoDetentor.getIdentificador() + "!");
	}

	public static Recurso getInstance() {
		return INSTANCE;
	}
}
