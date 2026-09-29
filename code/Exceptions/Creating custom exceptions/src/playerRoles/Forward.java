package playerRoles;

import exceptions.TransferDeclinedException;
import exceptions.PlayerInjuredException;

/**
 * Represents a forward player in our football simulation.
 * <p>
 * This class throws our brand-new custom exceptions when things go wrong,
 * relying on its internal state attributes to make those decisions.
 */
public class Forward {

    /**
     * Indicates whether the forward is currently suffering from a physical injury.
     */
    private boolean isInjured = true;

    /**
     * Indicates whether the club has officially listed this player for transfer.
     */
    private boolean isTransferListed = false;

    /**
     * Simulates playing a match. Throws an unchecked exception.
     * <p>
     * No {@code throws} declaration is strictly required for {@link RuntimeException}.
     * </p>
     *
     * @throws PlayerInjuredException if the player {@link #isInjured} is {@code true}
     */
    public void playMatch() {
        if (isInjured) {
            // We instantiate and throw our custom unchecked exception
            throw new PlayerInjuredException("The forward cannot play due to a knee injury!");
        }
        System.out.println("Forward: Playing the match.");
    }

    /**
     * Simulates requesting a transfer. Throws a checked exception.
     * <p>
     * The {@code throws} declaration is mandatory here because it is a checked exception.
     *
     * @param newTeam the team attempting to buy the player
     * @throws TransferDeclinedException if {@link #isTransferListed} is {@code false}
     */
    public void requestTransfer(String newTeam) throws TransferDeclinedException {
        if (!isTransferListed) {
            // We instantiate and throw our custom checked exception
            throw new TransferDeclinedException("The club refuses to sell this player to " + newTeam + ".");
        }
        System.out.println("Forward: Transfer to " + newTeam + " completed successfully.");
    }
}
