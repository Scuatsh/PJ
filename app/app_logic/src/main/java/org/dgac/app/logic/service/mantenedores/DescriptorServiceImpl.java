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
import org.dgac.common.dto.DescriptorDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.TipoDocumentoDTO;
import org.dgac.common.enums.LevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptorServiceImpl extends ServiceGeneric implements DescriptorService {

	/** The conn. */
	private Connection conn = null;
	
	/** The pstmt. */
	private PreparedStatement pstmt = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	@Autowired
	SistemaService sistema;
	
	@Override
	public boolean insertDescriptor(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.INSERT_DESCRIPTOR);
			pstmt.setString(1, new String(desc.getDesc_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getDesc_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getDesc_estado());
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
					DescriptorServiceImpl.class.getSimpleName()
							+ "[insertarDesc]", LevelEnum.ERR.getId(), e
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
	public boolean updateDescriptor(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.UPDATE_DESCRIPTOR);
			pstmt.setString(1, new String(desc.getDesc_nombre_small().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setString(2, new String(desc.getDesc_nombre_large().getBytes("ISO-8859-1"),"utf-8").toUpperCase());
			pstmt.setInt(3, desc.getDesc_estado());
			pstmt.setLong(4, desc.getDesc_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					DescriptorServiceImpl.class.getSimpleName()
							+ "[updateDesc]", LevelEnum.ERR.getId(), e
							.getMessage().toString().replaceAll("<<", "")
							.replaceAll(">>", "")));

		}

		finally {
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
		}
		
		return resultado;
	}

	public long tomarDependenciasDeId(String usuario, DescriptorDTO desc)
	{
		try
		{
			long cantidad = 0;
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECTDEPIDDESCR);
			pstmt.setLong(1, desc.getDesc_id());
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
	public boolean deleteDescriptor(String usuario,DescriptorDTO desc) {
		// TODO Auto-generated method stub
		boolean resultado=false;

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.DELETE_DESCRIPTOR);
			pstmt.setLong(1, desc.getDesc_id());
			pstmt.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			resultado=false;
			sistema.registroLog(new LogDTO(usuario,
					DescriptorServiceImpl.class.getSimpleName()
							+ "[deleteDesc]", LevelEnum.ERR.getId(), e
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
	public List<DescriptorDTO> selectDescriptores(String usuario) {
		// TODO Auto-generated method stub
		List<DescriptorDTO> lista = new ArrayList<DescriptorDTO>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECT_DESCRIPTOR);
			rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				DescriptorDTO objDesc = new DescriptorDTO();
				
				objDesc.setDesc_id(rs.getLong(ConstantesBd.DESC_ID));
				objDesc.setDesc_nombre_small(rs.getString(ConstantesBd.DESC_NOMBRE_SMALL));
				objDesc.setDesc_nombre_large(rs.getString(ConstantesBd.DESC_NOMBRE_LARGE));
				objDesc.setDesc_estado(rs.getInt(ConstantesBd.DESC_ESTADO));
				lista.add(objDesc);
			}
		} catch (Exception e) {

			sistema.registroLog(new LogDTO(usuario,
					DescriptorServiceImpl.class.getSimpleName()
							+ "[selectDesc]", LevelEnum.ERR.getId(), e
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
