package colabeduc

import grails.gorm.services.Service

@Service(UsuarioPapel)
interface UsuarioPapelService {

    UsuarioPapel get(Serializable id)

    List<UsuarioPapel> list(Map args)

    Long count()

    void delete(Serializable id)

    UsuarioPapel save(UsuarioPapel usuarioPapel)

}