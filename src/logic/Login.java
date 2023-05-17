package logic;

import java.util.LinkedList;

import data.*;
import entities.*;
import java.util.HashMap;
import java.util.Map;
public class Login {
	private DataPersona dp;
	private DataRol dr;
	private DataRol_personas dr_p;
	public Login() {
		dp=new DataPersona();
		dr=new DataRol();
		dr_p = new DataRol_personas();
	}
	
	public Persona validate(Persona p) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);
		
	}
	
	public LinkedList<Persona> getByApellido(Persona per){
		return dp.getByApellido(per);
	}
	
	public void add(Persona p, HashMap<Integer, Rol> roles){
		dp.add(p);
		for (Map.Entry<Integer, Rol> entry : roles.entrySet()) {
			dr_p.addRol_persona(p,entry.getKey());
		}
	}
	
	public void edit(Persona p) {
		dp.edit(p);
	}
	
	public void delete(Persona p) {
		dr_p.deleteRol_persona(p);
		dp.delete(p);
	}
	
	public LinkedList<Rol> getAllRol(){
		return dr.getAll();
	}
	
	public Rol getById(Rol r) {
		return dr.getById(r);
	}
}