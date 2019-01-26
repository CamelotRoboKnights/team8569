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
 */
public class IterativeStateException extends RuntimeException
{
    IterativeStateException()
    {
        super();
    }

    IterativeStateException(String message)
    {
        super(message);
    }
}
