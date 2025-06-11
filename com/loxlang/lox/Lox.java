package com.loxlang.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  static boolean hadError = false;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: lox [script]");
      System.exit(64); // exit code 64: general indicator of failure
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  /**
   * Run code from a .lox file.
   * 
   * @param path
   * @throws IOException
   * 
   */
  private static void runFile(String path) throws IOException {
    byte bytes[] = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError)
      System.exit(65);
  }

  /**
   * Run in interactive mode.
   * 
   * @throws IOException
   */
  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) {
      System.out.print("𜳡𜳤𜳭 𞢘 ");
      String line = reader.readLine();
      if (line == null)
        break;
      run(line);
      // reset `hadError` flag in the interactive loop; if the user makes a mistake,
      // it shouldn’t kill their entire session.
      hadError = false;
    }
  }

  private static void run(String source) {
    Scanner sc = new Scanner(source);
    List<Token> tokens = sc.scanTokens();

    // for now, print tokens
    for (Token token : tokens) {
      System.out.println(token);
    }
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where, String message) {
    System.err.println(
        "[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}