package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ObjetoDoConhecimentoController {

    ObjetoDoConhecimentoService objetoDoConhecimentoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond objetoDoConhecimentoService.list(params), model:[objetoDoConhecimentoCount: objetoDoConhecimentoService.count()]
    }

    def show(Long id) {
        respond objetoDoConhecimentoService.get(id)
    }

    def create() {
        respond new ObjetoDoConhecimento(params)
    }

    def save(ObjetoDoConhecimento objetoDoConhecimento) {
        if (objetoDoConhecimento == null) {
            notFound()
            return
        }

        try {
            objetoDoConhecimentoService.save(objetoDoConhecimento)
        } catch (ValidationException e) {
            respond objetoDoConhecimento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'objetoDoConhecimento.label', default: 'ObjetoDoConhecimento'), objetoDoConhecimento.id])
                redirect objetoDoConhecimento
            }
            '*' { respond objetoDoConhecimento, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond objetoDoConhecimentoService.get(id)
    }

    def update(ObjetoDoConhecimento objetoDoConhecimento) {
        if (objetoDoConhecimento == null) {
            notFound()
            return
        }

        try {
            objetoDoConhecimentoService.save(objetoDoConhecimento)
        } catch (ValidationException e) {
            respond objetoDoConhecimento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'objetoDoConhecimento.label', default: 'ObjetoDoConhecimento'), objetoDoConhecimento.id])
                redirect objetoDoConhecimento
            }
            '*'{ respond objetoDoConhecimento, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        objetoDoConhecimentoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'objetoDoConhecimento.label', default: 'ObjetoDoConhecimento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetoDoConhecimento.label', default: 'ObjetoDoConhecimento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
