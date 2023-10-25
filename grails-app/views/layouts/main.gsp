<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Colabeduc"/>
    </title>

     <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >
     
    <style>
      .navbar-toggler {
        background-color: #0074D9;
        border-color: white;
      }

      footer {
  background: #16222A;
  background: -webkit-linear-gradient(59deg, #3A6073, #16222A);
  background: linear-gradient(59deg, #3A6073, #16222A);
  color: white;
  margin-top:100px;
}

footer a {
  color: #fff;
  font-size: 14px;
  transition-duration: 0.2s;
}

footer a:hover {
  color: #FA944B;
  text-decoration: none;
}

.copy {
  font-size: 12px;
  padding: 10px;
  border-top: 1px solid #FFFFFF;
}

.footer-middle {
  padding-top: 2em;
  color: white;
}


/*SOCİAL İCONS*/

/* footer social icons */

ul.social-network {
  list-style: none;
  display: inline;
  margin-left: 0 !important;
  padding: 0;
}

ul.social-network li {
  display: inline;
  margin: 0 5px;
}


/* footer social icons */

.social-network a.icoFacebook:hover {
  background-color: #3B5998;
}

.social-network a.icoLinkedin:hover {
  background-color: #007bb7;
}

.social-network a.icoFacebook:hover i,
.social-network a.icoLinkedin:hover i {
  color: #fff;
}

.social-network a.socialIcon:hover,
.socialHoverClass {
  color: #44BCDD;
}

.social-circle li a {
  display: inline-block;
  position: relative;
  margin: 0 auto 0 auto;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
  text-align: center;
  width: 30px;
  height: 30px;
  font-size: 15px;
}

.social-circle li i {
  margin: 0;
  line-height: 30px;
  text-align: center;
}

.social-circle li a:hover i,
.triggeredHover {
  -moz-transform: rotate(360deg);
  -webkit-transform: rotate(360deg);
  -ms--transform: rotate(360deg);
  transform: rotate(360deg);
  -webkit-transition: all 0.2s;
  -moz-transition: all 0.2s;
  -o-transition: all 0.2s;
  -ms-transition: all 0.2s;
  transition: all 0.2s;
}

.social-circle i {
  color: #595959;
  -webkit-transition: all 0.8s;
  -moz-transition: all 0.8s;
  -o-transition: all 0.8s;
  -ms-transition: all 0.8s;
  transition: all 0.8s;
}

.social-network a {
  background-color: #F9F9F9;
}
    </style>
    
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:link rel="apple-touch-icon" sizes="76x76" href="/apple-touch-icon.png"/>
    <asset:link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
    <asset:link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
    <asset:link rel="manifest" href="/site.webmanifest"/>
    <asset:link rel="mask-icon" href="/safari-pinned-tab.svg" color="#5bbad5"/>
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>


<nav class="navbar navbar-expand-lg navbar-light navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#">
        <asset:image src="colabeduclogohorizontal.png" alt="Colabeduc Logo"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <sec:ifNotLoggedIn>

            <li class="nav-item">
                <g:link class="nav-link" controller="usuario" action="cadastro">
                    <span class="glyphicon glyphicon-user"></span> Cadastro
                </g:link>
            </li>
            <li class="nav-item">
                <g:link class="nav-link" controller="login" action="auth">
                    <span class="glyphicon glyphicon-log-in"></span> Entrar
                </g:link>
            </li>
          </sec:ifNotLoggedIn>


          <sec:ifLoggedIn>
                  <li class="nav-item">
                    <div class="dropdown">
                          <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                            Descrições
                          </button>
                          <div class="dropdown-menu">
                            <g:link class="dropdown-item" controller="descricao" action="index">
                        <span class="glyphicon glyphicon-user">Listar Descrições</span> 

                           </g:link>
                           <g:link class="dropdown-item" controller="descricao" action="cadastro">Criar descrição</g:link>

                          </div>
                    </div> 
                    
                  </li>

                  <li class="nav-item">
                    <g:link class="nav-link" controller="projeto" action="index">
                        <span class="glyphicon glyphicon-user">Projetos</span> 
                    </g:link>
                  </li>

                  <li class="nav-item">
                      <g:link class="nav-link" controller="descricao" action="minhas">
                          <span class="glyphicon glyphicon-user">Minhas Descrições</span> 
                      </g:link>
                  </li>

                  <li class="nav-item">
                      <g:link class="nav-link" controller="projeto" action="meus">
                          <span class="glyphicon glyphicon-user">Meus Projetos</span> 
                      </g:link>
                  </li>
                  <sec:ifAnyGranted roles='ROLE_ADMIN'>

                      <li class="nav-item">
                        <g:link class="nav-link" controller="metadescricao" action="index">
                            <span class="glyphicon glyphicon-user">Metadescrições</span> 
                        </g:link>
                      </li>
                     
                      <li class="nav-item">
                         <g:link class="nav-link" controller="categoria" action="index">
                            <span class="glyphicon glyphicon-user">Categorias</span> 
                        </g:link>
                      </li> 
                      
                      <li class="nav-item">
                         <g:link class="nav-link" controller="grupo" action="index">
                            <span class="glyphicon glyphicon-user">Grupos</span> 
                        </g:link>
                      </li> 
                      <li class="nav-item">
                         <g:link class="nav-link" controller="tarefa" action="index">
                            <span class="glyphicon glyphicon-user">Tarefas</span> 
                        </g:link>
                      </li> 
                      
                      
                      <li class="nav-item">
                         <g:link class="nav-link" controller="usuario" action="index">
                            <span class="glyphicon glyphicon-user">Usuarios</span> 
                        </g:link>
                      </li> 

                       <li class="nav-item">
                            <div class="dropdown">
                              <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                                Currículo
                              </button>
                              <div class="dropdown-menu">
                                <a class="dropdown-item" href="/etapa">Etapa</a>
                                <a class="dropdown-item" href="/ano">Ano</a>
                                <a class="dropdown-item" href="/areaDoConhecimento">Area do Conhecimento</a>
                                <a class="dropdown-item" href="/componenteCurricular">Componente Curricular</a>
                                <a class="dropdown-item" href="/unidadeTematica">UnidadeTematica</a>
                                <a class="dropdown-item" href="/objetoDoConhecimento">Objeto do Conhecimento</a>
                                <a class="dropdown-item" href="/habilidade">Habilidade</a>
                              </div>
                            </div> 
                           
                      </li>  
                  </sec:ifAnyGranted>
                  <li class="nav-item">
                        <div class="dropdown">
                          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            Bem vindo, <sec:loggedInUserInfo field='username'/>
                          </button>
                          <div class="dropdown-menu">
                            <a class="dropdown-item" href='/usuario/showProfile/<sec:loggedInUserInfo field='id'/>' >Perfil </a>
                            <a class="dropdown-item" href="/logout">Sair</a>
                          </div>
                        </div> 
                       
                  </li>  
        </sec:ifLoggedIn>
        </ul>
     </div>
     </nav>

        <g:layoutBody/>

   

    

<!--<div class="footer row" role="contentinfo">
    
</div>-->
  <!-- Footer-->
        <footer class="footer text-center">
            <div class="container">
                <div class="row">
                    <!-- Footer Location-->
                    <div class="col-lg-4 mb-0 mb-lg-0">
                        <!--<h4 class="text-uppercase mb-4">Localização</h4>
                        <p class="lead mb-0">Natal<br />RN</p>-->
                    </div>
                    <!-- Footer Social Icons-->
                    <div class="col-lg-4 mb-0 mb-lg-0">
                        <h4 class="text-uppercase mb-0">Apoio</h4>
                         <a href="http://www.natalnet.br"><asset:image src="Natalnet.png" class="mx-auto d-block img-fluid"/></a>   
                    </div>
                    <!-- Footer About Text-->
                    <div class="col-lg-4">
                        <h4>INSTAGRAM</h4>
                         
                      <ul class="social-network social-circle">
                       
                      <!--<li><a href="#" class="icoFacebook" title="Facebook"><i class="fa fa-facebook"></i></a></li>-->
                      <li><a href="https://instagram.com/colabeduc" class="icoLinkedin" title="Instagram"><i class="fa fa-instagram"></i></a></li>
                      </ul>
                       		
                    </div>
                </div>

                <div class="col-md-3">
                 		
              </div>
            </div>
        </footer>
        
        <!-- Copyright Section-->
        <div class="copyright py-4 text-center">
            <div class="container"><small>Copyright © Colabeduc 2023</small></div>
        </div>
        <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes)-->
        <div class="scroll-to-top d-lg-none position-fixed">
            <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top"><i class="fa fa-chevron-up"></i></a>
        </div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
