package org.dgac.app.logic.service.sistema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dgac.app.exception.DgacBusinessException;
import org.dgac.app.logic.service.ServiceGeneric;
import org.dgac.common.constantes.ConstantesBd;
import org.dgac.common.dto.FuncionarioDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.ResultActiveDirectoryDTO;
import org.dgac.common.dto.RolDTO;
import org.dgac.common.dto.UnidadAdicionalDTO;
import org.dgac.common.enums.LevelEnum;
import org.dgac.ws.integration.server.Grupo;
import org.dgac.ws.integration.server.LoginResponse;
import org.dgac.ws.integration.client.LoginClient;
import org.dgac.ws.integration.util.IntegrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO: Auto-generated Javadoc
/**
 * The Class SistemaServiceImpl.
 */
@Service
public class SistemaServiceImpl extends ServiceGeneric implements
SistemaService {

	/** The conn. */
	private Connection conn = null;

	/** The pstmt. */
	private PreparedStatement pstmt = null;

	/** The rset. */
	private ResultSet rset = null;

	/** The integration properties. */
	@Autowired
	IntegrationProperties integrationProperties;

	/* (non-Javadoc)
	 * @see org.dgac.app.logic.service.sistema.SistemaService#loginActiveDirectory(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultActiveDirectoryDTO loginActiveDirectory(String usrName, String password) throws DgacBusinessException {
		// TODO Auto-generated method stub
		ResultActiveDirectoryDTO result = new ResultActiveDirectoryDTO();

		try
		{
			LoginClient client=new LoginClient(integrationProperties);
			LoginResponse response=client.executeLogin(usrName, password);
			result.setCodigo(response.getReturn().getCodigo());
			result.setMensaje(response.getReturn().getMensaje());
			result.setStatus(response.getReturn().isStatus());

			List<String>nombres=new ArrayList<String>();

			for(Grupo gr:response.getReturn().getGrupos())
			{
				nombres.add(gr.getNombre().toString());
			}

			result.setNombreGrupos(nombres);
			FuncionarioDTO funcionario=new FuncionarioDTO();
			if(response.getReturn().getFuncionario()!=null)
			{
				funcionario.setRut(response.getReturn().getFuncionario().getRut());
				funcionario.setDigito_verificador(response.getReturn().getFuncionario().getDigitoVerificador());
				funcionario.setNombres(response.getReturn().getFuncionario().getNombres());
				funcionario.setApellido_materno(response.getReturn().getFuncionario().getApellidoMaterno());
				funcionario.setApellido_paterno(response.getReturn().getFuncionario().getApellidoPaterno());
				funcionario.setUnidad(response.getReturn().getFuncionario().getUnidad());
				funcionario.setUnidadNemo(response.getReturn().getFuncionario().getUnidadNemo());
				funcionario.setUnidadId(response.getReturn().getFuncionario().getUnidadId());
				funcionario.setUnidadNemoId(response.getReturn().getFuncionario().getUnidadNemoId());
				funcionario.setMail(response.getReturn().getFuncionario().getMail());
				List<UnidadAdicionalDTO> listaUnidad=new ArrayList<UnidadAdicionalDTO>();

				for(org.dgac.ws.integration.server.UnidadAdicionalDTO u:response.getReturn().getFuncionario().getUnidadAdicionalList())
				{
					UnidadAdicionalDTO unidad=new UnidadAdicionalDTO();
					unidad.setRut_funcionario(u.getRutFuncionario());
					unidad.setTabla_id(u.getTablaId());
					unidad.setUnidad_nemo(u.getUnidadNemo());
					unidad.setUnidad_nemo_gl(u.getUnidadNemoGl());
					listaUnidad.add(unidad);
				}

				funcionario.setUnidadAdicionalList(listaUnidad);
				result.setFuncionario(funcionario);
			}
			
		}
		catch(Exception e)
		{
			this.registroLog(new LogDTO(usrName, SistemaServiceImpl.class.getSimpleName()+"[loginActiveDirectory]", LevelEnum.ERR.getId(), e.getMessage()));

		}

		return result;
	}


	/* (non-Javadoc)
	 * @see org.dgac.app.logic.service.sistema.SistemaService#registroLog(org.dgac.common.dto.LogDTO)
	 */
	@Override
	public void registroLog(LogDTO log) {
		// TODO Auto-generated method stub
		Connection conexion=null;
		PreparedStatement ps=null;
		try
		{
			conexion = super.getConnection();
			ps = conexion.prepareCall(ConstantesBd.INSERT_LOG);
			ps.setString(1, log.getLogUsrId());
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			ps.setDate(2, new Date(currentDate.getTime()));
			ps.setString(3, log.getLogLocation());
			ps.setString(4, log.getLogLevel());
			ps.setString(5, log.getLogMessage().toString().replace("<<", "").replace(">>", ""));
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error al registrar el log "+e);
		}
		finally
		{
			super.closePreparedStatement(ps);
			super.closeConnection(conexion);
		}

	}


	@Override
	public List<RolDTO> returnRolesTramites(String usuario) {
		// TODO Auto-generated method stub
		List<RolDTO> lista=new ArrayList<RolDTO>();

		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(ConstantesBd.SELECCIONAR_ROL);
			rset = (ResultSet) pstmt.executeQuery();

			while (rset.next()) {
				RolDTO objRol = new RolDTO();
				objRol.setRol_id(rset.getLong(ConstantesBd.ROL_ID));
				objRol.setRol_nombre(rset.getString(ConstantesBd.ROL_NOMBRE));
				objRol.setRol_prioridad(rset.getInt(ConstantesBd.ROL_PRIORIDAD));
				objRol.setRol_estado(rset.getInt(ConstantesBd.ROL_ESTADO));
				lista.add(objRol);
			}
		} catch (Exception e) {

			this.registroLog(new LogDTO(usuario, SistemaServiceImpl.class.getSimpleName()+"[returnRolesTramites]", LevelEnum.ERR.getId(), e.getMessage()));
		}
		finally
		{
			super.closePreparedStatement(pstmt);
			super.closeResultSet(rset);
			super.closeConnection(conn);
		}

		return lista;
	}
}
