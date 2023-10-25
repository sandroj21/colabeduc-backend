package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AreaDoConhecimentoController {

    AreaDoConhecimentoService areaDoConhecimentoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond areaDoConhecimentoService.list(params), model:[areaDoConhecimentoCount: areaDoConhecimentoService.count()]
    }

    def show(Long id) {
        respond areaDoConhecimentoService.get(id)
    }

    def create() {
        respond new AreaDoConhecimento(params)
    }

    def save(AreaDoConhecimento areaDoConhecimento) {
        if (areaDoConhecimento == null) {
            notFound()
            return
        }

        try {
            areaDoConhecimentoService.save(areaDoConhecimento)
        } catch (ValidationException e) {
            respond areaDoConhecimento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'areaDoConhecimento.label', default: 'AreaDoConhecimento'), areaDoConhecimento.id])
                redirect areaDoConhecimento
            }
            '*' { respond areaDoConhecimento, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond areaDoConhecimentoService.get(id)
    }

    def update(AreaDoConhecimento areaDoConhecimento) {
        if (areaDoConhecimento == null) {
            notFound()
            return
        }

        try {
            areaDoConhecimentoService.save(areaDoConhecimento)
        } catch (ValidationException e) {
            respond areaDoConhecimento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'areaDoConhecimento.label', default: 'AreaDoConhecimento'), areaDoConhecimento.id])
                redirect areaDoConhecimento
            }
            '*'{ respond areaDoConhecimento, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        areaDoConhecimentoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'areaDoConhecimento.label', default: 'AreaDoConhecimento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'areaDoConhecimento.label', default: 'AreaDoConhecimento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
