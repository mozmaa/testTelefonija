package model.dao;

import java.util.Collection;

import model.Pretplata;

public interface PretplataDAO {

	Collection<Pretplata> getAll() throws Exception;

	void add(Pretplata pretplata) throws Exception;

}
