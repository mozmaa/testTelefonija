package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import util.Konzola;

public class Pretplata {

	private final long id;
	private String broj;
	private LocalDate datum;
	private int trajanje;
	
	Tarifa tarifa;

	public Pretplata(long id, String broj, LocalDate datum, int trajanje, Tarifa tarifa) {
		super();
		this.id = id;
		this.broj = broj;
		this.datum = datum;
		this.trajanje = trajanje;
		this.tarifa = tarifa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pretplata other = (Pretplata) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Pretplata [id=" + id + ", broj=" + broj + ", datum=" + Konzola.formatiraj(datum) + ", trajanje=" + trajanje + ", tarifa=" + tarifa.getNaziv() + ", ukupna cena=" + ukupnaCenaPretplate() +"]";
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public long getId() {
		return id;
	}
	
	public double ukupnaCenaPretplate () {
		return this.trajanje * this.tarifa.getCena();
	}
	
	public static boolean isValid (int broj) {
		if(broj == 12 || broj == 24 || broj == 36)
			return true;
		return false;
	}
	
	public boolean proveraDatuma(LocalDate min, LocalDate max) {
		return this.datum.compareTo(min) >= 0 && datum.compareTo(max) <= 0;
	}
}
