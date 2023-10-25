package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GrupoController {

    GrupoService grupoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond grupoService.list(params), model:[grupoCount: grupoService.count()]
    }

    def show(Long id) {
        respond grupoService.get(id)
    }

    def create() {
        respond new Grupo(params)
    }

    def save(Grupo grupo) {
        if (grupo == null) {
            notFound()
            return
        }

        try {
            grupoService.save(grupo)
        } catch (ValidationException e) {
            respond grupo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
                redirect grupo
            }
            '*' { respond grupo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond grupoService.get(id)
    }

    def update(Grupo grupo) {
        if (grupo == null) {
            notFound()
            return
        }

        try {
            grupoService.save(grupo)
        } catch (ValidationException e) {
            respond grupo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
                redirect grupo
            }
            '*'{ respond grupo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        grupoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
