package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * Thrown if a method is incorrectly
 * annotated with the {@link IterativeActionOpMode.Action} annotation.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeActionOpMode.Action
 * @see IterativeActionOpMode
 * @see IterativeActionOpMode#registerActions()
 * @see IterativeActionPool
 */
public class ActionAnnotationException extends Exception
{
    /**
     *
     * Constructs a new {@code ActionAnnotationException}
     * object with an empty message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #ActionAnnotationException(String)
     */
     ActionAnnotationException()
    {
        super();
    }

    /**
     *
     * Constructs a new {@code ActionAnnotationException}
     * object with the specified message.
     *
     * @param message the specified message
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #ActionAnnotationException()
     */
     ActionAnnotationException(String message)
    {
        super(message);
    }
}
