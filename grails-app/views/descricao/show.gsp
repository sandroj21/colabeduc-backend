<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'descricao.label', default: 'Descricao')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-descricao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="show-descricao" class="content scaffold-show" role="main">
            
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="card">
                        <div class="card-header">
                           <div class="d-flex justify-content-between mb-3">
                              <div class="p-2">
                                  <h5><g:link action="show" id="${descricao.id}">${descricao.titulo}</g:link></h5>
                              </div>
                              <div class="p-2"></div>
                              <!--<div class="p-2">${descricao.habilidades[0]?.componenteCurricular}
                              </div>-->
                             <% 
                                 def componentes = [];
                                 descricao.habilidades.eachWithIndex { it, id ->
                                     componentes[id] = it.componenteCurricular;
                                 }
                              %>
                                <g:each in="${componentes.toSet()}">
                                    <div class="p-2">${it} </div>
                                </g:each>
                            
                            </div>
                        </div>
                        <div class="card-body">
                          <h5 class="card-title">Resumo</h5>
                          
                          <p class="card-text">${descricao.resumo}</p>

                          <h5 class="card-title">Descrição</h5>
                          <p class="card-text">${descricao.descricao}</p>
                         
                          <h5 class="card-title">Habilidades</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="habilidade" in="${descricao.habilidades}">
                                 <li class="list-group-item"><b>(${habilidade.codigo})</b> ${habilidade.descricao}</li>
                              </g:each>
                          </ul>

                          

                          <h5 class="card-title">Projetos</h5>
                          <ul class="list-group list-group-flush">
                              <g:each var="projeto" in="${descricao.projetos}">
                                 <li class="list-group-item"><g:link action="show" controller="projeto" id="${projeto.id}"> ${projeto.nome}</g:link></li>
                              </g:each>
                          </ul>
                          <div class="d-flex justify-content-between mb-3">
                              <div class="p-2"><g:link controller="projeto" action="create" params="[descricao: descricao.id]" class="btn btn-info">Criar Projeto</g:link>
                                </div>
                              <div class="p-2"></div>
                              <div class="p-2"> 
                                  <img class="rounded-circle" src="<%=descricao.dono?.profileImageUrl %>" width="30" height="30">
                                  criado por ${descricao.dono.username}
                              </div>
                            </div>
                        
                        </div>
            </div>

            <!--<f:display bean="descricao" />-->


            <g:form resource="${this.descricao}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="editar" resource="${this.descricao}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
