package model.dao;

import java.util.Collection;

import model.Tarifa;

public interface TarifaDAO {

	Collection<Tarifa> getAll() throws Exception;
	Tarifa get(long id) throws Exception;

}
