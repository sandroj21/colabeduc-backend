package colabeduc.bncc

class ObjetoDoConhecimento {

	String descricao

	static hasMany = [habilidades: Habilidade]

	static belongsTo = [unidadeTematica: UnidadeTematica]

    static constraints = {
    	descricao widget: 'textarea'
    }

    static mapping = {
    	 descricao type: "text"
    }

    String toString() {
    	return descricao;
    }
}
