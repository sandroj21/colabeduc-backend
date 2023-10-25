package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MetadescricaoServiceSpec extends Specification {

    MetadescricaoService metadescricaoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Metadescricao(...).save(flush: true, failOnError: true)
        //new Metadescricao(...).save(flush: true, failOnError: true)
        //Metadescricao metadescricao = new Metadescricao(...).save(flush: true, failOnError: true)
        //new Metadescricao(...).save(flush: true, failOnError: true)
        //new Metadescricao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //metadescricao.id
    }

    void "test get"() {
        setupData()

        expect:
        metadescricaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Metadescricao> metadescricaoList = metadescricaoService.list(max: 2, offset: 2)

        then:
        metadescricaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        metadescricaoService.count() == 5
    }

    void "test delete"() {
        Long metadescricaoId = setupData()

        expect:
        metadescricaoService.count() == 5

        when:
        metadescricaoService.delete(metadescricaoId)
        sessionFactory.currentSession.flush()

        then:
        metadescricaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Metadescricao metadescricao = new Metadescricao()
        metadescricaoService.save(metadescricao)

        then:
        metadescricao.id != null
    }
}
