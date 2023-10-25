package colabeduc.bncc

class Ano {

	String nome

	static hasMany = [componentesCurriculares: ComponenteCurricular]

	static belongsTo = [etapa: Etapa]

    static constraints = {
    	nome()
    }

    String toString() {
    	return nome+" - "+etapa;
    }
}
