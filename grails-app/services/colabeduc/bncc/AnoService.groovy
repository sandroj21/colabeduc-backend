package colabeduc.bncc

import grails.gorm.services.Service

@Service(Ano)
interface AnoService {

    Ano get(Serializable id)

    List<Ano> list(Map args)

    Long count()

    void delete(Serializable id)

    Ano save(Ano ano)

}