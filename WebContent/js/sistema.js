$(function() {
    $( ".calendario").datepicker({
    	        dateFormat: 'dd/mm/yy',
    			dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
    	        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
    	        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
    	        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
    	        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    			});
});

function soNumeros(d) {
    return d.replace(/\D/g, "");
}

function mascara(el, masc) {
	el.value = masc(el.value);
}

function fone(d) {
    d = soNumeros(d);
    if (d.length < 11) {
        d = d.replace(/^(\d{2})(\d)/g, "($1) $2");
        d = d.replace(/(\d{4})(\d)/, "$1-$2");
    } else {
        d = d.replace(/^(\d{2})(\d)/g, "($1) $2");
        d = d.replace(/(\d{5})(\d)/, "$1-$2");
    }
    
    return d;
}

function cpfCnpjMask(d) {

    d = soNumeros(d);

    if (d.length < 14) { //CPF

        d = d.replace(/(\d{3})(\d)/, "$1.$2");
        d = d.replace(/(\d{3})(\d)/, "$1.$2");
        d = d.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    } else { //CNPJ

        d = d.replace(/^(\d{2})(\d)/, "$1.$2");
        d = d.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
        d = d.replace(/\.(\d{3})(\d)/, ".$1/$2");
        d = d.replace(/(\d{4})(\d)/, "$1-$2");
    }

    return d;
}

var max_fields_c = 21;
var c = 1;
$('#add_Contato').click(function (e) {
    e.preventDefault();	//prevenir novos clicks
    if (c < max_fields_c) {
        $('#listaContatos').append('\
					<tr class="remove_contato' + c + '">\
                         <td><input type="text" id="fixo" name="Fixo" class="form-control" maxlength="15", autocomplete="off" onkeyup = "mascara(this,fone);" placeholder="Telefone Fixo"></td>\
                         <td><input type="text" id="celular" name="Celular" class="form-control" maxlength="15", autocomplete="off" onkeyup = "mascara(this,fone);" placeholder="Telefone Celular"></td>\
					     <td><a href="#" class="remove_campo_contato btn btn-danger btn-sm" id="remove_contato' + c + '">Remover</a></td>\
                    </tr>\
			');
        c++;
    }
});

$('#listaContatos').on("click", ".remove_campo_contato", function (e) {
    e.preventDefault();
    var tr = $(this).attr('id');
    $('#listaContatos tr.' + tr).remove();
    c--;
});

var max_fields_e = 21;
var ex = 1;
$('#add_Endereco').click(function (e) {
    e.preventDefault();	//prevenir novos clicks
    if (ex < max_fields_e) {
        $('#listaEnderecos').append('\
					<tr class="remove_endereco' + ex + '">\
                         <td><input type="text" name="Logradouro" class="form-control" maxlength="255", autocomplete = "off" placeholder="Logradouro"></td>\
                         <td><input type="text" name="Cidade" class="form-control" maxlength="100", autocomplete = "off" placeholder="Cidade"></td>\
                         <td><input type="text" name="Uf" class="form-control" maxlength="2", autocomplete = "off" placeholder="UF"></td>\
					     <td style="width: 80px;"><a href="#" class="remove_campo_endereco btn btn-danger btn-sm" id="remove_endereco' + ex + '">Remover</a></td>\
                    </tr>\
			');
        ex++;
    }
});

$('#listaEnderecos').on("click", ".remove_campo_endereco", function (e) {
    e.preventDefault();
    var tr = $(this).attr('id');
    $('#listaEnderecos tr.' + tr).remove();
    ex--;
});