package colabeduc



class BootStrap {

    EmailService emailService

    def init = { servletContext ->

    	println "Criando papeis"
    	def userRole = Papel.findByAuthority('ROLE_USER')?: new Papel(authority:'ROLE_USER').save(failOnError: true)

    	 def adminRole = Papel.findByAuthority('ROLE_ADMIN')?: new Papel(authority:'ROLE_ADMIN').save(failOnError: true)

         println "Criando admin..."
         def adminUser = Usuario.findByUsername('admin')?:  new Usuario(username: 'admin', password: 'admin', email:'admin@gmail.com').save()


         UsuarioPapel.create adminUser, adminRole

         UsuarioPapel.withTransaction { status ->
            UsuarioPapel.withSession {
                it.flush()
                it.clear()
            }
        }
        println "terminou bootstrap..."

        //emailService.enviarEmail("chico","aquiles.burlamaqui@ufrn.br");


    }
    def destroy = {
    }
}
