package test;

import game.Board;
import game.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board = new Board();

    @BeforeEach
    public void reset() {
        board.reset();
    }

    @Test
    public void testField() {
        assertEquals(board.getField(7), Field.ROOK_WHITE);
        assertEquals(board.getField(6, 0), Field.PAWN_BLACK);
    }

    @Test
    public void testGetIndexRowCol() {
        assertEquals(0, board.getIndex(0, 0));
        assertEquals(8, board.getIndex(1, 0));
    }

    @Test
    public void testGetRow() {
        assertEquals(7, board.getRow(63));
        assertEquals(6, board.getRow(55));
        assertEquals(6, board.getRow(48));
        assertEquals(0, board.getRow(4));
        assertEquals(1, board.getRow(8));
        assertEquals(1, board.getRow(15));
    }

    @Test
    public void testGetColumn() {
        assertEquals(0, board.getColumn(0));
    }

    @Test
    public void testGetNotationHelper() {
        assertEquals(1, board.getNotationHelper("a2")[0]);
        assertEquals(0, board.getNotationHelper("a2")[1]);
    }

    @Test
    public void testMove() {
        board.move("a2 a3");
        board.move("h7 h5");
        board.move("a3 a5");
        assertEquals(Field.EMPTY, board.getField(4, 0));
        assertEquals(Field.PAWN_WHITE, board.getField(2, 0));
        assertEquals(Field.PAWN_BLACK, board.getField(4, 7));


        //Move Rook white
        board.reset();
        board.move("h1 h2");
        assertEquals(Field.PAWN_WHITE, board.getIndexNotationField("h2"));
        board.move("h2 h3");
        board.move("h1 h3");
        assertEquals(Field.PAWN_WHITE, board.getIndexNotationField("h3"));
        board.move("h3 h4");
        board.move("h1 h3");
        assertEquals(Field.ROOK_WHITE, board.getIndexNotationField("h3"));
        board.move("h3 g3");
        board.move("g3 g8");
        assertEquals(Field.KNIGHT_BLACK, board.getIndexNotationField("g8"));
        assertEquals(Field.ROOK_WHITE, board.getIndexNotationField("g3"));
        board.move("g3 g7");
        assertEquals(Field.ROOK_WHITE, board.getIndexNotationField("g7"));

        //Move left knight
        board.reset();
        board.move("c1 a3");
        assertEquals(Field.EMPTY, board.getIndexNotationField("a3"));
        board.move("c1 b2");
        assertEquals(Field.PAWN_WHITE, board.getIndexNotationField("b2"));
        board.move("c1 d2");
        assertEquals(Field.PAWN_WHITE, board.getIndexNotationField("d2"));
        board.move("d2 d3");
        board.move("c1 h6");
        assertEquals(Field.BISSHOP_WHITE, board.getIndexNotationField("h6"));
        board.move("h6 f8");
        assertEquals(Field.BISSHOP_BLACK, board.getIndexNotationField("f8"));

        board.reset();
        board.move("a1 a8");
        assertEquals(Field.ROOK_BLACK, board.getIndexNotationField("a8"));
        board.move("a1 a7");
        assertEquals(Field.PAWN_BLACK, board.getIndexNotationField("a7"));
        board.move("a2 a4");
        board.move("a1 a3");
        board.move("a3 b3");
        board.move("b3 b8");
        assertEquals(Field.KNIGHT_BLACK, board.getIndexNotationField("b8"));
        board.move("b3 b7");
        assertEquals(Field.ROOK_WHITE, board.getIndexNotationField("b7"));

        //Move Queen
        board.reset();
        board.move("d1 d5");
        assertEquals(Field.EMPTY, board.getIndexNotationField("d5"));
        board.move("d2 d3");
        board.move("d1 d4");
        board.move("d1 d2");
        assertEquals(Field.QUEEN_WHITE, board.getIndexNotationField("d2"));
        board.move("d2 g5");
        assertEquals(Field.QUEEN_WHITE, board.getIndexNotationField("g5"));
        //Move Black Queen
        board.move("e7 e6");
        board.move("d8 h4");
        assertEquals(Field.EMPTY, board.getIndexNotationField("h4"));
        board.move("d8 g5");
        assertEquals(Field.QUEEN_BLACK, board.getIndexNotationField("g5"));

        //Move Black King
        board.reset();
        board.move("e8 e7");
        assertEquals(Field.PAWN_BLACK, board.getIndexNotationField("e7"));
        board.move("e8 f8");
        assertEquals(Field.BISSHOP_BLACK, board.getIndexNotationField("f8"));
        board.move("e8 d7");
        assertEquals(Field.PAWN_BLACK, board.getIndexNotationField("d7"));
        board.move("f7 f6");
        board.move("e8 f7");
        assertEquals(Field.KING_BLACK, board.getIndexNotationField("f7"));

        //Move White King
        board.move("e2 e4");
        board.move("e1 e2");
        assertEquals(Field.KING_WHITE, board.getIndexNotationField("e2"));
        board.setField(5, Field.ROOK_BLACK);
        board.move("e2 f1");
        assertEquals(Field.KING_WHITE, board.getIndexNotationField("f1"));
    }
}
