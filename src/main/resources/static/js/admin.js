console.log('CONNECTED');

const listOptionBtn = $("#list-option-btn button");
const list = $("#main-content");

$(document).ready(function(){
    $("#list-option-btn").on('click', 'button', function() {
       if(!$(this).hasClass("active")) {
               $("button").removeClass("active");
               $(this).addClass("active");

               if ($(this).val() == 'Restaurants') {
                   $.ajax({
                           type: "GET",
                           url: "/fragments/listRestaurants",
                           data: { },
                           success: function(data){
                               console.log(data);
                               list.html(data);
                           }
                       });
               } else {
                   $.ajax({
                           type: "GET",
                           url: "/fragments/listAccounts",
                           data: { },
                           success: function(data){
                               console.log(data);
                               list.html(data);
                           }
                       });
               }
       }
   });
});