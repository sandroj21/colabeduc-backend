package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AnoController {

    AnoService anoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond anoService.list(params), model:[anoCount: anoService.count()]
    }

    def show(Long id) {
        respond anoService.get(id)
    }

    def create() {
        respond new Ano(params)
    }

    def save(Ano ano) {
        if (ano == null) {
            notFound()
            return
        }

        try {
            anoService.save(ano)
        } catch (ValidationException e) {
            respond ano.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ano.label', default: 'Ano'), ano.id])
                redirect ano
            }
            '*' { respond ano, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond anoService.get(id)
    }

    def update(Ano ano) {
        if (ano == null) {
            notFound()
            return
        }

        try {
            anoService.save(ano)
        } catch (ValidationException e) {
            respond ano.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ano.label', default: 'Ano'), ano.id])
                redirect ano
            }
            '*'{ respond ano, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        anoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ano.label', default: 'Ano'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ano.label', default: 'Ano'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
