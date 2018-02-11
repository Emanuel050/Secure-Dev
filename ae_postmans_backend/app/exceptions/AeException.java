package exceptions;

public class AeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AeMessage aeMessage;
	
	public AeException(AeMessage aeMessage) {
		super();
		this.aeMessage = aeMessage;
	}

	public AeMessage getAeMessage() {
		return aeMessage;
	}

	public void setAeMessage(AeMessage aeMessage) {
		this.aeMessage = aeMessage;
	}
	
}
