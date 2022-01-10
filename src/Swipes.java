/**
 * Determines changes in the model state based on different inputs
 */
public class Swipes {
    private FifteenThirtySix fts;

    public Swipes(FifteenThirtySix fts) {
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
     * shiftVertical checks if the numbers can be moved in the vertical
     * direction and alters the state of the board if so
     *
     * @param dir int direction of swipe
     * @param arr int array
     * @param col int of the col that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftVertical(int dir, int[] arr, int col) {
        int i = 0;
        if (dir == 2) {
            i = 2;
        }
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[i] == 0) {
                if (arr[1] == 0) {
                    if (arr[Math.abs(i - 2)] != 0) {
                        fts.setCell(arr[Math.abs(i - 2)], i, col);
                        fts.setCell(0, Math.abs(i - 2), col);
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], i, col);
                    fts.setCell(arr[Math.abs(i - 2)], 1, col);
                    fts.setCell(0, Math.abs(i - 2), col);
                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[Math.abs(i - 2)] != 0) {
                    fts.setCell(arr[Math.abs(i - 2)], 1, col);
                    fts.setCell(0, Math.abs(i - 2), col);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * mergeVertical checks if the numbers can be merged in the
     * vertical direction and alters the state of the board if so
     *
     * @param dir int direction
     * @param arr int array
     * @param col int of the col that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeVertical(int dir, int[] arr, int col) {
        int i = 0;
        if (dir == 2) {
            i = 2;
        }
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[i] == arr[1] && arr[1] != 0) {
            if (i == 0) {
                fts.setCell(arr[0] * 2, 0, col);
            } else {
                fts.setCell(arr[1] * 2, 2, col);
            }
            fts.setCell(0, 1, col);
            return true;
        } else if (arr[1] == arr[Math.abs(i - 2)] && arr[Math.abs(i - 2)] != 0) {
            fts.setCell(arr[1] * 2, 1, col);
            fts.setCell(0, Math.abs(i - 2), col);
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, i, col);
                fts.setCell(0, Math.abs(i - 2), col);
                return true;
            }
        }
        return false;
    }

    /**
     * shiftHorizontal checks if the numbers can be moved in the horizontal
     * direction and alters the state of the board if so
     *
     * @param dir int direction of swipe
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be moved
     */
    public boolean shiftHorizontal(int dir, int[] arr, int row) {
        int i = 0;
        if (dir == 1) {
            i = 2;
        }
        if (allEmpty(arr)) {
            return false;
        }
        if (hasEmpty(arr)) {
            if (arr[i] == 0) {
                if (arr[1] == 0) {
                    if (arr[Math.abs(i - 2)] != 0) {
                        fts.setCell(arr[Math.abs(i - 2)], row, i);
                        fts.setCell(0, row, Math.abs(i - 2));
                        return true;
                    }
                } else {
                    fts.setCell(arr[1], row, i);
                    fts.setCell(arr[Math.abs(i - 2)], row, 1);
                    fts.setCell(0, row, Math.abs(i - 2));
                    return true;
                }
            } else {
                if (arr[1] == 0 && arr[Math.abs(i - 2)] != 0) {
                    fts.setCell(arr[Math.abs(i - 2)], row, 1);
                    fts.setCell(0, row, Math.abs(i - 2));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * mergeHorizontal checks if the numbers can be merged in the
     * horizontal direction and alters the state of the board if so
     *
     * @param dir int direction
     * @param arr int array
     * @param row int of the row that the array is in the board
     * @return true if numbers can be merged
     */
    public boolean mergeHorizontal(int dir, int[] arr, int row) {
        int i = 0;
        if (dir == 1) {
            i = 2;
        }
        if (allEmpty(arr)) {
            return false;
        }
        if (arr[i] == arr[1] && arr[1] != 0) {
            fts.setCell(arr[i] * 2, row, i);
            fts.setCell(0, row, 1);
            return true;
        } else if (arr[1] == arr[Math.abs(i - 2)] && arr[1] != 0) {
            fts.setCell(arr[1] * 2, row, 1);
            fts.setCell(0, row, Math.abs(i - 2));
            return true;
        } else if (arr[0] == arr[2]) {
            if (arr[1] == 0) {
                fts.setCell(arr[0] * 2, row, i);
                fts.setCell(0, row, Math.abs(i - 2));
                return true;
            }
        }
        return false;
    }

    /**
     * swipeLeft calls the other methods and checks whether
     * they were successful
     *
     * @param dir int direction of the swipe
     * @return true if numbers can be merged/shifted
     */
    public boolean swipe(int dir) {
        boolean changed = false;
        int[] arr;
        boolean merged;
        boolean shifted;
        for (int i = 0; i < 3; i++) {
            if (dir == 0 || dir == 1) {
                arr = fts.getRow(i);
                merged = mergeHorizontal(dir, arr, i);
                shifted = shiftHorizontal(dir, arr, i);
            } else {
                arr = fts.getCol(i);
                merged = mergeVertical(dir, arr, i);
                shifted = shiftVertical(dir, arr, i);
            }
            if (merged || shifted) {
                changed = true;
            }
        }
        return changed;
    }

}
