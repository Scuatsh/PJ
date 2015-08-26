package org.dgac.webservice.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dgac.common.constantes.Conexion;
import org.dgac.common.dto.FuncionarioDTO;
import org.dgac.webservice.facade.FuncionarioFacade;


public class OracleFuncionarioDAO implements FuncionarioFacade {

	public FuncionarioDTO obtenerFuncionario(String p_email) throws Exception {
		// TODO Auto-generated method stub
		FuncionarioDTO objFuncionario = new FuncionarioDTO();
		Conexion cn = new Conexion();
		
		String sql = 
				
		"SELECT " +
		"          RUT, " +
		"          DIGITO_VERIFICADOR, " +
		"          NOMBRES, " +
		"          APELLIDO_PATERNO, " +
		"          APELLIDO_MATERNO, " +
		"          NOMBRE_UNIDAD, " +
		"		   COD_UNIDAD, " +
		"          E_MAIL " +
		"FROM " +
		"          DBO_AIRH.VIEW_DATOS_TRABAJADORES T " +
		"WHERE " +
		"          T.E_MAIL = ? ";
		
		try{
			PreparedStatement ps = cn.conectar_sigerh().prepareStatement(sql);
			ps.setString(1, p_email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				objFuncionario.setRut(rs.getInt("RUT"));
				objFuncionario.setDigito_verificador(rs.getString("DIGITO_VERIFICADOR"));
				objFuncionario.setNombres(rs.getString("NOMBRES"));
				objFuncionario.setApellido_paterno(rs.getString("APELLIDO_PATERNO"));
				objFuncionario.setApellido_materno(rs.getString("APELLIDO_MATERNO"));
				objFuncionario.setUnidadId(rs.getInt("COD_UNIDAD"));
				objFuncionario.setUnidad(rs.getString("NOMBRE_UNIDAD"));
			}else{
				objFuncionario = null;
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			cn.cerrarConexion();
		}
		
		return objFuncionario;
	}

	public List<FuncionarioDTO> obtenerFuncionarioList() throws Exception {
		// TODO Auto-generated method stub
		Conexion cn = new Conexion();
		List<FuncionarioDTO> funcionarioList = new ArrayList<FuncionarioDTO>();
		
		String sql = 
				
			"SELECT " +
			"          RUT, " +
			"          DIGITO_VERIFICADOR, " +
			"          NOMBRES, " +
			"          APELLIDO_PATERNO, " +
			"          APELLIDO_MATERNO, " +
			"          NOMBRE_UNIDAD, " +
			"		   COD_UNIDAD, " + 
			"          E_MAIL " +
			"FROM " +
			"          DBO_AIRH.VIEW_DATOS_TRABAJADORES T ";
			
		try{
			PreparedStatement ps = cn.conectar_sigerh().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				FuncionarioDTO objFuncionario = new FuncionarioDTO();
				objFuncionario.setRut(rs.getInt("RUT"));
				objFuncionario.setDigito_verificador(rs.getString("DIGITO_VERIFICADOR"));
				objFuncionario.setNombres(rs.getString("NOMBRES"));
				objFuncionario.setApellido_paterno(rs.getString("APELLIDO_PATERNO"));
				objFuncionario.setApellido_materno(rs.getString("APELLIDO_MATERNO"));
				objFuncionario.setUnidad(rs.getString("NOMBRE_UNIDAD"));
				objFuncionario.setUnidadId(rs.getInt("COD_UNIDAD"));
				objFuncionario.setMail(rs.getString("E_MAIL"));
				funcionarioList.add(objFuncionario);
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			cn.cerrarConexion();
		}
		
		return funcionarioList;
	}

	public FuncionarioDTO obtenerFuncionario(int p_rut) throws Exception {
		// TODO Auto-generated method stub
		Conexion cn = new Conexion();
		FuncionarioDTO objFuncionario = new FuncionarioDTO();
		
		
		String sql = 
				
				"SELECT " +
						"          RUT, " +
						"          DIGITO_VERIFICADOR, " +
						"          NOMBRES, " +
						"          APELLIDO_PATERNO, " +
						"          APELLIDO_MATERNO, " +
						"          NOMBRE_UNIDAD, " +
						"		   COD_UNIDAD, " +
						"          E_MAIL " +
						"FROM " +
						"          DBO_AIRH.VIEW_DATOS_TRABAJADORES T " +
						"WHERE " +
						"          T.RUT = ? ";
		
		try{
			PreparedStatement ps = cn.conectar_sigerh().prepareStatement(sql);
			ps.setInt(1, p_rut);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				objFuncionario.setRut(rs.getInt("RUT"));
				objFuncionario.setDigito_verificador(rs.getString("DIGITO_VERIFICADOR"));
				objFuncionario.setNombres(rs.getString("NOMBRES"));
				objFuncionario.setApellido_paterno(rs.getString("APELLIDO_PATERNO"));
				objFuncionario.setApellido_materno(rs.getString("APELLIDO_MATERNO"));
				objFuncionario.setUnidad(rs.getString("NOMBRE_UNIDAD"));
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			cn.cerrarConexion();
		}
		return objFuncionario;
	}

	

}
