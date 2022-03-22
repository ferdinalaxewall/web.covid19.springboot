$(window).on("load", function(){
	$(".pre-loader").fadeOut(2000);
});

$(document).ready(function(){
	
	$(".input-group").submit(function(e){
		e.preventDefault();
		var inputValue = $("#input-countryName").val();
		$(".capitalize").text(inputValue);
		searchByCountryName();
	});
	
	 $('.group-information-card').slick({
        dots: true,
        infinite: false,
        speed: 300,
        slidesToShow: 3,
        slidesToScroll: 1,
        responsive: [
          {
            breakpoint: 1024,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3,
              infinite: true,
              dots: true
            }
          },
          {
            breakpoint: 600,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 2
            }
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1
            }
          }
        ]
      });
	
	var reqUrl = "/searchByCountryName";
	
	function searchByCountryName(){
		$.ajax({
			type : "POST",
			url : reqUrl,
			data : JSON.stringify({
				countryName : $("#input-countryName").val()
			}),
			headers : {
				'Accept': 'application/json',
		        'Content-Type': 'application/json'
		    },
		    beforeSend : function(xhr){
		    	$(".group-case-card").html("<div class='circle'></div>").fadeIn("slow");
		    },
		    'dataType' : 'text',
		    success : function(result){
		    	if (result.error == "true") {
		    		iziToast.error({
					    title: 'Error',
					    message: 'Your request failed to load',
    					icon: "bx bx-z",
					    position: 'topCenter',
					    transitionIn: 'fadeInt',
					    transitionOut: 'fadeOutRight',
					    transitionInMobile: 'fadeIn',
					    transitionOutMobile: 'fadeOutRight', 
		    			timeout: 3000,
					});
					console.log("Success but error");
				} else {
					iziToast.success({
					    title: 'Success',
					    message: 'Your request has successfuly loaded',
    					icon: "bx bx-check",
					    position: 'bottomRight',
					    transitionIn: 'fadeIn',
					    transitionOut: 'fadeOutRight',
					    transitionInMobile: 'fadeIn',
					    transitionOutMobile: 'fadeOutRight', 
		    			timeout: 3000,
					});
					$(".group-case-card").html(result).fadeIn("slow");
			        $("#data .card").each(function(i){
			            setTimeout(function(){
			                $("#data .card").eq(i).addClass("scroll");
			            }, 300 * (i+1));
			        });
				}
		    },
		    error : function(xhr){
		    	console.log("Error : ", xhr)
		    }
		});
		resetData();
	}
	
//	function getSearchResponse(){
//		$.ajax({
//			url : "/searchResponse?countryName="+$("#input-countryName").val(),
//			success : function(data){
//				console.log(data)
//			},
//			error : function(xhr){
//				console.log("Error : ", xhr);
//			}
//		});
//	}
	
	function resetData(){
		$("#input-countryName").val("");
	}
});

$(window).scroll(function(){
    var wScroll = $(this).scrollTop();

    if (wScroll > 100) {
        $(".navbar").addClass("scroll");
    }

    if (wScroll < 100) {
        $(".navbar").removeClass("scroll");
    }

    if (wScroll > $("#data").offset().top - 250) {
        $("#data .content-title").addClass("scroll");
        $("#data .card").each(function(i){
            setTimeout(function(){
                $("#data .card").eq(i).addClass("scroll");
            }, 300 * (i+1));
        });
    }

    if (wScroll > $("#data").offset().top + 150) {
        $("#data .more-button").addClass("scroll");
    }

    if (wScroll > $("#indonesia").offset().top - 250) {
        $("#indonesia .content-title").addClass("scroll");
        setTimeout(function(){
            $("#indonesia .indonesia-bigger-card").addClass("scroll");
        }, 300);

        $("#indonesia .indonesia-card").each(function(i){
            setTimeout(function(){
                $("#indonesia .indonesia-card").eq(i).addClass("scroll");
            }, 300 * (i+2));
        });
    }
    
    if (wScroll > $("#indonesia").offset().top + 150) {
        $("#indonesia .more-button").addClass("scroll");
    }

    if (wScroll > $("#protocols").offset().top - 250) {
        $("#protocols .content-title").addClass("scroll");

        $("#protocols .card").each(function(i){
            setTimeout(function(){
                $("#protocols .card").eq(i).addClass("scroll");
            }, 300 * (i+1));
        });
    }
    
    if (wScroll > $("#information").offset().top - 250) {
        $("#information .content-title").addClass("scroll");

        $("#information .card").each(function(i){
            setTimeout(function(){
                $("#information .card").eq(i).addClass("scroll");
            }, 300 * (i+1));
        });
        
        setTimeout(function(){
        	$(".slick-prev:before").addClass("scroll");
        	$(".slick-next:before").addClass("scroll");
        }, 900);
    }
});