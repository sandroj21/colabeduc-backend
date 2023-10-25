package colabeduc

class Avaliacao {

	int nota=0;

	static belongsTo = [ projeto: Projeto, usuario: Usuario]

    static constraints = {
    }
}
