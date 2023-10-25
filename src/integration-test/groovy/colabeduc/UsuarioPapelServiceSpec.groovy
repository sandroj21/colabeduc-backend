package colabeduc

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UsuarioPapelServiceSpec extends Specification {

    UsuarioPapelService usuarioPapelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UsuarioPapel(...).save(flush: true, failOnError: true)
        //new UsuarioPapel(...).save(flush: true, failOnError: true)
        //UsuarioPapel usuarioPapel = new UsuarioPapel(...).save(flush: true, failOnError: true)
        //new UsuarioPapel(...).save(flush: true, failOnError: true)
        //new UsuarioPapel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //usuarioPapel.id
    }

    void "test get"() {
        setupData()

        expect:
        usuarioPapelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UsuarioPapel> usuarioPapelList = usuarioPapelService.list(max: 2, offset: 2)

        then:
        usuarioPapelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        usuarioPapelService.count() == 5
    }

    void "test delete"() {
        Long usuarioPapelId = setupData()

        expect:
        usuarioPapelService.count() == 5

        when:
        usuarioPapelService.delete(usuarioPapelId)
        sessionFactory.currentSession.flush()

        then:
        usuarioPapelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UsuarioPapel usuarioPapel = new UsuarioPapel()
        usuarioPapelService.save(usuarioPapel)

        then:
        usuarioPapel.id != null
    }
}
