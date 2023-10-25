package colabeduc.bncc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ObjetoDoConhecimentoServiceSpec extends Specification {

    ObjetoDoConhecimentoService objetoDoConhecimentoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ObjetoDoConhecimento(...).save(flush: true, failOnError: true)
        //new ObjetoDoConhecimento(...).save(flush: true, failOnError: true)
        //ObjetoDoConhecimento objetoDoConhecimento = new ObjetoDoConhecimento(...).save(flush: true, failOnError: true)
        //new ObjetoDoConhecimento(...).save(flush: true, failOnError: true)
        //new ObjetoDoConhecimento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //objetoDoConhecimento.id
    }

    void "test get"() {
        setupData()

        expect:
        objetoDoConhecimentoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ObjetoDoConhecimento> objetoDoConhecimentoList = objetoDoConhecimentoService.list(max: 2, offset: 2)

        then:
        objetoDoConhecimentoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        objetoDoConhecimentoService.count() == 5
    }

    void "test delete"() {
        Long objetoDoConhecimentoId = setupData()

        expect:
        objetoDoConhecimentoService.count() == 5

        when:
        objetoDoConhecimentoService.delete(objetoDoConhecimentoId)
        sessionFactory.currentSession.flush()

        then:
        objetoDoConhecimentoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ObjetoDoConhecimento objetoDoConhecimento = new ObjetoDoConhecimento()
        objetoDoConhecimentoService.save(objetoDoConhecimento)

        then:
        objetoDoConhecimento.id != null
    }
}
