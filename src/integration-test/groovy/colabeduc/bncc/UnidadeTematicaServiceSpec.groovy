package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UnidadeTematicaServiceSpec extends Specification {

    UnidadeTematicaService unidadeTematicaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UnidadeTematica(...).save(flush: true, failOnError: true)
        //new UnidadeTematica(...).save(flush: true, failOnError: true)
        //UnidadeTematica unidadeTematica = new UnidadeTematica(...).save(flush: true, failOnError: true)
        //new UnidadeTematica(...).save(flush: true, failOnError: true)
        //new UnidadeTematica(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //unidadeTematica.id
    }

    void "test get"() {
        setupData()

        expect:
        unidadeTematicaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UnidadeTematica> unidadeTematicaList = unidadeTematicaService.list(max: 2, offset: 2)

        then:
        unidadeTematicaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        unidadeTematicaService.count() == 5
    }

    void "test delete"() {
        Long unidadeTematicaId = setupData()

        expect:
        unidadeTematicaService.count() == 5

        when:
        unidadeTematicaService.delete(unidadeTematicaId)
        sessionFactory.currentSession.flush()

        then:
        unidadeTematicaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UnidadeTematica unidadeTematica = new UnidadeTematica()
        unidadeTematicaService.save(unidadeTematica)

        then:
        unidadeTematica.id != null
    }
}
