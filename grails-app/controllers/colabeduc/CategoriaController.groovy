package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CategoriaController {

    CategoriaService categoriaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond categoriaService.list(params), model:[categoriaCount: categoriaService.count()]
    }

    def show(Long id) {
        respond categoriaService.get(id)
    }

    def create() {
        respond new Categoria(params)
    }

    def save(Categoria categoria) {
        if (categoria == null) {
            notFound() 
            return
        }

        try {
            categoriaService.save(categoria)
        } catch (ValidationException e) {
            respond categoria.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.id])
                redirect categoria
            }
            '*' { respond categoria, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond categoriaService.get(id)
    }

    def update(Categoria categoria) {
        if (categoria == null) {
            notFound()
            return
        }

        try {
            categoriaService.save(categoria)
        } catch (ValidationException e) {
            respond categoria.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.id])
                redirect categoria
            }
            '*'{ respond categoria, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        categoriaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
