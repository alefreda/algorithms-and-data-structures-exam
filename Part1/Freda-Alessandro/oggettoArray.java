public class oggettoArray{

	private char categoria;
	private int status;

	//costruttori
	public oggettoArray(char categoria,int status){
		this.categoria=categoria;
		this.status=status;
	}
	//metodi set e get

	public void setStatus(int status){
		this.status = status;
	}

	public void setCategoria(char categoria){
		this.categoria= categoria;
	}

	public int getStatus(){
		return status;
	}

	public char getCategoria(){
		return categoria;
	}
}