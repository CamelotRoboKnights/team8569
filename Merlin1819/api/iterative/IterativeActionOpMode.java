package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * Defines the behavior of a class that implements a linear set of operations.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeAction
 * @see OpMode
 * @see Action
 * @see IterativeState
 */
public abstract class IterativeActionOpMode extends OpMode implements IterativeActionPool
{
    /**
     *
     * The associated {@link IterativeState} object
     * to provide for control with the state machine.
     *
     *
     * @since 1.0
     *
     * @see #registered
     */
    private IterativeState state;

    /**
     *
     * A flag to indicate whether the actions associated
     * with this {@link OpMode} have been initialized.
     *
     * @since 1.0
     *
     * @see #state
     */
    private boolean registered;

    @Override
    public final void init()
    {
        if (!this.registered) registerActions();
        initState();
    }

    /**
     *
     * Constructs a new
     * {@code IterativeActionOpMode}.
     * In general, this class will
     * not initialize members until
     * the init method is called.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    protected IterativeActionOpMode()
    {

    }

    /**
     *
     * This method is used to
     * initialize the
     * state of the robot.
     * It is recommended that initialization
     * be done in here, as every time the OpMode
     * is stopped and then started, the variables
     * will reset.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    protected void initState()
    {

    }

    @Override
    public final IterativeState getIterativeState()
    {
        return this.state;
    }

    @Override
    public void registerActions()
    {
        if (this.registered) {
            throw new ActionsReregisterException("Cannot re-register actions.");
        } else {
            final ArrayList<Method> methods = new ArrayList<>();
            final Class<?> clazz = this.getClass();
            boolean error = false;

            for (Method method: clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Action.class)) {
                    final Class<?>[] parameters = method.getParameterTypes();

                    /*
                     * Check if this method has the correct
                     * parameters. If it doesn't, throw an
                     * exception and report an error.
                     */
                    if (!(parameters.length == 2 &&
                            parameters[0] == IterativeState.class &&
                            parameters[1] == HardwareMap.class)) {
                        try {
                            throw new ActionAnnotationException("The method " + method.getName() +
                                    " does not have the signature (IterativeState, HardwareMap).");
                        } catch (ActionAnnotationException e) {
                            e.printStackTrace();
                            error = true;
                        }
                    }

                    /*
                     * Otherwise, if this method is
                     * abstract OR static, also
                     * throw an exception.
                     */
                    else if (Modifier.isAbstract(method.getModifiers()) ||
                            Modifier.isStatic(method.getModifiers())) {
                        try {
                            throw new ActionAnnotationException("The method " + method.getName() +
                                    " cannot be abstract or static.");
                        } catch (ActionAnnotationException e) {
                            e.printStackTrace();
                            error = true;
                        }
                    }

                    methods.add(method);
                }
            }

            /*
             * If there were no methods
             * that were annotated, simply
             * create an empty state.
             */
            if (methods.size() < 1) this.state = new IterativeState(new IterativeAction[0]);

            /*
             * Otherwise, if its size is
             * one, check if it is correctly
             * annotated with the default order
             * value; if it is not, throw an exception.
             */
            else if (methods.size() == 1) {
                final Method method = methods.get(0);
                if (method.getAnnotation(Action.class).order() != -1) {
                    try {
                        throw new ActionAnnotationException("The method " + method.getName() +
                        " needs the default order value.");
                    } catch (ActionAnnotationException e) {
                        e.printStackTrace();
                        error = true;
                    }
                }
            }

            /*
             * Otherwise, if the methods size
             * is more than one, iterate through
             * each method, and if it has the default
             * value or a value that is not -1 and below zero,
             * throw an exception.
             */
            else {
                for (Method method: methods) {
                    if (method.getAnnotation(Action.class).order() == -1) {
                        try {
                            throw new ActionAnnotationException("The method " + method.getName() +
                            " has the default annotation value even though multiple methods are present.");
                        } catch (ActionAnnotationException e) {
                            e.printStackTrace();
                             error = true;
                        }
                    } else if (method.getAnnotation(Action.class).order() < 0) {
                        try {
                            throw new ActionAnnotationException("The method " + method.getName() +
                            " has an invalid order value: " + method.getAnnotation(Action.class).order() + ".");
                        } catch (ActionAnnotationException e) {
                            e.printStackTrace();
                            error = true;
                        }
                    }
                }
            }

            /*
             * If there is an error, throw
             * a runtime exception and exit
             * to avoid further exceptions.
             */
            if (error) {
                throw new RuntimeException("There was an error while registering methods.");
            } else {
                final ArrayList<IterativeAction> actions = new ArrayList<>();

                /*
                 * Sort the methods annotated orders
                 * so that an order of zero is executed
                 * before an order of one.
                 */
                Collections.sort(methods, new Comparator<Method>() {
                    @Override
                    public int compare(Method m1, Method m2) {
                        return m1.getAnnotation(Action.class).order() -
                                m2.getAnnotation(Action.class).order();
                    }
                });

                for (final Method method: methods) {

                    /*
                     * Wrap each methods reflective
                     * invocation in an interface.
                     */
                    actions.add(new IterativeAction() {

                        @Override
                        public void execute(IterativeState state, HardwareMap map) {
                            try {
                                method.invoke(IterativeActionOpMode.this, state, map);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                /*
                 * Create a state based on
                 * the list of actions.
                 */
                this.state = new IterativeState(actions.toArray(new IterativeAction[0]));
            }
        }
    }

    @Override
    public final void loop()
    {

        if (!this.state.isFinished()) {
            this.state.getNextAction().execute(this.state, this.hardwareMap);
        } else {
            this.requestOpModeStop();
        }
    }

    @Override
    public final void stop()
    {
        this.state.restart();
    }
}
