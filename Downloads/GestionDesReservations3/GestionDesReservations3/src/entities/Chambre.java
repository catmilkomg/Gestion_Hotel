package entities;

public class Chambre {

	private int id;
	private String telephone;
	Categorie c;
	private static int count = 0;

	public Chambre(String telephone, Categorie c) {
		super();
		this.id = ++count;
		this.telephone = telephone;
		this.c = c;
	}

	public Chambre(int id, String telephone, Categorie c) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.c = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Categorie getC() {
		return c;
	}

	public void setC(Categorie c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return id+ " " +c.toString();
	}

}
