package ui;

import java.time.LocalDate;
import java.util.Collection;

import model.Pretplata;
import model.Tarifa;
import model.dao.PretplataDAO;
import util.Konzola;

public class PretplataUI {

	public static PretplataDAO pretplataDAO;
	
	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		PretplataUI.pretplataDAO = pretplataDAO;
	}
	

	public static void prikazSvih() {
		try {
			Collection<Pretplata> pretplate = pretplataDAO.getAll();
			
			for (Pretplata tempPret : pretplate) {
				System.out.println(tempPret);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	public static void add() {
		try {
			Tarifa tarifa = TarifaUI.pronalaznje();
			if(tarifa == null)
				return;
			
			Collection<Pretplata> pretplate = pretplataDAO.getAll();
			String broj = "";
			while (broj.equals("") || !(broj.length() == 13) || !(broj.startsWith("+38164"))) {
				broj = Konzola.ocitajString("Pretplatnicki broj (+38164xxxxxxx (13 karaktera))");	
			}
			
			for (Pretplata tempPret : pretplate) {
				if (broj.equals(tempPret.getBroj())) {
					System.out.println("Pretplata za broj postoji");
					return;
				}
			}
			
			LocalDate datum = null;
			while(datum == null || datum.compareTo(LocalDate.now()) < 0) {
				datum = Konzola.ocitajDate("Datum pocetka pretplate");
				if(datum == null || datum.compareTo(LocalDate.now()) < 0)
					System.out.println("Unos ne moze biti prazan niti datum pre danasnjeg");
			}
			
			int trajanje = 0;
			while (trajanje == 0 || !Pretplata.isValid(trajanje)) {
				trajanje = Konzola.ocitajInt("Trajanje pretplate");
				if(trajanje == 0 ||	!Pretplata.isValid(trajanje))
					System.out.println("Unos mora bit 12/24/36");
			}
			
			Pretplata pretplata = new Pretplata(0, broj, datum, trajanje, tarifa);
			pretplataDAO.add(pretplata);
			System.out.println("Uspesno dodavanje");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
		
	}

}
