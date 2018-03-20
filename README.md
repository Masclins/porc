# porc

PORC is a created to calculate the RTP of Video Poker games.
The aim is to be able to tell the RTP following the best strategy, given a paytable.

## Installation

This project is programmed entirely in Clojure.
I recommend https://www.braveclojure.com/ and following their installation guidelines.

## Usage

FIXME: explanation

    $ java -jar porc-0.1.0-standalone.jar [args]

## Options

The program will solve paytables based on the typical prizes, in that orden of exclusion: Royal Flush, Straight Flush, 4 of a kind, Full house, Flush, Straight, 3 of a kind, Double Pair and Pair (able to define which value is the minimum needed).

## License

Copyright © 2018 Albert Masclans (under MIT Copyright)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
