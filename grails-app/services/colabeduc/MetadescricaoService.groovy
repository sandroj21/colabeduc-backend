package colabeduc

import grails.gorm.services.Service

@Service(Metadescricao)
interface MetadescricaoService {

    Metadescricao get(Serializable id)

    List<Metadescricao> list(Map args)

    Long count()

    void delete(Serializable id)

    Metadescricao save(Metadescricao metadescricao)

}