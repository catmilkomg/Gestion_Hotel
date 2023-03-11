/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import dao.IDAO;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class UserService implements IDAO <User>{
	
			Connection con;
			public int log(User u) {
				String req="SELECT * FROM utilisateur where username=? AND password=? ;";
				con=Connexion.getCon();
				try {
					PreparedStatement st=con.prepareStatement(req);
					st.setString(1, u.getEmail());
					st.setString(2, u.getPassword());
					ResultSet rs= st.executeQuery();
					if(rs.next()) {
						return 1;
					}
					
				}catch(SQLException e) {
					System.out.println("login !!");
				}
				return 0;
			}
			@Override
			public boolean create(User o) {
				con=Connexion.getCon();
				String req="INSERT INTO utilisateur VALUES (null,?,?,?);";
				try {
					PreparedStatement st=con.prepareStatement(req);
					st.setString(1, o.getNom());
					
					st.setString(2, o.getEmail());
					st.setString(3, o.getPassword());
					st.executeUpdate();
				}catch(SQLException e) {
					System.out.println("add user !!");
					e.printStackTrace();
				}
				return false;
				
			}
			@Override
			public boolean update(User o) {
				   String sql = "update utilisateur set nom=?,username=?,password=? where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setString(1, o.getNom());
          
            ps.setString(2, o.getEmail());
            ps.setString(3, o.getPassword());
            ps.setInt(4, o.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
			}
			@Override
			public boolean delete(User o) {
				 String sql = "delete from utilisateur where id=?";

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
			public User findById(int id) {
				 String sql = "select * from utilisateur where id=?";

        try {
            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

			}
			@Override
			public List<User> findAll() {
				 String sql = "select * from utilisateur";
        List<User> user = new ArrayList<User>();

        try {

            PreparedStatement ps = Connexion.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
			
			
}