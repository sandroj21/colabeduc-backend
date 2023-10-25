package colabeduc.rest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController
import colabeduc.Descricao
import grails.converters.JSON

class DescricaoRestController extends RestfulController{

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    DescricaoRestController() {
        super(Descricao)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.offset = params?.offset?.toInteger()
        respond colabeduc.Descricao.list(params), model:[descricaoCount: colabeduc.Descricao.count()]
       
    }

}
