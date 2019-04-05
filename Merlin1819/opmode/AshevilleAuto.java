package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

/**
 *
 * The base class for second qualifier operations.
 *
 * @author Zachary
 * @author Eli
 * @author Zigy
 *
 * @version 1.0
 * @since 1.0
 *
 * @see AshevilleDepotAutoThrow
 * @see AshevilleCraterAuto
 */
public abstract class AshevilleAuto extends OpMode {

    protected MecanumHardwareMap hardwareMap;
    private MecanumIMU imu;
    private long startTime;
    private long curTime;
    double elapsedTime;
    private boolean motorShutdown;
    /*DcMotor liftMotor = this.hardwareMap.getLiftMotor();
    DcMotor frontRightMotor = this.hardwareMap.getFrontRightMotor();
    DcMotor frontLeftMotor = this.hardwareMap.getFrontLeftMotor();
    DcMotor backLeftMotor = this.hardwareMap.getBackLeftMotor();
    DcMotor backRightMotor = this.hardwareMap.getBackRightMotor();*/

    /**
     *
     * The time it takes for us to get down.
     *
     * @since 1.0
     */
    static double DOWNTIME = 4.55;

    /**
     *
     * The time it takes for us to go right, to startUnlatching.
     *
     * @since 1.0
     */
    static double FWDTIME = DOWNTIME + 1;

    /**
     *
     */
    static double RIGHTTIME = FWDTIME + 2;
    static double CENTERTIME = RIGHTTIME + .75;

    public void init() {
        motorShutdown = false;
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
        this.hardwareMap.getMarkerServo().setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }


    @Override
    public void loop() {
        curTime = System.currentTimeMillis();
        elapsedTime = (curTime - startTime) / 1000.0;

        telemetry.addData("Start Time", startTime);
        telemetry.addData("Current Time", curTime);
        telemetry.addData("Elapsed Time", elapsedTime);
        telemetry.update();

        if (elapsedTime < DOWNTIME) {
            this.hardwareMap.getLiftMotor().setPower(.5);
        } else if (elapsedTime < FWDTIME) {
            this.hardwareMap.getLiftMotor().setPower(0);

            goForward(.125); //Robot starts sideways and this gets our hook out of the bracket
        } else if (elapsedTime < RIGHTTIME) {

            goRight(.125); //This moves us away from the lander since the robot is sideways
        } else if(elapsedTime < CENTERTIME) {
             goBackward(.125);
        } else {
            if (!motorShutdown) {

                stopMotors();
                motorShutdown = true;

            } else {

                afterLanding();

            }
        }
    }

    protected abstract void afterLanding();

    protected final void spinIntakeServo(Servo.Direction direction)
    {
        this.hardwareMap.getCollectorServo().setDirection(direction);
        this.hardwareMap.getCollectorServo().setPosition(1);
    }

    protected final void stopIntakeServo()
    {
        this.hardwareMap.getCollectorServo().setPosition(0.5);
    }

    protected final void goForward(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(power);
        this.hardwareMap.getFrontRightMotor().setPower(power);
        this.hardwareMap.getBackLeftMotor().setPower(power);
        this.hardwareMap.getBackRightMotor().setPower(power);
    }
    protected final void goLeft(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(power);
        this.hardwareMap.getFrontRightMotor().setPower(-power);
        this.hardwareMap.getBackLeftMotor().setPower(-power);
        this.hardwareMap.getBackRightMotor().setPower(power);
    }
    protected final void goRight(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(-power);
        this.hardwareMap.getFrontRightMotor().setPower(power);
        this.hardwareMap.getBackLeftMotor().setPower(power);
        this.hardwareMap.getBackRightMotor().setPower(-power);
    }
    protected final void goBackward(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(-power);
        this.hardwareMap.getFrontRightMotor().setPower(-power);
        this.hardwareMap.getBackLeftMotor().setPower(-power);
        this.hardwareMap.getBackRightMotor().setPower(-power);
    }

    protected void stopMotors() {
        this.hardwareMap.getFrontLeftMotor().setPower(0);
        this.hardwareMap.getFrontRightMotor().setPower(0);
        this.hardwareMap.getBackLeftMotor().setPower(0);
        this.hardwareMap.getBackRightMotor().setPower(0);


        this.hardwareMap.getLiftMotor().setPower(0);
    }
    protected final void mark() {
        this.hardwareMap.getMarkerServo().setPosition(1.0);
    }
}
