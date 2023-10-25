package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HabilidadeController {

    HabilidadeService habilidadeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond habilidadeService.list(params), model:[habilidadeCount: habilidadeService.count()]
    }

    def show(Long id) {
        respond habilidadeService.get(id)
    }

    def create() {
        respond new Habilidade(params)
    }

    def save(Habilidade habilidade) {
        if (habilidade == null) {
            notFound()
            return
        }

        try {
            habilidadeService.save(habilidade)
        } catch (ValidationException e) {
            respond habilidade.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'habilidade.label', default: 'Habilidade'), habilidade.id])
                redirect habilidade
            }
            '*' { respond habilidade, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond habilidadeService.get(id)
    }

    def update(Habilidade habilidade) {
        if (habilidade == null) {
            notFound()
            return
        }

        try {
            habilidadeService.save(habilidade)
        } catch (ValidationException e) {
            respond habilidade.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'habilidade.label', default: 'Habilidade'), habilidade.id])
                redirect habilidade
            }
            '*'{ respond habilidade, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        habilidadeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'habilidade.label', default: 'Habilidade'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'habilidade.label', default: 'Habilidade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
