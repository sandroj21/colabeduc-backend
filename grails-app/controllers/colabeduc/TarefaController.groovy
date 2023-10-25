package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TarefaController {

    TarefaService tarefaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tarefaService.list(params), model:[tarefaCount: tarefaService.count()]
    }

    def show(Long id) {
        respond tarefaService.get(id)
    }

    def create() {
        respond new Tarefa(params)
    }

    def save(Tarefa tarefa) {
        if (tarefa == null) {
            notFound()
            return
        }

        try {
            tarefaService.save(tarefa)
        } catch (ValidationException e) {
            respond tarefa.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefa.id])
                redirect tarefa
            }
            '*' { respond tarefa, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tarefaService.get(id)
    }

    def update(Tarefa tarefa) {
        if (tarefa == null) {
            notFound()
            return
        }

        try {
            tarefaService.save(tarefa)
        } catch (ValidationException e) {
            respond tarefa.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefa.id])
                redirect tarefa
            }
            '*'{ respond tarefa, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tarefaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
