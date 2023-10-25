package colabeduc.bncc

class ComponenteCurricular {

	String nome

	static belongsTo = [areaDoConhecimento: AreaDoConhecimento, ano: Ano]
    static hasMany = [habilidades: Habilidade]

    static constraints = {
    	nome()
    }
    String toString() {
    	if(nome!=null && ano.nome!=null)  {
    		return nome+" "+ano.nome
    	} else {
    		if(nome!=null)
    		   return nome
    		
    	}
    	
    }
}
