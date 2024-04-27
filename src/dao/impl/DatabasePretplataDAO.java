package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import model.Pretplata;
import model.Tarifa;
import model.dao.PretplataDAO;

public class DatabasePretplataDAO implements PretplataDAO{

	private final Connection conn;
	
	public DatabasePretplataDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Pretplata> getAll() throws Exception {
		Collection <Pretplata> pretplate = new ArrayList<Pretplata>();
		
		String sql = "SELECT p.id, p.broj, p.datum, p.trajanje, t.id, t.ime, t.opis, t.cena FROM pretplate p " +
				     "LEFT JOIN tarife t ON p.idTarife = t.id";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					
					int kolona = 0;
					long pId = rset.getLong(++kolona);
					String broj = rset.getString(++kolona);
					LocalDate datum = rset.getDate(++kolona).toLocalDate();
					int trajanje = rset.getInt(++kolona);
					
					long tId = rset.getLong(++kolona);
					String ime = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					
					pretplate.add(new Pretplata(pId, broj, datum, trajanje, new Tarifa(tId, ime, opis, cena)));
					
				}
			}
		}
		return pretplate;
	}

	@Override
	public void add(Pretplata pretplata) throws Exception {
		
		String sql = "INSERT INTO pretplate (broj, datum , trajanje, idTarife) VALUES (? , ? , ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) { 
			int param = 0;
			stmt.setString(++param, pretplata.getBroj());
			stmt.setDate(++param, Date.valueOf(pretplata.getDatum()));
			stmt.setInt(++param, pretplata.getTrajanje());
			stmt.setLong(++param, pretplata.getTarifa().getId());
			
			stmt.executeUpdate(); 
		}
	}

}
