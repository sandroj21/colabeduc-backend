<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code='springSecurity.login.title'/></title>
</head>

<body>
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Recuperação de Senha</h5>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="alert alert-danger" role="alert">${flash.error}</div>
            </g:if>
            <g:form name="form_recovery" url="[action:'sendPassword',controller:'usuario']" method="POST" >
              <div class="form-group">
                  <label for="username">Username</label>
                <input type="text" class="form-control" name="${usernameParameter ?: 'username'}" id="username" autocapitalize="none"/>
              </div>

              <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Enviar</button>
              <hr class="my-4">
              <p>Não lembra seu username ? <g:link controller="usuario" action="recoveryUsername">Recuperar username</g:link></p>
            </g:form>
          </div>
        </div>
      </div>
    </div>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function(event) {
            document.forms['loginForm'].elements['username'].focus();
        });
        function passwordDisplayToggle() {
            var toggleEl = document.getElementById("passwordToggler");
            var eyeIcon = '\u{1F441}';
            var xIcon = '\u{2715}';
            var passEl = document.getElementById("password");
            if (passEl.type === "password") {
                toggleEl.innerHTML = xIcon;
                passEl.type = "text";
            } else {
                toggleEl.innerHTML = eyeIcon;
                passEl.type = "password";
            }
        }
    </script>
</body>
</html>

