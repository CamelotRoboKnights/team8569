package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    private static final String FRONT_LEFT_NAME = "frontLeftMotor",
                               FRONT_RIGHT_NAME = "frontRightMotor",
                                 BACK_LEFT_NAME = "backLeftMotor",
                                BACK_RIGHT_NAME = "backRightMotor";

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
                                   CURL_ARM_NAME = "curlArmMotor";

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
    private DcMotor curlArmMotor, retractArmMotor;

    /**
     *
     * The {@link MecanumIMU} for orientation
     * detection.
     */
    private MecanumIMU mecanumIMU;

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

        this.frontLeftMotor  = map.dcMotor.get(MecanumHardwareMap.FRONT_LEFT_NAME);
        this.frontRightMotor = map.dcMotor.get(MecanumHardwareMap.FRONT_RIGHT_NAME);
        this.backLeftMotor   = map.dcMotor.get(MecanumHardwareMap.BACK_LEFT_NAME);
        this.backRightMotor  = map.dcMotor.get(MecanumHardwareMap.BACK_RIGHT_NAME);

        this.retractArmMotor = map.dcMotor.get(MecanumHardwareMap.RETRACT_ARM_NAME);
        this.curlArmMotor    = map.dcMotor.get(MecanumHardwareMap.CURL_ARM_NAME);

        //this.mecanumIMU = new MecanumIMU(map);

        this.frontLeftMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.frontRightMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.backLeftMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.backRightMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);

        this.retractArmMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.curlArmMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);

        this.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

       this.frontLeftMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.frontRightMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.backLeftMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.backRightMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);

       this.retractArmMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.curlArmMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
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

    public DcMotor getRetractArmMotor() {
        return this.retractArmMotor;
    }

    public DcMotor getCurlArmMotor()
    {
        return this.curlArmMotor;
    }

    /*public MecanumIMU getMecanumIMU()
    {
        return this.mecanumIMU;
    } */
}
