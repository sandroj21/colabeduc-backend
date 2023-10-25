package colabeduc.rest

import grails.gorm.transactions.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.JSON
import groovy.json.JsonBuilder
import colabeduc.Usuario

@Transactional(readOnly = true)
class PublicController {

    def index() {
        println "entrou aqui... index public"
        def json = Usuario.list();
        render json as JSON;
     }

     def projetos() {
         def json = colabeduc.Projeto.list().size()
         render json;
     }

     def descricoes() {
         def json = colabeduc.Descricao.list().size()
         render json;
     }

    def numbers() {
         def descSize = colabeduc.Descricao.list().size()
         def projSize = colabeduc.Projeto.list().size()
         def usrSize = colabeduc.Usuario.list().size()

         def descricoes = colabeduc.Descricao.list()
         def habilidades = [];
         descricoes.each {
             habilidades = habilidades + it.habilidades
         }

         def habSize =  habilidades.unique().size();
         def json = new JsonBuilder()

         json descricoes: descSize,
              projetos: projSize,
              usuarios: usrSize,
              habilidades: habSize  
         render json
     }

     def usuarios() {
         def json = colabeduc.Usuario.list().size()
         render json;
     }

     def habilidadesbncc() {
         def descricoes = colabeduc.Descricao.list()
         def habilidades = [];
         descricoes.each {
             habilidades = habilidades + it.habilidades
         }
         render habilidades.unique().size();
     }
}
