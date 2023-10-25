package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PapelController {

    PapelService papelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond papelService.list(params), model:[papelCount: papelService.count()]
    }

    def show(Long id) {
        respond papelService.get(id)
    }

    def create() {
        respond new Papel(params)
    }

    def save(Papel papel) {
        if (papel == null) {
            notFound()
            return
        }

        try {
            papelService.save(papel)
        } catch (ValidationException e) {
            respond papel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'papel.label', default: 'Papel'), papel.id])
                redirect papel
            }
            '*' { respond papel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond papelService.get(id)
    }

    def update(Papel papel) {
        if (papel == null) {
            notFound()
            return
        }

        try {
            papelService.save(papel)
        } catch (ValidationException e) {
            respond papel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'papel.label', default: 'Papel'), papel.id])
                redirect papel
            }
            '*'{ respond papel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        papelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'papel.label', default: 'Papel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
