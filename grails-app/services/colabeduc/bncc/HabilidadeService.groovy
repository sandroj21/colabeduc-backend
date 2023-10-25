package colabeduc.bncc

import grails.gorm.services.Service

@Service(Habilidade)
interface HabilidadeService {

    Habilidade get(Serializable id)

    List<Habilidade> list(Map args)

    Long count()

    void delete(Serializable id)

    Habilidade save(Habilidade habilidade)

}