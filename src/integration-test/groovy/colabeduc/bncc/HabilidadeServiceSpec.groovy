package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HabilidadeServiceSpec extends Specification {

    HabilidadeService habilidadeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Habilidade(...).save(flush: true, failOnError: true)
        //new Habilidade(...).save(flush: true, failOnError: true)
        //Habilidade habilidade = new Habilidade(...).save(flush: true, failOnError: true)
        //new Habilidade(...).save(flush: true, failOnError: true)
        //new Habilidade(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //habilidade.id
    }

    void "test get"() {
        setupData()

        expect:
        habilidadeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Habilidade> habilidadeList = habilidadeService.list(max: 2, offset: 2)

        then:
        habilidadeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        habilidadeService.count() == 5
    }

    void "test delete"() {
        Long habilidadeId = setupData()

        expect:
        habilidadeService.count() == 5

        when:
        habilidadeService.delete(habilidadeId)
        sessionFactory.currentSession.flush()

        then:
        habilidadeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Habilidade habilidade = new Habilidade()
        habilidadeService.save(habilidade)

        then:
        habilidade.id != null
    }
}
