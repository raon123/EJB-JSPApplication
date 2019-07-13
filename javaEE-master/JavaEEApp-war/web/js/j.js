$( document ).ready(function() {
    
    $(".alert").toggle();
    
    $.getJSON( "GetJson", function (dataJson) {
        presentarJson(dataJson);
    });
    
    $("#send").click(function(){
        var jsonData = crearJson();
        enviarJsonData(jsonData);
    });
        
});

function presentarJson(dataJson){
    $.each(dataJson, function (i, item) {
        presentarFila(i,item);
    });
}

function presentarFila(i,item){
  var ul = '<h3>' + item.name + '</h3><li>'
          + item.age + '</li><li>'
          + item.address + '</li><li>'
          + item.salary + '</li>';
  var div = $("<div class='col-sm-4' id='"+item.id+"'></div>");
  $('.row').append(div);
  $('#'+item.id).append(ul);
}

function crearJson(){
    var j = new Object();
    j.name = $("#name").val();
    j.age = $("#age").val();
    j.address = $("#address").val();
    j.salary = $("#salary").val();
    return j;
}

function enviarJsonData(jsonData){
   $.ajax({
    type: 'post',
    url: 'SaveJson',
    dataType: 'JSON',
    data: { 
      company: JSON.stringify(jsonData)
    },
    success: function(data) {
        if (data!=1) {
            showSendDiv();
            $('.row').html("");
            presentarJson(data);
        }
        else showErrorDiv();
    },
    error: function(data) {
        showErrorDiv();
    }
    });
}

function showErrorDiv(){
    $(".alert-warning").fadeIn('slow').delay(3000).fadeOut('slow');
}
function showSendDiv(){
    $(".alert-success").fadeIn('slow').delay(3000).fadeOut('slow');
}