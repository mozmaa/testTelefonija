package model;

public class StavkaIzvestaja {
	
	public final String naziv;
	public final double ukupanPrihod;
	public final int maxBrojTrajanja;
	
	
	public StavkaIzvestaja(String naziv, double ukupanPrihod, int maxBrojTrajanja) {
		super();
		this.naziv = naziv;
		this.ukupanPrihod = ukupanPrihod;
		this.maxBrojTrajanja = maxBrojTrajanja;
	}


	@Override
	public String toString() {
		return "StavkaIzvestaja [naziv=" + naziv + ", ukupanPrihod=" + ukupanPrihod + ", maxBrojTrajanja="
				+ maxBrojTrajanja + "]";
	}
	
	
	
}
