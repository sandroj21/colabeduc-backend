package colabeduc.rest

import grails.converters.JSON
import colabeduc.*

class CadastroController {

    def index() { }

    def usuario() {
        def recebido = request.JSON
        def userRole = Papel.findByAuthority('ROLE_USER')?: new Papel(authority:'ROLE_USER').save(failOnError: true)
        def msg="";
        def newUser;    
        def code=0;
        if(recebido.username==null) {
            msg+=" Username não informado";
            code=1;
        }
        if(recebido.password==null) {
            msg+=" Password não informado";
            code=code+3;
        }
        if(recebido.email==null) {
            msg+=" Email não informado";
            code=code+6;
        }
        if(recebido.username!=null && recebido.password!=null && recebido.email!=null) {
            newUser = new Usuario(username: recebido.username, password: recebido.password, email:recebido.email).save()
            msg = "Usuario cadastrado com sucesso";
        }
        

         UsuarioPapel.create newUser, userRole

         UsuarioPapel.withTransaction { status ->
            UsuarioPapel.withSession {
                it.flush()
                it.clear()
            }
        }
        def resposta = [responsecode:code, message:msg];
        render resposta as JSON
    }
}
