package colabeduc

import grails.gorm.services.Service

@Service(Papel)
interface PapelService {

    Papel get(Serializable id)

    List<Papel> list(Map args)

    Long count()

    void delete(Serializable id)

    Papel save(Papel papel)

}