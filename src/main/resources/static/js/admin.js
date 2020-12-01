console.log('CONNECTED');

const listOptionBtn = $("#list-option-btn button");
const list = $("#main-content");
var activeButton = '';

$(document).ready(function(){
    $("#list-option-btn").on('click', 'button', function() {
       if(!$(this).hasClass("active")) {
               $("button").removeClass("active");
               $(this).addClass("active");
               activeButton = $(this).val();

               if (activeButton == 'Restaurants') {
                   $.ajax({
                           type: "GET",
                           url: "/admin/listRestaurants",
                           data: { },
                           success: function(data){
                               list.html(data);
                           }
                       });
               }

               if (activeButton == 'Accounts') {
                   $.ajax({
                           type: "GET",
                           url: "/admin/listAccounts",
                           data: { },
                           success: function(data){
                               list.html(data);
                           }
                       });
               }
       }
   });
});

$(function() {
    /* Toggle category item - use .on('click') and delegate event */
    $('#main-content').on('click', '.btn', function(e) {
        e.preventDefault();
        const target = e.target.id;

        if(activeButton === 'Accounts') {
            $.ajax({
               type: "GET",
               url: "/admin/delete-user-" + target,
               data: { },
               success: function(data){
                   list.empty();
                   list.html(data);
               }
           });
        }
    });
});

