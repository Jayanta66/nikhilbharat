   $(document).ready(function() {
    $('#loader').hide();
    $("#submit").on("click", function() {
    	$("#submit").prop("disabled", true);
    	var name = $("#name").val();
        var file = $("#image").val(); 
        var text = $("#text").val();
        var form = $("#form").serialize();
    	var data = new FormData($("#form")[0]);
    	data.append('name', name);
    	data.append('text', text);
    	data.append('image',image);
    	data.append('file',file);
    	//alert(data);
        $('#loader').show();
        if (name === "") {
        	$("#submit").prop("disabled", false);
            $('#loader').hide();
            $("#name").css("border-color", "red");
            $("#text").css("border-color", "red");
            $("#image").css("border-color", "red");
            $("#error_name").html("Please fill the required field.");
            $("#error_file").html("Please fill the required field.");
            $("#error_city").html("Please fill the required field.");
        } else {
            $("#name").css("border-color", "");
            $("#image").css("border-color", "");
            $("#text").css("border-color", "");
            $('#error_name').css('opacity', 0);
            $('#error_file').css('opacity', 0);
            $('#error_city').css('opacity', 0);
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/chat/saveChat", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        	$('#loader').hide(); 
                        	$("#form")[0].reset();
                        	$('#success').css('display','block');
                            $("#error").text("");
                            $("#success").html("Inserted Succsessfully.");
                            $('#success').delay(2000).fadeOut('slow');
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            location.reload();
                        }
                    });
        }
            });
        });
