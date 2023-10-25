<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>

        <script type="application/javascript">
          function getSelectionHtml(identidade){
          var html = "";
            var antes = document.getElementById(identidade).innerHTML;
            var novalinha = "<li>";
            var fechalinha = "</li>";
            if (typeof window.getSelection != "undefined") {
              var sel = window.getSelection();
              if (sel.rangeCount) {
                var container = document.createElement("div");
                for (var i = 0, len = sel.rangeCount; i < len; ++i) {
                  container.appendChild(sel.getRangeAt(i).cloneContents());
                }
                html = container.innerHTML;
              }
            } else if (typeof document.selection != "undefined") {
              if (document.selection.type == "Text") {
                html = document.selection.createRange().htmlText;
              }
            }
            document.getElementById(identidade).innerHTML = antes.concat(novalinha, html, fechalinha);
             var sel, range, node;
              if (window.getSelection) {
                  sel = window.getSelection();
                  if (sel.getRangeAt && sel.rangeCount) {
                      range = window.getSelection().getRangeAt(0);
                      if(identidade=='listaarte'){
                      var textohtml = '<span style="background-color:#FFD700;">' + range + '</span>';
                      }
                      else if(identidade=='listadesenv'){
                      var textohtml = '<span style="background-color:#1E90FF;">' + range + '</span>';
                      }
                      else if(identidade='listaaudio'){
                      var textohtml = '<span style="background-color:#FF69B4;">' + range + '</span>';
                      }
                      range.deleteContents();
                      
                      var el = document.createElement("div");
                      el.innerHTML = textohtml;
                      var frag = document.createDocumentFragment(), node, lastNode;
                      while ( (node = el.firstChild) ) {
                          lastNode = frag.appendChild(node);
                      }
                      range.insertNode(frag);
                  }
              } else if (document.selection && document.selection.createRange) {
                  range = document.selection.createRange();
                  range.collapse(false);
                  range.pasteHTML(textohtml);
              }
          }

          function removeLista(identidade) {
            var html_2 = "";
            document.getElementById(identidade).innerHTML = html_2;

          }
          </script>
    </head>
    <body>
          <h2>Editor SGDDEDU</h2>
          <div class="row">
            <div class="col-sm-6">
                <form action="sgddedu/create" method="GET">
                 <input v-model="titulo" name="titulo" placeholder="Titulo"><br>
                 <input type="hidden" name="autor" value="<sec:loggedInUserInfo field='username'/>">
                 <textarea v-model="historia" name="historia" placeholder="Historia"></textarea><br>
                 <textarea v-model="jogo" name="jogo" placeholder="Jogo"></textarea><br>
                 <button type="submit" class="btn btn-primary">Submit</button>
               </form>  
            </div>
            <div class="col-sm-6">
               <h1 contenteditable= "true"> Titulo</h1>
               <h4><i>Autor(a):  <sec:loggedInUserInfo field="username" /></i></h4>
               <h3>Historia</h3>
               <p style="white-space: pre-line;" contenteditable= "true">História}</p>
               <h3>Jogo</h3>
               <p style="white-space: pre-line;" contenteditable= "true">Descrição do Jogo</p>
            </div>
          </div> 
      <button onclick="getSelectionHtml('listaarte');" class="button">Arte</button>
      <button onclick="getSelectionHtml('listadesenv');" class="button button2">Programação</button>
      <button onclick="getSelectionHtml('listaaudio');" class="button button3">Audio</button>
      <button onclick="removeLista('listaarte');"> Limpar Arte</button>
      <button onclick="removeLista('listadesenv');"> Limpar Prog</button>
      <button onclick="removeLista('listaaudio');"> Limpar Audio</button>
      <br><br><br>
      <div class="box1">
      <div id="header">
        <h2>
          Arte
        </h2>
      </div>
      <ul id="listaarte"></ul>
      <br>
      </div>
      <div class="box2">
      <div id="header2">
        <h2>
          Programação
        </h2>
      </div>
      <ul id="listadesenv"></ul>
      <br>
      </div>
      <div class ="box3">
      <div id="header3">
        <h2>
          Audio
        </h2>
      </div>
      <ul id="listaaudio"></ul>
      <br>
      </div>
      </div>

    </body>
<style>

#header {
  background-color: #FFD700;
  padding: 5px;
  text-align: center;
  font-size: 20px;
  color: black;
}

#header2 {
  background-color: #1E90FF;
  padding: 5px;
  text-align: center;
  font-size: 20px;
  color: black;
}

#header3 {
  background-color: #FF69B4;
  padding: 5px;
  text-align: center;
  font-size: 20px;
  color: black;
}

#listaaudio {
  list-style-image: url("https://png.icons8.com/metro/13/FF69B4/music.png");
  list-style-position: inside;
}

#listadesenv {
  list-style-image: url("https://png.icons8.com/small/16/1E90FF/source-code.png");
  list-style-position: inside;
  
}

#listaarte {
  list-style-image: url("https://png.icons8.com/metro/13/FFD700/paint-bucket.png");
  list-style-position: inside;
}
.box1{
  width:30%;
  float:left;
  margin-right: 20px;
  overflow: hidden;
  border: 1px solid #000;
}
.box2{
  width:30%;
  float: left;
  margin-right: 20px;
  overflow: hidden;
  border: 1px solid #000;
}
.box3{
  width:30%;
  float: left;
  overflow: hidden;
  border: 1px solid #000;
}

.button {
  background-color: #FFD700;
  /*amarelo */
  border-radius: 4px;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  color: white;
  padding: 8px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
}

.button2 {
  background-color: #1E90FF;
}

.button3 {
  background-color: #FF69B4;
}
</style>

</html>
