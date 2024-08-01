package co.edu.usa.ArtesaniasDeNuestraTierra.jwt;

public class JwtResponse
{
	private String token;
    private String phone;
    private int id;
    private String name;

    public JwtResponse(String token, String phone, int id, String name) {
        this.token = token;
        this.phone = phone;
        this.id    = id;
        this.name  = name;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}