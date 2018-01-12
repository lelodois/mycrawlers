package br.com.lelo.mycrawlers.exception;

public class SescspInvalidXpahExpression extends Exception {

	private static final long serialVersionUID = -1280918771231163822L;

	@Override
	public String getMessage() {
		return "Nenhum resultado encontrado";
	}
}
