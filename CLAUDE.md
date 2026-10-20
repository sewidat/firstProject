# CLAUDE.md

This file gives Claude Code (claude.ai/code) guidance for working in this repository.

## Overview

A JavaFX desktop app called "NAV" that finds a route between two towns in Palestine and draws it on a map. You pick a start town, a destination town, and an algorithm (**UCS**, uniform-cost search, or **A\***). The app shows the path, the total cost, and draws the route as line segments between town markers.

It's an IntelliJ IDEA project (`firstProject.iml`, `.idea/`) targeting **JDK 1.8**, which bundles JavaFX. There is no Maven or Gradle build.

## Building and running

- **IntelliJ:** open the folder and run `sample.Main`. The `FindPath` artifact (`.idea/artifacts/FindPath.xml`) builds the runnable jar.
- **Prebuilt jar:** it lives in `../JarFile/`. Run it from inside that directory:
  ```
  java -jar JavaFXApp.jar
  ```
- **Library:** `.idea/libraries/jfoenix_8_0_10.xml` points at `$USER_HOME$/Downloads/jfoenix-8.0.10.jar`. Fix that path if the library can't be found.

**Working directory matters:** `Page.startMethod()` reads the graph from `./map.txt`, relative to the process's working directory, not the classpath. Run the app from the project root (where the root `map.txt` lives) or from the jar's directory. The app prints `user.dir` on startup to help with debugging.

There are no tests.

## Data format

`map.txt` holds one undirected edge per line: `townA,townB,distance`. Each line is added in both directions.

- Town names must exactly match the `fx:id` of a `Circle` in `src/sample/page.fxml`, and a matching `@FXML private Circle <name>;` field in `Page.java`. The route is drawn with `Main.scene.lookup("#" + name)`, and the A\* heuristic uses each circle's layout coordinates.
- To add a town, add it in three places: `map.txt`, a `Circle` in `page.fxml` (positioned on the map image), and a field in `Page.java`.
- Copies of `map.txt` also exist in `src/sample/` and `../JarFile/`. Keep whichever copy is actually loaded in sync.
- The `../towns.csv` (name, x, y) and `../roads.csv` files outside the repo are reference data. The code doesn't read them.

## Code structure (`src/sample/`)

- `Main.java`: the JavaFX `Application`. It loads `page.fxml` and stores the `scene` and `stage` statically so other classes can look up nodes.
- `Page.java`: the FXML controller. It parses `map.txt` into a `Graph`, fills the From/To combo boxes, and handles the "find path" action. Its `getD()` method is the A\* heuristic: the Euclidean distance between two circles on screen × 0.04.
- `Graph.java`: holds the adjacency-list graph and the search code. `method(start, end)` dispatches on the field `i` (`0` = UCS via `findPath`, `1` = `aStar`). It rebuilds the path by following each node's `father` links onto a `Stack`. The accumulated cost is left in the field `y`.
- `Node.java` / `AdjNode.java`: a graph vertex (with `visited` and `father` state) and a weighted edge. `AdjNode` is ordered by weight for the `PriorityQueue`.

## Gotchas

- Search state (`visited`, `father`, and edge `weight`, which the searches change in place) lives on the nodes. A graph can't be reused between searches. That's why `Page.findPath` calls `startMethod()` again to rebuild the graph before every search after the first.
- Both searches are recursive and assume a path exists. If the destination can't be reached, the priority queue empties and `poll()` returns `null`, which causes a `NullPointerException`.
- `out/` contains IntelliJ build output and is ignored by git.
