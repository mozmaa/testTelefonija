package ui;

import java.util.Collection;

import model.Tarifa;
import model.dao.TarifaDAO;
import util.Konzola;

public class TarifaUI {

	public static TarifaDAO tarifaDAO;
	
	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		TarifaUI.tarifaDAO = tarifaDAO;
	}
	
	public static Tarifa pronalaznje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite id tarife");

		Tarifa tarifa = tarifaDAO.get(id);
		if (tarifa == null)
			Konzola.prikazi("Tarifa nije pronađena!");

		return tarifa;
	}

	public static void prikazSvih() {
		try {
			Collection<Tarifa> tarife = tarifaDAO.getAll();
			
			for (Tarifa tempTari : tarife) {
				System.out.println(tempTari);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

}
