import java.io.Serializable;
import java.util.ArrayList;

public class infoClient  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int ID;
	String message;
	ArrayList<String> clientsOnline = new ArrayList<String>();
}
