package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import java.lang.RuntimeException;

/**
 *
 * Thrown if a method that registers
 * an action attempts to register
 * some action(s) twice.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeActionOpMode
 * @see IterativeActionPool
 * @see IterativeState
 */
public class ActionsReregisterException extends RuntimeException
{
    /**
     *
     * Constructs a new {@code ActionsReregisterException}
     * object with an empty message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #ActionsReregisterException(String)
     */
    public ActionsReregisterException()
    {
        super();
    }

    /**
     *
     * Constructs a new {@code ActionsReregisterException}
     * with the specified message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @param message the specified message
     *
     * @see #ActionsReregisterException(String)
     */
    public ActionsReregisterException(String message)
    {
        super(message);
    }
}
