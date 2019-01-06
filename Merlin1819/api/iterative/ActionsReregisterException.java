package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import java.lang.RuntimeException;

/**
 *
 * Thrown if a method that registers
 * an action attempts to register
 * some action(s) twice.
 *
 * @author Zigy Lim
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeActionOpMode
 * @see IterativeActionPool
 * @see IterativeState
 */
public class ActionsReregisterException extends RuntimeException
{
    public ActionsReregisterException(String message)
    {
        super(message);
    }

    public ActionsReregisterException()
    {
        super();
    }
}
