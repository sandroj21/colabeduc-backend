package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AreaDoConhecimentoServiceSpec extends Specification {

    AreaDoConhecimentoService areaDoConhecimentoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AreaDoConhecimento(...).save(flush: true, failOnError: true)
        //new AreaDoConhecimento(...).save(flush: true, failOnError: true)
        //AreaDoConhecimento areaDoConhecimento = new AreaDoConhecimento(...).save(flush: true, failOnError: true)
        //new AreaDoConhecimento(...).save(flush: true, failOnError: true)
        //new AreaDoConhecimento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //areaDoConhecimento.id
    }

    void "test get"() {
        setupData()

        expect:
        areaDoConhecimentoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AreaDoConhecimento> areaDoConhecimentoList = areaDoConhecimentoService.list(max: 2, offset: 2)

        then:
        areaDoConhecimentoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        areaDoConhecimentoService.count() == 5
    }

    void "test delete"() {
        Long areaDoConhecimentoId = setupData()

        expect:
        areaDoConhecimentoService.count() == 5

        when:
        areaDoConhecimentoService.delete(areaDoConhecimentoId)
        sessionFactory.currentSession.flush()

        then:
        areaDoConhecimentoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AreaDoConhecimento areaDoConhecimento = new AreaDoConhecimento()
        areaDoConhecimentoService.save(areaDoConhecimento)

        then:
        areaDoConhecimento.id != null
    }
}
