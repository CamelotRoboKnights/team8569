package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 *
 * Wraps an {@link BNO055IMU} sensor.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see MecanumHardwareMap
 * @see MecanumRobot
 */
public final class MecanumIMU
{
    /**
     *
     * The {@link AxesReference} for determining
     * whether the vector normal moves with the x, y, and z
     * or stays the same.
     *
     * @since 1.0
     */
    private static final AxesReference AXES_REFERENCE = AxesReference.INTRINSIC;

    /**
     *
     * The {@link AxesOrder} for determining the order
     * at which the x, y, and z axis are detected.
     *
     * @since 1.0
     */
    private static final AxesOrder     AXES_ORDER     = AxesOrder.ZYX;

    /**
     *
     * The {@link AngleUnit} for determining whether to use
     * degrees or radians.
     *
     * @since 1.0
     */
    private static final AngleUnit     ANGLE_UNIT     = AngleUnit.DEGREES;

    /**
     *
     * The wrapped {@link BNO055IMU} sensor.
     *
     * @since 1.0
     */
    private BNO055IMU imu;

    /**
     * Constructs a new {@code MacanumIMU}.
     * Throws an exception if an attempt
     * to pass a null
     * map is made.
     *
     * @param map
     */
    public MecanumIMU(HardwareMap map)
    {
        if (map == null) {
            throw new NullPointerException("Cannot have a null HardwareMap - imu parameter.");
        } else {
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json";
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            imu = map.get(BNO055IMU.class, "imu");
            imu.initialize(parameters);
        }
    }

    public double getOrientation()
    {
        return -this.imu.getAngularOrientation(MecanumIMU.AXES_REFERENCE,
                                               MecanumIMU.AXES_ORDER,
                                               MecanumIMU.ANGLE_UNIT).firstAngle;
    }
}
