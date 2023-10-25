package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UnidadeTematicaController {

    UnidadeTematicaService unidadeTematicaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond unidadeTematicaService.list(params), model:[unidadeTematicaCount: unidadeTematicaService.count()]
    }

    def show(Long id) {
        respond unidadeTematicaService.get(id)
    }

    def create() {
        respond new UnidadeTematica(params)
    }

    def save(UnidadeTematica unidadeTematica) {
        if (unidadeTematica == null) {
            notFound()
            return
        }

        try {
            unidadeTematicaService.save(unidadeTematica)
        } catch (ValidationException e) {
            respond unidadeTematica.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'unidadeTematica.label', default: 'UnidadeTematica'), unidadeTematica.id])
                redirect unidadeTematica
            }
            '*' { respond unidadeTematica, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond unidadeTematicaService.get(id)
    }

    def update(UnidadeTematica unidadeTematica) {
        if (unidadeTematica == null) {
            notFound()
            return
        }

        try {
            unidadeTematicaService.save(unidadeTematica)
        } catch (ValidationException e) {
            respond unidadeTematica.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'unidadeTematica.label', default: 'UnidadeTematica'), unidadeTematica.id])
                redirect unidadeTematica
            }
            '*'{ respond unidadeTematica, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        unidadeTematicaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'unidadeTematica.label', default: 'UnidadeTematica'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadeTematica.label', default: 'UnidadeTematica'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
