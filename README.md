## Chess Game Implementation Version 1.2
NOTE: THE GAME STILL HAS A LOT OF LACKINGS.

This basic chess game implements a simple chess board.

## New in Version 1.2
    - Updated to jdk-17.0.7 and javafx-sdk-17.0.7
    - Fixed a bug where pawn could walk straight forward and capture a piece
    - Implemented piece collision, so pieces can't walk through each other

## Features
    - Shows possible legal moves when a piece is selected
    - It makes sure the game is played sequentially
    - Forces pieces to move legally

## Lacking Features
    - Does not implement en passant
    - Does not implement castling
    - Does not implement promotion
    - Does not implement stalemate
    - Does not implement checks
    - Does not implement checkmate

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
