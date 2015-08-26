package business.entity.Luoghi;

import business.entity.Entity;

public class Agenzia extends Entity{
	private int IDAgenzia;
	private String NumTelefono;
	private String nome;
	private int IDDitta;
	
	public Agenzia(int iDAgenzia, String numTelefono,
			String nome, int iDDitta) {
		super();
		IDAgenzia = iDAgenzia;
		NumTelefono = numTelefono;
		this.nome = nome;
		IDDitta = iDDitta;
	}
	

	public int getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(int iDAgenzia) {
		IDAgenzia = iDAgenzia;
	}
	public String getNumTelefono() {
		return NumTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		NumTelefono = numTelefono;
	}
	public int getIDDitta() {
		return IDDitta;
	}
	public void setIDDitta(int iDDitta) {
		IDDitta = iDDitta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
