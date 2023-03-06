package entities;

import java.util.Date;

public class Reservation {

	private int id;
	private Date datedebut;
	private Date datefin;
	Chambre ch;
	Client l;
	private static int count = 0;

	public Reservation(Client l,Chambre ch,Date datedebut, Date datefin ) {
		super();
		this.id = ++count;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.ch = ch;
		this.l = l;
	}

	public Reservation(int id, Client l,Chambre ch,Date datedebut, Date datefin) {
		super();
		this.id = id;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.ch = ch;
		this.l = l;
	}

   

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Chambre getCh() {
		return ch;
	}

	public void setCh(Chambre ch) {
		this.ch = ch;
	}

	public Client getL() {
		return l;
	}

	public void setL(Client l) {
		this.l = l;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", ch=" + ch + ", l=" + l
				+ "]";
	}

}
