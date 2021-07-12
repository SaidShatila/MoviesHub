# MoviesHub
# This sample was created to showcase my skills and the latest learnings in the Android Framework.
# To be able to compile this project, you need to add the following in your global gradle properties file:
# API_KEY
# API_KEY_VALUE
# BASE_URL
# POSTER_BASE_URL
For this client app I used "https://api.themoviedb.org/3/" to fetch the movies list.
For this client app I used "https://image.tmdb.org/t/p/w342" to fetch the movies list.
Used Glide for a more efficient image loading.
Used LiveData and ViewModels as well.
Sealed classes and states where added.
Addition of DateSeriliazer Class to manage the publish date retrieval of the Movie.
Addition of UnsafeokHttpClient to solve the SSl Certificate issue.
Kotlin-coroutines were used for blocking operations(fetching Movies list from the server).

# Architecture:
I am using the MVVM architecture and some state machine concept on top of it. Every screen has a view, a model, and a ViewModel. The ViewModel contains a state that represents the properties of the View. This state will be emitted using LiveData to the observer(view).

The ViewModel state is represented using a simple kotlin data class with different fields.

I also use sealed classes to model some repetitive behaviors. Like, when fetching data in an asynchronous fashion, the usual states are Loading, Failed(with the failure), or Success(with the actual data).

Repository is the single source of truth that is used to fetch data(either from the network or from the local storage).

# Future Enhancements:
In addition I will add Anime Tv Series and even include a search bar, improve the UI Design of the app maybe even add some features like bookmark this movie.
Also I will try to add the link of the movie so you can download it.

# Video
Here is a small video demonstrating this super mini app.

https://user-images.githubusercontent.com/57522137/125276103-7cf3ba00-e318-11eb-9ca9-512ba8d80087.mp4

