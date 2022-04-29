package game;

import java.util.List;

public class Board {
    static final int DIM = 8;
    private static final String DELIM = "  ";
    private static final String[] NUMBERING = {"   |   |   |   |   |   |   |   ", "----+----+----+----+----+----+----+----"};
    private static final String[] BOARD_NUMBERING = {" A  | B  | C  | D  |  E |  F |  G |  H ", "----+----+----+----+----+----+----+----"};
    private static final String LINE = NUMBERING[1];

    private static final List<Field> blackPieces = List.of(new Field[]{Field.BISSHOP_BLACK, Field.ROOK_BLACK, Field.KNIGHT_BLACK, Field.PAWN_BLACK, Field.KING_BLACK, Field.QUEEN_BLACK});
    private static final List<Field> whitePieces = List.of(new Field[]{Field.BISSHOP_WHITE, Field.ROOK_WHITE, Field.KNIGHT_WHITE, Field.PAWN_WHITE, Field.KING_WHITE, Field.QUEEN_WHITE});


    Field[] fields = new Field[DIM * DIM];

    public Board() {
        reset();
    }

    public void reset() {
        fields[getIndex(7, 0)] = Field.ROOK_BLACK;
        fields[getIndex(7, 1)] = Field.KNIGHT_BLACK;
        fields[getIndex(7, 2)] = Field.BISSHOP_BLACK;
        fields[getIndex(7, 3)] = Field.QUEEN_BLACK;
        fields[getIndex(7, 4)] = Field.KING_BLACK;
        fields[getIndex(7, 5)] = Field.BISSHOP_BLACK;
        fields[getIndex(7, 6)] = Field.KNIGHT_BLACK;
        fields[getIndex(7, 7)] = Field.ROOK_BLACK;
        for (int i = 0; i < 8; i ++) {
            fields[getIndex(6, i)] = Field.PAWN_BLACK;
        }
        fields[getIndex(0, 0)] = Field.ROOK_WHITE;
        fields[getIndex(0, 1)] = Field.KNIGHT_WHITE;
        fields[getIndex(0, 2)] = Field.BISSHOP_WHITE;
        fields[getIndex(0, 3)] = Field.QUEEN_WHITE;
        fields[getIndex(0, 4)] = Field.KING_WHITE;
        fields[getIndex(0, 5)] = Field.BISSHOP_WHITE;
        fields[getIndex(0, 6)] = Field.KNIGHT_WHITE;
        fields[getIndex(0, 7)] = Field.ROOK_WHITE;
        for (int i = 0; i < 8; i ++) {
            fields[getIndex(1, i)] = Field.PAWN_WHITE;
        }

        for (int i = 0; i < 4 * DIM; i++) {
            fields[16 + i] = Field.EMPTY;
        }
    }

    public int getIndex(int row, int col) {
        return row * 8 + col;
    }

    public Field getField(int index) {
        return fields[index];
    }

    public Field getField(int row, int col) {
        return fields[getIndex(row, col)];
    }

    public void setField(int index, Field field) {
        fields[index] = field;
    }

    public void setField(int row, int col, Field field) {
        fields[getIndex(row, col)] = field;
    }

    public int getRow(int index) {
        if (index < 8) {
            return 0;
        } else if (index < 16) {
            return 1;
        } else if (index < 24) {
            return 2;
        } else if (index < 32) {
            return 3;
        } else if (index < 40) {
            return 4;
        } else if (index < 48) {
            return 5;
        } else if (index < 56) {
            return 6;
        } else {
            return 7;
        }
    }

    public int getColumn(int index) {
        if (index % 8 == 0) {
            return 0;
        } else if ((index - 1) % 8 == 0) {
            return 1;
        } else if ((index - 2) % 8 == 0) {
            return 2;
        } else if ((index - 3) % 8 == 0) {
            return 3;
        } else if ((index - 4) % 8 == 0) {
            return 4;
        } else if ((index - 5) % 8 == 0) {
            return 5;
        } else if ((index - 6) % 8 == 0) {
            return 6;
        } else {
            return 7;
        }

    }

    public int getIndexNotation(String notation) {
        String number = notation.substring(1, 2);
        int row = Integer.parseInt(number) - 1;
        int col = 0;
        switch (notation.charAt(0)) {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case 'c':
                col = 2;
                break;
            case 'd':
                col = 3;
                break;
            case 'e':
                col = 4;
                break;
            case 'f':
                col = 5;
                break;
            case 'g':
                col = 6;
                break;
            case 'h':
                col = 7;
                break;
        }
        return getIndex(row, col);
    }

    public Field getIndexNotationField(String notation) {
        String number = notation.substring(1, 2);
        int row = Integer.parseInt(number) - 1;
        int col = 0;
        switch (notation.charAt(0)) {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case 'c':
                col = 2;
                break;
            case 'd':
                col = 3;
                break;
            case 'e':
                col = 4;
                break;
            case 'f':
                col = 5;
                break;
            case 'g':
                col = 6;
                break;
            case 'h':
                col = 7;
                break;
        }
        return getField(row, col);
    }

    /**
     * Moves a chess piece to the desired field and checks if it is a valid move;
     */
    public void move(String notation) {
        String[] fields = notation.split("\\s+");
        int index = getIndexNotation(fields[1]);
        int currentIndex = getIndexNotation(fields[0]);
        boolean isWhite = whitePieces.contains(getField(currentIndex));
        Field piece = getIndexNotationField(fields[0]);
        switch (piece) {
            case EMPTY:
                System.out.println("You cannot move an empty field");
                break;
            case PAWN_WHITE:
                if ((index == currentIndex + 8)|| (index == currentIndex + 16 && getRow(currentIndex) == 1) && (getField(index) == Field.EMPTY)) {
                    setField(index, Field.PAWN_WHITE);
                    setField(currentIndex, Field.EMPTY);
                }
                if ((index == currentIndex + 7 || index == currentIndex + 9) && blackPieces.contains(getField(index))) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
                break;
            case PAWN_BLACK:
                if ((index == currentIndex - 8 || (index == currentIndex - 16 && getRow(currentIndex) == 6)) && (getField(index) == Field.EMPTY)) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
                if ((index == currentIndex + 7 || index == currentIndex + 9) && whitePieces.contains(getField(index))) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
                break;
            case ROOK_BLACK:
            case ROOK_WHITE:
                checkMoveStraight(index, currentIndex, piece, isWhite);
                break;
            case BISSHOP_BLACK:
            case BISSHOP_WHITE:
                //TODO
                boolean canMove = true;
                if (getRow(index) > getRow(currentIndex)) {
                    //Diagonal to right top
                    if (getColumn(index) > getColumn(currentIndex)) {
                        for (int i = currentIndex + 9; i <= index; i += 9) {
                            if (getField(i) != Field.EMPTY) {
                                if (!(whitePieces.contains(getField(currentIndex)) && blackPieces.contains(getField(index)) || whitePieces.contains(getField(index)) && blackPieces.contains(getField(currentIndex)))) {
                                    System.out.println("canMove set to false" + " with i = " + i);
                                    canMove = false;
                                }
                            }
                        }
                    } else {
                        for (int i = currentIndex + 7; i <= index; i += 7) {
                            if (getField(i) != Field.EMPTY) {
                                if (!(whitePieces.contains(getField(currentIndex)) && blackPieces.contains(getField(index)) || whitePieces.contains(getField(index)) && blackPieces.contains(getField(currentIndex)))) {
                                    System.out.println("canMove set to false");
                                    canMove = false;
                                }
                            }
                        }
                    }
                } else {
                    //Diagonal to left down
                    if (getColumn(index) < getColumn(currentIndex)) {
                        for (int i = currentIndex - 9; i <= index; i -= 9) {
                            if (getField(i) != Field.EMPTY) {
                                if (!(whitePieces.contains(getField(currentIndex)) && blackPieces.contains(getField(index)) || whitePieces.contains(getField(index)) && blackPieces.contains(getField(currentIndex)))) {
                                    System.out.println("canMove set to false");
                                    canMove = false;
                                }
                            }
                        }
                    } else {
                        for (int i = currentIndex - 7; i <= index; i -= 7) {
                            if (getField(i) != Field.EMPTY) {
                                if (!(whitePieces.contains(getField(currentIndex)) && blackPieces.contains(getField(index)) || whitePieces.contains(getField(index)) && blackPieces.contains(getField(currentIndex)))) {
                                    System.out.println("canMove set to false");
                                    canMove = false;
                                }
                            }
                        }
                    }
                }
                if (canMove) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
        }
        //Update should probably be removed in the future and be called by the game class.
        update();
    }

    public void checkMoveStraight(int index, int currentIndex, Field piece, boolean isWhite) {
        if (getRow(index) == getRow(currentIndex)) {
            if (getColumn(index) > getColumn(currentIndex)) {
                boolean canMove = true;
                for (int i = getColumn(currentIndex) + 1; i <= getColumn(index); i++) {
                    if (getField(getRow(currentIndex), i) != Field.EMPTY) {
                        canMove = isSameColor(index, isWhite) && i == getColumn(index);
                        break;
                    }
                }
                if (canMove) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
            } else if (getColumn(index) < getColumn(currentIndex)) {
                boolean canMove = true;
                for (int i = getColumn(currentIndex) - 1; i >= getColumn(index); i--) {
                    if (getField(getRow(currentIndex), i) != Field.EMPTY) {
                        canMove = isSameColor(index, isWhite) && i == getColumn(index);
                        break;
                    }
                }
                if (canMove) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
            }
        } else if (getColumn(index) == getColumn(currentIndex)) {
            if (getRow(index) > getRow(currentIndex)) {
                boolean canMove = true;
                for (int i = getRow(currentIndex) + 1; i <= getRow(index); i++) {
                    if (getField(i, getColumn(currentIndex)) != Field.EMPTY) {
                        System.out.println("canMove set to false");
                        canMove = isSameColor(index, isWhite) && i == getRow(index);
                        break;
                    }
                }
                System.out.println("Calculated canMove");
                if (canMove) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
                System.out.println("Rook has moved");
            } else if (getRow(index) < getRow(currentIndex)) {
                boolean canMove = true;
                for (int i = getRow(currentIndex) - 1; i >= getRow(index); i--) {
                    if (getField(i, getColumn(index)) != Field.EMPTY) {
                        canMove = isSameColor(index, isWhite) && i == getRow(index);
                        break;
                    }
                }
                if (canMove) {
                    setField(index, piece);
                    setField(currentIndex, Field.EMPTY);
                }
            }
        }
    }

    public boolean isSameColor(int index, boolean isWhite) {
        if (isWhite) {
            if (!blackPieces.contains(getField(index))) {
                return false;
            }
        } else {
            if (!whitePieces.contains(getField(index))) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        System.out.println(this.toString());
    }

    public String toString() {
        String s = "";
        for (int i = DIM - 1; i >= 0; i--) {
            String row = "";
            for (int j = 0; j < DIM; j++) {
                if (getField(i, j) != Field.EMPTY) {
                    row += " " + getField(i, j).toString().substring(0, 2).replace("EM", "  ") + " ";
                } else {
                    row += " " + getField(i, j).toString().substring(0, 2).replace("EM", "  ") + " ";
                }
                if (j < DIM - 1) {
                    row += "|";
                }
            }
            s = s + row + DELIM + (i + 1);
            if (i > 0) {
                s += "\n" + LINE + DELIM+ "\n";
            }
        }
        s += "\n" + "\n" +BOARD_NUMBERING[0];
        return s;
    }
}
