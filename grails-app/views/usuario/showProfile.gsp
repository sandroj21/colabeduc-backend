<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <style>
            .card {
                margin: 0 auto; /* Added */
                float: none; /* Added */
                margin-bottom: 10px; /* Added */
            }
        </style>
    </head>
    <body>
        <a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
              
            </ul>
        </div>
        <div id="show-usuario" class="content scaffold-show" role="main">
            <h1>Meu Perfil</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
                <div class="card" style="width:400px">
                  
                  <div class="card-body">
                    <h4 class="card-title">
                        <f:display bean="usuario" property="username"/>
                    </h4>
                    <p class="card-text"><f:display bean="usuario" property="email"/></p>
                    <p class="card-text">${usuario.getAuthorities()}</p>
                    
                  </div>
                  <img class="card-img-top" src="<%=usuario.profileImageUrl %>" width="304" height="236">
                </div>
              
                
                
            <g:form resource="${this.usuario}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="editProfile" resource="${this.usuario}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
