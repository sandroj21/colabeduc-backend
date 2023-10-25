package colabeduc.bncc

class AreaDoConhecimento {

	String nome;

	static hasMany = [componentesCurriculares: ComponenteCurricular]

    static constraints = {
    	nome()
    }
    String toString() {
    	return nome;
    }
}
