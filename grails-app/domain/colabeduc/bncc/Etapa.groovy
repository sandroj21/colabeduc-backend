package colabeduc.bncc

class Etapa {

	String nome;

	static hasMany = [anos: Ano]
  
    static constraints = {
    	nome()
    }
    String toString() {
    	return nome;
    }
}
