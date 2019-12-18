function youtube() {
    let value = $("#widget-name-youtube").val();
    let selected = $("#widget-selector-youtube").val();

    if (selected == "subscribers") {
        $.getJSON('http://localhost:8080/service/youtube/subscribers?value=' + value, function (data) {
            let subscribers = data["items"]["0"]["statistics"]["subscriberCount"];
            let html = "<div class=\"col-xl-12\">\n" +
                "                 <div class=\"card shadow\">\n" +
                "                    <div class=\"card-header border-0\">\n" +
                "                        <div class=\"row align-items-center\">\n" +
                "                            <div class=\"icon icon-shape bg-danger text-white rounded-circle shadow\">\n" +
                "                                <i class=\"fab fa-youtube\"></i>\n" +
                "                            </div>\n" +
                "                            <div class=\"col\">\n" +
                "                                <h3 class=\"mb-0\">Nombre d'abonnés</h3>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"table-responsive\">\n" +
                "                        <!-- Projects table -->\n" +
                "                        <table class=\"table align-items-center table-flush\">\n" +
                "                            <thead class=\"thead-black\">\n" +
                "                            <tr>\n" +
                "                                <th scope=\"col\">Chaîne</th>\n" +
                "                                <th scope=\"col\">Abonnés</th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <th>\n" +
                "                                    <span>" + value + "</span>\n" +
                "                                </th>\n" +
                "                                <th>\n" +
                "                                    <span>" + subscribers + " abonnés" + "</span>\n" +
                "                                </th>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
            $("#subscribers").show();
            $("#subscribers").html(html);
        });
    } else {
        $.getJSON('https://www.googleapis.com/youtube/v3/channels?part=contentDetails&forUsername=' + value + '&key=AIzaSyDNpcyII1P4k7-Dz80GNx-UcNrUh7-TmgY', function (data) {
            var userid = data["items"]["0"]["contentDetails"]["relatedPlaylists"]["uploads"];
            $.getJSON('http://localhost:8080/service/youtube/lastvideo?userid=' + userid, function (data) {
                let lastvideo = data["items"]["0"]["snippet"]["title"];
                let html = "<div class=\"col-xl-12\">\n" +
                    "                <div class=\"card shadow\">\n" +
                    "                    <div class=\"card-header border-0\">\n" +
                    "                        <div class=\"row align-items-center\">\n" +
                    "                            <div class=\"icon icon-shape bg-danger text-white rounded-circle shadow\">\n" +
                    "                                <i class=\"fab fa-youtube\"></i>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col\">\n" +
                    "                                <h3 class=\"mb-0\">Dernière vidéo</h3>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"table-responsive\">\n" +
                    "                        <!-- Projects table -->\n" +
                    "                        <table class=\"table align-items-center table-flush\">\n" +
                    "                            <thead class=\"thead-black\">\n" +
                    "                            <tr>\n" +
                    "                                <th scope=\"col\">Chaîne</th>\n" +
                    "                                <th scope=\"col\">Titre</th>\n" +
                    "                            </tr>\n" +
                    "                            </thead>\n" +
                    "                            <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <th>\n" +
                    "                                    <span>" + value + "</span>\n" +
                    "                                </th>\n" +
                    "                                <th>\n" +
                    "                                    <span>" + lastvideo + "</span>\n" +
                    "                                </th>\n" +
                    "                            </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>";
                $("#lastvideo").show();
                $("#lastvideo").html(html);
            });
        });
    }
}

function twitch() {
    let value = $("#widget-count-twitch").val();
    let selected = $("#widget-selector-twitch").val();

    if (selected == "streams") {
        $.getJSON('http://localhost:8080/service/twitch/streams?value=' + parseInt(value), function (data) {
            let htmldiv = "<div class=\"col-xl-12\">\n" +
                "                <div class=\"card shadow\">\n" +
                "                    <div class=\"card-header border-0\">\n" +
                "                        <div class=\"row align-items-center\">\n" +
                "                            <div class=\"icon icon-shape bg-warning text-white rounded-circle shadow\">\n" +
                "                                <i class=\"fab fa-twitch\"></i>\n" +
                "                            </div>\n" +
                "                            <div class=\"col\">\n" +
                "                                <h3 class=\"mb-0\">Les lives les plus regardés</h3>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"table-responsive\">\n" +
                "                        <!-- Projects table -->\n" +
                "                        <table class=\"table align-items-center table-flush\">\n" +
                "                            <thead class=\"thead-black\">\n" +
                "                            <tr>\n" +
                "                                <th scope=\"col\">Nom du streamer</th>\n" +
                "                                <th scope=\"col\">Nombre de spectateurs</th>\n" +
                "                                <th scope=\"col\">Titre du stream</th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                            <tbody id=\"streams\"></tbody>\n" +
                "                        </table>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
            $("#streams-div").show();
            $("#streams-div").html(htmldiv);
            $("#streams").html('');
            $.each(data["data"], function (data, value) {
                let username = value["user_name"];
                let viewercount = value["viewer_count"];
                let title = value["title"];
                let html = "<tr>\n" +
                    "                  <th scope=\"row\">" + username + "</th>\n" +
                    "                  <td>" + viewercount + "</td>\n" +
                    "                  <td>\n" +
                    "                    <div class=\"d-flex align-items-center\">\n" +
                    "                      <span class=\"mr-2\">" + title + "\n" +
                    "                    </div>\n" +
                    "                  </td>\n" +
                    "                </tr>";
                $("#streams").show();
                $("#streams").append(html);
            });
        });
    } else {
        $.getJSON('http://localhost:8080/service/twitch/games?value=' + parseInt(value), function (data) {
            let htmldiv = "<div class=\"col-xl-12\">\n" +
                "                <div class=\"card shadow\">\n" +
                "                    <div class=\"card-header border-0\">\n" +
                "                        <div class=\"row align-items-center\">\n" +
                "                            <div class=\"icon icon-shape bg-warning text-white rounded-circle shadow\">\n" +
                "                                <i class=\"fab fa-twitch\"></i>\n" +
                "                            </div>\n" +
                "                            <div class=\"col\">\n" +
                "                                <h3 class=\"mb-0\">Les jeux les plus regardés</h3>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"table-responsive\">\n" +
                "                        <!-- Projects table -->\n" +
                "                        <table class=\"table align-items-center table-flush\">\n" +
                "                            <thead class=\"thead-black\">\n" +
                "                            <tr>\n" +
                "                                <th scope=\"col\">Nom du jeu</th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                            <tbody id=\"games\"></tbody>\n" +
                "                        </table>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
            $("#games-div").show();
            $("#games-div").html(htmldiv);
            $("#games").html('');
            $.each(data["data"], function (data, value) {
                let game = value["name"];
                let html = "<tr>\n" +
                    "                  <th scope=\"row\">" + game + "</th>\n" +
                    "                  </tr>";
                $("#games").show();
                $("#games").append(html);
            });
        });
    }
}

function weather() {
    let value = $("#widget-name-weather").val();

    $.getJSON('http://localhost:8080/service/weather/temperature?value=' + value, function (data) {
        let temperature = data["main"]["temp"];
        let weather = data["weather"]["description"];
        temperature = temperature - 273.15;
        temperature = Math.floor(temperature);

        let html = "<div class=\"col-xl-12\">\n" +
            "                <div class=\"card shadow\">\n" +
            "                    <div class=\"card-header border-0\">\n" +
            "                        <div class=\"row align-items-center\">\n" +
            "                            <div class=\"icon icon-shape bg-yellow text-white rounded-circle shadow\">\n" +
            "                                <i class=\"fas fa-cloud\"></i>\n" +
            "                            </div>\n" +
            "                            <div class=\"col\">\n" +
            "                                <h3 class=\"mb-0\">Température</h3>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"table-responsive\">\n" +
            "                        <!-- Projects table -->\n" +
            "                        <table class=\"table align-items-center table-flush\">\n" +
            "                            <thead class=\"thead-black\">\n" +
            "                            <tr>\n" +
            "                                <th scope=\"col\">Ville</th>\n" +
            "                                <th scope=\"col\">Degrès</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>\n" +
            "                            <tr>\n" +
            "                                <th>\n" +
            "                                    <span>" + value + "</span>\n" +
            "                                </th>\n" +
            "                                <th>\n" +
            "                                    <span>" + temperature + "°C" + "</span>\n" +
            "                                </th>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "                        </table>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>";
        $("#weather").show();
        $("#weather").html(html);
    });
}

function deezer() {
    let value = $("#widget-name-deezer").val();

    $.getJSON('http://localhost:8080/service/deezer/artist?value=' + value, function (data) {
        let song = data["data"]["0"]["title"];

        let html = "<div class=\"col-xl-12\">\n" +
            "                <div class=\"card shadow\">\n" +
            "                    <div class=\"card-header border-0\">\n" +
            "                        <div class=\"row align-items-center\">\n" +
            "                            <div class=\"icon icon-shape bg-cyan text-white rounded-circle shadow\">\n" +
            "                                <i class=\"fas fa-music\"></i>\n" +
            "                            </div>\n" +
            "                            <div class=\"col\">\n" +
            "                                <h3 class=\"mb-0\">Dernière musique</h3>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"table-responsive\">\n" +
            "                        <!-- Projects table -->\n" +
            "                        <table class=\"table align-items-center table-flush\">\n" +
            "                            <thead class=\"thead-black\">\n" +
            "                            <tr>\n" +
            "                                <th scope=\"col\">Artiste</th>\n" +
            "                                <th scope=\"col\">Titre du son</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>\n" +
            "                            <tr>\n" +
            "                                <th>\n" +
            "                                    <span>" + value + "</span>\n" +
            "                                </th>\n" +
            "                                <th>\n" +
            "                                    <span>" + song + "</span>\n" +
            "                                </th>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "                        </table>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>";
        $("#deezer").show();
        $("#deezer").html(html);
    });
}

function movie() {
    let value = $("#widget-count-movie").val();
    let selected = $("#widget-selector-movie").val();

    if (selected == "movie") {
        $.getJSON('https://api.themoviedb.org/3/movie/'+ value + '?api_key=48726af5faa4c886b10bab390ddd736a', function (data) {
            let title = data["title"];

            let html = "<div class=\"col-xl-12\">\n" +
                "                <div class=\"card shadow\">\n" +
                "                    <div class=\"card-header border-0\">\n" +
                "                        <div class=\"row align-items-center\">\n" +
                "                            <div class=\"icon icon-shape bg-cyan text-white rounded-circle shadow\">\n" +
                "                                <i class=\"fas fa-movie\"></i>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"table-responsive\">\n" +
                "                        <!-- Projects table -->\n" +
                "                        <table class=\"table align-items-center table-flush\">\n" +
                "                            <thead class=\"thead-black\">\n" +
                "                            <tr>\n" +
                "                                <th scope=\"col\">Titre du film</th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <th>\n" +
                "                                    <span>" + title + "</span>\n" +
                "                                </th>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
            $("#movie").show();
            $("#movie").html(html);
        });
    } else {

        $.getJSON('https://api.themoviedb.org/3/search/person?api_key=48726af5faa4c886b10bab390ddd736a&query=' + value, function (data) {
            let movie1 = data["results"][0]["known_for"][0]["title"];

            let html = "<div class=\"col-xl-12\">\n" +
                "                <div class=\"card shadow\">\n" +
                "                    <div class=\"card-header border-0\">\n" +
                "                        <div class=\"row align-items-center\">\n" +
                "                            <div class=\"icon icon-shape bg-cyan text-white rounded-circle shadow\">\n" +
                "                                <i class=\"fas fa-movie\"></i>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"table-responsive\">\n" +
                "                        <!-- Projects table -->\n" +
                "                        <table class=\"table align-items-center table-flush\">\n" +
                "                            <thead class=\"thead-black\">\n" +
                "                            <tr>\n" +
                "                                <th scope=\"col\">Titre du film</th>\n" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <th>\n" +
                "                                    <span>" + movie1 + "</span>\n" +
                "                                </th>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
            $("#actor").show();
            $("#actor").html(html);
        });
    }
}


