package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ComponenteCurricularServiceSpec extends Specification {

    ComponenteCurricularService componenteCurricularService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ComponenteCurricular(...).save(flush: true, failOnError: true)
        //new ComponenteCurricular(...).save(flush: true, failOnError: true)
        //ComponenteCurricular componenteCurricular = new ComponenteCurricular(...).save(flush: true, failOnError: true)
        //new ComponenteCurricular(...).save(flush: true, failOnError: true)
        //new ComponenteCurricular(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //componenteCurricular.id
    }

    void "test get"() {
        setupData()

        expect:
        componenteCurricularService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ComponenteCurricular> componenteCurricularList = componenteCurricularService.list(max: 2, offset: 2)

        then:
        componenteCurricularList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        componenteCurricularService.count() == 5
    }

    void "test delete"() {
        Long componenteCurricularId = setupData()

        expect:
        componenteCurricularService.count() == 5

        when:
        componenteCurricularService.delete(componenteCurricularId)
        sessionFactory.currentSession.flush()

        then:
        componenteCurricularService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ComponenteCurricular componenteCurricular = new ComponenteCurricular()
        componenteCurricularService.save(componenteCurricular)

        then:
        componenteCurricular.id != null
    }
}
