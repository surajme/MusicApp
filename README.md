An assignment for GreedyGames.

Time spent: 2 days

**Required api calls:**
tag.getTopTags
tag.getInfo
tag.getTopAlbums
tag.getTopArtists
tag.getTopTracks
album.getInfo
album.getTopTags
artist.getInfo
artist.getTopTags
artist.getTopAlbums
artist.getTopTracks

Dependencies:
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

API:
All of the required information for building this app is available in the API provided by last.fm. You can get more information at https://www.last.fm/api

