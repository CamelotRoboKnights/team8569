package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

/**
 * Defines the state of the state machine.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeActionPool
 * @see IterativeAction
 * @see IterativeActionOpMode
 */
public final class IterativeState
{
    /**
     *
     * The queue counter to determine
     * which action will be returned
     * to be executed next.
     *
     * @since 1.0
     *
     * @see #actions
     */
    private int counter;

    /**
     *
     * The actions to be executed in a (usually)
     * linear fashion.
     *
     * @since 1.0
     *
     * @see #counter
     */
    private IterativeAction[] actions;

    /**
     *
     * Indicates whether the
     * current action has been
     * is completed or not.
     *
     * @since 1.0
     */
    private boolean isCompleted;

    /**
     *
     * Indicates whether further
     * actions are allowable within the <b>current</b> action to be
     * done without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}.
     *
     * @since 1.0
     */
    private boolean isStateChangeAllowable;

    /**
     * Constructs a new {@code IterativeState}.
     * The constructor will throw an {@link IllegalArgumentException}
     * if a zero length or null array reference is passed.
     *
     * @param actions the actions to be passed
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    public IterativeState(IterativeAction[] actions)
    {
        if (actions == null || actions.length == 0) {
            throw new IllegalArgumentException();
        } else {
            this.counter = -1;
            this.actions = actions;
            this.isStateChangeAllowable = true;
        }
    }

    /**
     *
     * Returns if this operation is finished.
     *
     * @return is this operation finished.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     */
    public boolean isFinished()
    {
        return (this.counter + 1 == this.actions.length);
    }

    /**
     *
     * Restarts the series of actions, allowing for the action(s)
     * to be repeated.
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    public void restart()
    {
        if (this.isStateChangeAllowable) {
            this.counter = -1;
            this.setStateChangeAllowable(false);
        } else throw new StateChangeDisallowedException();
    }

    /**
     *
     * Skips over every action, meaning that this
     * Action will end every other action, assuming
     * that a call to {@link #restart()}
     * wasn't made.
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    public void stop()
    {
        if (this.isStateChangeAllowable) {
            while (this.getNextAction() != null) {
                this.setStateChangeAllowable(true);
            }
            this.setStateChangeAllowable(false);
        } else throw new StateChangeDisallowedException();
    }

    /**
     *
     * Restarts the action chain at
     * the action that called it. Therefore,
     * if an action has called this method, the
     * next iteration this method will be executed again.
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    public void restartFromMethod()
    {
        if (this.isStateChangeAllowable) {
            counter--;
            this.setStateChangeAllowable(false);
        } else throw new StateChangeDisallowedException();
    }

    /**
     *
     * Returns the next linear operation of execution.
     * Note that this function could also act as a skipping
     * function, if you need to skip over an operation.
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @return the next action if there is one.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    public IterativeAction getNextAction()
    {
        if (!this.isStateChangeAllowable)
            throw new StateChangeDisallowedException();
        else if (this.isFinished()) {
            this.setStateChangeAllowable(false);
            return null;
        } else {
            this.setStateChangeAllowable(false);
            return this.actions[++counter];
        }
    }

    /**
     *
     * Skips over a certain number of actions.
     * If an attempt to skip over zero or less actions
     * is made, this method will throw an exception.
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @param number the number of instructions to skip
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #getNextAction()
     */
    public void skipActions(int number)
    {
        if (!this.isStateChangeAllowable)
            throw new StateChangeDisallowedException();
        else if (number < 1) throw new IllegalArgumentException("Cannot skip below 1 actions.");
        else if (this.actions.length - 1 < this.counter + number)
            throw new IllegalArgumentException("Cannot skip past actions.");
        else {
            for (int i = 0; i < number; i++) {
                this.setStateChangeAllowable(true);
                this.getNextAction();
            }

            this.setStateChangeAllowable(false);
        }
    }

    /**
     *
     * Returns whether the <b> current </b> operation has finished.
     * If it is not, the action will repeat over again until this
     * method returns true.
     *
     * @return whether the <b> current </b> operation is completed.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #setCompleted(boolean)
     */
    public boolean isCompleted()
    {
        return this.isCompleted;
    }

    /**
     *
     * Sets the <b>current</b> operation to finished.
     *
     * <p>
     * This method will throw a {@link StateChangeDisallowedException}
     * if an attempt to call it twice without an explicit override from
     * {@link #setStateChangeAllowable(boolean)}. The exception should
     * not be caught.
     * <p>
     *
     * @param isCompleted the boolean that sets if the <b>current</b> operation is completed.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #isCompleted()
     */
    public void setCompleted(boolean isCompleted)
    {
        if (this.isStateChangeAllowable) {
            this.isCompleted = isCompleted;
            this.setStateChangeAllowable(false);
        } else throw new StateChangeDisallowedException();
    }

    /**
     *
     * Sets if a state change is allowable within the
     * <b> current </b> action.
     *
     * @param isStateChangeAllowable whether a state change is allowable
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #isStateChangeAllowable()
     * @see #getNextAction()
     * @see #skipActions(int)
     * @see #restart()
     * @see #restartFromMethod()
     * @see #stop()
     */
    public void setStateChangeAllowable(boolean isStateChangeAllowable)
    {
        this.isStateChangeAllowable = isStateChangeAllowable;
    }

    /**
     *
     * Returns whether a state change within the <b> current </b>
     * action is allowable.
     *
     * @return whether a state change within the <b> current </b> action is allowable.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #setStateChangeAllowable(boolean)
     */
    public boolean isStateChangeAllowable()
    {
        return this.isStateChangeAllowable;
    }
}
