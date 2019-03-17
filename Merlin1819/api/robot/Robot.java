package org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot;

/**
 *
 * Defines a standard
 * way to manipulate a robots motors,
 * rotation, etc.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see AbstractRobot
 */
public interface Robot
{
    /**
     *
     * Defines
     * the various ways a robot
     * can move without rotation.
     * Left would assume strafing left,
     * right would assume strafing right, etc.
     *
     * @author Zigy Lim
     *
     * @version 1.0
     * @since 1.0
     *
     * @see #startMoving(MovementDirection, float)
     * @see RotationDirection
     */
    enum MovementDirection {
        FORWARD, BACKWARD, LEFT, RIGHT
    }

    /**
     *
     * Defines the ways
     * a robot can rotate.
     *
     * @author Zigy Lim
     *
     * @version 1.0
     * @since 1.0
     *
     * @see #startRotating(RotationDirection, float)
     * @see RotationDirection
     */
    enum RotationDirection {
        LEFT, RIGHT
    }

    /**
     *
     * Returns a robot controller of type T.
     * Implementations that do not define extended
     * functionality may return null or throw an exception.
     *
     * @param <T> the type of {@link RobotComponentController} returned.
     * @param clazz the type of the {@link RobotComponentController}.
     *
     * @return a robot component controller.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see RobotComponentController
     * @see #hasRobotComponentController(Class)
     */
    <T extends RobotComponentController> T getRobotComponentController(Class<? extends T> clazz);

    /**
     *
     * Returns whether this robot has the specified
     * {@link RobotComponentController}.
     *
     * @param clazz the class for the specified {@link RobotComponentController}.
     *
     * @return whether the robot has the specified {@link RobotComponentController}.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see RobotComponentController
     * @see #getRobotComponentController(Class)
     */
    boolean hasRobotComponentController(Class<? extends RobotComponentController> clazz);

    /**
     *
     * Moves the robot continuously
     * until the {@link #stopMoving()} method is called.
     *
     * @param direction The direction that the robot will move
     * @param power the power, from -1 to 1, that the robot will use
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #stopMoving()
     * @see #startMoving(float, float)
     * @see MovementDirection
     */
    void startMoving(MovementDirection direction, float power);

    /**
     *
     * Moves the robot freely
     * at the certain number of degrees.
     *
     * In general, some implementations
     * may throw an exception if one
     * attempts to move like this.
     *
     * @param degrees the degree at which to holonomically move the robot
     * @param power the power, from -1 to 1, that the robot will use
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #stopMoving()
     * @see #startMoving(MovementDirection, float)
     */
    void startMoving(float degrees, float power);

    /**
     *
     * Stops the continuous
     * method {@link #startMoving}.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #startMoving(MovementDirection, float)
     * @see #startMoving(float, float)
     */
    void stopMoving();

    /**
     *
     * Rotates the robot in a
     * certain direction with specified power.
     *
     *
     * @param direction the direction we want to rotate
     * @param power the power that we will be using to rotate
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #stopRotating()
     * @see MovementDirection
     */
    void startRotating(RotationDirection direction, float power);

    /**
     *
     * This method stops the continuous
     * method {@link #startRotating(RotationDirection, float)}.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #startRotating(RotationDirection, float)
     */
    void stopRotating();

    /**
     *
     * Moves the specified
     * direction a certain distance,
     * specified in feet.
     * Returns if this method has finished moving the robot.
     *
     * Note that if encoders are not supported
     * on this implementation then this method
     * will either throw an exception or return.
     *
     *
     * @param direction the direction to move the robot
     * @param feet the amount of meters to move the robot
     *
     * @return whether the robot has finished moving.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #startMoving(MovementDirection, float)
     * @see #startMoving(float, float)
     */
    boolean moveDistance(MovementDirection direction, float feet);

    /**
     *
     * Moves the robot at the specified
     * number of degrees the specified distance.
     * Returns if this method has finished moving the robot.
     *
     * In general, implementations that do not
     * support encoders will throw an exception.
     *
     *
     * @param degrees the number of degrees to move by
     * @param feet the number of meters to move by
     *
     * @return whether the robot has finished moving.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #moveDistance(MovementDirection, float)
     * @see #startMoving(float, float)
     * @see #startMoving(MovementDirection, float)
     */
    boolean moveDistance(float degrees, float feet);

    /**
     *
     * Rotates the specified
     * direction a certain distance,
     * specified in degrees.
     * Returns if this method has finished rotating the robot.
     *
     * Note that if encoders are not supported
     * on this implementation then this method
     * will either throw an exception or return.
     *
     * @param direction the direction to move, left or right
     * @param degrees the number of degrees to move
     *
     * @return whether the robot has finished moving.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #startRotating(RotationDirection, float)
     * @see MovementDirection
     */
    boolean rotateDistance(RotationDirection direction, float degrees);

    /**
     *
     * Returns the orientation in degrees
     * If gyroscopes are not supported,
     * this method may throw an exception.
     *
     * @return the orientation.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #gyroSupported()
     */
    float getOrientation();

    /**
     *
     * Returns whether encoders are supported.
     * In general, implementations should provide
     * some warning that encoders are not supported
     * by throwing an exception.
     *
     * @return whether encoders are supported.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #moveDistance(MovementDirection, float)
     * @see #moveDistance(float, float)
     * @see #rotateDistance(RotationDirection, float)
     */
    boolean encodersSupported();

    /**
     *
     * Returns whether the gyroscope is supported.
     * In general, implementations should provide
     * some warning that encoders are not supported
     * by throwing an exception.
     *
     * @return whether the gyroscope is supported.
     *
     * @author Zigy Lim
     *
     * @since 1.0
     *
     * @see #getOrientation()
     */
    boolean gyroSupported();
}
