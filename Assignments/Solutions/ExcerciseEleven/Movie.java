public class Movie {
    private String title;
    private String studio;
    private String rating;

    //Answer A
    public Movie(String t, String s, String r) {
        title = t;
        studio = s;
        rating = r;
    }

    //Answer B
    //This constructor will be used if a Movie object is created but no rating is provided in the parameters
    //Such as:
    // Movie mov = new Movie("Spider-Man", "Sony");
    public Movie (String t, String s) {
        title = t;
        studio = s;
        rating = "PG";
    }

    //Answer C
    public static Movie[] getPG(Movie[] mov) {
        Movie[] pgMov = new Movie[mov.length];
        int newArrayIndex = 0;
        for (int i = 0; i < mov.length; i++) {
            if (mov[i].rating.equals("PG")) {
                pgMov[newArrayIndex] = mov[i];
                newArrayIndex++;
            }
        }
        return pgMov;
    }
}
