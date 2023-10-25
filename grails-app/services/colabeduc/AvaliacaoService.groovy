package colabeduc

import grails.gorm.services.Service

@Service(Avaliacao)
interface AvaliacaoService {

    Avaliacao get(Serializable id)

    List<Avaliacao> list(Map args)

    Long count()

    void delete(Serializable id)

    Avaliacao save(Avaliacao avaliacao)

}