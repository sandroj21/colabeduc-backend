package colabeduc.bncc

import grails.gorm.services.Service

@Service(ObjetoDoConhecimento)
interface ObjetoDoConhecimentoService {

    ObjetoDoConhecimento get(Serializable id)

    List<ObjetoDoConhecimento> list(Map args)

    Long count()

    void delete(Serializable id)

    ObjetoDoConhecimento save(ObjetoDoConhecimento objetoDoConhecimento)

}