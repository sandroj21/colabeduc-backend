package colabeduc.bncc

class UnidadeTematica {

	String nome

	static hasMany = [objetosDoConhecimento: ObjetoDoConhecimento]

    static constraints = {
    	nome()
    }

    String toString() {
    	return nome
    }
}
