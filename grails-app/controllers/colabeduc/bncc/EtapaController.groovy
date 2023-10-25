package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EtapaController {

    EtapaService etapaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond etapaService.list(params), model:[etapaCount: etapaService.count()]
    }

    def show(Long id) {
        respond etapaService.get(id)
    }

    def create() {
        respond new Etapa(params)
    }

    def save(Etapa etapa) {
        if (etapa == null) {
            notFound()
            return
        }

        try {
            etapaService.save(etapa)
        } catch (ValidationException e) {
            respond etapa.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapa.id])
                redirect etapa
            }
            '*' { respond etapa, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond etapaService.get(id)
    }

    def update(Etapa etapa) {
        if (etapa == null) {
            notFound()
            return
        }

        try {
            etapaService.save(etapa)
        } catch (ValidationException e) {
            respond etapa.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapa.id])
                redirect etapa
            }
            '*'{ respond etapa, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        etapaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
