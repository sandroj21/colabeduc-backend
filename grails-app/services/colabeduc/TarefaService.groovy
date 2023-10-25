package colabeduc

import grails.gorm.services.Service

@Service(Tarefa)
interface TarefaService {

    Tarefa get(Serializable id)

    List<Tarefa> list(Map args)

    Long count()

    void delete(Serializable id)

    Tarefa save(Tarefa tarefa)

}