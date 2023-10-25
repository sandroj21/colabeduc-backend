package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MetadescricaoController {

    MetadescricaoService metadescricaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond metadescricaoService.list(params), model:[metadescricaoCount: metadescricaoService.count()]
    }

    def show(Long id) {
        respond metadescricaoService.get(id)
    }

    def create() {
        respond new Metadescricao(params)
    }

    def save(Metadescricao metadescricao) {
        if (metadescricao == null) {
            notFound()
            return
        }

        try {
            metadescricaoService.save(metadescricao)
        } catch (ValidationException e) {
            respond metadescricao.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'metadescricao.label', default: 'Metadescricao'), metadescricao.id])
                redirect metadescricao
            }
            '*' { respond metadescricao, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond metadescricaoService.get(id)
    }

    def update(Metadescricao metadescricao) {
        if (metadescricao == null) {
            notFound()
            return
        }

        try {
            metadescricaoService.save(metadescricao)
        } catch (ValidationException e) {
            respond metadescricao.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'metadescricao.label', default: 'Metadescricao'), metadescricao.id])
                redirect metadescricao
            }
            '*'{ respond metadescricao, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        metadescricaoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'metadescricao.label', default: 'Metadescricao'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'metadescricao.label', default: 'Metadescricao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
