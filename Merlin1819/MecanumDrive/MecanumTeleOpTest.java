package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast.WestCoastHardware;
//import org.firstinspires.ftc.teamcode.team.Merlin1718.HolonomicClass;

/**
 * Created by Zachary Ireland on 11/24/2018.
 */

//@TeleOp(name = "MecanumTeleOp", group = "Cardinal")
//@Disabled //Uncomment this if it is not wanted on the phone
public class MecanumTeleOpTest extends OpMode {

    public MecanumHardware robot = new MecanumHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start()
    {

    }//This runs when the start button is pressed

    //boolean isPressed = false;

    @Override
    public void loop() {//This runs while opmode is active
        boolean pressed = gamepad1.left_trigger > .9 && gamepad1.right_trigger > .9;


        if (gamepad1.right_trigger > .9) {
            robot.backRightMotor.setPower(1);
            robot.backLeftMotor.setPower(-1);
            robot.frontRightMotor.setPower(1);
            robot.frontLeftMotor.setPower(-1);
        } else if (gamepad1.left_trigger > .9) {
            robot.frontLeftMotor.setPower(1);
            robot.frontRightMotor.setPower(-1);
            robot.backLeftMotor.setPower(1);
            robot.backRightMotor.setPower(-1);
        } else {
            robot.frontLeftMotor.setPower(0);
            robot.frontRightMotor.setPower(0);
            robot.backLeftMotor.setPower(0);
            robot.backRightMotor.setPower(0);

        }
    }
}

    /*public void Drive(MecanumClass.JoyValues givenXYZ) {
        if (Math.abs(givenXYZ.x) >= 0.01 || Math.abs(givenXYZ.y) >= 0.01) {

            double frontLeftMotorPower = givenXYZ.y + givenXYZ.x;
            double frontRightMotorPower = givenXYZ.y - givenXYZ.x;
            double backRightMotorPower = givenXYZ.y + givenXYZ.x;
            double backLeftMotorPower = givenXYZ.y - givenXYZ.x;


            // otherwise move motors accordingly
        } else if (Math.abs(givenXYZ.z) >= .01) {
            double frontLeftMotorPower = givenXYZ.z * .5;
            double frontRightMotorPower = -givenXYZ.z * .5;
            double backRightMotorPower = -givenXYZ.z * .5;
            double backLeftMotorPower = givenXYZ.z * .5;



        } else {//If none of these is true turn the power off to the motors to stop the robot

        }
    }

        }


       /*
       if (pressed && !isPressed) {
            isPressed = true;
        } else if (!pressed&& isPressed) {
            isPressed = false;
            relic = !relic;
        }
        if(!relic) {
            robot.westCoast.teleOp(gamepad1, true);
            robot.glyphCollector.teleOp(gamepad2);
        } else {
            robot.westCoast.teleOp(gamepad1, false);
            robot.relic.teleOp(gamepad2);
        }
        */






