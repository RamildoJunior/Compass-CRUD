package br.com.projeto.product.config.validacao;

import java.util.List;

public class ExceptionResponses {

	private int status_code;

	private List<String> messages;

	public ExceptionResponses(int status_code, List<String> messages) {

		this.status_code = status_code;
		this.messages = messages;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
