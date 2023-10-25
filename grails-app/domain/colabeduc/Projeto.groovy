package colabeduc

class Projeto {

    String nome

    String video

    String linkJogo

    Usuario dono

    String file

    int nota

    Date dateCreated
    Date lastUpdated

    static belongsTo = [ descricao: Descricao]
  
    static hasMany = [colaboradores: Usuario, tarefas: Tarefa, grupos: Grupo, avaliacoes: Avaliacao]

    static constraints = {
    	nome()
    	dono()
    	descricao()
    	colaboradores()
    	tarefas()
    	grupos()
        video nullable: true
        linkJogo nullable: true
        file nullable : true
    }

    static mapping = {
        dateCreated defaultValue: 'now()'
        lastUpdated defaultValue: 'now()'
    }

    String toString() {
        return nome
    }

    void updateNota() {
        def soma;
        Projeto.list().each {
            soma = 0;
            it.avaliacoes.each {
                soma=soma+it.nota
            }
            it.nota = soma
            it.save(flush: true, failOnError: true)
        }
    }
}

