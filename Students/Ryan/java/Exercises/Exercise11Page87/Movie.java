package Exercises.Exercise11Page87;

public class Movie {
    private String title;
    private String studio;
    private String rating;

    public Movie(String title, String studio, String rating)
    {
        this.title = title;
        this.studio = studio;
        this.rating = rating;
    }

    public Movie(String title, String studio)
    {
        this.title = title;
        this.studio = studio;
        this.rating = "PG";
    }

    public Movie[] getPG(Movie[] input)
    {
        Movie[] pgMovies = new Movie[input.length];
        for (int i = 0; i < input.length; i++)
        {
            if (input[i].rating == "PG")
            {
                pgMovies[i] = input[i];
            }
            else 
            {
                pgMovies[i] = null;
            }
        }
        return pgMovies;
    }

}
