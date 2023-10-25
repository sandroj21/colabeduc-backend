package colabeduc.rest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController
import grails.converters.JSON
import colabeduc.Projeto

class ProjetoRestController extends RestfulController{

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ProjetoRestController() {
        super(Projeto)
    }
    
    def totalprojetos(){
        def json = "123"
        render json as JSON;
    }
    
}
