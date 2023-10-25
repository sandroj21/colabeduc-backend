import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*
import colabeduc.bncc.*

class GroovyExcelParser {

  def parse(path) {
    InputStream inp = new FileInputStream(path)
    Workbook wb = WorkbookFactory.create(inp);
    Sheet sheet = wb.getSheetAt(0);

    Iterator<Row> rowIt = sheet.rowIterator()
    Row row = rowIt.next()
    def headers = getRowData(row)

    def rows = []
    while(rowIt.hasNext()) {
      row = rowIt.next()
      rows << getRowData(row)
    }
    [headers, rows]
  }

  def getRowData(Row row) {
    def data = []
    for (Cell cell : row) {
      getValue(row, cell, data)
    }
    data
  }

  def getRowReference(Row row, Cell cell) {
    def rowIndex = row.getRowNum()
    def colIndex = cell.getColumnIndex()
    CellReference ref = new CellReference(rowIndex, colIndex)
    ref.getRichStringCellValue().getString()
  }
 
  def getValue(Row row, Cell cell, List data) {
    def rowIndex = row.getRowNum()
    def colIndex = cell.getColumnIndex()
    def value = ""
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING:
        value = cell.getRichStringCellValue().getString();
        break;
      case Cell.CELL_TYPE_NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
            value = cell.getDateCellValue();
        } else {
            value = cell.getNumericCellValue();
        }
        break;
      case Cell.CELL_TYPE_BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case Cell.CELL_TYPE_FORMULA:
        value = cell.getCellFormula();
        break;
      default:
        value = ""
    }
    data[colIndex] = value
    data
  }

  def toXml(header, row) {
    def obj = "<object>\n"
    row.eachWithIndex { datum, i -> 
      def headerName = header[i]
      obj += "\t<$headerName>$datum</$headerName>\n" 
    } 
    obj += "</object>"
  }

  public static void main(String[] args) {
    def dir ="/home/aquiles/grailsProjects/colabeduc/scripts"
    def filename = dir+"/BNCC_EF_matematica.xlsx";
    def count
    GroovyExcelParser parser = new GroovyExcelParser()
    //def (headers, rows) = parser.parse(filename)
    /*println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++;
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Matemática")?: new AreaDoConhecimento(nome:"Matemática").save(failOnError: true)
      if(count>2) {
          
          //println "row[1]"+row[1]
          def ano = Ano.findByNome(row[1]+" Ano")?: new Ano(nome:row[1]+" Ano",etapa:etapa).save(failOnError: true)
          //println "row[2]"+row[2]
          def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
          //println "row[3]"+row[3]
          def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
          // println "row[0]"+row[0]
          def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
          //println "row[4]"+row[4]
          def codHabilidade = row[4].substring(1,9);
          print codHabilidade+"   " 
          def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
          //println "desc.Habilidade:"+descricaoHabilidade
          def habilidade = Habilidade.findByCodigo(codHabilidade)?: new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true)
       }
      
    }

    filename = dir+"/BNCC_EF_ciencias.xlsx";
    
    parser = new GroovyExcelParser()
    (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++;
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Ciências Naturais")?: new AreaDoConhecimento(nome:"Ciências Naturais").save(failOnError: true)
      if(count>2) {
          
          //println "row[1]"+row[1]
          def ano = Ano.findByNome(row[1]+" Ano")?: new Ano(nome:row[1]+" Ano",etapa:etapa).save(failOnError: true)
          //println "row[2]"+row[2]
          def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
          //println "row[3]"+row[3]
          def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
          // println "row[0]"+row[0]
          def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
          //println "row[4]"+row[4]
          def codHabilidade = row[4].substring(1,9);
          print codHabilidade+"   " 
          def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
          //println "desc.Habilidade:"+descricaoHabilidade
          def habilidade = Habilidade.findByCodigo(codHabilidade)?: new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true)
       }
      
    }

    filename = dir+"/BNCC_EF_geografia.xlsx";
    
    parser = new GroovyExcelParser()
    (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Ciências Humanas")?: new AreaDoConhecimento(nome:"Ciências Humanas").save(failOnError: true)
      if(count>2) {
          
          //println "row[1]"+row[1]
          def ano = Ano.findByNome(row[1]+" Ano")?: new Ano(nome:row[1]+" Ano",etapa:etapa).save(failOnError: true)
          //println "row[2]"+row[2]
          def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
          //println "row[3]"+row[3]
          def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
          // println "row[0]"+row[0]
          def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
          //println "row[4]"+row[4]
          def codHabilidade = row[4].substring(1,9);
          print codHabilidade+"   " 
          def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
          //println "desc.Habilidade:"+descricaoHabilidade
          def habilidade = Habilidade.findByCodigo(codHabilidade)?: new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true)
       }
      
    }

    filename = dir+"/BNCC_EF_historia.xlsx";
    
    parser = new GroovyExcelParser()
    (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Ciências Humanas")?: new AreaDoConhecimento(nome:"Ciências Humanas").save(failOnError: true)
      if(count>2) {
          
          //println "row[1]"+row[1]
          def ano = Ano.findByNome(row[1]+" Ano")?: new Ano(nome:row[1]+" Ano",etapa:etapa).save(failOnError: true)
          //println "row[2]"+row[2]
          def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
          //println "row[3]"+row[3]
          def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
          // println "row[0]"+row[0]
          def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
          //println "row[4]"+row[4]
          def codHabilidade = row[4].substring(1,9);
          print codHabilidade+"   " 
          def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
          //println "desc.Habilidade:"+descricaoHabilidade
          def habilidade = Habilidade.findByCodigo(codHabilidade)?: new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true)
       }
      
    }

    filename = dir+"/BNCC_EF_ingles.xlsx";
    
    parser = new GroovyExcelParser()
    (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++;
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Linguagens")?: new AreaDoConhecimento(nome:"Linguagens").save(failOnError: true)
      if(count>2) {
          
          //println "row[1]"+row[1]
          def ano = Ano.findByNome(row[1]+" Ano")?: new Ano(nome:row[1]+" Ano",etapa:etapa).save(failOnError: true)
          //println "row[2]"+row[2]
          def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
          //println "row[3]"+row[3]
          def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
          // println "row[0]"+row[0]
          def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
          //println "row[4]"+row[4]
          def codHabilidade = row[4].substring(1,9);
          print codHabilidade+"   " 
          def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
          //println "desc.Habilidade:"+descricaoHabilidade
          def habilidade = Habilidade.findByCodigo(codHabilidade)?: new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true)
       }
      
    }

*/
    filename = dir+"/BNCC_EducacaoFisica.xlsx";
    
    parser = new GroovyExcelParser()
    def (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    count =0;
    rows.each { row ->
      //println count++ +" "+row
      count++;
      def etapa = Etapa.findByNome("Ensino Fundamental")?: new Etapa(nome:"Ensino Fundamental").save(failOnError: true)
      def areaDoConhecimento = AreaDoConhecimento.findByNome("Linguagens")?: new AreaDoConhecimento(nome:"Linguagens").save(failOnError: true)
      if(count>2) {
          
          println "row[1] "+row[1]+ "size:"+row[1].length()
          def ano_list = row[1].replaceAll(",","").replaceAll(";","").split().toList()
          println "lista de anos: "+ano_list
          ano_list.eachWithIndex { ano_item,index->
              def novo_ano = ano_item.replaceAll("\\s","");
              println "\n "+index+"#"+novo_ano+"#";
              def ano = Ano.findByNome(novo_ano+" Ano")?: new Ano(nome:novo_ano+" Ano",etapa:etapa).save(failOnError: true)
              //println "row[2]"+row[2]
              def unidadeTematica = UnidadeTematica.findByNome(row[2])?: new UnidadeTematica(nome:row[2]).save(failOnError: true)
              //println "row[3]"+row[3]
              def objetoDoConhecimento = ObjetoDoConhecimento.findByDescricao(row[3])?: new ObjetoDoConhecimento(descricao:row[3],unidadeTematica:unidadeTematica).save(failOnError: true)
              // println "row[0]"+row[0]
              def componenteCurricular = ComponenteCurricular.findWhere(nome: row[0], ano: ano)?: new ComponenteCurricular(nome:row[0], ano:ano,areaDoConhecimento:areaDoConhecimento).save(failOnError: true)
              //println "row[4]"+row[4]
              def codHabilidade = row[4].substring(1,9);
              codHabilidade = codHabilidade+((index==0)?"":novo_ano.substring(0,1));
              print codHabilidade+"   " 
              def descricaoHabilidade = row[4].substring(11,row[4].size()-1);
              //println "desc.Habilidade:"+descricaoHabilidade
              def habilidade = Habilidade.findByCodigo(codHabilidade);
              if(habilidade!=null) {
                componenteCurricular.addToHabilidades(habilidade).save(flush:true); 
              } else {
                new Habilidade(descricao:descricaoHabilidade, componenteCurricular:componenteCurricular, codigo:codHabilidade,objetoDoConhecimento:objetoDoConhecimento).save(failOnError: true);
              }
          }
          
          
       }
      
    }





  }
}
