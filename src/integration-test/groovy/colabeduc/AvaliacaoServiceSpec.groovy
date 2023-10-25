package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AvaliacaoServiceSpec extends Specification {

    AvaliacaoService avaliacaoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Avaliacao(...).save(flush: true, failOnError: true)
        //new Avaliacao(...).save(flush: true, failOnError: true)
        //Avaliacao avaliacao = new Avaliacao(...).save(flush: true, failOnError: true)
        //new Avaliacao(...).save(flush: true, failOnError: true)
        //new Avaliacao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //avaliacao.id
    }

    void "test get"() {
        setupData()

        expect:
        avaliacaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Avaliacao> avaliacaoList = avaliacaoService.list(max: 2, offset: 2)

        then:
        avaliacaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        avaliacaoService.count() == 5
    }

    void "test delete"() {
        Long avaliacaoId = setupData()

        expect:
        avaliacaoService.count() == 5

        when:
        avaliacaoService.delete(avaliacaoId)
        sessionFactory.currentSession.flush()

        then:
        avaliacaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Avaliacao avaliacao = new Avaliacao()
        avaliacaoService.save(avaliacao)

        then:
        avaliacao.id != null
    }
}
