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

public class CategorieService implements IDAO<Categorie> {

    List<Categorie> categories;

    public CategorieService() {
        categories = new ArrayList<Categorie>();

    }

    @Override
    public boolean create(Categorie o) {
        String sql = "insert into categorie values(null,?,?)";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getCode());
            ps.setString(2, o.getLibelle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.toString();
        }

        return true;
    }

    @Override
    public boolean update(Categorie o) {
        String sql = "update categorie set code=?,libelle=? where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getCode());
            ps.setString(2, o.getLibelle());
            ps.setInt(3, o.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(Categorie o) {

        String sql = "delete from categorie where id=?";

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
    public Categorie findById(int id) {
        String sql = "select * from categorie where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Categorie> findAll() {
        String sql = "select * from categorie";
        List<Categorie> categories = new ArrayList<Categorie>();

        try {

            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Categorie(rs.getInt("id"), rs.getString("code"), rs.getString("libelle")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

}
