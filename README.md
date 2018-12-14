# Pac-Man
[![pacman.gif](https://i.loli.net/2018/12/14/5c13bf0563d18.gif)](https://i.loli.net/2018/12/14/5c13bf0563d18.gif)

This is a pac-man game written in JavaFX for G52SWM CW2, using some design patterns like MVC, Singleton and Fatory.

## Running

You can launch the app either by running that `pacman.Main.main()` method inside your IDE, or type `mvn clean compile package exec:java` on the command line if you have maven installed.

In IntelliJ, Click <kbd>Run</kbd>  -> <kbd>Run 'Main()'</kbd>.

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

## Design Parterns

- MVC
- Singleton
- Factory

## Credits

This project is modified from a basic [one](https://projects.cs.nott.ac.uk/psysz4/swm).

All assets (images & music) are derived from the internet, and are ensured to be free to use for non-commercial use.