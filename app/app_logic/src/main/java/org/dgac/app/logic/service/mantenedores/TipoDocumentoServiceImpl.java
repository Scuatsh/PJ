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
import org.dgac.common.dto.TipoDocumentoDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.enums.LevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServiceImpl extends ServiceGeneric implements TipoDocumentoService {

	/** The conn. */
	private Connection conn = null;

	/** The pstmt. */
	private PreparedStatement pstmt = null;

	/** The rs. */
	private ResultSet rs = null;

	@Autowired
	SistemaService sistema;

	@Override
	public boolean insertTipoDocumento(String usuario, TipoDocumentoDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.INSERT_TPO_DOCUMENTO);
			pstmt.setString(1, new String(desc.getTpo_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getTpo_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getTpo_estado());
			long estado = pstmt.executeUpdate();

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
					TipoDocumentoServiceImpl.class.getSimpleName()
					+ "[insertarTipoDocumento]", LevelEnum.ERR.getId(), e
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
	public boolean updateTipoDocumento(String usuario, TipoDocumentoDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.UPDATE_TPO_DOCUMENTO);
			pstmt.setString(1, new String(desc.getTpo_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getTpo_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getTpo_estado());
			pstmt.setLong(4, desc.getTpo_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					TipoDocumentoServiceImpl.class.getSimpleName()
					+ "[updateTipoDocumento]", LevelEnum.ERR.getId(), e
					.getMessage().toString().replaceAll("<<", "")
					.replaceAll(">>", "")));

		}

		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
		}

		return resultado;
	}

	public long tomarDependenciasDeId(String usuario, TipoDocumentoDTO desc)
	{
		try
		{
			long cantidad = 0;
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECTDEPIDTIPDOC);
			pstmt.setLong(1, desc.getTpo_id());
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
	public boolean deleteTipoDocumento(String usuario,TipoDocumentoDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.DELETE_TPO_DOCUMENTO);
			pstmt.setLong(1, desc.getTpo_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					TipoDocumentoServiceImpl.class.getSimpleName()
					+ "[deleteTipoDocumento]", LevelEnum.ERR.getId(), e
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
	public List<TipoDocumentoDTO> selectTiposDocumentos(String usuario) {
		// TODO Auto-generated method stub
		List<TipoDocumentoDTO> lista = new ArrayList<TipoDocumentoDTO>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECT_TPO_DOCUMENTO);
			rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				TipoDocumentoDTO objDesc = new TipoDocumentoDTO();

				objDesc.setTpo_id(rs.getLong(ConstantesBd.TPO_ID));
				objDesc.setTpo_nombre_small(rs.getString(ConstantesBd.TPO_NOMBRE_SMALL));
				objDesc.setTpo_nombre_large(rs.getString(ConstantesBd.TPO_NOMBRE_LARGE));
				objDesc.setTpo_estado(rs.getInt(ConstantesBd.TPO_ESTADO));
				lista.add(objDesc);
			}
		} catch (Exception e) {

			sistema.registroLog(new LogDTO(usuario,
					TipoDocumentoServiceImpl.class.getSimpleName()
					+ "[selectTipoDocumento]", LevelEnum.ERR.getId(), e
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
