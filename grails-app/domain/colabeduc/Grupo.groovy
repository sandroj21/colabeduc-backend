package colabeduc

class Grupo {

	String titulo
	String regras
	String repositorio
	String chat
	String forum

	static belongsTo = [projeto: Projeto, categoria: Categoria]

    static constraints = {
    	titulo()
    	regras widget: 'textarea'
    	repositorio()
    	chat()
    	forum()

    }

    static mapping = {
        regras type: "text"
    }

    String toString() {
    	return titulo
    }
}
