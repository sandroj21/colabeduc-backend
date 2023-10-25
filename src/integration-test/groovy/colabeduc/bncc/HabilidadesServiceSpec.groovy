package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HabilidadesServiceSpec extends Specification {

    HabilidadesService habilidadesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Habilidades(...).save(flush: true, failOnError: true)
        //new Habilidades(...).save(flush: true, failOnError: true)
        //Habilidades habilidades = new Habilidades(...).save(flush: true, failOnError: true)
        //new Habilidades(...).save(flush: true, failOnError: true)
        //new Habilidades(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //habilidades.id
    }

    void "test get"() {
        setupData()

        expect:
        habilidadesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Habilidades> habilidadesList = habilidadesService.list(max: 2, offset: 2)

        then:
        habilidadesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        habilidadesService.count() == 5
    }

    void "test delete"() {
        Long habilidadesId = setupData()

        expect:
        habilidadesService.count() == 5

        when:
        habilidadesService.delete(habilidadesId)
        sessionFactory.currentSession.flush()

        then:
        habilidadesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Habilidades habilidades = new Habilidades()
        habilidadesService.save(habilidades)

        then:
        habilidades.id != null
    }
}
