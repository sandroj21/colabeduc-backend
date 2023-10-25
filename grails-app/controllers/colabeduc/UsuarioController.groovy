package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class UsuarioController {

    UsuarioService usuarioService

    def springSecurityService

    EmailService emailService

    UploadUsuarioProfileImageService uploadUsuarioProfileImageService

    UsuarioDataService usuarioDataService
    
    //CrudMessageService crudMessageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

     def cadastro() {
        respond new Usuario(params)
    }

    def recoveryUsername() {
        
    }

    def recoveryPassword() {
          
    }

    def sendUsernames() {
         def usuarios = Usuario.findAllWhere(email: params.email) 
         if(usuarios) {
             try {
                emailService.recoveryUsernames(params.email,usuarios); 
                flash.message = "E-mail enviado com usuarios"
            } catch(Exception e) {
                 flash.error = "Problemas ao enviar E-mail "+e
            }
            render(view: "/usuario/recoveryUsername") 
         } else  {
            flash.error = "E-mail não encontrado no sistema" 
            render(view: "/usuario/recoveryUsername") 
        }
    }
    def sendPassword() {
         def usuario = Usuario.findWhere(username: params.username)
         
         println params  
         
    
         if(usuario) {
             //geração de uma senha aleatoria
             def pool = ['a'..'z','A'..'Z',0..9,'_'].flatten()
             Random rand = new Random(System.currentTimeMillis())
             def passChars = (0..10).collect { pool[rand.nextInt(pool.size())] }
             def password = passChars.join()

             //envio do email com nova senha
             try {
                emailService.recoveryPassword(usuario.email,usuario.username, password)
                flash.message = "E-mail enviado com senha provisoria para "+usuario.email
                //atualizaçao da senha
                usuario.password = password
                usuario.save(flush: true)
                println password+ ": "+usuario.password
             } catch(Exception e) {
                 flash.error = "Problemas ao Atualizar e enviar senha "+e
            }
             render(view: "/usuario/recoveryPassword") 
         } else { 
           flash.error = "Usuário não encontrato na base"
           render(view: "/usuario/recoveryPassword") 
         }
    }

    @Secured(value=["hasRole('ROLE_ADMIN')"], httpMethod='GET')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usuarioService.list(params), model:[usuarioCount: usuarioService.count()]
    }

    def show(Long id) {
        respond usuarioService.get(id)
    }

    def showProfile(Long id) {
        respond usuarioService.get(id)
    }

    def create() {
        respond new Usuario(params)
    }

    def save(Usuario usuario) {
        if (usuario == null) {
            notFound()
            return
        }

        try {
            usuarioService.save(usuario)
        } catch (ValidationException e) {
            flash.message = ""
            if (usuario.errors.hasFieldErrors("username")) {
                if(usuario.errors.getFieldError().getCode()=="unique") 
                    flash.error = 'Escolha outro username, '+usuario.username+' já em uso.'; 
            } else {
              flash.error = 'Erro ao cadastrar usuário. \n'+usuario.errors;
            }
            respond usuario.errors, view:'cadastro'
            return
        }
        try {
            println "Enviando e-mail para: "+usuario.username+" "+usuario.email
            emailService.enviarEmail(usuario.username, usuario.email)
            flash.message = "Conta criada com sucesso" 
         }  catch (Exception e) {
            flash.message = "Conta criada com sucesso"
            flash.error = "Houve erro no envio de email de confirmação "+e
        }

        request.withFormat {
            form multipartForm {
               // flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id])
                flash.message = 'Usuario Cadastrado com Sucesso.';
                //redirect usuario
                redirect(uri: "/")

            }
            '*' { respond usuario, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond usuarioService.get(id)
    }

    def editProfileImage(Long id) {
        Usuario usuario = usuarioDataService.get(id)
        if (!usuario) {
            notFound()
            return
        }
        [usuario: usuario]
    }

    def uploadProfileImage(ProfileImageCommand cmd) {
        println "Passo 1";

        if (cmd.hasErrors()) {
            respond(cmd, model: [usuario: cmd], view: 'editProfileImage')
            return
        }
        println "Passo 2";
        Usuario usuario = uploadUsuarioProfileImageService.uploadProfileImage(cmd)

        if (usuario == null) {
            notFound()
            return
        }
        println "Passo 3";
        if (usuario.hasErrors()) {
            respond(usuario, model: [usuario: usuario], view: 'editProfileImage')
            return
        }
        println "Passo 3";
        Locale locale = request.locale
        //flash.message = crudMessageService.message(CRUD.UPDATE, domainName(locale), usuario.id, locale)
        redirect usuario
    }
    
    def editProfile(Long id) {
        respond usuarioService.get(id)
    }

    def update(Usuario usuario) {
        if (usuario == null) {
            notFound()
            return
        }

        try {
            usuarioService.save(usuario)
        } catch (ValidationException e) {
            respond usuario.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.id])
                redirect usuario
            }
            '*'{ respond usuario, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usuarioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
