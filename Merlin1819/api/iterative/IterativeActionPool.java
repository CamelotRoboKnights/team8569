package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 *
 * Defines the behavior
 * of a pool of iterative actions that
 * execute, usually linearly, however
 * the flow of execution can be easily
 * altered by the iterative state instance.
 *
 * @author Zigy Lim
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeAction
 * @see IterativeState
 * @see IterativeActionOpMode
 */
public interface IterativeActionPool
{
    /**
     *
     * This method registers the actions
     * that are to be executed.
     *
     * @author Zigy Lim
     * @since 1.0
     */
    void registerActions();

    /**
     * This gives back the iterative state of the pool.
     *
     * @return the iterative state instance.
     *
     * @author Zigy Lim
     * @since 1.0
     */
    IterativeState getIterativeState();
}
