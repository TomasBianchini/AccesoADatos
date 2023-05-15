package ui;


import java.util.Scanner;
import java.util.LinkedList;
import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
		Persona p = null;
		do { 
			p = login();
		}while(p == null);
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			System.out.println(search());
			break;
		case "new":
				add();
			break;
		case "edit":
				edit();
			break;
		case "delete":
			delete();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println("exit\t\tsalir");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
		
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private LinkedList<Persona> search(){
		LinkedList<Persona> listPer = new LinkedList<>(); 
		Persona p = new Persona();
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		listPer = ctrlLogin.getByApellido(p);
		return listPer;
	}
	
	private void add() {
		Persona p = new Persona(); 
		Rol r = new Rol();
		Documento d = new Documento();
		System.out.println("Nombre: ");
		p.setNombre(s.nextLine());
		System.out.println("Apellido: ");
		p.setApellido(s.nextLine());
		System.out.println("Tipo documento: ");
		d.setTipo(s.nextLine());
		System.out.println("Numero documento: ");
		d.setNro(s.nextLine());
		System.out.println("Email: ");
		p.setEmail(s.nextLine());
		System.out.println("Password: ");
		p.setPassword(s.nextLine());
		System.out.println("Telefono: ");
		p.setTel(s.nextLine());
		System.out.println("Habilitado: ");
		p.setHabilitado(Boolean.parseBoolean(s.nextLine()));	
		p.setDocumento(d);
		
		System.out.println("Ingrese el ID del ROL:");
		System.out.println(ctrlLogin.getAllRol());
		r.setId(Integer.parseInt(s.nextLine()));
		r = ctrlLogin.getById(r);
		p.addRol(r);
		ctrlLogin.add(p);
	}
	
	private void edit() {
		System.out.println();
		Persona p=new Persona();
		p = find();
		if(p != null){
			System.out.println("Nombre: ");
			p.setNombre(s.nextLine());
			System.out.println("Apellido: ");
			p.setApellido(s.nextLine());
			System.out.println("Email: ");
			p.setEmail(s.nextLine());
			System.out.println("Password: ");
			p.setPassword(s.nextLine());
			System.out.println("Telefono: ");
			p.setTel(s.nextLine());
			System.out.println("Habilitado: ");
			p.setHabilitado(Boolean.parseBoolean(s.nextLine()));
			ctrlLogin.edit(p);
		}else {
			System.out.println("El usuario no existe.");
			System.out.println();
		}
	}

	private void delete() {
		Persona p = new Persona();
		p = find(); 
		if(p != null) {
			ctrlLogin.delete(p);
			System.out.println("Persona eliminada");
			System.out.println();
		}else {
			System.out.println("No se encontro la persona");
			System.out.println();
		}
	}

}