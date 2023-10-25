package colabeduc

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioPapel implements Serializable {

	private static final long serialVersionUID = 1

	Usuario usuario
	Papel papel

	@Override
	boolean equals(other) {
		if (other instanceof UsuarioPapel) {
			other.usuarioId == usuario?.id && other.papelId == papel?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (usuario) {
            hashCode = HashCodeHelper.updateHash(hashCode, usuario.id)
		}
		if (papel) {
		    hashCode = HashCodeHelper.updateHash(hashCode, papel.id)
		}
		hashCode
	}

	static UsuarioPapel get(long usuarioId, long papelId) {
		criteriaFor(usuarioId, papelId).get()
	}

	static boolean exists(long usuarioId, long papelId) {
		criteriaFor(usuarioId, papelId).count()
	}

	private static DetachedCriteria criteriaFor(long usuarioId, long papelId) {
		UsuarioPapel.where {
			usuario == Usuario.load(usuarioId) &&
			papel == Papel.load(papelId)
		}
	}

	static UsuarioPapel create(Usuario usuario, Papel papel, boolean flush = false) {
		def instance = new UsuarioPapel(usuario: usuario, papel: papel)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Usuario u, Papel r) {
		if (u != null && r != null) {
			UsuarioPapel.where { usuario == u && papel == r }.deleteAll()
		}
	}

	static int removeAll(Usuario u) {
		u == null ? 0 : UsuarioPapel.where { usuario == u }.deleteAll() as int
	}

	static int removeAll(Papel r) {
		r == null ? 0 : UsuarioPapel.where { papel == r }.deleteAll() as int
	}

	static constraints = {
	    usuario nullable: false
		papel nullable: false, validator: { Papel r, UsuarioPapel ur ->
			if (ur.usuario?.id) {
				if (UsuarioPapel.exists(ur.usuario.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['usuario', 'papel']
		version false
	}
}
