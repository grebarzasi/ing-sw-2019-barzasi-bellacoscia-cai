# Prova Finale Ingegneria del Software 2019
## Gruppo AM14

- ###   10573032    Gregorio Barzasi ([@grebarzasi](https://github.com/grebarzasi))<br>gregorio.barzasi@mail.polimi.it
- ###   10560632    Carlo Bellacoscia ([@CarloBellacoscia](https://github.com/CarloBellacoscia))<br>carlo.bellacoscia@mail.polimi.it
- ###   10499668    Yuting Cai ([@theodoretsai](https://github.com/theodoretsai))<br>yuting.cai@mail.polimi.it

| Functionality | State |
|:-----------------------|:------------------------------------:|
| Basic rules | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Complete rules | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Socket | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| RMI | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| GUI | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| CLI | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Multiple games | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Persistence | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Domination or Towers modes | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Terminator | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |

<!--
[![RED](https://placehold.it/15/f03c15/f03c15)](#)
[![YELLOW](https://placehold.it/15/ffdd00/ffdd00)](#)
[![GREEN](https://placehold.it/15/44bb44/44bb44)](#)
-->


## Instructions for starting the game

## Running the App

### From IntelliJ

To start the server run MainServer located in the connection package. 
To start the client run MainView in the view package.

### With JAR

To be updated

## Getting Started

#### Starting the Server

To run the server locally leave all fields empty by just pressing enter, rmi and socket ports will be set to default (IP: 127.0.0.1, port: 1234).

Advanced mode enables customisation of timer setting and test mode. In test mode the KillShot track is initialised with two skulls to allow the game to end faster and the bot deals 11 Damage.

#### Starting the Client

Upon starting the game the user will be asked to select between a Graphical Interface and a Command Line Interface, the interface can be selected by entering a number (1 for CLI and 2 for GUI) or by entering the type of the desired interface (gui or cli, the selection is not case sensitive).

#### From CLI

The user will be first tasked to set up the connection, one type between socket and rmi can be selected, in case of entering an empty statement socket connection will be selected, then the player is asked to insert the server's IP address, in case of a local server leave the IP empty, after entering the IP the player is asked to select the port, leaving it empty will automatically select the default port, in case of rmi the server's default port is 1235 and will need to be entered manually.

The player will then be asked to enter a Username and a Character color, any username can be selected, the available colors are: Red, Blue, Yellow, Green, Grey. If the player enters a username or color that has already been taken the server will ask the player to change the login username or character.

After being accepted the player will be asked to decide the game preferences (in case of empty entry the default value will be selected):

###### Bot (Yes/No) default: no
###### Frenzy (Yes/No) default: no
###### Number of Skulls on the Killshot Track (5 - 8) default: 8
###### Map Size(1: Small - 4: Large) default: medium 1
###### Reselect Preferences: (Yes/No) default: no

After a countdown the game will be started.

