package colabeduc

class EmailSenderController {

    def index() { }

    def send() {
        sendMail {
            to params.address
            subject params.subject
            html params.body
        }

        flash.message = "Message sent at "+new Date()
        redirect action:"index"
    }
}
