package playerRoles;

/**
 * Represents a forward player in our football simulation.
 * <p>
 * This class demonstrates a method that can naturally fail in multiple ways,
 * requiring specific exception handling from the caller.
 */
public class Forward {

    /**
     * Processes the transfer of the forward to a new team.
     *
     * @param teamName the name of the purchasing team (cannot be null)
     * @param totalFee the total amount of money paid
     * @param installments the number of payments (cannot be zero)
     */
    public void processTransfer(String teamName, int totalFee, int installments) {
        // Natural failure 1: If teamName is null, calling .toUpperCase()
        // will naturally trigger a NullPointerException.
        System.out.println("Destination: " + teamName.toUpperCase());

        // Natural failure 2: If installments is 0, this division
        // will naturally trigger an ArithmeticException.
        int feePerInstallment = totalFee / installments;

        System.out.println("Forward: Transfer approved. Fee per installment: " + feePerInstallment + " euros");
    }
}
