package co.edu.usa.ArtesaniasDeNuestraTierra.jwt;

public class JwtRequest
{
	private String phone;
	private String password;
	
	public JwtRequest()
	{
		
	}
	
	public JwtRequest(String phone, String password)
	{
		this.phone = phone;
		this.password = password;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone) {
        this.phone = phone;
    }
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password) {
        this.password = password;
    }
}