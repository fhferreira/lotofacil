<!DOCTYPE html>

<html>
  <head>
 <meta charset="utf-8">    
    <title>LotoFacil - Lista de Jogos</title>
    

  </head>
  
    <body>
    <script src="/lotofacil/scripts/jquery-1.11.2.js"></script>
    <script src="/lotofacil/scripts/jquery.jscroll.js"></script>


    <a href=/lotofacil/incluir>Incluir Concurso</a>
    <br>
    <a href=/lotofacil/>Inicio</a>
    
    

    <div class="scroll" style="border:1px solid red;">
		<#include "/lotofacil/listaDeJogosInclude.ftl" />	
	</div>


<form class="nextPageForm" method="get">

	<input class="nextButton" type="button" value=" next " />
	
</form>

	
	
<script type="text/javascript">
    var frm = $('.nextPageForm');
    
    $(".nextButton").click(function() {
    	
    	frm.attr('action', $(".nextAction").last().text() );
    	frm.submit();
    });

    frm.submit(function (ev) {
        $.ajax({
            type: frm.attr('method'),
            url: frm.attr('action'),
            data: frm.serialize(),
            success: function (data) {
                $(".scroll" ).append( data );
            }
        });

        ev.preventDefault();
    });
</script>	
    


    </body>
    
    
</html>