## Chess Game Implementation Version 1
NOTE: THE GAME STILL HAS A LOT OF LACKINGS.

This basic chess game implements a simple chess board.

## Features
    - It makes sure the game is played sequentially (starting with white).
    - Forces pieces to move legally (some things not yet implemented see section on missing features under).
    - Implements pawn capture
    - Implements possiblility of moving 2 squares on first move of a pawn
    - Restricts pawns to move forward

## Lacking Features
    - Does not implement en passant
    - Does not implement castling
    - Does not implement promotion
    - Does not implement stalemate
    - Does not implement checks
    - Does not implement checkmate
    - Pieces are ghosts (they are able to move through other pieces)

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
