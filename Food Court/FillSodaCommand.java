class FillSodaCommand implements Command {
    private Soda soda;

    public FillSodaCommand(Soda soda) {
        this.soda = soda;
    }

    public void execute() {
        System.out.println("Filled cup with: " + soda.getDescription());
    }
}