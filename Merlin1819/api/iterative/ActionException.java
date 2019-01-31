package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * The superclass of all exceptions
 * related to actions.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 */
public abstract class ActionException extends RuntimeException
{
    /**
     *
     * Initializes with an empty message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #ActionException(String)
     */
    ActionException()
    {
        super();
    }

    /**
     *
     * Initializes with the specified message.
     *
     * @param message the specified message
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #ActionException()
     */
    ActionException(String message)
    {
        super(message);
    }
}