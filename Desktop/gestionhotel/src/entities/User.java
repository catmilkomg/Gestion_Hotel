/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Utilisateur
 */
public class User {
      private int id;
		private String nom;
		
		private String email;
		private String password;
		
		
		public User(String nom,String email, String password) {
			super();
			this.nom = nom;
			
			this.email = email;
			this.password = password;
		}
                public User(int id,String nom,String email, String password) {
			super();
			this.id = id;
			this.nom = nom;
			
			this.email = email;
			this.password = password;
		}

		public User() {
		
		}

		public User( String email, String password) {
			this.email = email;
			this.password = password;	
		}
		
		public int getId() {
			return id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		
}
