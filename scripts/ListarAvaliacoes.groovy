import colabeduc.Projeto

Projeto.list().each {
  if(it.nota==0) {
      soma = 0;
       println " -  "+it.avaliacoes.each {
          soma=soma+it.nota
          println "   - "+soma
       } 
       println " soma:"+soma
       it.nota = soma
       println(it.save(flush: true, failOnError: true))
  } else {
      println "teste "+it.nota
  }
}   


Projeto.list().each {
    println it.nota
}    
       