package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.Pretplata;
import model.StavkaIzvestaja;
import model.dao.PretplataDAO;
import util.Konzola;

public class IzvestajUI {

	public static PretplataDAO pretplataDAO;
	
	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		IzvestajUI.pretplataDAO = pretplataDAO;
	}

	public static void izvestavanje() {
		
		LocalDate min = Konzola.ocitajDate("Pocatak");
		LocalDate max = Konzola.ocitajDate("Kraj");
		
		try {
		Collection<Pretplata> pretplate = pretplataDAO.getAll();
		
		Set<String> nazivi = new LinkedHashSet<String>();
		
		List<StavkaIzvestaja> stavke = new ArrayList<>();
		
		for (Pretplata temppPret : pretplate) {
			nazivi.add(temppPret.getTarifa().getNaziv());
		}
		
		for (String tempNaz : nazivi) {
			
			double ukupanPrihod = 0;
			int maxTrajanje = 0;
			int tra12 = 0;
			int tra24 = 0;
			int tra36 = 0;
			for (Pretplata tempPret: pretplate) {
				if(tempNaz.equals(tempPret.getTarifa().getNaziv()) && tempPret.proveraDatuma(min, max)){
					ukupanPrihod += tempPret.getTarifa().getCena();
					if(tempPret.getTrajanje() == 12)
						tra12 ++;
					if(tempPret.getTrajanje() == 24)
						tra24 ++;
					if(tempPret.getTrajanje() == 36)
						tra36 ++;
				}
				if(tra12 > tra24 && tra12 > tra36)
					maxTrajanje = 12;
				if(tra24 > tra12 && tra24 > tra36)
					maxTrajanje = 24;
				if(tra36 > tra12 && tra36 > tra24)
					maxTrajanje = 36;
				
			}
			StavkaIzvestaja stavka = new StavkaIzvestaja(tempNaz, ukupanPrihod, maxTrajanje);
			stavke.add(stavka);
		}
		
		stavke = stavke.stream()
				.sorted((stat1, stat2) -> -Double.compare(stat1.ukupanPrihod, stat2.ukupanPrihod))
				.toList();
		
		for (StavkaIzvestaja TempStavka : stavke) {
			System.out.println(TempStavka);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske");
		}
	}

	

}
