console.log('CONNECTED');

const listOptionBtn = $("#list-option-btn button");
const list = $("#main-content");

$(document).ready(function(){
    $("#list-option-btn").on('click', 'button', function() {
       if(!$(this).hasClass("active")) {
               $("button").removeClass("active");
               $(this).addClass("active");

               if($(this).val() == 'Restaurants') {
                list.html("<p>This is list of Restaurant</p>");
               } else {
                 list.html("<p>This is list of Accounts</p>");
               }
       }
   }); 
});