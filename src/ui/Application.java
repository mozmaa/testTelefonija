package ui;

import java.sql.Connection;
import java.sql.DriverManager;


import dao.impl.DatabasePretplataDAO;
import dao.impl.DatabaseTarifaDAO;
import model.dao.PretplataDAO;
import model.dao.TarifaDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/telefonija?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");

		PretplataDAO pretplataDAO = new DatabasePretplataDAO(conn);
		TarifaDAO tarifaDAO = new DatabaseTarifaDAO(conn);

		TarifaUI.setTarifaDAO(tarifaDAO);
		PretplataUI.setPretplataDAO(pretplataDAO);
		IzvestajUI.setPretplataDAO(pretplataDAO);
	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");
			
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		Meni.pokreni("Telefonija", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Izlaz"),
			new FunkcionalnaStavkaMenija("Prikaz svih tarifa") {

				@Override
				public void izvrsi() { TarifaUI.prikazSvih(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Prikaz svih pretplata sa tarifama") {

				@Override
				public void izvrsi() { PretplataUI.prikazSvih(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Dodavanje pretplate ") {

				@Override
				public void izvrsi() { PretplataUI.add(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Izveštavanje") {

				@Override
				public void izvrsi() { IzvestajUI.izvestavanje(); }
				
			}
		});
	}

}
