//manager iteration 2.0
 
 
package BackEnd;
 
public class Manager {
	private String name, id, password;
	private Contact contact;
 
	public Manager(String name, String id, String password,Contact contact){
		this.name = name;
		this.id = id;
		this.password = password;
		this.contact=contact;
	}
 
	public Manager(String name, String string2, Contact contact, Password password2) {
		this.name=name;
		this.password=password;
		this.id=id;
		this.contact=contact;
		// TODO Auto-generated constructor stub
	}
 
}