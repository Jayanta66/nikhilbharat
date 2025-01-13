function changeVid(clicked_id)
                    {

                        var newVid = "javatechie.mp4";
                        
                        if(clicked_id === 'vid1'){
                            newVid = 'videos/javatechie.mp4';
                        }
                         if(clicked_id === 'vid2'){
                            newVid = "videos/javatechie.mp4";
                        }
                         if(clicked_id === 'vid3'){
                            newVid = "/videos/javatechie.mp4";
                        }
                         if(clicked_id === 'vid4'){
                            newVid = "/videos/javatechie.mp4";
                        }
                         if(clicked_id === 'vid5'){
                            newVid = " ";
                        }
                         if(clicked_id === 'vid6'){
                            newVid = " ";
                        }

                        document.getElementById('player1').src = newVid;
                    }
                    
/*                    
var $allVideos = $("iframe[src^='//player.vimeo.com'], iframe[src^='//www.youtube.com']"); */





