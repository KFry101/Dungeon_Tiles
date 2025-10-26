# Dungeon Tiles

**Dungeon Tiles** is a 2D tile-based video game built in **Java** using **Processing 4** for rendering.

---

## 🕹️ Features

- Custom dungeon tile engine using the Processing graphics library
- Modular Java-based architecture (easily extensible for new tiles or game mechanics)
- Asset management through a dedicated **Media** folder
- Real-time rendering and sound playback via Processing’s `core` and `sound` libraries

---

## ⚙️ Requirements

- **Java Development Kit (JDK):** 17 or later
- **Processing 4** (for rendering and audio support)
- **Processing Libraries:**
    - `core.jar`
    - `sound.jar`

These libraries are required for the game to run properly.

---

## 🧩 Setting Up Processing Libraries

### To obtain `core.jar` and `sound.jar` for Processing 4, follow these steps:

1. **Download and Install Processing 4**
    - Navigate to the official Processing website: [https://processing.org/download](https://processing.org/download)
    - Download the appropriate version for your operating system (Windows, macOS, Linux)
    - Install Processing by following your OS-specific instructions:
        - Windows: Extract the ZIP file
        - macOS: Drag to Applications
        - Linux: Run `install.sh`

2. **Locate `core.jar`**
    - After installing Processing, navigate to the installation directory
    - Inside the Processing folder, go to:
      ```
      processing-4.x.x/core/library/
      ```
    - The `core.jar` file is located in this folder

3. **Install and Locate `sound.jar`**
    - Open the **Processing IDE**
    - Go to `Sketch > Import Library > Add Library...`
    - In the Library Manager, search for **"Sound"**
    - Select **Sound | Provides a simple way to work with audio** and click **Install**
    - Once installed, `sound.jar` will be located in:
      ```
      Documents/Processing/libraries/sound/library/sound.jar
      ```

4. **Add the Libraries to Your IDE (IntelliJ, Eclipse, or VS Code)**
    - Add both `core.jar` and `sound.jar` as external libraries or dependencies
    - Ensure they are on the **classpath** for compilation and runtime

---

## 💻 Running the Game

### Option 1: IntelliJ IDEA
1. Clone or download this repository.
   ```bash
   git clone https://github.com/KFry101/Dungeon_Tiles.git
   ```
2. Open the project in **IntelliJ IDEA**.
3. Go to **File > Project Structure > Libraries** and add:
    - `core.jar` (Processing core)
    - `sound.jar` (Processing sound)
4. Set the project SDK to your installed JDK (e.g., JDK 17 or 21).
5. Run the main class from the `src` directory.

---

### Option 2: Eclipse
1. Import the project as an **Existing Java Project**.
2. Add external JARs under **Build Path > Configure Build Path > Libraries**.
3. Select both `core.jar` and `sound.jar`.
4. Run the main file as a **Java Application**.

---

### Option 3: Visual Studio Code (VS Code)

You can also run the project in **VS Code** using the Java and Processing setup.

#### Prerequisites
- Install the following VS Code extensions:
    - **Extension Pack for Java** (by Microsoft)
    - **Language Support for Java by Red Hat**
    - *(Optional but recommended)* **Code Runner**

#### ⚙️ Setup Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/KFry101/Dungeon_Tiles.git
   ```
2. Open the project folder in VS Code.
3. Create a `.vscode/settings.json` file (if it doesn’t exist) and add:
   ```json
   {
     "java.project.referencedLibraries": [
       "path/to/processing/core/library/core.jar",
       "path/to/Processing/libraries/sound/library/sound.jar"
     ],
     "java.configuration.runtimes": [
       {
         "name": "JavaSE-17",
         "path": "C:\\Program Files\\Java\\jdk-17"
       }
     ]
   }
   ```
   > ⚠️ Adjust the paths above to match your system.
4. Open the main Java file in `src/` (e.g., `DungeonTilesUI.java`).
5. Click **Run ▶** at the top right of the editor, or use:
   ```
   Ctrl + F5
   ```
6. The Processing window should open, displaying your game.

---


## 🎨 Media and Assets

All visual and audio assets are stored in the `/Media` folder.  
Ensure that relative paths in the source code correctly point to this directory.

---

## Authors

**Developed by:** [KFry101](https://github.com/KFry101)  
**Language:** Java  
**Graphics Engine:** Processing 4

---

## 📜 License

This project is open-source under the **MIT License**.  
You are free to modify and distribute the code, provided proper attribution is given.

---


