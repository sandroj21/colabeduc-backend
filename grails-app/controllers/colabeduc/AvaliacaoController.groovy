package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AvaliacaoController {

    AvaliacaoService avaliacaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond avaliacaoService.list(params), model:[avaliacaoCount: avaliacaoService.count()]
    }

    def show(Long id) {
        respond avaliacaoService.get(id)
    }

    def create() {
        respond new Avaliacao(params)
    }

    def save(Avaliacao avaliacao) {
        if (avaliacao == null) {
            notFound()
            return
        }

        try {
            avaliacao.projeto.updateNota()
            avaliacaoService.save(avaliacao)
        } catch (ValidationException e) {
            respond avaliacao.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacao.id])
                redirect avaliacao.projeto
            }
            '*' { respond avaliacao.projeto, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond avaliacaoService.get(id)
    }

    def update(Avaliacao avaliacao) {
        if (avaliacao == null) {
            notFound()
            return
        }

        try {
            avaliacao.projeto.updateNota()
            avaliacaoService.save(avaliacao)
        } catch (ValidationException e) {
            respond avaliacao.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacao.id])
                redirect avaliacao.projeto
            }
            '*'{ respond avaliacao.projeto, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        avaliacaoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
