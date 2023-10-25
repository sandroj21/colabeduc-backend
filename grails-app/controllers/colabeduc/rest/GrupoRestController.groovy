package colabeduc.rest

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController
import colabeduc.Grupo

class GrupoRestController extends RestfulController{

    static responseFormats = ['json']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    GrupoRestController() {
        super(Grupo)
    }

}
