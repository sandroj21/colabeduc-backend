package colabeduc

import grails.gorm.services.Service

@Service(Projeto)
interface ProjetoService {

    Projeto get(Serializable id)

    List<Projeto> list(Map args)

    Long count()

    void delete(Serializable id)

    Projeto save(Projeto projeto)

}