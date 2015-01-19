# matching-artists

Problem: 
	The attached text file "Artist_lists_small.txt" contains the favorite musical artists of 1000 users from LastFM. Each line is a list of up to 50 artists, formatted as follows:

	Radiohead,Pulp,Morrissey,Delays,Stereophonics,Blur,Suede,Sleeper,The La's,Super Furry Animals\n
	Band of Horses,Iggy Pop,The Velvet Underground,Radiohead,The Decemberists,Morrissey,Television\n
	etc.

	Write a program that, using this file as input, produces an output file containing a list of pairs of artists which appear TOGETHER in at least fifty different lists. For example, in the above sample, Radiohead and Morrissey appear together twice, but every other pair appears only once.

Run Instructions
1. Ensure "Artists_lists_small.txt" is saved in the project directory.

2. For command line: 
	$ javac MatchingArtists.java
	$ java MatchingArtists

	Then check project directory for output file, "ArtistPairs.txt"

3. For Eclipse:
	- Create new project directory and create new main class "MatchingArtists.java"
	- Add "Artist_lists_small.txt" into command line arguments, under Run Configurations.
	- Eclipse will enforce saving non MacRoman text files as UTF-8 encoding.
	- Save and run file. Open finder to check output file "ArtistPairs.txt"
