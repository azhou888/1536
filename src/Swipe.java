/**
 * Determines changes in the model state based on different inputs
 */
public class Swipe {

    private FifteenThirtySix fts;

    // initializes
    public Swipe(FifteenThirtySix fts) {
        this.fts = fts;
    }

    /**
     * hasEmpty checks if the array has 0's
     *
     * @param arr int array
     * @return true if array has 0's
     */
    public boolean hasEmpty(int[] arr) {
        return (arr[0] == 0 || arr[1] == 0 || arr[2] == 0);
    }

    /**
     * allEmpty checks if the array has all 0's
     *
     * @param arr int array
     * @return true if array is all 0's
     */
    public boolean allEmpty(int[] arr) {
        return (arr[0] == 0 && arr[1] == 0 && arr[2] == 0);
    }

    /**
     * shiftLeft checks if the numbers can be moved to the left
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftLeft(int[] arr, int row) {
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[0] == 0) {
                if (arr[1] == 0) {
                    if (arr[2] != 0) {
                        fts.setCell(arr[2], row, 0);
                        fts.setCell(0, row, 2);
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], row, 0);
                    fts.setCell(arr[2], row, 1);
                    fts.setCell(0, row, 2);

                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[2] != 0) {
                    fts.setCell(arr[2], row, 1);
                    fts.setCell(0, row, 2);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * mergeLeft checks if the numbers can be merged to the left
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeLeft(int[] arr, int row) {
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[0] == arr[1] && arr[0] != 0) {
            fts.setCell(arr[0] * 2, row, 0);
            fts.setCell(0, row, 1);
            return true;

        } else if (arr[1] == arr[2] && arr[1] != 0) {
            fts.setCell(arr[1] * 2, row, 1);
            fts.setCell(0, row, 2);
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, row, 0);
                fts.setCell(0, row, 2);
                return true;
            }
        }
        return false;
    }

    /**
     * swipeLeft calls the other methods and checks whether
     * they were successful
     *
     *
     * @return true if numbers can be merged/shifted
     */
    public boolean swipeLeft() {
        boolean changed = false;
        for (int i = 0; i < 3; i++) {
            int[] row = fts.getRow(i);
            boolean merged = mergeLeft(row, i);
            boolean shifted = shiftLeft(row, i);
            if (merged || shifted) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * shiftRight checks if the numbers can be moved to the right
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftRight(int[] arr, int row) {
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[2] == 0) {
                if (arr[1] == 0) {
                    if (arr[0] != 0) {
                        fts.setCell(arr[0], row, 2);
                        fts.setCell(0, row, 0);
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], row, 2);
                    fts.setCell(arr[0], row, 1);
                    fts.setCell(0, row, 0);
                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[0] != 0) {
                    fts.setCell(arr[0], row, 1);
                    fts.setCell(0, row, 0);
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * mergeRight checks if the numbers can be merged to the right
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeRight(int[] arr, int row) {
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[2] == arr[1] && arr[1] != 0) {
            fts.setCell(arr[2] * 2, row, 2);
            fts.setCell(0, row, 1);
            return true;
        } else if (arr[1] == arr[0] && arr[0] != 0) {
            fts.setCell(arr[1] * 2, row, 1);
            fts.setCell(0, row, 0);
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, row, 2);
                fts.setCell(0, row, 0);
                return true;
            }
        }
        return false;
    }

    /**
     * swipeRight calls the other methods and checks whether
     * they were successful
     *
     * @return true if numbers can be merged/shifted
     */
    public boolean swipeRight() {
        boolean changed = false;
        for (int i = 0; i < 3; i++) {
            int[] row = fts.getRow(i);
            boolean merged = mergeRight(row, i);
            boolean shifted = shiftRight(row, i);
            if (merged || shifted) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * shiftUp checks if the numbers can be moved up
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param col int of the column that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftUp(int[] arr, int col) {
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[0] == 0) {
                if (arr[1] == 0) {
                    if (arr[2] != 0) {
                        fts.setCell(arr[2], 0, col);
                        fts.setCell(0, 2, col);
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], 0, col);
                    fts.setCell(arr[2], 1, col);
                    fts.setCell(0, 2, col);
                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[2] != 0) {
                    fts.setCell(arr[2], 1, col);
                    fts.setCell(0, 2, col);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * mergeUp checks if the numbers can be merged up
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param col int of the column that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeUp(int[] arr, int col) {
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[0] == arr[1] && arr[0] != 0) {
            fts.setCell(arr[0] * 2, 0, col);
            fts.setCell(0, 1, col);
            return true;
        } else if (arr[1] == arr[2] && arr[1] != 0) {
            fts.setCell(arr[1] * 2, 1, col);
            fts.setCell(0, 2, col);
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, 0, col);
                fts.setCell(0, 2, col);
                return true;
            }
        }
        return false;
    }

    /**
     * swipeUp calls the other methods and checks whether
     * they were successful
     *
     * @return true if numbers can be merged/shifted
     */
    public boolean swipeUp() {
        boolean changed = false;
        for (int i = 0; i < 3; i++) {
            int[] col = fts.getCol(i);
            boolean merged = mergeUp(col, i);
            boolean shifted = shiftUp(col, i);
            if (merged || shifted) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * shiftDown checks if the numbers can be moved down
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param col int of the column that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftDown(int[] arr, int col) {
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[2] == 0) {
                if (arr[1] == 0) {
                    if (arr[0] != 0) {
                        fts.setCell(arr[0], 2, col);
                        fts.setCell(0, 0, col);
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], 2, col);
                    fts.setCell(arr[0], 1, col);
                    fts.setCell(0, 0, col);
                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[0] != 0) {
                    fts.setCell(arr[0], 1, col);
                    fts.setCell(0, 0, col);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * mergeDown checks if the numbers can be merged down
     * and alters the state of the board if so
     *
     * @param arr int array
     * @param col int of the column that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeDown(int[] arr, int col) {
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[2] == arr[1] && arr[1] != 0) {
            fts.setCell(arr[1] * 2, 2, col);
            fts.setCell(0, 1, col);
            return true;
        } else if (arr[1] == arr[0] && arr[0] != 0) {
            fts.setCell(arr[1] * 2, 1, col);
            fts.setCell(0, 0, col);
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, 2, col);
                fts.setCell(0, 0, col);
                return true;
            }
        }
        return false;
    }

    /**
     * swipeDown calls the other methods and checks whether
     * they were successful
     *
     * @return true if numbers can be merged/shifted
     */
    public boolean swipeDown() {
        boolean changed = false;
        for (int i = 0; i < 3; i++) {
            int[] col = fts.getCol(i);
            boolean merged = mergeDown(col, i);
            boolean shifted = shiftDown(col, i);
            if (merged || shifted) {
                changed = true;
            }
        }
        return changed;
    }
}