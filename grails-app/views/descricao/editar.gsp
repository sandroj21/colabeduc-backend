<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'descricao.label', default: 'Descricao')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <script>
            function removerNaoSelecionados() {
              // $('input:checkbox').filter(":not(:checked)").empty();
              $('input:checkbox').each(function(){
                  if($(this).is(':checked')){
                      console.log('Checked '+this.value);
                  }else {
                    $(this).next().remove();
                    $(this).next().remove();
                    $(this).remove();
                  }
              });
            }      


            function atualizarComponentes() {
                  console.log("atualizando Componentes...");
                  var options = $("#selectComponentes");
                  var anos = $("#selectAnos");
                  console.log(anos.val());
                  $.getJSON( "/descricao/listarComponentesCurriculares?id="+anos.val(), function( data ) {
                    //options.empty();
                    removerNaoSelecionados();
                    //options.append("<option value='-1'>Nenhum</option>");
                    $.each( data, function(key, val) {
                      options.append(new Option(val.nome, val.id));
                    });
                    selecionaComponente();
                    atualizarHabilidades();
                  });


            }
            function atualizarHabilidades() {
                  console.log("atualizando Habilidades...");
                  var componentes = $("#selectComponentes");
                  var habilidades = $("#selectHabilidades");
                  console.log(componentes.val());
                  $.getJSON( "/descricao/listarHabilidades?id="+componentes.val(), function( data ) {
                    //habilidades.empty();
                    removerNaoSelecionados();
                    //habilidades.append(" <option value='-1'>Nenhum</option>");
                    $.each( data, function(key, val) {

                      habilidades.append("<input type='checkbox' name='habilidades' value='"+val.id+"'><span>("+val.codigo+") "+val.descricao+"</span><br>");
                      //habilidades.append(new Option("("+val.codigo+") "+val.descricao, val.id));
                    });
                    selecionaHabilidade();
                  });

            }
            function selecionaAno() {
                  console.log("selecionando Ano...");
                  var componentes = $("#selectAnos");
                  console.log(componentes.val());
                  componentes.val("${(this.descricao.habilidades[0]==null)?:this.descricao.habilidades[0].componenteCurricular.ano.id}").change(); 
            }
            function selecionaComponente() {
                  console.log("selecionando Componente...");
                  var componentes = $("#selectComponentes");
                  console.log(componentes.val()+" ${(this.descricao.habilidades[0]==null)?1:this.descricao.habilidades[0].componenteCurricular.ano.id}");
                  componentes.val("${(this.descricao.habilidades[0]==null)?:this.descricao.habilidades[0].componenteCurricular.ano.id}").change(); 
            }
            function selecionaHabilidade() {
                  console.log("selecionando Habilidade...");
                  var componentes = $("#selectHabilidades");
                  console.log(componentes.val()+" ${(this.descricao.habilidades[0]==null)?1:this.descricao.habilidades[0].componenteCurricular.ano.id}");
                  componentes.val("${(this.descricao.habilidades[0]==null)?:this.descricao.habilidades[0].componenteCurricular.ano.id}").change(); 
            }



            $( document ).ready(function() {
                selecionaAno();

                //atualizarComponentes();
               

                //atualizarHabilidades();
               
            });
           
         </script>
    </head>
    <body>
        <a href="#create-descricao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="create-descricao" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.descricao}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.descricao}" var="error">
                  <li> 
                    <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"
                    </g:if>>
                    <g:message error="${error}"/>
                  </li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.descricao}"  method="PUT">
                <fieldset>
                    <div id="legend">
                      <legend class="">Editar Descrição</legend>
                    </div>

                    <div class="form-group">
                      <!-- Password-->
                      <label class="control-label" for="Habilidades">Ano do Ensino Fundamental</label>
                      <div class="controls">
                          <g:select name="ano"
                            from='${colabeduc.bncc.Ano.list(sort:"nome")}'
                            optionKey="id"  
                            id="selectAnos" 
                            class="custom-select" 
                            onChange="atualizarComponentes();"/>
                      </div>
                    </div>
                     <div class="form-group">
                      <!-- Password-->
                      <label class="control-label" for="Habilidades">Componente</label>
                      <div class="controls">
                         <select id="selectComponentes" name="componenteCurricular" class="custom-select"  onChange="atualizarHabilidades();"> 
                             
                         </select>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="control-label" for="Habilidades">Habilidades</label>
                      <div class="controls">
                         <div id="selectHabilidades">
                         </div>
                         <!--<select id="selectHabilidades" name="habilidades" class="custom-select">
                             
                         </select>-->
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="titulo">Titulo do Jogo:</label>

                      <div class="controls">
                        <g:field type="text" class="form-control" name="titulo" value="${this.descricao.titulo}"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="resumo">Resumo:</label>
                      <div class="controls">
  <textarea name="resumo" class="form-control" rows="5" id="comment">${this.descricao.resumo}</textarea>
                      </div>
                    </div>

                    <div class="form-group">
                      <!-- Password-->
                      <label class="control-label" for="Descricao">Descrição</label>
                      <div class="controls">
                         <textarea name="descricao" class="form-control" rows="5" id="comment">${this.descricao.descricao}</textarea>
                      </div>
                    </div>
                 
                    <div class="form-group">
                      <!-- Password -->
                      <label class="control-label"  for="dono">Dono</label>
                      <div class="controls">
                        <select class="custom-select" name="dono" >
                              <option value="<sec:loggedInUserInfo field="id" />">
                                  <sec:loggedInUserInfo field="username" />
                              </option>
                        </select> 
                      </div>
                    </div>  

                    
                 
                    <div class="form-group">
                      <!-- Button -->
                    <g:submitButton class="btn btn-primary" name="create" value="Cadastrar" />
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>

</html>
