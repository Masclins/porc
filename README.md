# PORC

PORC is a project created to calculate the RTP of Video Poker games.
The aim is to be able to tell the RTP following the best strategy given a paytable.

## Installation

This project is programmed entirely in Clojure.
I recommend https://www.braveclojure.com/ and following their installation guidelines.

## Testing

To test, type:

    $ lein test-refresh

## Options

PORC will solve paytables based on the typical prizes, in the order of exclusion: Royal Flush, Straight Flush, 4 of a kind, Full House, Flush, Straight, 3 of a kind, Double Pair, and Pair (able to define which value is the minimum needed).

## License

Copyright © 2018 Albert Masclans
under MIT Copyright


