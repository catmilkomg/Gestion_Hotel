package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import connexion.Connexion;
import dao.IDAO;
import entities.Chambre;
import entities.Client;
import entities.Reservation;
import java.util.Date;

public class ReservationService implements IDAO<Reservation> {

    List<Reservation> res;
    List<Chambre> chs = new ArrayList<Chambre>();
    Client l;
    ClientService cs = new ClientService();

    ChambreService cha = new ChambreService();
    Connexion con;

    public ReservationService() {
        res = new ArrayList<Reservation>();
    }

    @Override
    public boolean create(Reservation o) {
        String sql = "insert into reservation1(id,client,chambre,date_debut,date_fin) values(null,?,?,?,?)";
java.sql.Date dd= new java.sql.Date(o.getDatedebut().getTime());
			java.sql.Date df= new java.sql.Date(o.getDatefin().getTime());
        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setDate(4, dd);
            ps.setDate(3, df);
            ps.setInt(2, o.getCh().getId());
            ps.setInt(1, o.getL().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.toString();
        }

        return true;
    }

    @Override
    public boolean update(Reservation o) {// simpleDateFormat
        String sql = "update reservation1 set date_debut=?,date_fin=?,chambre=?,client=? where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) o.getDatedebut());
            ps.setDate(2, (java.sql.Date) o.getDatefin());
            ps.setInt(3, o.getCh().getId());
            ps.setInt(4, o.getL().getId());
            ps.setInt(5, o.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(Reservation o) {
        String sql = "delete from reservation1 where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, o.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Reservation findById(int id) {
        String sql = "select * from reservation1 where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt(1), cs.findById(rs.getInt(2)),cha.findById(rs.getInt(3)), rs.getDate(4), rs.getDate(5)
                       );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "select * from reservation1";
        List<Reservation> reservations = new ArrayList<Reservation>();

        try {

            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(rs.getInt(1), cs.findById(rs.getInt(2)),cha.findById(rs.getInt(3)), rs.getDate(5), rs.getDate(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
public boolean checkRes(Chambre c,Date dateDebut,Date dateFin){
          
           con= (Connexion) Connexion.getCon();
		  String req="select * from reservation r  where ( (r.date_debut>=? and r.date_fin<=?) or (? between r.date_debut and r.date_fin) or (? between r.date_debut and r.date_fin) )and r.id_ch=?";
                 java.sql.Date dd= new java.sql.Date(dateDebut.getTime());
			java.sql.Date df= new java.sql.Date(dateFin.getTime());
                try {
                 
			PreparedStatement st=con.prepareStatement(req);
                       // st.setInt(1, c.getId());
                        st.setDate(1, dd);
                        st.setDate(2,  df);
                        st.setDate(3, dd);
                        st.setDate(4, df);
                        st.setInt(5, c.getId());
                        
                      //  st.setDate(2, (java.sql.Date) new Date(dateDebut.getTime()));
                      //  st.setDate(3, (java.sql.Date) new Date(dateFin.getTime()));
                        
			ResultSet res=st.executeQuery();
		while(res.next()){
                    return true;
                }
             
		}catch(SQLException e) {
			System.out.println("checkRes !!");
                        e.printStackTrace();
		}
        return false;
       }
    public List<Chambre> findChambreBetweenDates(Client cl, Date dateDebut, Date dateFin) {
        List<Chambre> chs2 = new ArrayList<Chambre>();

        for (Reservation r : findAll()) {
            if (r.getL() == cl && r.getDatedebut().after(dateDebut) && r.getDatefin().before(dateFin)) {
                chs2.add(r.getCh());
            }
        }
        return chs2;

    }

}
