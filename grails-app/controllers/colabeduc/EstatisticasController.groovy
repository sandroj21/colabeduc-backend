package colabeduc

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import org.hibernate.criterion.CriteriaSpecification
import colabeduc.bncc.*

class EstatisticasController {
    
    EstatisticasService estatisticasService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def vitorias = estatisticasService.calcularVitorias()
        def derrotas = estatisticasService.calcularDerrotas()
        def empates = estatisticasService.calcularEmpates()

        render(view: 'index', model: [vitorias: vitorias, derrotas: derrotas, empates: empates])
    }

}