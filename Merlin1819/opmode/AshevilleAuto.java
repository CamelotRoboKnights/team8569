package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumIMU;

@Autonomous(name = "AshevilleAuto")
public class AshevilleAuto extends OpMode {

    private MecanumHardwareMap hardwareMap;
    private MecanumIMU imu;
    private long startTime;
    private long curTime;
    private long elapsedTime;
    /*DcMotor frontRightMotor = this.hardwareMap.getFrontRightMotor();
    DcMotor frontLeftMotor = this.hardwareMap.getFrontLeftMotor();
    DcMotor backLeftMotor = this.hardwareMap.getBackLeftMotor();
    DcMotor backRightMotor = this.hardwareMap.getBackRightMotor();*/

    public void init() {
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

        if (elapsedTime < 4) {
            this.hardwareMap.getLiftMotor().setPower(1);
        } else if (elapsedTime < 6) {
            this.hardwareMap.getFrontLeftMotor().setPower(.5);
            this.hardwareMap.getFrontRightMotor().setPower(.5);
            this.hardwareMap.getBackLeftMotor().setPower(.5);
            this.hardwareMap.getBackRightMotor().setPower(.5);
        } else if (elapsedTime < 15) {
            this.hardwareMap.getFrontRightMotor().setPower(-1);
            this.hardwareMap.getFrontLeftMotor().setPower(1);
            this.hardwareMap.getBackRightMotor().setPower(1);
            this.hardwareMap.getBackLeftMotor().setPower(-1);
        }
    }
}
