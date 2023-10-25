package colabeduc

class Tarefa {

    String nome
    String descricao
    int prioridade
    Categoria categoria  
    Usuario criador  

    static belongsTo = [projeto: Projeto]
    
    static hasMany = [responsaveis: Usuario]

    static constraints = {
        nome()
        descricao widget: 'textarea'
        prioridade()
        categoria()
        criador()

    }

    static mapping = {
        descricao type: "text"
    }

    String toString() {
        return nome;
    }
}