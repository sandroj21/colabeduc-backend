package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TarefaServiceSpec extends Specification {

    TarefaService tarefaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Tarefa(...).save(flush: true, failOnError: true)
        //new Tarefa(...).save(flush: true, failOnError: true)
        //Tarefa tarefa = new Tarefa(...).save(flush: true, failOnError: true)
        //new Tarefa(...).save(flush: true, failOnError: true)
        //new Tarefa(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tarefa.id
    }

    void "test get"() {
        setupData()

        expect:
        tarefaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Tarefa> tarefaList = tarefaService.list(max: 2, offset: 2)

        then:
        tarefaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        tarefaService.count() == 5
    }

    void "test delete"() {
        Long tarefaId = setupData()

        expect:
        tarefaService.count() == 5

        when:
        tarefaService.delete(tarefaId)
        sessionFactory.currentSession.flush()

        then:
        tarefaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Tarefa tarefa = new Tarefa()
        tarefaService.save(tarefa)

        then:
        tarefa.id != null
    }
}
