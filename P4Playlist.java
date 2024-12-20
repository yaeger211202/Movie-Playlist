/*************************************************
 File: P4Playlist.java
 By: Harry Kakadiya
 Date: 05/07/2024
 Description: This class defines the Movie object with attributes for title and duration.
 Instances of this class represent individual movies that can be added to a
 playlist managed by the LList class. The Movie class provides methods to
 retrieve movie details such as title and duration.
 *************************************************/

import java.util.Scanner;

public class P4Playlist {
    private static LList<Movie> playlist = new LList<>();
    private static Movie[] movieDb = {
            new Movie("Get Out", 100),
            new Movie("Paprika", 115),
            new Movie("The Lost Boys", 85),
            new Movie("Blood Tea and Red String", 80),
            new Movie("Candyman", 105),
            new Movie("WALL-E", 120),
            new Movie("The Shining", 125),
            new Movie("Ghost in the Shell", 95),
            new Movie("Hellraiser", 90),
            new Movie("King Kong", 110)
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMovies();
            System.out.print("Enter position of movie in playlist to add (>=1, invalid position to exit): ");
            int position = scanner.nextInt();
            if (position < 1 || position > playlist.getLength() + 1) {
                break;
            }
            System.out.print("Enter ID of movie to add: ");
            int movieId = scanner.nextInt();
            if (movieId >= 0 && movieId < movieDb.length) {
                playlist.add(position, movieDb[movieId]);
                updateStartTimes();
            } else {
                System.out.println("Invalid movie ID.");
            }
            displayPlaylist();
        }

        while (true) {
            System.out.print("Enter position of movie in playlist to remove (>=1, invalid position to exit): ");
            int removePosition = scanner.nextInt();
            if (removePosition < 1 || removePosition > playlist.getLength()) {
                break;
            }
            playlist.remove(removePosition);
            updateStartTimes();
            displayPlaylist();
        }

        simulateWatchingMovies(scanner);
        scanner.close();
        System.out.println("All done!");
    }

    private static void displayMovies() {
        System.out.println("Movies in database:");
        for (int i = 0; i < movieDb.length; i++) {
            System.out.println(i + " " + movieDb[i].getTitle() + " " + movieDb[i].getDuration());
        }
    }

    private static void displayPlaylist() {
        System.out.println("Playlist:");
        for (int i = 1; i <= playlist.getLength(); i++) {
            Movie movie = playlist.getEntry(i);
            System.out.println(i + " " + movie + " starts at " + movie.getStartTime() + " mins");
        }
    }

    private static void updateStartTimes() {
        int currentTime = 0;
        for (int i = 1; i <= playlist.getLength(); i++) {
            Movie movie = playlist.getEntry(i);
            movie.setStartTime(currentTime);
            currentTime += movie.getDuration();
        }
    }

    private static void simulateWatchingMovies(Scanner scanner) {
        int currentTime = 0;
        while (!playlist.isEmpty()) {
            System.out.print("Enter next timestamp (in minutes, >= current time of " + currentTime + "): ");
            int nextTime = scanner.nextInt();
            if (nextTime < currentTime) {
                System.out.println("Time cannot move backwards. Please enter a correct time.");
                continue;
            }
            currentTime = nextTime;

            boolean moviePlaying = false;
            for (int i = 1; i <= playlist.getLength(); i++) {
                Movie movie = playlist.getEntry(i);
                if (currentTime >= movie.getStartTime() && currentTime < movie.getStartTime() + movie.getDuration()) {
                    System.out.println(movie.getTitle() + " is playing.");
                    moviePlaying = true;
                    break;
                }
            }

            if (!moviePlaying) {
                System.out.println("No movie is currently playing.");
            }

            // Remove movies that have finished
            while (!playlist.isEmpty() && playlist.getEntry(1).getStartTime() + playlist.getEntry(1).getDuration() <= currentTime) {
                System.out.println(playlist.getEntry(1).getTitle() + " finished playing.");
                playlist.remove(1);
            }
        }
    }
}
