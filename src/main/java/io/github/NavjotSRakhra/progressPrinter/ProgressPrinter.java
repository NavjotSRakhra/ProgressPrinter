package io.github.NavjotSRakhra.progressPrinter;

public class ProgressPrinter {
    private final int progressBarSize;
    private final char completeCharacter, incompleteCharacter;
    private int lastComplete;

    public ProgressPrinter(int progressBarSize, char completeCharacter, char incompleteCharacter) {
        this.progressBarSize = progressBarSize;
        this.completeCharacter = completeCharacter;
        this.incompleteCharacter = incompleteCharacter;
        this.lastComplete = -1;
    }

    public ProgressPrinter(int progressBarSize) {
        this(progressBarSize, '=', '-');
    }

    public ProgressPrinter() {
        this(100);
    }

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
