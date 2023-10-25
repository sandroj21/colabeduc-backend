<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bem vindo ao ColabEduc</title>
   <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 45%;
            height: 45%;
        }



    </style>
    
</head>
<body>
    

    <div class="container-fluid">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:if test="${flash.error}">
            <div class="alert alert-danger" role="alert">${flash.error}</div>
        </g:if>

        <div id="like_button_container"></div>
                    
       

        <!-- CAROUSEL -->
    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- Indicators -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
            <li data-target="#demo" data-slide-to="3"></li>
        </ul>
        
        <!-- The slideshow -->
        <div class="carousel-inner">
              <div class="carousel-item text-center img-fluid active">
                <asset:image src="noticias/colabimage1.jpg"/> 
            </div>
            <div class="carousel-item text-center img-fluid ">
                <a href="https://www.ect.ufrn.br/projeto-da-ect-une-educacao-tecnologia-e-diversao-em-uma-plataforma/">
                <asset:image src="noticias/colabimage2.jpg" />
                </a> 
            </div>
          
            <div class="carousel-item text-center img-fluid">
                   <asset:image src="noticias/colabimage3.jpg" /> 
                
            </div>
            
            <div class="carousel-item text-center img-fluid">
                   <asset:image src="noticias/colabeducnoticia.png" /> 
                
            </div>
            
        </div>
        
        <!-- Left and right controls -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
        
        <div id="content" role="main">
            <section class="row colset-2-its">

                <h1 class="page-section-heading text-center text-uppercase text-secondary mb-0">Bem vindos, colabeducs :D</h1>
                
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <p style="font-size:22px">
                    O ColabEduc é um sistema de desenvolvimento colaborativo de objetos de aprendizagem, sejam eles virtuais ou reais.  O ColabEduc cria um ambiente onde profissionais de diferentes áreas possam colaborar, construir e compartilhar seus objetos de aprendizagem. 
                </p>
            </section>
        </div>

        <!--BARRA COM NUMEROS-->
        <div class="row text-center">
            <div class="col">
                <div class="counter">
                    <i class="fa fa-code fa-2x"></i>
                    <h2 id="usuarios" class="timer count-title count-number" data-to="1" data-speed="1500"></h2>
                    <p class="count-text text-uppercase">Desenvolvedores de Games</p>
                </div>
            </div>
            <div class="col">
                <div class="counter">
                    <i class="fa fa-coffee fa-2x"></i>
                    <h2 id="descricoes" class="timer count-title count-number" data-to="1" data-speed="1500"></h2>
                    <p class="count-text text-uppercase">Descrições de Games</p>
                </div>
            </div>
            <div class="col">
                <div class="counter">
                    <i class="fa fa-lightbulb-o fa-2x"></i>
                    <h2 id="projetos" class="timer count-title count-number" data-to="1" data-speed="1500"></h2>
                    <p class="count-text text-uppercase">Projetos de Games</p>
                </div>
            </div>
            <div class="col">
                <div class="counter">
                <i class="fa fa-bug fa-2x"></i>
                <h2 id="habilidadesbncc" class="timer count-title count-number" data-to="1" data-speed="1500"></h2>
                <p class="count-text text-uppercase">Habilidades BNCC Contempladas</p>
                </div>
            </div>
        </div>
        
        <div id="result"></div>    
        </div>
    </div>

  

</body>
</html>
