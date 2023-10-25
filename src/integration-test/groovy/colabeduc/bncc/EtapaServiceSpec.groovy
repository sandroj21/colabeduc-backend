package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EtapaServiceSpec extends Specification {

    EtapaService etapaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Etapa(...).save(flush: true, failOnError: true)
        //new Etapa(...).save(flush: true, failOnError: true)
        //Etapa etapa = new Etapa(...).save(flush: true, failOnError: true)
        //new Etapa(...).save(flush: true, failOnError: true)
        //new Etapa(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //etapa.id
    }

    void "test get"() {
        setupData()

        expect:
        etapaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Etapa> etapaList = etapaService.list(max: 2, offset: 2)

        then:
        etapaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        etapaService.count() == 5
    }

    void "test delete"() {
        Long etapaId = setupData()

        expect:
        etapaService.count() == 5

        when:
        etapaService.delete(etapaId)
        sessionFactory.currentSession.flush()

        then:
        etapaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Etapa etapa = new Etapa()
        etapaService.save(etapa)

        then:
        etapa.id != null
    }
}
