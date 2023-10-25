<!DOCTYPE html>
<html>
    <head>
         
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projeto.label', default: 'Projeto')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
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
        <a href="#edit-projeto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-projeto" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.projeto}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.projeto}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

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
             <g:form action="addCollaborator" id="${this.projeto?.id}" method="PUT">
                <fieldset class="form">
                    <div class="ui-widget">
                    <label for="collaborators">Usuário do Colaboradores: </label>
                    <input id="collaborators" name="collaborators">
                    </div>  
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="Adicionar Colaborador" />
                </fieldset>
            </g:form>


            <g:form resource="${this.projeto}" method="PUT">
                <g:hiddenField name="version" value="${this.projeto?.version}" />
                <fieldset class="form">


                    <f:all bean="projeto" except="colaboradores, tarefas, avaliacoes, nota, file"/>
                    
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
           
           
        </div>
    </body>
</html>