package colabeduc

class Metadescricao {

    String tipo
    String dado

    static belongsTo = [ descricao: Descricao ]

    static constraints = {
    }
    String toString() {
        return tipo+" - "+dado;
    }
}