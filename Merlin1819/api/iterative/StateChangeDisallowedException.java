package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import org.firstinspires.ftc.teamcode.R;

/**
 *
 * Thrown if an attempt to call
 * <pre>
 * {@link IterativeState#skipActions(int)},
 * {@link IterativeState#getNextAction()},
 * {@link IterativeState#restart()},
 * {@link IterativeState#restartFromMethod()},
 * {@link IterativeState#stop()}
 * {@link IterativeState#setCompleted(boolean)}</pre>
 * is attempted to be made without an
 * explicit override from {@link IterativeState#setStateChangeAllowable(boolean)}
 * is made.
 *
 */
public class StateChangeDisallowedException extends RuntimeException
{
    /**
     *
     * Constructs a new {@code StateChangeDisallowedException}
     * with an empty messaqe.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #StateChangeDisallowedException(String)
     */
    StateChangeDisallowedException()
    {
        super();
    }

    /**
     *
     * Constructs a new {@code StateChangeDisallowedException}
     * with the specified message.
     *
     * @param message the specified message
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #StateChangeDisallowedException()
     */
    StateChangeDisallowedException(String message)
    {
        super(message);
    }
}
