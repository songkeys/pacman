# <img src="https://i.loli.net/2018/12/14/5c13bf0563d18.gif" alt="pacman logo" width="50"/> Pacman

[![](https://img.shields.io/github/license/Songkeys/pacman.svg)](https://github.com/Songkeys/pacman) [![](https://img.shields.io/badge/GitHub-pacman-blue.svg)](https://github.com/Songkeys/pacman) [![](https://img.shields.io/badge/GitLab-pacman-orange.svg)](https://projects.cs.nott.ac.uk/psysz4/pacman)

[English](README.md) | [简体中文](README.zh-cn.md)

> This is a pacman game project written in JavaFX for *[G52SWM CW2](https://projects.cs.nott.ac.uk/psysz4/swm)*, using some design patterns like MVC, Singleton and Factory, etc.

## Content

- [Screenshots](#screenshots)
- [Prerequisite](#prerequisite)
- [Compiling & Running](#compiling-running)
- [Source File Structure](#source-file-structure)
- [Creating New Levels](#creating-new-levels)
- [File Storage](#file-storage)
- [Design Parterns](#design-parterns)
- [About G52SWM CW2](#about-g52swm-cw2)
- [Credits](#credits)

## Screenshots

![screenshots.jpg](https://i.loli.net/2018/12/29/5c266b2ff35e1.jpg)

## Prerequisite

- Java 10

> Compatible with:
>
> - Java 11
> - Maven
>
> *(Since [JavaFX 11](https://openjfx.io/index.html) is dropped from Java 11, you would need [Maven](https://maven.apache.org/) installed to get the required sources if you use Java 11.)*

## Compiling & Running

You can launch the app either by running that `pacman.Main.main()` method inside your IDE, or type `mvn clean compile package exec:java` on the command line if you have maven installed.

For example, in IntelliJ, Click <kbd>Run</kbd>  -> <kbd>Run 'Main()'</kbd>.

## Source File Structure

```
src
└── main
    ├── java
    |   └── pacman
    |       ├── constant
    |       ├── controller
    |       ├── model
    |       ├── util
    |       └── Main.java
    └── resources
        └── pacman
            ├── css
            ├── image
            ├── map
            ├── music
            └── view
```

## Creating New Levels

A new level can be easily added by creating a new ASCII `.txt` file under `/src/main/resources/pacman/map`.

### Step 1: Creating the ASCII file

Here is an example file `example.txt` defining the initial state of a level:

```
/* This is a comment line. */
@PACMAN_PADDING_RATE 0.1
@PACMAN_STEP_RATE 0.15
@GHOST_PADDING_RATE 0.15
@GHOST_STEP_RATE 0.18
@COOKIE_PADDING_RATE 0.5

#########################
#@....#...........#....o#
#.###.#.#########.#.###.#
#.#O.................O#.#
#.#.###.###   ###.###.#.#
#.#.....#     X #.....#.#
<...###.#       #.###...>
#.#.....# X X X #.....#.#
#.#.###.#########.###.#.#
#.#O.................O#.#
#.###.#.#########.#.###.#
#o....#...........#....o#
#########################
```

#### Symbols

|  Symbol   |             Model              |
| :-------: | :----------------------------: |
|    `#`    |          An obstacle.          |
|    `@`    |          The pacman.           |
|    `.`    | A small cookie for `1` score.  |
|    `o`    | A medium cookie for `5` score. |
|    `O`    |  A big cookie for `10` score.  |
|    `X`    |            A ghost.            |
| `<` & `>` |          The portals.          |
|    ` `    |         An open space.         |

> Note that neither of portals should be missing when the other twin is defined.

#### Configurations

|          Key           |                    Value                     |
| :--------------------: | :------------------------------------------: |
| `@PACMAN_PADDING_RATE` | The rate of pacman's padding. Default: `0.1` |
|  `@PACMAN_STEP_RATE`   |  The rate of pacman's step. Default: `0.1`   |
| `@GHOST_PADDING_RATE`  | The rate of ghosts' padding. Default: `0.2`  |
|   `@GHOST_STEP_RATE`   |   The rate of ghosts' step. Default: `0.1`   |
| `@COOKIE_PADDING_RATE` | The rate of cookies' padding. Default: `0.3` |

> A padding defines the acceptable area of overlapping when two models touch.
>
> A step defines how fast a movable model can move.

### Step 2: Adding the Filename to Constants

In `src/main/java/pacman/constant/FileName.java`, add the filename of the map we created in step 1 to `MAPS`:

```java
  public static final Set<String> MAPS =
      new TreeSet<>(
          Arrays.asList(
              "pacman/map/#001 So Easy.txt",
              "pacman/map/#002 Easy Again?.txt",
              "pacman/map/#003 A Traitor.txt",
              "pacman/map/#004 Freedom.txt",
              "pacman/map/#005 Less is More.txt",
              "pacman/map/#006 Up and Down.txt",
              "pacman/map/#007 One Way.txt",
              "pacman/map/#008 The Maze.txt",
              "pacman/map/#009 Accel World.txt",
              "pacman/map/#010 Spires.txt",
              "pacman/map/example.txt"));
```

Done! A new level is created.

## File Storage

This project stores scoreboards in user's home directory: `~/.pacman`.

Feel free to delete it when you don't need mess up with this game any more.

## Design Parterns

- MVC
- Singleton
- Factory
- Builder

## About G52SWM CW2

Click [here](https://projects.cs.nott.ac.uk/psysz4/swm) to know more about its requirements.

My final submission for the coursework is tagged as `1.0`.

### Class Diagram

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fyn2czxz5qj314y0u0k1y.jpg)

### Video in Action

https://youtu.be/U7muRCK_orU

## Credits

This project is modified from a basic [one](https://projects.cs.nott.ac.uk/psysz4/swm).

All media assets (images & music) are derived from the internet, and are ensured to be free to use for non-commercial use.

This project adopts MIT license.