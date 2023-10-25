package colabeduc

import grails.gorm.services.Service

@SuppressWarnings(['LineLength', 'UnusedVariable', 'SpaceAfterOpeningBrace', 'SpaceBeforeClosingBrace'])
@Service(Usuario)
interface UsuarioDataService {

    Usuario get(Long id)

    List<Usuario> list(Map args)

    Number count()

    void delete(Serializable id)

    Usuario save(String username)

    Usuario updateName(Serializable id, Long version, String username)

    Usuario updateProfileImageUrl(Serializable id, Long version, String profileImageUrl)
}