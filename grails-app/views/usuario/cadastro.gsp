<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <meta charset="utf-8">
        <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
       
    </head>
    <body>
     <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Cadastro de Usuário</h5>

          <g:form name="form_cadastro" resource="${this.usuario}" method="POST" >
            <fieldset>
              <g:if test="${flash.message}">
                  <div class="alert alert-danger" role="alert">${flash.message}</div>
              </g:if>
              <div class="form-group">
                <!-- Username -->
                <label for="username">Username:</label>
                <div id="result-username"></div>
                <div class="controls">
                  <g:field type="text" id="input_username" onBlur="validateUsername()" class="form-control" name="username"/>
                </div>
              </div>
              <div class="form-group">
                <!-- email -->
                <label for="email">E-mail:</label>
                <div id="result"></div>
                <div class="controls">
                  <g:field type="text" id="email" onBlur="validateEmail()" class="form-control" name="email"/>
                </div>
              </div>

              <div class="form-group">
                <!-- Password-->
                <label class="control-label" for="password">Password</label>
                <div id="result_pass"></div>
                <div class="controls">
                  <g:field type="password" onBlur="validadePassword()" id="password" class="form-control" name="password"/>
                </div>
              </div>
          
              <div class="form-group">
                <!-- Password Check -->
                <label class="control-label"  for="password_confirm">Confirmação de Password</label>
                <div id="result_check"></div>
                <div class="controls">
                  <g:field type="password" onBlur="validadePassword()" id="password_check" class="form-control" name="password_confirm"/>
                </div>
              </div>
               
              <div class="form-group">
                <!-- Button -->
                  
                  <input id="btn_cadastrar" type="button" class="btn btn-primary" onclick="cadastrar()" value="Cadastrar">
                  <!--<g:submitButton class="btn btn-primary" id="btn_cadastrar" name="create" value="Cadastrar" />-->
              </div>
              <hr class="my-4">
              <p>Já possui conta? <g:link controller="login" action="auth">Logar</g:link></p>
            </fieldset>
            
          </g:form>
          </div>
        </div>
      </div>
    </div>
         <script>
            document.addEventListener("DOMContentLoaded", function(event) {
                $("#input_username").focus();
            });
            var check_username=false;
            var check_email_validity=false;
            var check_same_password=false;

           function hasWhiteSpace(s) {
              return s.indexOf(' ') >= 0;
           }

           function validateUsername() {
              const $result = $("#result-username");
              const username = $("#input_username").val();
              $result.text("");
              if(hasWhiteSpace(username)) {
                 $result.text("username não pode conter espaços");
                 $result.css("color", "red");
              } else { 
                if(username.length==0) {
                  $result.text("username não pode ficar em branco");
                  $result.css("color", "red");
                } else {
                  check_username=true;
                }
              }
            

           }

           function checkEmail(email) {
              const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
              return re.test(email);
            }
            function validateEmail() {
              const $result = $("#result");
              const email = $("#email").val();
              $result.text("");

              if(email.length==0) {
                 $result.text("e-mail não pode ficar em branco");
                 $result.css("color", "red");
              }else {
                  if (checkEmail(email)) {
                    check_email_validity=true;
                  } else {
                    $result.text("e-mail "+email + " não é valido :(");
                    $result.css("color", "red");
                    check_email_validity=false;
                  }
              }
              return false;
            }

            function validadePassword() {
              const password =  $("#password").val();
              const password_check =  $("#password_check").val();
              const $result_check = $("#result_check");
              const $result_pass = $("#result_pass");
              $result_check.text("");
              $result_pass.text("");
              if(password.length==0) {
                 $result_pass.text("password não pode ficar em branco");
                 $result_pass.css("color", "red");
              } else {
                  if (password!=password_check) {
                    $result_check.text("Passwords estão diferentes");
                    $result_check.css("color", "red");
                    check_same_password=false;
                  } else {
                    check_same_password=true;
                  }
              }
            }

            function cadastrar() {
              validateUsername();
              validateEmail();
              validadePassword();
              if(check_username&&check_email_validity&&check_same_password){
                   $("#form_cadastro").submit();
              }
            }

            
          </script>
    </body>
    
</html>




