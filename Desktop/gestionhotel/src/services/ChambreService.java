package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDAO;
import entities.Categorie;
import entities.Chambre;

public class ChambreService implements IDAO<Chambre> {

    List<Chambre> chambres;

    CategorieService cs = new CategorieService();

    public ChambreService() {
        chambres = new ArrayList<Chambre>();
    }

    @Override
    public boolean create(Chambre o) {
        String sql = "insert into chambre values(null,?,?)";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getTelephone());
            ps.setInt(2, o.getC().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.toString();
        }

        return true;
    }

    @Override
    public boolean update(Chambre o) {
        String sql = "update chambre set libelle=?,categorie_id=? where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getTelephone());
            ps.setInt(2, o.getC().getId());
            ps.setInt(3, o.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(Chambre o) {
        String sql = "delete from chambre where id=?";

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
    public Chambre findById(int id) {
        String sql = "select * from chambre where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Chambre(rs.getInt(1), rs.getString(2), cs.findById(rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        String sql = "select * from chambre";
        List<Chambre> chambres = new ArrayList<Chambre>();

        try {

            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                chambres.add(new Chambre(rs.getInt(1), rs.getString(2), cs.findById(rs.getInt(3))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;
    }

    public List<Chambre> findChambreByCategorie(Categorie c) {
        List<Chambre> chs2 = new ArrayList<Chambre>();
        for (Chambre ch : findAll()) {
            if (ch.getC() == c) {
                chs2.add(ch);
            }
        }
        return chs2;
    }

}
