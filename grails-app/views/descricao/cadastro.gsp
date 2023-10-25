<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'descricao.label', default: 'Descricao')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script>
            var habilidadesEscolhidas = [];  
            
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
                    removerNaoSelecionados();
                    //options.append("<option value='-1'>Nenhum</option>");
                    $.each( data, function(key, val) {
                      options.append(new Option(val.nome, val.id));
                    });
                    atualizarHabilidades();
                  });

            }
           
            function atualizarHabilidades() {
                  console.log("atualizando Habilidades...");
                  var componentes = $("#selectComponentes");
                  var habilidades = $("#selectHabilidades");
                  //console.log(componentes.val());
                 
                  $.getJSON( "/descricao/listarHabilidades?id="+componentes.val(), function( data ) {
                    //habilidades.empty();  
                     removerNaoSelecionados();
                    $.each( data, function(key, val) {
                      //console.log("quantidade de descricoes"+val.descricoes);
                      if (!$('#hab_'+val.id).length){
                        if(val.descricoes=="0")
                          habilidades.append("<input class='checkHab' id='hab_"+val.id+"' type='checkbox' name='habilidades' value='"+val.id+"'><span>("+val.codigo+") "+val.descricao+"</span><br>");
                        else
                          habilidades.append("<input class='checkHab' id='hab_"+val.id+"' type='checkbox' name='habilidades' value='"+val.id+"'><span style='color:red' data-toggle='tooltip' title='"+val.descricoes+" descrições já foram criadas com essa habilidade, sugerimos escolher uma habilidade que ainda não tenha sindo contemplada'> ("+val.codigo+") "+val.descricao+" - "+val.descricoes+" descrições já criadas</span><br>");
                      }
                    });
                  }).done(function() {
                    console.log( "second success" );
                    $('input:checkbox').change(function(){
                        if($(this).is(':checked')){
                          //  alert('Checked '+this.value);
                        }
                    });
                  });

                  
            }
            $(document).ready(function() {
                atualizarComponentes();
                atualizarHabilidades(); 
               
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
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form url="[action:'salvar',controller:'descricao']" method="POST">
                <fieldset>
                    <div id="legend">
                      <legend class="">Crie uma Descrição de Jogo Educacional</legend>
                    </div>

                    <div class="form-group">
                      <!-- Password-->
                      <label class="control-label" for="Habilidades">Nível de Ensino</label>
                      <div class="controls">
                          <g:select name="ano"
                          from="${colabeduc.bncc.Ano.list(sort:"nome")}"
                          optionKey="id"  id="selectAnos" class="custom-select" onChange="atualizarComponentes();"/>
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

                    <h2>EVITEM usar habilidades em <span style="color:red;">Vermelho</span>, elas já possuem descrições. Dê preferencia a habilidades que ainda não tenham sido utilizadas.</h2>
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
                        <g:field type="text" class="form-control" name="titulo"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="resumo">Resumo:</label>
                      <div class="controls">
                        <textarea name="resumo" class="form-control" rows="5" id="comment"></textarea>
                      </div>
                    </div>

                    <div class="form-group">
                      <!-- Password-->
                      <label class="control-label" for="Descricao">Descrição</label>
                      <div class="controls">
                         <textarea name="descricao" class="form-control" rows="5" id="comment"></textarea>
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
