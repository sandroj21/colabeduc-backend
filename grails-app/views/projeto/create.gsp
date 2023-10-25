<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projeto.label', default: 'Projeto')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-projeto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="create-projeto" class="content scaffold-create" role="main">
            <h1>Criação de Projeto</h1>
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
            <g:form url="[action:'salvar',controller:'projeto']" method="POST">
                <fieldset class="form">
                    <fieldset>

                    <div class="form-group">
                   
                      <label class="control-label" for="Habilidades">Nome</label>
                      <g:field type="text" class="form-control" name="nome" value='${colabeduc.Descricao.get(params.descricao).titulo+" by "+sec.loggedInUserInfo(field: 'username')}'/>
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
                      <!-- Password -->
                      <label class="control-label"  for="dono">Descrição</label>
                      <div class="controls">
                        <select class="custom-select" name="descricao" >
                              <option value="${params.descricao}">
                                  ${colabeduc.Descricao.get(params.descricao).titulo}
                              </option>
                        </select> 
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="control-label" for="repositorio">Repositorio</label>
                      <g:field placeholder="Endereço do repositorio de códigos(ex: http://www.github.com)" type="text" class="form-control" name="repositorio"/>
                    </div>

                    <div class="form-group">
                      <label class="control-label" for="chat">Chat</label>
                      <g:field placeholder="Chat para pessoas que queiram ajudar no desenvolvimento( ex: https://chat.whatsapp.com)" type="text" class="form-control" name="chat"/>
                    </div>

                     <div class="form-group"> 
                      <!--<label class="control-label" for="video">Video</label>-->
                      <input placeholder="Endereço do link do video(ex.:youtube)" type="hidden" class="form-control" name="video" value="-"/>
                    </div>

                    <div class="form-group">
                   
                      <!--<label class="control-label" for="linkJogo">Link Jogo</label>-->
                      <input placeholder="Link parar o jogo" type="hidden" class="form-control" name="linkJogo" value="-"/>
                    </div>

                  <!--<f:all bean="projeto"/>-->
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="Criar Projeto" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
