package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CategoriaServiceSpec extends Specification {

    CategoriaService categoriaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Categoria(...).save(flush: true, failOnError: true)
        //new Categoria(...).save(flush: true, failOnError: true)
        //Categoria categoria = new Categoria(...).save(flush: true, failOnError: true)
        //new Categoria(...).save(flush: true, failOnError: true)
        //new Categoria(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //categoria.id
    }

    void "test get"() {
        setupData()

        expect:
        categoriaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Categoria> categoriaList = categoriaService.list(max: 2, offset: 2)

        then:
        categoriaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        categoriaService.count() == 5
    }

    void "test delete"() {
        Long categoriaId = setupData()

        expect:
        categoriaService.count() == 5

        when:
        categoriaService.delete(categoriaId)
        sessionFactory.currentSession.flush()

        then:
        categoriaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Categoria categoria = new Categoria()
        categoriaService.save(categoria)

        then:
        categoria.id != null
    }
}
