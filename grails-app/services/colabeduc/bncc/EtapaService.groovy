package colabeduc.bncc

import grails.gorm.services.Service

@Service(Etapa)
interface EtapaService {

    Etapa get(Serializable id)

    List<Etapa> list(Map args)

    Long count()

    void delete(Serializable id)

    Etapa save(Etapa etapa)

}