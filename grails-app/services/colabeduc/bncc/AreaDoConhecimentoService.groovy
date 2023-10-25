package colabeduc.bncc

import grails.gorm.services.Service

@Service(AreaDoConhecimento)
interface AreaDoConhecimentoService {

    AreaDoConhecimento get(Serializable id)

    List<AreaDoConhecimento> list(Map args)

    Long count()

    void delete(Serializable id)

    AreaDoConhecimento save(AreaDoConhecimento areaDoConhecimento)

}