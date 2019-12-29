<div align="center"><h1>Dashboard - Epitech</h1>

##### by Maxence & Martin

## Projet

### Librairies

- [Java](https://fr.wikipedia.org/wiki/Java_\(technique\))
- [Spring boot](https://spring.io/projects/spring-boot)
- [HTML5](https://fr.wikipedia.org/wiki/Hypertext_Markup_Language)
- [CSS3](https://fr.wikipedia.org/wiki/Hypertext_Markup_Language)
- [Docker](https://www.docker.com)
- [PostgreSQL](https://www.postgresql.org)
- [Gradle](https://gradle.org)


### Comment compiler ?

```
$ sudo docker-compose build
```

### Comment lancer le programme ?

```
$ sudo docker-compose up
```

#### Dark mode 

- Dans les settings à droite (@creativetim)

## Services & widgets

### Youtube 

- Nombre d'abonnés d'une chaîne
- Dernière vidéo d'une chaîne

### Twitch

- Lives les plus regardés
- Jeux les plus regardés

### OpenWeather

- Température d'une ville

### Deezer

- Dernière musique d'un artiste

### Cinéma

- Top 3 des films d'un acteur
- Information d'un film

### Chuck Norris Facts

- Citations sur Chuck Norris

### Cocktail

- Top 10 des cocktails par alcool


### Actualités

- Actualités par Pays (exemple : Fr --> les gilets jaunes)

### Lyrics

- Parole d'une chanson 


## Comment fonctionne le projet ?

```
<input class="form-control" id="widget-count-movie" placeholder="Recherche">
```

- Interprétation et envoi de cette valeur vers le back-end

```
$.getJSON('https://api.themoviedb.org/3/search/person?api_key=48726af5faa4c886b10bab390ddd736a&query=' + value, function (data)
```

- Redirection vers la méthode permettant la requête API

```
@RequestMapping("/service/movie/movie")
    public String Movie(@RequestParam("value") String value) { return MovieService.Movie(value); 
}
```
- Execution de la requête vers l'API avec la valeur en paramètre

```
public String Movie(String id) {
	String url = "https://api.themoviedb.org/3/movie/"+ id +"?api_key=" + key;
	RestTemplate restTemplate = new RestTemplate();
	String response = restTemplate.getForObject(url, String.class);
	
	return response;
    }
```
- Parsing du résultat

```
let movie1 = data["results"][0]["known_for"][0]["title"];
```

- Affichage du résultat sur la page web
```
$("#movie").show();
$("#movie").html(html);
```
