import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private ArrayList<String> actors = new ArrayList<>(){};
    private ArrayList<String> allGenres = new ArrayList<>(){};
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a acor search name: ");
        String searchName = scanner.nextLine();

        // prevent case sensitivity
        searchName = searchName.toLowerCase();

        // arraylist to hold search results
        ArrayList<String> results = new ArrayList<String>();

        // search through ALL actors in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String allActors = actors.get(i);
            allActors = allActors.toLowerCase();

            if (allActors.indexOf(searchName) != -1)
            {
                //add the Movie objest to the results list
                results.add(actors.get(i));
            }
        }

        // sort the results by name
        results.sort(String.CASE_INSENSITIVE_ORDER);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which acter's movie's woudl you leik to ?see");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Movie> searchResults = new ArrayList<Movie>();
        String selectedActor = results.get(choice - 1).toLowerCase();
        for (int i = 0; i < movies.size(); i++)
        {
            String movieActors = movies.get(i).getCast();
            movieActors = movieActors.toLowerCase();

            if (movieActors.indexOf(selectedActor) != -1)
            {
                //add the Movie objest to the results list
                searchResults.add(movies.get(i));
            }
        }

        sortResults(searchResults);
        // now, display them all to the user
        for (int i = 0; i < searchResults.size(); i++)
        {
            String title = searchResults.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie2 = searchResults.get(choice - 1);

        displayMovieInfo(selectedMovie2);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        {
            System.out.print("Enter a keword search term: ");
            String searchWord = scanner.nextLine();

            // prevent case sensitivity
            searchWord = searchWord.toLowerCase();

            // arraylist to hold search results
            ArrayList<Movie> results = new ArrayList<Movie>();

            // search through ALL movies in collection
            for (int i = 0; i < movies.size(); i++)
            {
                String movieKeywords = movies.get(i).getKeywords();
                movieKeywords = movieKeywords.toLowerCase();

                if (movieKeywords.indexOf(searchWord) != -1)
                {
                    //add the Movie objest to the results list
                    results.add(movies.get(i));
                }
            }

            // sort the results by title
            sortResults(results);

            // now, display them all to the user
            for (int i = 0; i < results.size(); i++)
            {
                String title = results.get(i).getTitle();

                // this will print index 0 as choice 1 in the results list; better for user!
                int choiceNum = i + 1;

                System.out.println("" + choiceNum + ". " + title);
            }

            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Movie selectedMovie = results.get(choice - 1);

            displayMovieInfo(selectedMovie);

            System.out.println("\n ** Press Enter to Return to Main Menu **");
            scanner.nextLine();
        }
    }


    private void listGenres() {
        for (int i = 0; i < allGenres.size(); i++)
        {
            String genre = allGenres.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + genre);
        }

        System.out.println("Which genre of movies would you like too se?");
        System.out.print("Enter genre: ");

        String selectedGenre = scanner.nextLine().toLowerCase();
        ArrayList<Movie> results = new ArrayList<Movie>();
        System.out.println(movies.size());

        for (int i = 0; i < movies.size(); i++)
        {
            String movieGenres = movies.get(i).getGenres();
            movieGenres = movieGenres.toLowerCase();

            if (movieGenres.indexOf(selectedGenre.trim()) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        System.out.println(results.size());
        for (int a = 0; a < results.size(); a++)
        {
            String title = results.get(a).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = a + 1;

            System.out.println("" + choiceNum + ". " + title);
        }


        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();


    }

    private void listHighestRated()
    {
    double[] ratings = new double[movies.size()];

    // Get a list of movies based on how many movies they are better than
    for(int i = 0; i < movies.size(); i++)
    {
        int movieRating = 0;
        for (int i2 = 0; i2 < movies.size(); i2++)
        {
            if(movies.get(i).getUserRating() > movies.get(i2).getUserRating())
            {
                movieRating++;
            }
        }
        ratings[i] = movieRating;
    }

    int choiceNum = 0;
    double biggerest = 11;
    for(int i3 = 0; i3 < 50; i3++)
        {
            double biggest = 0;
            int index = 0;
            int indexOfBestMovie = 0;
            for (Double rating : ratings) {
                index++;
                if ((rating > biggest) && (rating < biggerest))
                {
                    biggest = rating;
                    indexOfBestMovie = index;
                    System.out.println(index + " is the index, " + biggest + " is the biuggest, ");
                }
            }
            biggerest = biggest;
            choiceNum++;
            String title = movies.get(indexOfBestMovie).getTitle();
            System.out.println("" + choiceNum + ". " + title);
        }

    }

    private void listHighestRevenue()
    {

    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);

                String[] actorsInMovie = nextMovie.getCast().split("\\|");
                for(String actor: actorsInMovie)
                {
                    if (!(actors.contains(actor)))
                    {
                        actors.add(actor);
                    }
                }

                String[] genresInMovie = nextMovie.getGenres().split("\\|");
                for(String movieGenre : genresInMovie)
                {
                    if (!(allGenres.contains(movieGenre)))
                    {
                        allGenres.add(movieGenre);
                    }
                }
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}