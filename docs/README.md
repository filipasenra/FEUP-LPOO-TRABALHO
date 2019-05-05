# LPOO_36 PAC XON

 Our game is based on Pac Xon, a game that was inspired by Xenon and Pacman. The goal of the game is to reduce the space in wich the little monsters are floating until 80% of the screen is filled with walls. When a monster touches the path in construction or the player, a life is lost. Quando um fantasma cruza a linha antes que chegues à área azul segura, perdes uma vida. After each level the difficulty increases. When there are no more lives, the game ends.

 Made by Ana Filipa Campos Senra and Cláudia Inês da Costa Martins.

## Implemented Features

> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.

![Begin](images/start.png)
ESCOLHER 2 ou 3 IMAGENS


## Planned Features

> This section is similar to the previous one but should list the features that are not yet implemented. Instead of screenshots you should include GUI mock-ups for the planned features.

## Design

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts: "Problem in Context", "The Pattern", "Implementation" and "Consequences".

## Known Code Smells and Refactoring Suggestions

1. Game and Arena have too many fields (Large Class). None of the classical treatmants seem to be perfectly adequate, but moving the graphical interface to another class seems to be a possiblity. More discussion on this topic is needed.
2. The class Menu seems to be a so-called "Lazy Class". However, since we anticipated future changes to the graphic design (and we know that this may seem like a pitfall) such as adding images, suport to mouse and others, this class will be usefull in the future.

## Testing Results

> This section should contain screenshots of the main results of both the test coverage and mutation testing reports. It should also contain links to those reports in HTML format (you can copy the reports to the docs folder).

## Self-evaluation
The work was carried out by both students but not divided specifically. We talked regularly and distributily small tasks accordingly. 
