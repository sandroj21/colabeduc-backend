package colabeduc

import colabeduc.bncc.Habilidade

class Descricao {

    String titulo

    String resumo

    Usuario dono

    String descricao
    Date dateCreated
    Date lastUpdated

    static hasMany = [projetos: Projeto, metadescricoes: Metadescricao, habilidades:Habilidade]
  
    static constraints = {  
        titulo()
        resumo widget: 'textarea'
        descricao widget: 'textarea'
        dono()
        projetos()
        metadescricoes()
    }

    static mapping = {
        resumo type: "text"
        descricao type: "text"
        dateCreated defaultValue: 'now()'
        lastUpdated defaultValue: 'now()'
    }

    String toString() {
        return titulo
    }
}