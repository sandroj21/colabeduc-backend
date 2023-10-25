package colabeduc

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import grails.rest.*

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
//@Resource(uri='/api/usuario')
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String profileImageUrl
    Date dateCreated
    Date lastUpdated


    Set<Papel> getAuthorities() {
        (UsuarioPapel.findAllByUsuario(this) as List<UsuarioPapel>)*.papel as Set<Papel>
    }
    
    static hasMany = [descricoes: Descricao]

    static constraints = {
        username nullable: false, blank: false, unique: true
        email()
        password nullable: false, blank: false, password: true
        profileImageUrl nullable : true
    }

    static mapping = {
        password column: '`password`'
        dateCreated defaultValue: 'now()'
        lastUpdated defaultValue: 'now()'
    }

    String toString() {
        return username;
    }
}
