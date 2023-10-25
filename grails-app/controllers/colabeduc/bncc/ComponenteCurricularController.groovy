package colabeduc.bncc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ComponenteCurricularController {

    ComponenteCurricularService componenteCurricularService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond componenteCurricularService.list(params), model:[componenteCurricularCount: componenteCurricularService.count()]
    }

    def show(Long id) {
        respond componenteCurricularService.get(id)
    }

    def create() {
        respond new ComponenteCurricular(params)
    }

    def save(ComponenteCurricular componenteCurricular) {
        if (componenteCurricular == null) {
            notFound()
            return
        }

        try {
            componenteCurricularService.save(componenteCurricular)
        } catch (ValidationException e) {
            respond componenteCurricular.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'componenteCurricular.label', default: 'ComponenteCurricular'), componenteCurricular.id])
                redirect componenteCurricular
            }
            '*' { respond componenteCurricular, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond componenteCurricularService.get(id)
    }

    def update(ComponenteCurricular componenteCurricular) {
        if (componenteCurricular == null) {
            notFound()
            return
        }

        try {
            componenteCurricularService.save(componenteCurricular)
        } catch (ValidationException e) {
            respond componenteCurricular.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'componenteCurricular.label', default: 'ComponenteCurricular'), componenteCurricular.id])
                redirect componenteCurricular
            }
            '*'{ respond componenteCurricular, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        componenteCurricularService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'componenteCurricular.label', default: 'ComponenteCurricular'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'componenteCurricular.label', default: 'ComponenteCurricular'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
