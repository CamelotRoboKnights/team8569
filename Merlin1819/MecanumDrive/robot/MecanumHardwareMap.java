package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @author Zigy Lim
 * @version 1.0
 *
 * This class provides a facility
 * for accessing the members of
 * a mecanum robot in a safe way.
 */
public final class MecanumHardwareMap
{
    private static final String FRONT_LEFT_NAME = "frontLeftMotor",
                               FRONT_RIGHT_NAME = "frontRightMotor",
                                 BACK_LEFT_NAME = "backLeftMotor",
                                BACK_RIGHT_NAME = "backRightMotor";

    private static final float DEFAULT_POWER = 0F;

    private static final DcMotor.RunMode DEFAULT_RUN_MODE = DcMotor.RunMode.RUN_USING_ENCODER;

    private DcMotor frontLeftMotor, frontRightMotor,
                     backLeftMotor, backRightMotor;

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

        this.frontLeftMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.frontRightMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.backLeftMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);
        this.backRightMotor.setPower(MecanumHardwareMap.DEFAULT_POWER);

        this.frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

       this.frontLeftMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.frontRightMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.backLeftMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
       this.backRightMotor.setMode(MecanumHardwareMap.DEFAULT_RUN_MODE);
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
}
