<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'descricao.label', default: 'Descricao')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <a href="#list-descricao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="cadastro">Criar descrição</g:link></li>

                <li class="home"> 
                  <g:form name="ng" url="[action:'index',controller:'descricao']">
                    <input style="border-radius: 0.25rem;" type="text" size="100" name="buscar"/>
                    <input style="border-radius: 0.25rem;" type="submit" value="Procurar" />
                  </g:form>
                </li>
            </ul>
        </div>
        <br>
         <h1>Descrições de Jogos</h1>
        <div id="list-descricao" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="card-columns">
                  <g:each var="descricao" in="${descricaoList}">
                      <div class="card border-primary">
                        <div class="card-header">
                            <div class="d-flex justify-content-between mb-3">
                              <div class="p-2">

                                  <h5> 
                                    <!--<g:if test="${descricao.dono.profileImageUrl != null}">
                                         <img class="rounded-circle" src="<%=descricao.dono.profileImageUrl %>" width="50" height="50"> 
                                    </g:if>
                                    <g:else>
                                         
                                         <asset:image src="apple-touch-icon.png"  class="rounded-circle" width="50" height="50" alt="Colabeduc Logo"/>
                                    </g:else>-->


                                    <g:link action="show" id="${descricao.id}">${descricao.titulo}</g:link>
                                  </h5>
                              </div>
                              <div class="p-2"></div>
                              <div class="p-2">${descricao.habilidades[0]?.componenteCurricular}
                              </div>
                            </div>
                        </div>
                        <div class="card-body">
                          <h5 class="card-title">Resumo</h5>
                          <g:if test="${descricao.resumo.size() > 500}">
                              <p class="card-text">${descricao.resumo.substring(0,499)} ...</p>
                          </g:if>
                          <g:else>
                             <p class="card-text">${descricao.resumo}</p>
                          </g:else>
                         
                          <h5 class="card-title">Habilidades</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="habilidade" in="${descricao.habilidades}">
                                 <li class="list-group-item"><b>(${habilidade.codigo})</b> ${habilidade.descricao}</li>
                              </g:each>
                          </ul>
                          <div class="d-flex justify-content-between mb-3">
                              <div class="p-2">
                                <g:link controller="projeto" action="create" params="[descricao: descricao.id]" class="btn btn-info">Criar Projeto</g:link>
                              </div>
                              <div class="p-2"></div>
                              <div class="p-2">
                                   <g:if test="${descricao.dono.profileImageUrl != null}">
                                         <img class="rounded-circle" src="<%=descricao.dono.profileImageUrl %>" width="30" height="30"> 
                                    </g:if>
                                    <g:else>
                                         
                                         <asset:image src="apple-touch-icon.png"  class="rounded-circle" width="30" height="30" alt="Colabeduc Logo"/>
                                    </g:else> ${descricao.dono.username}
                              </div>
                          </div>
                        </div>
                      </div>
                  </g:each>
            </div>

          

            <div class="pagination">
                <g:paginate total="${descricaoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>