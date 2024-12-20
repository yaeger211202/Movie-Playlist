/*************************************************
 File: Movie.java
 By: Harry Kakadiya
 Date: 05/07/2024
 Description: This class defines the Movie object with attributes for title and duration.
 Instances of this class represent individual movies that can be added to a
 playlist managed by the LList class. The Movie class provides methods to
 retrieve movie details such as title and duration.
 *************************************************/
public class Movie {
    private String title;
    private int duration;
    private int startTime;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.startTime = 0;  // Initial start time is zero, will be updated
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return title + " " + duration + "min, starts at " + startTime + "min";
    }
}
