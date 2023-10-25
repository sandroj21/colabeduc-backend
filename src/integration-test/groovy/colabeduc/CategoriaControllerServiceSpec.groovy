package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CategoriaControllerServiceSpec extends Specification {

    CategoriaControllerService categoriaControllerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CategoriaController(...).save(flush: true, failOnError: true)
        //new CategoriaController(...).save(flush: true, failOnError: true)
        //CategoriaController categoriaController = new CategoriaController(...).save(flush: true, failOnError: true)
        //new CategoriaController(...).save(flush: true, failOnError: true)
        //new CategoriaController(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //categoriaController.id
    }

    void "test get"() {
        setupData()

        expect:
        categoriaControllerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CategoriaController> categoriaControllerList = categoriaControllerService.list(max: 2, offset: 2)

        then:
        categoriaControllerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        categoriaControllerService.count() == 5
    }

    void "test delete"() {
        Long categoriaControllerId = setupData()

        expect:
        categoriaControllerService.count() == 5

        when:
        categoriaControllerService.delete(categoriaControllerId)
        sessionFactory.currentSession.flush()

        then:
        categoriaControllerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CategoriaController categoriaController = new CategoriaController()
        categoriaControllerService.save(categoriaController)

        then:
        categoriaController.id != null
    }
}
