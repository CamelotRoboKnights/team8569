package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Defines the means
 * to mark a method that it implements
 * the IterativeAction class.
 * Note that order should,
 * but does not have to start at zero;
 * also note that if two indexes have
 * the same order, the order at which they
 * would be executed is undefined. This is useful
 * for a path where either can be executed.
 *
 * @author Zigy Lim
 * @version 1.0
 * @since 1.0
 *
 * @see Action#order()
 * @see IterativeAction
 * @see IterativeActionPool
 * @see IterativeActionOpMode
 * @see IterativeState
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action
{
    /**
     * Defines the order in which
     * the methods will be called.
     * if it is -1, there must be no
     * other annotated methods or else
     * it will throw an exception.
     * If there is only one method,
     * order must be -1, or an exception
     * will be thrown.
     *
     * @author Zigy Lim
     * @since 1.0
     */
    int order() default -1;

}
