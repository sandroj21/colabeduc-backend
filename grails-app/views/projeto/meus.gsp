<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projeto.label', default: 'Projeto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-projeto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="list-projeto" class="content scaffold-list" role="main">
            <h1>Lista de Projetos</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
                <g:if test="${projetoList==null}">
                      <div class="row">
                        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                            <div class="card card-signin my-5">
                                <div class="card-body">
                                    <h5 class="card-title text-center">Você ainda não possui nenhum projeto. </h5>
                                        <p class="text-center">Crie o primeiro agora, não perca tempo.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                  </g:if>
             <div class="card-columns">
                  <g:each var="projeto" in="${projetoList}">
                  <div class="card border-primary">
                        <div class="card-header">
                            <h5><g:link action="show" id="${projeto.id}"> Projeto: ${projeto.nome}</g:link></h5>
                        </div>
                        <div class="card-body">
                          <h4 class="card-title">${projeto.descricao}</h4>
                          <iframe width="100%" src="https://www.youtube.com/embed/${projeto.video}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

                          <p class="card-text"><b>link do Jogo:</b> <a href="${projeto.linkJogo}" target="_blank">${projeto.linkJogo}</a></p>
                          <br>
                          <h5 class="card-title">Descrição</h5>
                          <p class="card-text">${projeto.descricao.resumo}</p>
                         <h5 class="card-title">Habilidades</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="habilidade" in="${projeto.descricao.habilidades}">
                                 <li class="list-group-item"><b>(${habilidade.codigo})</b> ${habilidade.descricao}</li>
                              </g:each>
                          </ul>
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
                          
                          
                        
                        </div>
                   </div>
                  </g:each>
            </div>
            <div class="pagination">
                <g:paginate total="${projetoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>