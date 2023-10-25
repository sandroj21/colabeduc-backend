package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import org.hibernate.criterion.CriteriaSpecification
import grails.converters.JSON

class ProjetoController {

    ProjetoService projetoService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def results;
        println params.buscar
        params.max = Math.min(max ?: 10, 100)
        params.sort = "nota"
        params.order = "desc"
        if(params.buscar==null || params.buscar.size()==0)
          respond projetoService.list(params), model:[projetoCount: projetoService.count()]
        else {
          def parametro = "%"+params.buscar+"%"
          println parametro
          results = Projeto.withCriteria {
             createAlias("dono", "do", CriteriaSpecification.LEFT_JOIN)
             createAlias("descricao", "d", CriteriaSpecification.LEFT_JOIN)
             createAlias("d.habilidades", "hab", CriteriaSpecification.LEFT_JOIN)
             or {
                ilike('nome', parametro)
                ilike('do.username', parametro)
                ilike('d.descricao', parametro)
                ilike('d.resumo', parametro)
                ilike('d.titulo', parametro)
                ilike('hab.codigo',parametro)
             }
          }
          respond results  
        }
    }

     def meus(Integer max) {
       // def principal = springSecurityService.principal
       def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);
       //println username
       params.max = Math.min(max ?: 10, 100)
       //println "loggedUser "+loggedUser
       /*def query = Projeto.where {
            dono == loggedUser || colaboradores.contains(loggedUser)
       }
       def results = query.list(sort:"nome")
       println results*/

       respond Projeto.findAllWhere(dono: loggedUser ), model:[descricaoCount: projetoService.count()]
    }
  
    def usuarios() {
        render Usuario.list() as JSON;
        //def usuarios = Usuario.list() ;
        //def renderMe = [[id:"1", name: "Joe"],[id:"2", name: "Aquiles"]]
        //println params.q
        //render renderMe as JSON;
        
        /* render(contentType: "application/javascript") { 
            render renderMe
        }*/
    }

    def show(Long id) {
        def projeto = projetoService.get(id)
        projeto.updateNota();
        respond projeto
    }

    def create() {
        respond new Projeto(params)
    }

    def salvar(Projeto projeto) {
        if (projeto == null) {
            notFound()
            return
        }

        params.lastUpdated = new Date()
        println params

        if(params.repositorio=="")
            params.repositorio="-"
        if(params.chat=="")
            params.chat="-"
        

        try {
            projetoService.save(projeto)
            
            def grupo = new Grupo(titulo: projeto.nome+" - "+projeto.dono.username, regras:"-", forum:"-", repositorio: params.repositorio, chat: params.chat, projeto:projeto, categoria: 1).save()
            println "Grupos criado: "+grupo;
        } catch (ValidationException e) {
            respond projeto.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'projeto.label', default: 'Projeto'), projeto.id])
                redirect projeto
            }
            '*' { respond projeto, [status: CREATED] }
        }
    }

    def save(Projeto projeto) {
        if (projeto == null) {
            notFound()
            return
        }
        def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);

       if( (Projeto.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) {

            try {
                projetoService.save(projeto)
            } catch (ValidationException e) {
                respond projeto.errors, view:'create'
                return
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'projeto.label', default: 'Projeto'), projeto.id])
                    redirect projeto
                }
                '*' { respond projeto, [status: CREATED] }
            }
        }else {
            request.withFormat {
                form multipartForm {
                    flash.message = "Você não tem permissão para editar esse projeto"
                    redirect projeto
                }
                '*' { respond projeto, [status: CREATED] }
            }
        }
    }

    def edit(Long id) {
         def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);

       if( (Projeto.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) {
            respond projetoService.get(id)
       } else {
            request.withFormat {
                form multipartForm {
                    flash.message = "Você não tem permissão para editar esse projeto"
                    redirect(action: "index")
                }
                
            }

        }
    }

    def addCollaborator() {
        println params.id+" "+params.collaborators
        def projeto = projetoService.get(params.id)
        projeto.addToColaboradores(Usuario.findByUsername(params.collaborators))
        projetoService.save(projeto)

        redirect(controller: "projeto", action: "edit", id: params.id)

    }

    def update(Projeto projeto) {
        if (projeto == null) {
            notFound()
            return
        }

        try {
            projetoService.save(projeto)
        } catch (ValidationException e) {
            respond projeto.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'projeto.label', default: 'Projeto'), projeto.id])
                redirect projeto
            }
            '*'{ respond projeto, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
       def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);

       if( (Projeto.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) {

        projetoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'projeto.label', default: 'Projeto'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }

       }else {
            request.withFormat {
                form multipartForm {
                    flash.message = "Voce nao tem permissao para apagar esse projeto"
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }

       }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'projeto.label', default: 'Projeto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
