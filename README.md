# MusicWiki

### NAME OF THE APP
MusicWiki

### ESTIMATED COMPLETION TIME
24 hours

### DESCRIPTION

MusicWiki contains information about different music genres,
the albums, artists and tracks listed under the genre.

### JUDGING CRITERIA

1. Clean and readable code
2. Following best practices and design patterns
3. Completion of the task
4. Test cases carry bonus points

### API

All of the required information for building this app is available in the API provided by last.fm.
You can get more information at https://www.last.fm/api

### REQUIRED API CALLS FOR MUSICWIKI

- tag.getTopTags 
- tag.getInfo
- tag.getTopAlbums
- tag.getTopArtists
- tag.getTopTracks
- album.getInfo
- album.getTopTags
- artist.getInfo
- artist.getTopTags
- artist.getTopAlbums
- artist.getTopTracks

### OPERATIONS

1. HOME SCREEN - displays initially 10 genres, can expand list to view all genres

- Display the list of genres available. The list will initially contain top 10 genres and when
  the user clicks on the expand button the entire list is shown in the same screen.

- Clicking on the genre it should take the user to a page which contains information
  regarding it.

2. GENRE DETAIL SCREEN - display genre information with albums, artists and tracks

- It should have a genre as the title, description of the genre. 
  In the page it should list top albums, top tracks and top artists as different sections.

- Each item listed under the album should have the cover image if available or a
  default image, the title and artist.

- Each item listed under the artists, should have the cover image, if available or a
  default image and the name.

- Each item listed under tracks should have the cover image if available or a
  default image, the title and artist name.

3. ALBUM DETAIL SCREEN - display album information and genre

- It should display the cover image, title and the artist information. It should
  also have the description and the genres. Clicking on the genre it should
  display the details about the genre, similar to the flow in the first screen.

4. ARTIST DETAIL SCREEN - display artist information, his top tracks, albums and genres

- It should display the image, title ,play count and followers, description, a
  list of top tracks and top albums and the genres. Clicking on the genre it
  should display the details about the genre, similar to the flow in the first screen.
    
- Clicking on the album should show its information.

### DEPENDENCIES USED

    //retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

    //gson converter
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    //coordinator layout
    implementation 'androidx.coordinatorlayout:coordinatorlayout:1.1.0'

    //flex box layout
    implementation 'com.google.android:flexbox:2.0.1'

    //recycler view
    implementation 'androidx.viewpager:viewpager:1.0.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'

    //shimmer effect
    implementation 'com.facebook.shimmer:shimmer:0.1.0@aar'

    //glide image loading
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
