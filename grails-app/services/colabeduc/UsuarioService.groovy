package colabeduc

import grails.gorm.services.Service

@Service(Usuario)
interface UsuarioService {

    Usuario get(Serializable id)

    List<Usuario> list(Map args)

    Long count()

    void delete(Serializable id)

    Usuario save(Usuario usuario)

     Usuario updateProfileImageUrl(Serializable id, Long version, String profileImageUrl)

}