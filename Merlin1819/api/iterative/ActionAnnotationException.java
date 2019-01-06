package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * Thrown if a method is incorrectly
 * annotated with the {@link Action} annotation.
 *
 * @author Zigy Lim
 * @version 1.0
 * @since 1.0
 *
 * @see Action
 * @see IterativeActionOpMode
 * @see IterativeActionPool
 */
public class ActionAnnotationException extends Exception
{
    public ActionAnnotationException()
    {
        super();
    }

    public ActionAnnotationException(String message)
    {
        super(message);
    }
}
