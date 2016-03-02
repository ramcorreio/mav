import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MensagemView implements Serializable {
	
	private static final long serialVersionUID = 6137507973393064760L;

	private String input;
	
	private String result;
	
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public void enviar() {
		
		System.out.println("enviando: " + input);
		StringBuilder b = new StringBuilder();
		b.append("entrada: " + input);
		b.append("<br/>======================================<br/>");
		b.append("saída: " + input);
		result = b.toString();
	}

}
