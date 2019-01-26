package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * Thrown if a method specifies invalid arguments
 * in {@link IterativeState#skipActions(int)}.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeState
 * @see IterativeState#skipActions(int)
 */
public class IterativeStateException extends RuntimeException
{
    /**
     *
     * Constructs a new {@code IterativeStateException}
     * object with an empty message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #IterativeStateException(String)
     */
    IterativeStateException()
    {
        super();
    }

    /**
     *
     * Constructs a new {@code IterativeStateException}
     * with the specified message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @param message the specified message
     *
     * @see #IterativeStateException()
     */
    IterativeStateException(String message)
    {
        super(message);
    }
}
