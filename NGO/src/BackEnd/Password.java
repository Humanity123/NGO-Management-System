package BackEnd;
 
public class Password {
	private String password;
 
	public Password(String password){
		this.password = password;
	}
	private String encrypt(String pass){
		return pass ;//TODO To write encryption function 
	}
	public String getPassword(){
		return encrypt(password);
	}
}