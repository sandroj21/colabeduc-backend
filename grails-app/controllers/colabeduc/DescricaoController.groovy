package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import org.hibernate.criterion.CriteriaSpecification
import colabeduc.bncc.*

class DescricaoController {

    DescricaoService descricaoService
    
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def results
        if(params.buscar==null || params.buscar.size()==0)
          respond descricaoService.list(params), model:[descricaoCount: descricaoService.count()]
        else {
          def parametro = "%"+params.buscar+"%"
          println parametro
          results = Descricao.withCriteria {
             createAlias("habilidades", "hab", CriteriaSpecification.LEFT_JOIN)
             or {
                ilike('descricao', parametro)
                ilike('resumo', parametro)
                ilike('titulo', parametro)
                ilike('hab.codigo',parametro)
             }
          }
          respond results  
        }
    }

    def minhas(Integer max) {
       // def principal = springSecurityService.principal
       def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);
       println username
       params.max = Math.min(max ?: 10, 100)
       respond Descricao.findAllWhere(dono: loggedUser ), model:[descricaoCount: descricaoService.count()]
    }

    def show(Long id) {
        respond descricaoService.get(id)
    }

    def create() {
        respond new Descricao(params)
    }

    def listarComponentesCurriculares() {
        println params.id
        def ano = Ano.get(params.id)
        render ComponenteCurricular.findAllWhere(ano:ano) as JSON;
    }

    def listarHabilidades() {
        //println "########\n";
      

       
        def componenteCurricular = ComponenteCurricular.get(params.id)
        //println "#listarHabilidades()#"+componenteCurricular.habilidades;
       // println "#params.id "+params.id+"#";
        // println "#componenteCurricular "+componenteCurricular+"#";
        //  println "########\n";
        def habilidades = Habilidade.findAllWhere(componenteCurricular: componenteCurricular)
        //def habilidades = Habilidade.findAllWhere(componenteCurricular: componenteCurricular)
        //def habilidades = componenteCurricular.habilidades;
        def results
        //println "Habilidades do Componente Curricular: "+componenteCurricular.habilidades;
        habilidades.each() { it->
            def parametro = it.codigo
            results = Descricao.withCriteria {
                createAlias("habilidades", "hab", CriteriaSpecification.LEFT_JOIN)
                or {
                    ilike('hab.codigo',parametro)
                }
            }
            it.descricoes = results.size();
        }
        render habilidades as JSON;
    }
    

    def cadastro() {
        respond new Descricao(params)
    }

    def salvar(Descricao descricao) {
        if (descricao == null) {
            notFound()
            return
        }
        println "Paramestros salvar descricao: "+params

        try {
            descricaoService.save(descricao)
        } catch (ValidationException e) {
            respond descricao.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'descricao.label', default: 'Descricao'), descricao.id])
                redirect descricao
            }
            '*' { respond descricao, [status: CREATED] }
        }   
    }
    def save(Descricao descricao) {
        if (descricao == null) {
            notFound()
            return
        }

       def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);

       if( (Descricao.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) {

                try {
                    descricaoService.save(descricao)
                } catch (ValidationException e) {
                    respond descricao.errors, view:'create'
                    return
                }

                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.created.message', args: [message(code: 'descricao.label', default: 'Descricao'), descricao.id])
                        redirect descricao
                    }
                    '*' { respond descricao, [status: CREATED] }
                }
        }else {
            request.withFormat {
                form multipartForm {
                    flash.message = "Você não tem permissão para editar essa Descrição"
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

       if( (Descricao.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) { 
          respond descricaoService.get(id)
       } else {
         request.withFormat {
            form multipartForm {
                flash.message = "Você não tem permissão para editar essa Descrição"
                redirect(action: "index")
            }
         }

       }
    }

    def editar(Long id) {
       def user = springSecurityService.currentUser
       String username = user.username
       def loggedUser = Usuario.get(user.id);

       if( (Descricao.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) { 
          respond descricaoService.get(id)
       } else {
         request.withFormat {
            form multipartForm {
                flash.message = "Você não tem permissão para editar essa Descrição"
                redirect(action: "index")
            }
         }

       }
    }

    def update(Descricao descricao) {
        if (descricao == null) {
            notFound()
            return
        }

        try {
            descricaoService.save(descricao)
        } catch (ValidationException e) {
            respond descricao.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'descricao.label', default: 'Descricao'), descricao.id])
                redirect descricao
            }
            '*'{ respond descricao, [status: OK] }
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

        if( (Descricao.get(id).dono==loggedUser) ||  (user.authorities.any { it.authority == "ROLE_ADMIN" }) ) { 
                descricaoService.delete(id)

                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.deleted.message', args: [message(code: 'descricao.label', default: 'Descricao'), id])
                        redirect action:"index", method:"GET"
                    }
                    '*'{ render status: NO_CONTENT }
                }
        }else {
            request.withFormat {
                form multipartForm {
                    flash.message = "Voce nao tem permissao para apagar essa descrição"
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }

       }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'descricao.label', default: 'Descricao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
