package colabeduc.bncc

import grails.gorm.services.Service

@Service(ComponenteCurricular)
interface ComponenteCurricularService {

    ComponenteCurricular get(Serializable id)

    List<ComponenteCurricular> list(Map args)

    Long count()

    void delete(Serializable id)

    ComponenteCurricular save(ComponenteCurricular componenteCurricular)

}