# Compiler and flags
JAVAC = javac
JAVA = java

# Directories
SRC_DIR = ./src
BIN_DIR = ./bin

# Main class to run (with package name if needed)
MAIN_CLASS = Main

# Java files in src and parent directory
JAVA_FILES = $(shell find $(SRC_DIR) -name "*.java") Main.java

# Convert Java file paths to corresponding class file paths in the bin directory
CLASS_FILES = $(patsubst %.java, $(BIN_DIR)/%.class, $(JAVA_FILES))

# Default target: compile all class files
all: $(CLASS_FILES)

# Compile Java files into class files
$(BIN_DIR)/%.class: %.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $<

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) $<

# Run the main class
run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean class files
clean:
	rm -rf $(BIN_DIR)/*.class
	rm -rf $(BIN_DIR)/*/
