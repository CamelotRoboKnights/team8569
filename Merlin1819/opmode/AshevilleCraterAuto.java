package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@Autonomous(name = "AshevilleCraterAuto")
public class AshevilleCraterAuto extends OpMode {

    private MecanumHardwareMap hardwareMap;
    private MecanumIMU imu;
    private ColorSensor color;
    private long startTime;
    private long curTime;
    private long elapsedTime;
    /*DcMotor liftMotor = this.hardwareMap.getLiftMotor();
    DcMotor frontRightMotor = this.hardwareMap.getFrontRightMotor();
    DcMotor frontLeftMotor = this.hardwareMap.getFrontLeftMotor();
    DcMotor backLeftMotor = this.hardwareMap.getBackLeftMotor();
    DcMotor backRightMotor = this.hardwareMap.getBackRightMotor();*/

    public void init() {


        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis() / 1000;
    }


    @Override
    public void loop() {
        curTime = System.currentTimeMillis() / 1000;
        elapsedTime = curTime - startTime;

        telemetry.addData("Start Time", startTime);
        telemetry.addData("Current Time", curTime);
        telemetry.addData("Elapsed Time", elapsedTime);
        telemetry.update();

        if (elapsedTime < 9.5) {
            this.hardwareMap.getLiftMotor().setPower(.5);
        } else if (elapsedTime < 11) {
            this.hardwareMap.getLiftMotor().setPower(0);

            this.hardwareMap.getFrontLeftMotor().setPower(.125);
            this.hardwareMap.getFrontRightMotor().setPower(.125);
            this.hardwareMap.getBackLeftMotor().setPower(.125);
            this.hardwareMap.getBackRightMotor().setPower(.125);
        } else if (elapsedTime < 13) {
            this.hardwareMap.getFrontRightMotor().setPower(.25);
            this.hardwareMap.getFrontLeftMotor().setPower(-.25);
            this.hardwareMap.getBackRightMotor().setPower(-.25);
            this.hardwareMap.getBackLeftMotor().setPower(.25);
        }else if (elapsedTime < 16) {
            this.hardwareMap.getFrontRightMotor().setPower(.75);
            this.hardwareMap.getFrontLeftMotor().setPower(-.75);
            this.hardwareMap.getBackRightMotor().setPower(-.75);
            this.hardwareMap.getBackLeftMotor().setPower(.75);
        } else {
            this.hardwareMap.getFrontLeftMotor().setPower(0);
            this.hardwareMap.getFrontRightMotor().setPower(0);
            this.hardwareMap.getBackLeftMotor().setPower(0);
            this.hardwareMap.getBackRightMotor().setPower(0);

            this.hardwareMap.getLiftMotor().setPower(0);
        }
    }
}
