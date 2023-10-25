package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PapelServiceSpec extends Specification {

    PapelService papelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Papel(...).save(flush: true, failOnError: true)
        //new Papel(...).save(flush: true, failOnError: true)
        //Papel papel = new Papel(...).save(flush: true, failOnError: true)
        //new Papel(...).save(flush: true, failOnError: true)
        //new Papel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //papel.id
    }

    void "test get"() {
        setupData()

        expect:
        papelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Papel> papelList = papelService.list(max: 2, offset: 2)

        then:
        papelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        papelService.count() == 5
    }

    void "test delete"() {
        Long papelId = setupData()

        expect:
        papelService.count() == 5

        when:
        papelService.delete(papelId)
        sessionFactory.currentSession.flush()

        then:
        papelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Papel papel = new Papel()
        papelService.save(papel)

        then:
        papel.id != null
    }
}
