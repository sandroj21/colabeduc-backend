<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projeto.label', default: 'Projeto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <asset:javascript src="application.js"/>
        <asset:stylesheet src="application.css"/>
        <script>
          $(document).ready(function(){
              $(function() {
                var cache = {};
                $("#collaborators").autocomplete({
                  minLength: 2,
                  source: function( request, response ) {
                    var term = request.term;
                    if ( term in cache ) {
                      response( cache[ term ] );
                      return;
                    }
             
                    $.getJSON( "../usuarios", request, function( data, status, xhr ) {
                      var users = [];
                      for ( i=0; i < data.length; i++ )
                      {
                        console.log(data[i].username);
                        users[i]=data[i].username;
                      }
                      cache[ term ] = users;
                      response( users);
                    });
                  }
                });
              } );
          });
          </script>
    </head>
    <body>
        <a href="#show-projeto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="show-projeto" class="content scaffold-show" role="main">
            <h1>Exibindo Projeto</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

             <div class="card">
                        <div class="card-header">
                            <h5>Projeto: ${projeto.nome}<span class="badge badge-light"> NOTA:${projeto.nota} Avaliações:${projeto.avaliacoes.size()}</span></h5>
                        </div>
                        <div class="card-body">
                          <h4 class="card-title">${projeto.descricao}</h4>
                          <h5 class="card-title">Descrição</h5>
                          <p class="card-text">${projeto.descricao.resumo}</p>

                          <h5 class="card-title">Habilidades</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="habilidade" in="${projeto.descricao.habilidades}">
                                 <li class="list-group-item"><b>(${habilidade.codigo})</b> ${habilidade.descricao}</li>
                              </g:each>
                          </ul>

                         
                         </br>
                          <h5 class="card-title">Grupos</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="grupo" in="${projeto.grupos}">
                                 <li class="list-group-item">
                                   <b>${grupo.titulo} - ${grupo.categoria}</b>
                                   <ul> 
                                     <li>Repositório: <a href="${grupo.repositorio}">${grupo.repositorio}</a></li>
                                     <li>Chat: <a href="${grupo.chat}">${grupo.chat}</a></li>
                                   </ul>

                                 </li>
                                 
                              </g:each>
                          </ul>
                          </br>
                          <h5 class="card-title">Colaboradores</h5>
                          <g:if test="${projeto.dono.profileImageUrl != null}">
                               <img class="rounded-circle" src="<%=projeto.dono.profileImageUrl %>" width="30" height="30"> 
                          </g:if>
                          <g:else>
                               
                               <asset:image src="apple-touch-icon.png"  class="rounded-circle" width="30" height="30" alt="Colabeduc Logo"/>
                          </g:else> ${projeto.dono.username}
                          <ul class="list-group list-group-flush">
                              <g:each var="colaborador" in="${projeto.colaboradores}">
                                 <li class="list-group-item"><g:link action="show" controller="projeto" id="${projeto.id}"> ${colaborador.username}</g:link></li>
                              </g:each>
                          </ul>
                          
                          </br>
                           <h5 class="card-title">Info</h5>
                          <p class="card-text"><b>Youtube Vídeo ID:</b> ${projeto.video}</p>
                          <iframe width="560" height="315" src="https://www.youtube.com/embed/${projeto.video}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                          <p class="card-text"><b>link do Jogo:</b> <a href="${projeto.linkJogo}" target="_blank">${projeto.linkJogo}</a></p>
                          <p>
                              <g:form name="g" url="[action:'save',controller:'avaliacao']">
                                <input type="hidden" name="projeto" value="${projeto.id}"/>
                                <input type="hidden" name="usuario" value="<sec:loggedInUserInfo field='id'/>"/>
                                 <input type="hidden" name="nota" value="1"/>
                                 <input type="submit" value="Gostei" onclick="return confirm('Tem certeza?');" />
                              </g:form>

                               <g:form name="ng" url="[action:'save',controller:'avaliacao']">
                                <input type="hidden" name="projeto" value="${projeto.id}"/>
                                <input type="hidden" name="usuario" value="<sec:loggedInUserInfo field='id'/>"/>
                                 <input type="hidden" name="nota" value="-1"/>
                                 <input type="submit" value="Não Gostei" onclick="return confirm('Tem certeza?');" />
                              </g:form>
                          </p>
                        
                        </div>
            </div>
            <g:form resource="${this.projeto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.projeto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
