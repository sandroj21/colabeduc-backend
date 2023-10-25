package colabeduc

class AreaDoConhecimento {

	String nome;

	static hasMany = [areasDoConhecimento: AreaDoConhecimento]


    static constraints = {
    	nome()
    }
    String toString() {
    	return nome;
    }
}
