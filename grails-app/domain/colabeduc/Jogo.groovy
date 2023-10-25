class Jogo {
    
    String nome
    Date data
    String resultado

    static constraints = {
        nome(blank: false)
        data(nullable: false)
        resultado(inList: ['Vitória', 'Empate', 'Derrota'])
    }
}