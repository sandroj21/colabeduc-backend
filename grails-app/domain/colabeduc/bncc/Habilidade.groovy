package colabeduc.bncc

class Habilidade {

	String codigo
	String descricao
    int descricoes

	static belongsTo = [objetoDoConhecimento: ObjetoDoConhecimento, componenteCurricular: ComponenteCurricular]
    //static belongsTo = [objetoDoConhecimento: ObjetoDoConhecimento]
    //static hasMany = [componentes: ComponenteCurricular]

    static constraints = {
    	descricao widget: 'textarea'
    }

    static mapping = {
    	 descricao type: "text"
    }

    String toString() {
    	return "("+codigo+") "+descricao
    }
}
   