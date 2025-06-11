PROJECT_NAME := Lox Lang
SRC_DIR := .
BIN_DIR := bin
MAIN_CLASS := com.loxlang.lox.Lox

JAVAC := javac
JAVAC_FLAGS := -d $(BIN_DIR)

JAVA := java
JAVA_FLAGS := -cp $(BIN_DIR)

# --- Targets ---

.PHONY: all clean run compile

all: compile

compile: $(BIN_DIR)
	@echo "Compiling Java sources..."
	$(JAVAC) $(JAVAC_FLAGS) $(SRC_DIR)/com/loxlang/lox/*.java
	@echo "Compilation complete."

$(BIN_DIR):
	@echo "Creating output directory $(BIN_DIR)..."
	mkdir -p $(BIN_DIR)

run: compile
	@echo "Running $(MAIN_CLASS) with argument: '$(ARG)'..."
	# Pass the ARG variable directly to the Java command
	$(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS) $(ARG)

clean:
	@echo "Cleaning up compiled files and directories..."
	rm -rf $(BIN_DIR)
	@echo "Clean complete."

.PHONY: all compile run clean