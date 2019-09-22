package org.firstinspires.ftc.teamcode.team.Merlin2020.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.function.Function;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.*;

@Autonomous(name = "AutonomousTestFunction", group = "test")
public class AutonomousMovementTest extends LinearOpMode {

    private MecanumHardwareMap mecanumHardwareMap;
    private Function<Double, Double> conversionFunction;

    public enum ConversionUnit implements Function<Double, Double> {
        INCH_UNIT {
            private static final double CONV = (537.6 * 40) / (2 * PI * 2);

            @Override
            public Double apply(Double unit) {
                return unit * CONV;
            }
        }
    }

    private void initialize() {
        this.mecanumHardwareMap = new MecanumHardwareMap(this.hardwareMap);
        this.setConversionFunction(ConversionUnit.INCH_UNIT);
    }

    @Override
    public void runOpMode() {
        final String initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        telemetry.addData("init", "Autonomous initialized at " + initDate);
        telemetry.update();

        this.initialize();

        this.waitForStart();

        this.telemetry.addData("In runOpMode", "in run opmdoe");
        //   while(true) ;
//
//        //Units in inches
        this.move(0, 12, 0.5);
    }

    public void setConversionFunction(Function<Double, Double> conversionFunction) {
        this.conversionFunction = conversionFunction;
    }

    /**
     * Moves diagonally with the right and forward parameters.
     */
    public void move(final double right, final double forward, final double power) {
        this.telemetry.addData("In move mode", "" + this.isStopRequested());
        if (this.opModeIsActive()) {
            this.telemetry.addData("Inside", "");
            final double magnitude = hypot(right, forward);
            final double rightNormalized = right / magnitude,
                    forwardNormalized    = forward / magnitude;

            //Uses a mathematical transformation to calculate the powers
            final double frontLeftPower = forwardNormalized * power + rightNormalized * power,
                    frontRightPower     = forwardNormalized * power - rightNormalized * power,
                    backLeftPower       = forwardNormalized * power - rightNormalized * power,
                    backRightPower      = forwardNormalized * power + rightNormalized * power;

            final double encoderTicks = this.conversionFunction.apply(magnitude);

            final DcMotor frontLeft = this.mecanumHardwareMap.getFrontLeftMotor(),
                    frontRight      = this.mecanumHardwareMap.getFrontRightMotor(),
                    backLeft        = this.mecanumHardwareMap.getBackLeftMotor(),
                    backRight       = this.mecanumHardwareMap.getBackRightMotor();

            this.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeft.setTargetPosition((int) round(frontLeftPower * encoderTicks));
            frontRight.setTargetPosition((int) round(frontRightPower * encoderTicks));
            backLeft.setTargetPosition((int) round(backLeftPower * encoderTicks));
            backRight.setTargetPosition((int) round(backRightPower * encoderTicks));

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

            //Block until task is finished
            while (this.opModeIsActive() && !(frontLeft.isBusy() &&
                                              frontRight.isBusy() &&
                                              backLeft.isBusy() &&
                                              backRight.isBusy())) this.idle();

            this.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    private void setMotorModes(DcMotor.RunMode runMode) {
        this.mecanumHardwareMap.getFrontLeftMotor().setMode(runMode);
        this.mecanumHardwareMap.getFrontRightMotor().setMode(runMode);
        this.mecanumHardwareMap.getBackLeftMotor().setMode(runMode);
        this.mecanumHardwareMap.getBackRightMotor().setMode(runMode);
    }
}
