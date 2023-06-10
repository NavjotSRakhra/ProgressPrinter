package io.github.NavjotSRakhra.progressPrinter;

public class ProgressPrinter {
    private final int progressBarSize;
    private final char completeCharacter, incompleteCharacter;
    private int lastComplete;

    /**
     * Creates an instance of ProgressPrinter to display progress-bar for console.
     *
     * @param progressBarSize     size of the progress-bar in characters
     * @param completeCharacter   the character that replaces {@link ProgressPrinter#incompleteCharacter} when the progress
     *                            percent increases
     * @param incompleteCharacter the character that is displayed representing the progress to be covered
     */
    public ProgressPrinter(int progressBarSize, char completeCharacter, char incompleteCharacter) {
        this.progressBarSize = progressBarSize;
        this.completeCharacter = completeCharacter;
        this.incompleteCharacter = incompleteCharacter;
        this.lastComplete = -1;
    }

    /**
     * Creates an instance of ProgressPrinter to display progress-bar for console, with progress complete character '='
     * and progress incompleteCharacter '-'.
     *
     * @param progressBarSize size of the progress-bar in characters
     */
    public ProgressPrinter(int progressBarSize) {
        this(progressBarSize, '=', '-');
    }

    /**
     * Creates an instance of ProgressPrinter to display progress-bar for console, with progress size 100,
     * progress complete character '=' and progress incompleteCharacter '-'.
     */
    public ProgressPrinter() {
        this(100);
    }

    /**
     * Updates the progress on the progress bar. Once 1 is passed into the method, the progress bar is exited and
     * cannot be updated again.
     *
     * @param completeFraction the fraction of progress complete in double
     */
    public void update(double completeFraction) {
        if (completeFraction < 0 || completeFraction > 1)
            throw new IllegalArgumentException(completeFraction + "argument not allowed. Percentage must be between 0 and 100");

        if (lastComplete == (int) (completeFraction * 100)) return;
        lastComplete = (int) (completeFraction * 100);

        System.out.print("\r" + generateProgressString(progressBarSize, (int) (completeFraction * progressBarSize)) + " " + (int) (completeFraction * 100) + "%");

        if (completeFraction == 1)
            System.out.println();
    }

    private String generateProgressString(int total, int complete) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < total; i++) {
            if (i < complete) str.append(completeCharacter);
            else str.append(incompleteCharacter);
        }
        if (complete != total) str.setCharAt(complete, '>');
        return str.toString();
    }
}
