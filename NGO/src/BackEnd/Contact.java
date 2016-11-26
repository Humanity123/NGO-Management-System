//contact class
 
package BackEnd;
 
public class Contact {
	private String address;
	private String phone;
 
	public Contact(String address, String phone){
		this.address = address;
		this.phone = phone;
	}
	public String getAddress(){
		return address;
	}
	public String getPhone(){
		return phone;
	}
 
}