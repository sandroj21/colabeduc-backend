package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DescricaoServiceSpec extends Specification {

    DescricaoService descricaoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Descricao(...).save(flush: true, failOnError: true)
        //new Descricao(...).save(flush: true, failOnError: true)
        //Descricao descricao = new Descricao(...).save(flush: true, failOnError: true)
        //new Descricao(...).save(flush: true, failOnError: true)
        //new Descricao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //descricao.id
    }

    void "test get"() {
        setupData()

        expect:
        descricaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Descricao> descricaoList = descricaoService.list(max: 2, offset: 2)

        then:
        descricaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        descricaoService.count() == 5
    }

    void "test delete"() {
        Long descricaoId = setupData()

        expect:
        descricaoService.count() == 5

        when:
        descricaoService.delete(descricaoId)
        sessionFactory.currentSession.flush()

        then:
        descricaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Descricao descricao = new Descricao()
        descricaoService.save(descricao)

        then:
        descricao.id != null
    }
}
