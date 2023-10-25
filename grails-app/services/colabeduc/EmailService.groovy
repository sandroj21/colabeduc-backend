package colabeduc

import grails.gorm.transactions.Transactional

@Transactional
class EmailService {

    def enviarEmail(def nome_usuario, def email) {

         sendMail {
            to email
            subject "Cadastro Realizado com sucesso"
            html "<h2>Parabéns, "+ nome_usuario+'!</h2>'+
                 "<p>Você agora faz parte do Team ColabEduc. Vamos juntos mudar a educação! Let's go :D </p>"
        }

    }

     def recoveryUsernames(def email, def usuarios) {

         sendMail {
            to email
            subject "Colabeduc - Recuperação de usuarios"
            html "<h2>Seu email "+ email+' possui os seguintes usuarios:</h2>'+
                 "<p>"+usuarios+"</p>"
        }

    }

    def recoveryPassword(def email, def username, def password) {

         sendMail {
            to email
            subject "Colabeduc - Senha Recuperada"
            html "<h2>A nova senha do usuario "+ username+' é:</h2>'+
                 "<p>"+password+"</p>"
        }

    }
}
