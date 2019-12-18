window.onload = function() {
    $('#deezer').hide();
    $('#movie').hide();
    $('#ChuckNorris').hide();
    $('#actor').hide();
    $('#weather').hide();
    $('#games-div').hide();
    $('#streams-div').hide();
    $('#subscribers').hide();
    $('#lastvideo').hide();
    $('#cocktail').hide();
};

function hideYoutubeModal() {
    $('#YoutubeModal').modal('hide');
}

function hideTwitchModal() {
    $('#TwitchModal').modal('hide');
}

function hideWeatherModal() {
    $('#WeatherModal').modal('hide');
}

function hideDeezerModal() {
    $('#DeezerModal').modal('hide');
}

function hideMovieModal() {
    $('#MovieModal').modal('hide');
}

function hideChuckNorrisModal() {
    $('#ChuckNorrisModal').modal('hide');
}

function hideYoutubeDeleteModal() {
    $('#YoutubeDeleteModal').modal('hide');
}

function hideChuckNorrisModal() {
    $('#ChuckNorrisDeleteModal').modal('hide');
}

function hideCocktailModal() {
    $('#CocktailDeleteModal').modal('hide');
}

function hideMovieDeleteModal() {
    $('#MovieDeleteModal').modal('hide');
}

function hideTwitchDeleteModal() {
    $('#TwitchDeleteModal').modal('hide');
}

function hideYoutubeWidget() {
    let value = $("#widget-selector-youtube-delete").val();

    if (value == "subscribers-delete") {
        $('#subscribers').hide();
    } else {
        $('#lastvideo').hide();
    }
}

function hideTwitchWidget() {
    let value = $("#widget-selector-twitch-delete").val();

    if (value == "streams-delete") {
        $('#streams-div').hide();
    } else {
        $('#games-div').hide();
    }
}

function hideMovieWidget() {
    let value = $("#widget-selector-movie-delete").val();
    console.debug(value);

    if (value == "movie-delete") {
        $('#movie').hide();
    } else {
        $('#actor').hide();
    }
}


function hideWeatherWidget() {
    $('#weather').hide();
}

function hideChuckNorrisWidget() {
    $('#ChuckNorris').hide();
}

function hideCocktailWidget() {
    $('#cocktail').hide();
}


function hideDeezerWidget() {
    $('#deezer').hide();
}
