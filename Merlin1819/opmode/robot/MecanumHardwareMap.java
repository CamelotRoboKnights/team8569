package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 *
 * Provides a facility
 * for accessing the members of
 * a mecanum robot in a safe way.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see MecanumIMU
 * @see MecanumRobot
 */
public final class MecanumHardwareMap
{
    /**
     *
     * Phone wheel motor component names.
     *
     * @since 1.0
     *
     * @see #MecanumHardwareMap(HardwareMap)
     * @see #getFrontLeftMotor()
     * @see #getFrontRightMotor()
     * @see #getBackLeftMotor()
     * @see #getBackRightMotor()
     */
    private static final String FRONT_LEFT_MOTOR_NAME = "frontLeftMotor",
                               FRONT_RIGHT_MOTOR_NAME = "frontRightMotor",
                                 BACK_LEFT_MOTOR_NAME = "backLeftMotor",
                                BACK_RIGHT_MOTOR_NAME = "backRightMotor";

    private static final String COLLECTOR_SERVO_NAME = "collectorServo";

    /**
     *
     * Phone arm component motor names.
     *
     * @since 1.0
     *
     * @see #getRetractArmMotor()
     * @see #getCurlArmMotor()
     */
    private static final String RETRACT_ARM_NAME = "retractArmMotor",
                                   CURL_ARM_NAME = "curlArmMotor",
                                 LIFT_MOTOR_NAME = "liftMotor";

    private static final String COLOR_SENSOR_NAME = "colorSensor";
    private static final String MARKER_SERVO_NAME = "markerServo";

    /**
     *
     * The default power level for the motors
     * when they are initialized.
     *
     * @since 1.0
     *
     * @see #MecanumHardwareMap(HardwareMap)
     */
    private static final float DEFAULT_POWER = 0F;

    /**
     *
     * The default {@code DcMotor.RunMode} for
     * the motors.
     *
     * @since 1.0
     *
     * @see #MecanumHardwareMap(HardwareMap)
     */
    private static final DcMotor.RunMode DEFAULT_RUN_MODE = DcMotor.RunMode.RUN_USING_ENCODER;

    /**
     *
     * The default {@link DcMotor.ZeroPowerBehavior} for all of the motors.
     *
     * @since 1.0
     */
    private static final DcMotor.ZeroPowerBehavior DEFAULT_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;

    /**
     *
     * The various {@link DcMotor} fields.
     *
     * @since 1.0
     *
     * @see #getFrontLeftMotor()
     * @see #getFrontRightMotor()
     * @see #getBackLeftMotor()
     * @see #getBackRightMotor()
     */
    private DcMotor frontLeftMotor, frontRightMotor,
                     backLeftMotor, backRightMotor;

    /**
     *
     * These fields hold the motors that
     */
    private DcMotor curlArmMotor, retractArmMotor, liftMotor;

    private Servo collectorServo;

    private Servo markerServo;

    /**
     *
     * The {@link MecanumIMU} for orientation
     * detection.
     */
    private MecanumIMU mechanumIMU;

    /**
     *
     * The {@link HardwareMap} for this {@code Robot}.
     */
    private HardwareMap map;

    /**
     * @author Zigy Lim
     *
     * Constructs a new instance
     * of the class {@link MecanumHardwareMap}.
     *
     * @param map the {@link HardwareMap} to be used
     */
    public MecanumHardwareMap(HardwareMap map)
    {
        this.map = map;

        this.frontLeftMotor  = map.dcMotor.get(FRONT_LEFT_MOTOR_NAME);
        this.frontRightMotor = map.dcMotor.get(FRONT_RIGHT_MOTOR_NAME);
        this.backLeftMotor   = map.dcMotor.get(BACK_LEFT_MOTOR_NAME);
        this.backRightMotor  = map.dcMotor.get(BACK_RIGHT_MOTOR_NAME);

        this.collectorServo  = map.servo.get(COLLECTOR_SERVO_NAME);

        this.retractArmMotor = map.dcMotor.get(RETRACT_ARM_NAME);
        this.curlArmMotor    = map.dcMotor.get(CURL_ARM_NAME);
        this.liftMotor       = map.dcMotor.get(LIFT_MOTOR_NAME);

        this.markerServo     = map.servo.get(MARKER_SERVO_NAME);

        this.collectorServo  = map.servo.get(COLLECTOR_SERVO_NAME);

        //this.mechanumIMU = new MecanumIMU(map);

        this.frontLeftMotor.setPower(DEFAULT_POWER);
        this.frontRightMotor.setPower(DEFAULT_POWER);
        this.backLeftMotor.setPower(DEFAULT_POWER);
        this.backRightMotor.setPower(DEFAULT_POWER);

        this.curlArmMotor.setPower(DEFAULT_POWER);
        this.retractArmMotor.setPower(DEFAULT_POWER);
        this.liftMotor.setPower(DEFAULT_POWER);

        this.markerServo = map.servo.get(MARKER_SERVO_NAME);

        this.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

       this.frontLeftMotor.setMode(DEFAULT_RUN_MODE);
       this.frontRightMotor.setMode(DEFAULT_RUN_MODE);
       this.backLeftMotor.setMode(DEFAULT_RUN_MODE);
       this.backRightMotor.setMode(DEFAULT_RUN_MODE);

       this.retractArmMotor.setMode(DEFAULT_RUN_MODE);
       this.curlArmMotor.setMode(DEFAULT_RUN_MODE);
       this.liftMotor.setMode(DEFAULT_RUN_MODE);

       this.frontLeftMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
       this.frontRightMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
       this.backLeftMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
       this.backRightMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);

       this.retractArmMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
       this.curlArmMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
       this.liftMotor.setZeroPowerBehavior(DEFAULT_POWER_BEHAVIOR);
    }

    /**
     * @author Zigy Lim
     *
     * Returns the front left motor on the robot.
     *
     * @return the front left motor
     */
    public DcMotor getFrontLeftMotor()
    {
        return this.frontLeftMotor;
    }

    /**
     * @author Zigy Lim
     *
     * Returns the front right motor on the robot.
     *
     * @return the front right motor
     */
    public DcMotor getFrontRightMotor()
    {
        return this.frontRightMotor;
    }

    /**
     * @author Zigy Lim
     *
     * Returns the back left motor on the robot.
     *
     * @return the back left motor
     */
    public DcMotor getBackLeftMotor()
    {
        return this.backLeftMotor;
    }

    /**
     * @author Zigy Lim
     *
     * Returns the back left motor on the robot.
     *
     * @return the back right motor
     */
    public DcMotor getBackRightMotor()
    {
        return this.backRightMotor;
    }

    /**
     *
     * @return
     */
    public DcMotor getRetractArmMotor() {
        return this.retractArmMotor;
    }

    public DcMotor getCurlArmMotor()
    {
        return this.curlArmMotor;
    }

//    public DcMotor getCollectorMotor() {
//        return this.collectorMotor;
//    }


    public DcMotor getLiftMotor()
    {
        return this.liftMotor;
    }

    public Servo getMarkerServo() {
        return this.markerServo;
    }

    public Servo getCollectorServo()
    {
        return this.collectorServo;
    }


    /*public MecanumIMU getMecanumIMU()
    {
        return this.mechanumIMU;
    } */
}
