package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProjetoServiceSpec extends Specification {

    ProjetoService projetoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Projeto(...).save(flush: true, failOnError: true)
        //new Projeto(...).save(flush: true, failOnError: true)
        //Projeto projeto = new Projeto(...).save(flush: true, failOnError: true)
        //new Projeto(...).save(flush: true, failOnError: true)
        //new Projeto(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //projeto.id
    }

    void "test get"() {
        setupData()

        expect:
        projetoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Projeto> projetoList = projetoService.list(max: 2, offset: 2)

        then:
        projetoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        projetoService.count() == 5
    }

    void "test delete"() {
        Long projetoId = setupData()

        expect:
        projetoService.count() == 5

        when:
        projetoService.delete(projetoId)
        sessionFactory.currentSession.flush()

        then:
        projetoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Projeto projeto = new Projeto()
        projetoService.save(projeto)

        then:
        projeto.id != null
    }
}
