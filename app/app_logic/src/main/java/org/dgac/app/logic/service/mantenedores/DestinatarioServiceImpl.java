package org.dgac.app.logic.service.mantenedores;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dgac.app.logic.service.ServiceGeneric;
import org.dgac.app.logic.service.sistema.SistemaService;
import org.dgac.common.constantes.ConstantesBd;
import org.dgac.common.dto.DestinatarioDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.TipoDocumentoDTO;
import org.dgac.common.enums.LevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinatarioServiceImpl extends ServiceGeneric implements DestinatarioService {

	/** The conn. */
	private Connection conn = null;
	
	/** The pstmt. */
	private PreparedStatement pstmt = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	@Autowired
	SistemaService sistema;
	
	@Override
	public boolean insertDestinatario(String usuario, DestinatarioDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.INSERT_DESTINATARIO);
			pstmt.setString(1, new String(desc.getDes_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getDes_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getDes_estado());
		
			long estado = 	pstmt.executeUpdate();
			if(estado>0)
			{
				resultado=true;
			}
			else
			{
				resultado=false;
			}
			
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					DestinatarioServiceImpl.class.getSimpleName()
							+ "[insertarDes]", LevelEnum.ERR.getId(), e
							.getMessage().toString().replaceAll("<<", "")
							.replaceAll(">>", "")));

		}

		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
		}
		
		return resultado;
	}

	@Override
	public boolean updateDestinatario(String usuario, DestinatarioDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.UPDATE_DESTINATARIO);
			pstmt.setString(1, new String(desc.getDes_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getDes_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getDes_estado());
			pstmt.setLong(4, desc.getDes_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					DestinatarioServiceImpl.class.getSimpleName()
							+ "[updateDes]", LevelEnum.ERR.getId(), e
							.getMessage().toString().replaceAll("<<", "")
							.replaceAll(">>", "")));

		}

		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
		}
		
		return resultado;
	}
	public long tomarDependenciasDeId(String usuario, DestinatarioDTO desc)
	{
		try
		{
			long cantidad = 0;
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECTDEPIDDESTINA);
			pstmt.setLong(1, desc.getDes_id());
			rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				cantidad = rs.getLong(ConstantesBd.CONTADOR);
			}
			return cantidad;
		}
		catch(Exception error)
		{
			
			sistema.registroLog(new LogDTO(usuario,
					TipoDocumentoServiceImpl.class.getSimpleName()
					+ "[tomarDependenciasDeId]", LevelEnum.ERR.getId(),error
					.getMessage().toString().replaceAll("<<", "")
					.replaceAll(">>", "")));
			return 0;
		}
	}
	
	@Override
	public boolean deleteDestinatario(String usuario,DestinatarioDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.DELETE_DESTINATARIO);
			pstmt.setLong(1, desc.getDes_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					DestinatarioServiceImpl.class.getSimpleName()
							+ "[deleteDes]", LevelEnum.ERR.getId(), e
							.getMessage().toString().replaceAll("<<", "")
							.replaceAll(">>", "")));

		}

		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
		}
		
		return resultado;
	}

	@Override
	public List<DestinatarioDTO> selectDestinatarios(String usuario) {
		// TODO Auto-generated method stub
		List<DestinatarioDTO> lista = new ArrayList<DestinatarioDTO>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECT_DESTINATARIO);
			rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				DestinatarioDTO objDesc = new DestinatarioDTO();
				
				objDesc.setDes_id(rs.getLong(ConstantesBd.DES_ID));
				objDesc.setDes_nombre_small(rs.getString(ConstantesBd.DES_NOMBRE_SMALL));
				objDesc.setDes_nombre_large(rs.getString(ConstantesBd.DES_NOMBRE_LARGE));
				objDesc.setDes_estado(rs.getInt(ConstantesBd.DES_ESTADO));
				lista.add(objDesc);
			}
		} catch (Exception e) {

			sistema.registroLog(new LogDTO(usuario,
					DestinatarioServiceImpl.class.getSimpleName()
							+ "[selectDes]", LevelEnum.ERR.getId(), e
							.getMessage()));
		}
		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
			super.closeResultSet(rs);
		}
		return lista;
	}

}
