# COM1008 - Software Engineering


[Lines in square brackets are placeholders or instructions; you should replace them with the appropriate information. Please ensure no placeholder text is left in the final version (including this line, and the Lines below).]

[As this is a markdown file, you should edit it in a IDE or text editor that supports markdown - don't use Microsoft Word or other word processors. For more information on markdown, see: https://www.markdownguide.org/]

[** Before you submit, preview the rendered version of the file to ensure it is formatted correctly. Gitlab will render the markdown file as HTML, so please check the output is correct. Also, you can use an IDE or text editor that supports markdown to preview the file (e.g., VSCode). ** ] 

# Assignment 1 - Report

**Student Name: Adarsh Ram Sivakumar**

**Student ID: [6867838]**

## 1.1 Proto Personas

### Persona 1

 ## Andrew, the Casual Viewer

**Age**: 29

**Occupation**: Marketing Executive

**Tech Level**: Intermediate

**Goals**: Wants to find good movies to watch on weekends often with his friends.
Prefers highly rated and popular films.
Sometimes wants to search movies by actors they like.

**Frustrations**:
Overwhelmed by too many choices.
Annoyed by apps that don’t show basic movie info quickly.

**Behaviors**: Prefers uncluttered interfaces, fast-loading apps

### Persona 2

## Priya, the Film Student

**Age**: 22

**Occupation**: University Student (Film Studies)

**Tech Level: Advanced**

**Goals**: Research actors’ filmographies and movie trends and analyse career trajectories for assignments.
Identify patterns in movie ratings, popularity, and release trends
Cross-reference movie casts and crew members
Use reliable metadata (like birth years, vote counts, and ratings)

**Frustrations**: Pain Points: Hates when data lacks context (e.g., no birth years for actors or incomplete cast lists).

**Behaviors**: Searches by directors, ratings, and year; often dives deep into older or lesser-known films.


## 1.2 Scenario

#### Andrews Movie Night
Alex is planning a movie night with friends on a Friday evening. He wants to find a movie that everyone will enjoy—preferably something popular and highly rated. He opens the FlickFinder app on his phone and navigates to the search page.

He decides to look for movies released in 2022. Using the built-in filter options, he sets the year to 2022 and chooses to sort the results by rating in descending order. To avoid watching obscure indie films that may not appeal to everyone, he also enables a filter to show only movies with more than 1,000 votes.

The app quickly returns a list of the top-rated movies from 2022 that meet his criteria. Alex scrolls through the results, which are limited to a concise list of 20 options to keep it manageable. He taps on one movie to view more details such as its synopsis, duration, genre, and rating. While reviewing the movie, he also checks out the cast list using the “stars” feature of the app, to see if it includes actors he and his friends like.

Impressed by the cast and intrigued by the plot, he adds the movie to his watchlist. Before finalizing, he explores one of the lead actors’ profiles to see what other movies they’ve starred in, using the linked person page. He discovers another interesting film starring the same actor and adds that to the watchlist too, just in case his friends want options.

Thanks to FlickFinder’s filters and movie data, Alex feels confident that movie night will be a success.


## 1.3 User Stories

### Find top-rated movies from a specific year - 1

**Find top-rated movies from a specific year**

As a casual movie watcher, I want to view a list of movies sorted by rating for a specific year so that I can quickly find the most popular and critically acclaimed films to watch with friends, without having to scroll through hundreds of irrelevant results.

### Jordan – The Film Enthusiast Blogger - 2

**Search an actor’s full filmography**

As a film enthusiast, I want to search for all movies that a specific actor has appeared in so that I can explore their body of work, find hidden gems, and reference these films in my blog content.

### Lunjin – The Casual Movie Watcher - 3

**Limit the number of results shown**

As a user, I want to limit the number of search results returned so that I can quickly scan a manageable list without feeling overwhelmed or having to scroll endlessly.

### Gerhalt – The Film Student - 4

**Analyze casting and director patterns**

As a film student, I want to see detailed information about movies, cast members, and directors—including birth years and filmographies—so that I can support my academic research with accurate and contextual data.

### Lyroy - The Producer - 5

**Find if stars have been in similar movies to his own.**

As a film producer, I want to find out if the stars in my current movie project have worked together in similar films before so that I can assess their on-screen chemistry and make informed casting or marketing decisions.

### Nina: The Indie Film Curator - 6

**Curate high-quality underrated films**

As an indie film curator, I want to filter movies by rating and sort them by vote count (ascending) so that I can discover high-quality but under-the-radar films to feature in my film festival.

## 2 Critical Analysis and Reflection

### 2.1 Reflection

This project provided a valuable opportunity to apply back-end development skills in a real-world context, particularly through the use of RESTful APIs and structured database queries. One aspect that went well was the clear separation of responsibilities using the MVC pattern, which made it easier to implement and test individual components. Additionally, integrating parameterised queries to support filtering by year, rating, and vote count enhanced both performance and usability.

However, there were challenges. Navigating the structure of the SQLite database and ensuring accurate SQL queries required careful debugging and time investment. In future iterations, more detailed database documentation or schema visualization would improve development speed and reduce initial friction. Not only that but many of the tests could have been more through and functional. There were some issues with some of the tests and in future iterations this would be improved. Also the code was not commented very well by me so this is also something to be improved in the future. Overall, the project strengthened both technical and analytical skills and highlighted areas for more robust planning and tooling in future developments.

 

### 2.2 Professional Aspects

The FlickFinder project highlights several key professional aspects of software engineering. Code maintainability is supported by the use of the MVC architecture, allowing clear separation of concerns and making the system easier to extend and debug. Adhering to consistent naming conventions, modular methods, and readable code structure improves collaboration and long-term support. From a system architecture perspective, the use of RESTful APIs and SQLite ensures lightweight deployment while supporting scalability through clean endpoints and filtering capabilities. Legal and ethical considerations are especially important when using real-world datasets such as IMDB’s — the application must ensure proper licensing, data sourcing compliance, and user privacy. Additionally, ethical design should avoid promoting biased content or misleading data. Sustainability is promoted by leveraging efficient database queries, limiting unnecessary data retrieval (e.g., using limits and filters), and considering low-resource environments where mobile users may rely on constrained connectivity. Collectively, these practices ensure that FlickFinder is built to be responsible, efficient, and adaptable to future needs.

## 3. References

[use a know referencing style, for example APA, Harvard, etc.]
