package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsuarioPapelController {

    UsuarioPapelService usuarioPapelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usuarioPapelService.list(params), model:[usuarioPapelCount: usuarioPapelService.count()]
    }

    def show(Long id) {
        respond usuarioPapelService.get(id)
    }

    def create() {
        respond new UsuarioPapel(params)
    }

    def save(UsuarioPapel usuarioPapel) {
        if (usuarioPapel == null) {
            notFound()
            return
        }

        try {
            usuarioPapelService.save(usuarioPapel)
        } catch (ValidationException e) {
            respond usuarioPapel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapel.id])
                redirect usuarioPapel
            }
            '*' { respond usuarioPapel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond usuarioPapelService.get(id)
    }

    def update(UsuarioPapel usuarioPapel) {
        if (usuarioPapel == null) {
            notFound()
            return
        }

        try {
            usuarioPapelService.save(usuarioPapel)
        } catch (ValidationException e) {
            respond usuarioPapel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapel.id])
                redirect usuarioPapel
            }
            '*'{ respond usuarioPapel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usuarioPapelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
