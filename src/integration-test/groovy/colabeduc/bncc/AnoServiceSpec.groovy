package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnoServiceSpec extends Specification {

    AnoService anoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Ano(...).save(flush: true, failOnError: true)
        //new Ano(...).save(flush: true, failOnError: true)
        //Ano ano = new Ano(...).save(flush: true, failOnError: true)
        //new Ano(...).save(flush: true, failOnError: true)
        //new Ano(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ano.id
    }

    void "test get"() {
        setupData()

        expect:
        anoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Ano> anoList = anoService.list(max: 2, offset: 2)

        then:
        anoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        anoService.count() == 5
    }

    void "test delete"() {
        Long anoId = setupData()

        expect:
        anoService.count() == 5

        when:
        anoService.delete(anoId)
        sessionFactory.currentSession.flush()

        then:
        anoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Ano ano = new Ano()
        anoService.save(ano)

        then:
        ano.id != null
    }
}
