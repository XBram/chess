package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                board.move(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
