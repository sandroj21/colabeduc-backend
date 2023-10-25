package colabeduc

class Etapa {

	String nome;

	static hasMany = [areasDoConhecimento: AreaDoConhecimento]

    static constraints = {
    	nome()
    }
    String toString() {
    	return nome;
    }
}
