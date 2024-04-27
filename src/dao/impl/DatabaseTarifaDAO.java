package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import model.Tarifa;
import model.dao.TarifaDAO;

public class DatabaseTarifaDAO implements TarifaDAO {

	private final Connection conn;
	
	public DatabaseTarifaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Collection<Tarifa> getAll() throws Exception {
		Collection <Tarifa> tarife = new ArrayList<Tarifa>();
		
		String sql = "SELECT id, ime , opis, cena FROM tarife";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					
					long id = rset.getLong(++kolona);
					String ime = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					
					tarife.add(new Tarifa(id, ime, opis, cena));
				}
				
			}
		}
		return tarife;
	}

	@Override
	public Tarifa get(long id) throws Exception {
		Tarifa tarifa = null;
		
		String sql = "SELECT  ime , opis, cena FROM tarife WHERE id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, id);
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					
					String ime = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					
					tarifa = new Tarifa(id, ime, opis, cena);
				}
				
			}
		}
		return tarifa;
	}

}
