package com.capgemini.moviemanager

import android.content.ContentUris
import android.net.Uri

import android.provider.BaseColumns




class MovieContract {

    companion object {
        /**
         * The Content Authority is a name for the entire content provider, similar to the relationship
         * between a domain name and its website. A convenient string to use for content authority is
         * the package name for the app, since it is guaranteed to be unique on the device.
         */
        val CONTENT_AUTHORITY = "com.capgemini.moviedatabase"

        /**
         * The content authority is used to create the base of all URIs which apps will use to
         * contact this content provider.
         */
        val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

        /**
         * A list of possible paths that will be appended to the base URI for each of the different
         * tables.
         */
        val PATH_MOVIE = "movie"
        val PATH_GENRE = "genre"

    }
    /**
     * Create one class for each table that handles all information regarding the table schema and
     * the URIs related to it.
     */
    class GenreEntry : BaseColumns {

        companion object {
            const val _ID = BaseColumns._ID
            val CONTENT_URI: Uri =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE)
                    .build()
            val CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_GENRE
            val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_GENRE

            val TABLE_NAME = "genreTable"
            val COLUMN_NAME = "genreName"
            fun buildGenreUri(id: Long): Uri {
                return ContentUris.withAppendedId(CONTENT_URI, id)
            }
        }
    }

    class MovieEntry : BaseColumns {

        companion object {
            const val _ID = BaseColumns._ID
            // Content URI represents the base location for the table
            val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build()

            // These are special type prefixes that specify if a URI returns a list or a specific item
            val CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_MOVIE
            val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_MOVIE

            // Define the table schema
            val TABLE_NAME = "movieTable"
            val COLUMN_NAME = "movieName"
            val COLUMN_RELEASE_DATE = "movieReleaseDate"
            val COLUMN_GENRE = "movieGenre"

            // Define a function to build a URI to find a specific movie by it's identifier
            fun buildMovieUri(id: Long): Uri {

                return ContentUris.withAppendedId(CONTENT_URI, id)
            }
        }
    }

}




