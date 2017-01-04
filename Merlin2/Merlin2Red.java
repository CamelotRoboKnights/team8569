package org.firstinspires.ftc.teamcode.team.Merlin2;//This might need to changed to be in a differnt folder like Merlin1 or K9Robo


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.DifferentialControlLoopCoefficients;
import com.qualcomm.robotcore.util.Range;

import java.lang.annotation.Target;
import java.util.*;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware; //More Import statements may be needed

import java.sql.Time;

@TeleOp(name = "Template", group = "Other")//This NEEDS to be changed tp the name of the code
@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin2Red extends LinearOpMode { //The name after public class needs to be the same as the file name
    long StartTime = 0;
    long TargetTime = 0;
    double LastWorldLinearAccelX;
    double LastWorldLinearAccelY;
    static double LeftLightValue = .1;
    static double RightLightValue = 1.8;
    boolean CheckForWhiteLine = false;

    /* Declare OpMode members. */
    Merlin2Hardware robot = new Merlin2Hardware();//The hardware map needs to be the hardware map of the robot we are using

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //init other variables.

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Where you write the code that you want to run


            telemetry.update();
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

    public String turnToGyroHeading(double TargetHeading) {
        String ReturnValue = "----";
        double CurrentHeading = robot.navx_device.getCompassHeading();
        double HeadingDifference = TargetHeading - CurrentHeading;
        double HeadingScaler = .01;
        double HeadingDiffernceScalled = HeadingDifference * HeadingScaler;
        HeadingDiffernceScalled = Range.clip(HeadingDiffernceScalled, -1, 1);
        robot.Motor1.setPower(-HeadingDiffernceScalled);
        robot.Motor2.setPower(HeadingDiffernceScalled);
        robot.Motor3.setPower(HeadingDiffernceScalled);
        robot.Motor4.setPower(-HeadingDiffernceScalled);
        if (1 <= Math.abs(HeadingDifference)) {
            ReturnValue = "Done";
        } else {
            ReturnValue = "----";
        }
        return ReturnValue;
    }

    public String loadBall() {
        String ReturnValue = "";
        double CurrentTime = System.currentTimeMillis();
        if (StartTime == 0) {
            StartTime = System.currentTimeMillis();
            TargetTime = StartTime + 1000;
        }
        if (TargetTime - CurrentTime <= 10) {
            ReturnValue = "Done";
            robot.LiftCollector.setPower(0);
        } else {
            ReturnValue = "----";
            robot.LiftCollector.setPower(.5);
        }
        return "";
    }

    public String driveUltilWhiteLine(String Direction, String Sensor) {
        double LeftLightLine = .0001;//Set these values to half on white and half on black
        double RightLightLine = .0001;
        double LightRead;
        String ReturnValue = "";
        if (Sensor.equals("Left")) {
            LightRead = robot.LeftLight.getRawLightDetected();
            if (LightRead > LeftLightLine) ReturnValue = "Done";
            else ReturnValue = "----";
        } else if (Sensor.equals("Right")) {
            LightRead = robot.RightLight.getRawLightDetected();
            if (LightRead > RightLightLine) ReturnValue = "Done";
            else ReturnValue = "----";
        }
        robot.Motor1.setPower(.5);
        robot.Motor2.setPower(.5);
        robot.Motor3.setPower(.5);
        robot.Motor4.setPower(.5);
        return ReturnValue;


    }

    public String collisionDetection() {
        double CurrWorldLinearAccelX;//My current acceleration
        double CurrWorldLinearAccelY;
        double CurrentJerkX;//My chang in Acceleration
        double CurrentJerkY;
        final double CollisionThesholdG = 0.5;//The threshold that has to be crossed to trigger a colision
        String ReturnValue = "";//The Value that will be returned

        CurrWorldLinearAccelX = robot.navx_device.getRawAccelX();//Gives the current acceleration
        CurrWorldLinearAccelY = robot.navx_device.getRawAccelY();
        CurrentJerkX = CurrWorldLinearAccelX - LastWorldLinearAccelX;//Figures out the acceleration change
        CurrentJerkY = CurrWorldLinearAccelY - LastWorldLinearAccelY;
        if (CollisionThesholdG < Math.abs(CurrentJerkX) || CollisionThesholdG < Math.abs(CurrentJerkY)) {//Detects if a collision has happened
            if (CurrentJerkX >= CollisionThesholdG) {
                ReturnValue = "YFast";//The robot is going faster in the Y direction
            } //if
            else if (CurrentJerkX <= -CollisionThesholdG) {
                ReturnValue = "YStop";//The robot is going slower in the Y direction
            } //else if

            if (CurrentJerkY >= CollisionThesholdG) {
                ReturnValue = "XFast";//The robot is going faster in the X direction
            } //if
            else if (CurrentJerkY <= -CollisionThesholdG) {
                ReturnValue = "XStop";//The robot is going slower in the X direction
            }//else if
        } //if
        else {
            ReturnValue = "";
        }//else

        LastWorldLinearAccelX = CurrWorldLinearAccelX;
        LastWorldLinearAccelY = CurrWorldLinearAccelY;

        return ReturnValue;
    }

    public double launchBall(double TargetEncoder) {//NEEDS MORE WORK
        double CurrentEncoder = robot.Flipper.getCurrentPosition();
        double OneRotation = 1650;

        if (TargetEncoder - CurrentEncoder < 3) {
            robot.Flipper.setPower(0);
            if (gamepad2.dpad_up) {
                TargetEncoder = TargetEncoder + OneRotation;
            }
        } else {
            robot.Flipper.setPower(.9);
        }
        return TargetEncoder;
    }


    public void lineFollow(String SideOfRobot, String SideOfLine) {// REMEMBER TO SET THE MOTOR DIRECTIONS BACK
        double LightError = 0;
        double LightInegral = 0;
        double LightDerivative = 0;
        double LightErrorScaled;
        double LightInegralScaled;
        double LightDerivativeScaled;
        double LightErrorScaler = 1;
        double LightIntegralScaler = 1;
        double LightDerivativeScaler = 1;

        double TargetLightValue = 0;
        double CurrentLightValue = 0;
        double LastLightError = 0;
        double MotorPowerMotoifier;
        double Motor1Power = 0;
        double Motor2Power = 0;
        double Motor3Power = 0;
        double Motor4Power = 0;

        if (SideOfRobot.equals("left")) {
            TargetLightValue = 0.0771358;//The left is 0.0771358 and the right is .02833764
            CurrentLightValue = robot.LeftLight.getLightDetected();
        } else if (SideOfRobot.equals("right")) {
            TargetLightValue = 0.02833764;
            CurrentLightValue = robot.LeftLight.getLightDetected();
        } else {
            telemetry.addData("Break!!!!!!!!!!!!!!!!!!", "");
            telemetry.update();
        }
        LightError = TargetLightValue - CurrentLightValue;
        LightInegral = LightInegral + LightError;
        LightDerivative = LightError - LastLightError;
        LastLightError = LightError;
        LightErrorScaled = LightError * LightErrorScaler;
        LightInegralScaled = LightInegral * LightIntegralScaler;
        LightDerivativeScaled = LightDerivative * LightDerivativeScaler;
        MotorPowerMotoifier = LightErrorScaled + LightDerivativeScaled + LightInegralScaled;
        if (SideOfLine.equals("left")) {
            Motor1Power = .2 - MotorPowerMotoifier;
            Motor2Power = -.2 + MotorPowerMotoifier;
            Motor3Power = .2 + MotorPowerMotoifier;
            Motor4Power = -.2 - MotorPowerMotoifier;
        } else if (SideOfLine.equals("right")) {
            Motor1Power = .2 + MotorPowerMotoifier;
            Motor2Power = -.2 - MotorPowerMotoifier;
            Motor3Power = .2 - MotorPowerMotoifier;
            Motor4Power = -.2 + MotorPowerMotoifier;
        } else {
            telemetry.addData("Break!!!!!!!!!!!!!!!!!!", "");
            telemetry.update();
        }

        Motor1Power = Range.clip(Motor1Power, -1, 1);
        Motor2Power = Range.clip(Motor2Power, -1, 1);
        Motor3Power = Range.clip(Motor3Power, -1, 1);
        Motor4Power = Range.clip(Motor4Power, -1, 1);
        robot.Motor1.setPower(Motor1Power);
        robot.Motor2.setPower(Motor2Power);
        robot.Motor3.setPower(Motor3Power);
        robot.Motor4.setPower(Motor4Power);

        telemetry.addData("current light value", CurrentLightValue);
        telemetry.addData("light error", LightError);
        telemetry.addData("leght derivitatve", LightDerivative);
        telemetry.addData("light integral", LightInegral);
        telemetry.addData("motor modifier", MotorPowerMotoifier);
        telemetry.addData("motor1", Motor1Power);
        telemetry.addData("motor2", Motor2Power);
        telemetry.addData("motor3", Motor3Power);
        telemetry.addData("motor4", Motor4Power);


    }

    public String DriveUntilHit() {
        double CurrWorldLinearAccelX;//My current acceleration
        double CurrWorldLinearAccelY;
        double CurrentJerkX;//My chang in Acceleration
        double CurrentJerkY;
        final double CollisionThesholdG = 0.5;//The threshold that has to be crossed to trigger a colision
        String ReturnedValue = collisionDetection();
        CurrWorldLinearAccelX = robot.navx_device.getRawAccelX();//Gives the current acceleration
        CurrWorldLinearAccelY = robot.navx_device.getRawAccelY();
        CurrentJerkX = CurrWorldLinearAccelX - LastWorldLinearAccelX;//Figures out the acceleration change
        CurrentJerkY = CurrWorldLinearAccelY - LastWorldLinearAccelY;

        if (CollisionThesholdG <= Math.abs(CurrentJerkX) || CollisionThesholdG <= Math.abs(CurrentJerkY)) {

            if (ReturnedValue == "YStop") {
                robot.Motor1.setPower(0);
                robot.Motor2.setPower(0);
                robot.Motor3.setPower(0);
                robot.Motor4.setPower(0);
                return "done";
            } else if (ReturnedValue == "XStop") {
                robot.Motor1.setPower(0);
                robot.Motor2.setPower(0);
                robot.Motor3.setPower(0);
                robot.Motor4.setPower(0);
                return "done";
            } else {
                return "----";
            }
        } else {
            return "----";
        }
    }
    public String CrossLeftLight() {

        double CurrentLightValue;
        double TargetLightValue;

        CurrentLightValue = robot.LeftLight.getLightDetected();

        if(CurrentLightValue > LeftLightValue & CheckForWhiteLine == true){
            return "done";
        }
        else if(CurrentLightValue > LeftLightValue){
            CheckForWhiteLine = true;
            return("----");
        }
        else{
            telemetry.addData("----","");
            telemetry.update();
        }
        return("----");
    }


    public String IdentifyColor(){ //this is for red side
        //these variables need to be passed in from other vision code
        String BeaconLeftSide;
        String BeaconRightSide;
        if (BeaconLeftSide == "red" & BeaconRightSide == "blue"){
            return "go left";
            telemetry.addData("go left", BeaconLeftSide);
        }
        else if(BeaconLeftSide == "blue" & BeaconRightSide == "red"){
            return "go right";
            telemetry.addData("go right", BeaconLeftSide)
;        }
        else{
            return "bad!!!!!!";
            telemetry.addData("no color detected", "");
        }



    }
}
