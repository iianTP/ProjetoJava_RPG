package exceptions;

public class InvalidEffectException extends Exception {
	public InvalidEffectException(String effect) {
		super("efeito \""+effect+"\" é inválido");
	}
}
