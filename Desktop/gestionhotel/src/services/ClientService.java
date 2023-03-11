package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDAO;
import entities.Client;

public class ClientService implements IDAO<Client> {

    List<Client> clients; // Creation de la liste

    public ClientService() {
        clients = new ArrayList<Client>(); // constructeur et affectation de la liste
    }

    @Override
    public boolean create(Client o) {
        String sql = "insert into client values(null,?,?,?,?)";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTelephone());
            ps.setString(4, o.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.toString();
        }

        return true;
    }

    @Override
    public boolean update(Client o) {
        String sql = "update Client set nom=?,prenom=?,telephone=?,email=? where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTelephone());
            ps.setString(4, o.getEmail());
            ps.setInt(5, o.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(Client o) {
        String sql = "delete from client where id=?";

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
    public Client findById(int id) {
        String sql = "select * from client where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Client> findAll() {
        String sql = "select * from client";
        List<Client> clients = new ArrayList<Client>();

        try {

            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("telephone"), rs.getString("email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

}
