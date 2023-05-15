package data;
import entities.*;
import java.sql.*;

public class DataRol_personas {
	
	public void addRol_persona(Persona p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement("insert into rol_persona(id_persona, id_rol) values(?,?)");
			pstmt.setInt(1,p.getId());
			pstmt.setInt(2, p.getIdRol());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(pstmt!=null)pstmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}

	public void deleteRol_persona(Persona p) {
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnector.getInstancia().getConn().prepareStatement(
					"delete from rol_persona where id_persona=?");
			pstmt.setInt(1, p.getId());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace(); 
		}finally{
	            try {
	                if(pstmt!=null)pstmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
		}
	}
}
