package umu.tds.dao;

import umu.tds.persistencia.FactoriaDAO;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {	}
	
	@Override
	public TDSUsuarioDAO getUsuarioDAO() {	
		return new TDSUsuarioDAO(); 
	}

}
