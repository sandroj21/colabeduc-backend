package colabeduc

import grails.gorm.services.Service

@Service(Descricao)
interface DescricaoService {

    Descricao get(Serializable id)

    List<Descricao> list(Map args)

    Long count()

    void delete(Serializable id)

    Descricao save(Descricao descricao)

}