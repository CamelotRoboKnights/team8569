package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * Thrown if a method is incorrectly
 * annotated with the {@link Action} annotation.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see Action
 * @see IterativeActionOpMode
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
    public ActionAnnotationException()
    {
        super();
    }

    /**
     *
     * Constructs a new {@code ActionAnnotationException}
     * object with the specified message.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @param message the specified message
     *
     * @see #ActionAnnotationException()
     */
    public ActionAnnotationException(String message)
    {
        super(message);
    }
}
