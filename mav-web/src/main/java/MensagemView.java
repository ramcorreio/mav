import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MensagemView {
	
	private String string;
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public void enviar() {
		
		System.out.println("enviando: " + string);
	}

}
