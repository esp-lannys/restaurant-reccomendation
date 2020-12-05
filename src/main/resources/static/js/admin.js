console.log('CONNECTED');

const listOptionBtn = $("#list-option-btn button");
const list = $("#main-content");
var activeButton = '';

$(document).ready(function(){
	//LIST OPTIONS
    $("#list-option-btn").on('click', 'button', function() { 
       if(!$(this).hasClass("active")) {
               $("button").removeClass("active");
               $(this).addClass("active");
               activeButton = $(this).val();

               if (activeButton == 'Restaurants') {
               console.log("SHOW LIST RESTAURANT");
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
              console.log("SHOW LIST ACCOUNT");
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
	//DELETE
    $('#main-content').on('click', 'table .delete', function(e) {
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

	//SEARCH FORM
     $('#main-content').on('submit', '.header-search #searchForm', function(e) {
	    e.preventDefault();

	    var form = $(this);
	    var url = form.attr('action');

	    $.ajax({
	           type: "GET",
	           url: url,
	           data: form.serialize(), // serializes the form's elements.
	           success: function(data) {
	                list.empty();
                    list.html(data);
	           }
         });
	});
});

