/*
* Cases:
*
* 1. Launch ball 1 Using encoder
* 2. Spin spinner and launch ball 2 Using encoder
* 3. Go forward using NavX
* 4. Turn using NavX
* 5. Determine both beacon colors Using camera
* 6. Drive strait till over light sensor. Using NavX
* 7. Turn using NavX to strait in front of first beacon
* 8. If left go to L1 if right go to R1
* 9. If left to to A if right go to B
*
*
*
*           First beacon is left
* L1. Drive right till it sees the left sensor. Using NavX
* L2. Drive forward till contact using Navx
* L3. Drive backwards Using NavX
* L4. Drive Right till crossed both sensors
* L5. Go to case 9
*
*           First beacon is Right
* R1. Drive left till it sees the right sensor Using NavX
* R2. Drive forward till contact using Navx
* R3. Drive backwards using NavX
* R4. Drive Right till crossed both sensors
* R5. Go to case 9
*
*           Second beacon is Left
* A1. Drive left till it sees the left sensor using NavX
* A2. Drive forward till contact Using Navex
*
*           Second beacon is Right
* B1. Drive left till it sees right sensor using NavX
* B2. Drive forward till contact Using NaveX
*
*
 */
package org.firstinspires.ftc.teamcode.Merlin1;


import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.lasarobotics.vision.ftc.resq.Beacon;
import java.security.Permission;
import java.sql.Time;
import java.util.Currency;

@Autonomous(name = "RED", group = "Merlin1")


public class Merlin1AutonomousRed extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);


        double ODLValue;//The value of the left Optical Distance Sensor
        double ODRValue;//The value of the right Optical Distance Sensor
        double RangeValue = 1;//The value of the range sensor
        double LastWorldRangeValue;
        double CompassValue;//The value of the compass sensor
        double OrignalCompassValue = 1;//The orignal compass sensor
        long CurrentWorldLinearAccel = 1;
        double LastWorldLinearAccel;

        double RunTime;
        double TargetRuntime =1;


        double Motor1BallHit = 0;
        double Motor2BallHit;
        double Motor3BallHit;
        double Motor4BallHit;


        String WAIT = "WAIT";

        double TimeInCase = 1;//How many times the case has gone through, used when making things run the first time comming through
        double TimeInCase2 = 1;
        String FirstBeaconColor = "None";//The color of the first beacon
        String SecondBeaconColor = "None";//The color of the second beacon

        String CurentCase = "FirstTimeThrough";//This is the value in the switch that defines what case it is in
        String Previouscase = "None";//This is the value for what the PreviousCase was

        String FirstTimeThrough = "FirstTimeThrough";//The case that does nothing but initalize things



        String LaunchBall = "LaunchBall";//The first case where the ball is launched
        double BallFlickingSpeed = 0.9; //The speed that the launcher will launch at



        String DriveForward1 = "DriveForward1";//The second case where the robot drives forward strait away from the wall
        double directionForDriveForward1 = 0;
        double motor1ValueForDriveForward1;
        double motor2ValueForDriveForward1;
        double motor3ValueForDriveForward1;
        double motor4ValueForDriveForward1;

        String TurnToBeacon = "TurnToBeacon";//The third case where the robot turns to the beacon
        double TargetForTurnToBeacon = -90;
        double DistanceFromTargetForTurnToBeacon;
        double motor1ValueForturnToBeacon;
        double motor2ValueForturnToBeacon;
        double motor3ValueForturnToBeacon;
        double motor4ValueForturnToBeacon;
        double VarianceAllowedForTurnToBeacon=2;



        String DetermineBothBeaconColor = "DetermineBothBeaconColor";//Determine both beacon colors, the forth case

        String BeaconDesision = "BeaconDesision";//The fifth case where the robot decides which case to go to next

        String DriveForwardTillLeftLight = "DriveForwardTillLeftLight";//One of the sixth cases where it goes to the right side
        double motor1ValueForForwardTillLeftLight;
        double motor2ValueForForwardTillLeftLight;
        double motor3ValueForForwardTillLeftLight;
        double motor4ValueForForwardTillLeftLight;
        double headingForForwardTillLeftLight = TargetForTurnToBeacon;
        double offHeadingByThisForForwardTillLeftLight;


        String DriveForwardTillRightLight = "DriveForwardTillRightLight";//One of the sixth cases where it goes to the left side
        double motor1ValueForForwardTillRightLight;
        double motor2ValueForForwardTillRightLight;
        double motor3ValueForForwardTillRightLight;
        double motor4ValueForForwardTillRightLight;
        double headingForForwardTillRightLight = TargetForTurnToBeacon;
        double offHeadingByThisForForwardTillRightLight;


        String TurnToFrontOfBeacon = "TurnToFrontOfBeacon";//The seventh case where the robot turns prepping to hit the beacon
        double NintyDegreesFromTheBeaconCompasHeading = -90;
        double Motor1ValueForTurnToFrontOfBeacon;
        double Motor2ValueForTurnToFrontOfBeacon;
        double Motor3ValueForTurnToFrontOfBeacon;
        double Motor4ValueForTurnToFrontOfBeacon;
        double ToleranceLevelForTurnToFrontOfBeacon = 2;



        String DriveForwardTillHit = "DriveForwardTillHit";//The Eighth and thirteenth case where the robot drives forward till it hits the beacon
        double RangeValueForHitDetection = .2;




        String DriveBackFromBeacon = "DriveBackFromBeacon";//The ninth case where the robot drives back away from the beacon
        double Motor1ForDriveBackFromBeacon;
        double Motor2ForDriveBackFromBeacon;
        double Motor3ForDriveBackFromBeacon;
        double Motor4ForDriveBackFromBeacon;
        double DistanceWantedFromWallForDriveBackFromBeacon = 5;
        double ToleranceLevelForDriveBackFromBeacon = 2;


        String CrossOverTheLeftLight = "CrossOverTheLeftLight";//The tenth case where the robot crosses over the white line entirely
        double LeftLineSightings = 0;//How many times the left line has been seen, to be used in case CrossOverTheLeftLine
        double DistanceWantedForCrossOverTheLeftLine = DistanceWantedFromWallForDriveBackFromBeacon;
        double Motor1ForCrossOverTheLeftLine;
        double Motor2ForCrossOverTheLeftLine;
        double Motor3ForCrossOverTheLeftLine;
        double Motor4ForCrossOverTheLeftLine;
        double OffHeadingByThisForCrossOverTheLeftLight;





        String SecondColorDesision = "SecondColorDesision";//The eleventh case where the robot decided if the red light is on the left or the right
        String DriveLeftTillLeftLight = "DriveLeftTillLeftLight";//If the red is on th right this is the twelfth case where it drives past the left button
        String DriveLeftTillRightLight = "DiveLeftTillRightLight";//If the red is on the left this is the twelfth case where it drives to the right button



        int BallLauncherEncoderPosition = 560; //One rotation is 1120
        double SweeperSpeed = .6;//How fast the sweeper will spin
        double WhiteLine = 60;//What the value of the white line is




















        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver version = 3");    //
        telemetry.update();

        double CurrentEncoder = 0;
        double HalfARotation = 1650;
        double TargetEncoder = 0;


        robot.Flipper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//First Reset the Encoders
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.Flipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("CurentCase", CurentCase);
            telemetry.update();

            ODLValue = robot.LeftLight.getLightDetected();
            ODRValue = robot.LeftLight.getLightDetected();
            LastWorldRangeValue = RangeValue;
            RangeValue = robot.rangeSensor.getLightDetected();
            CompassValue = robot.compas.getDirection();
            CurrentEncoder = robot.Flipper.getCurrentPosition();
            RunTime = getRuntime();



            switch (CurentCase) {

                case "FirstTimeThrough":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.update();

                    OrignalCompassValue = CompassValue;
                    CurentCase = "ForwardQuick";
                    Previouscase = "FirstTimeThrough";

                    break;

                case "ForwardQuick":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.update();

                    robot.Motor1.setPower(-.3);
                    robot.Motor2.setPower(-.3);
                    robot.Motor3.setPower(-.3);
                    robot.Motor4.setPower(-.3);
                    robot.Sweeper.setPower(.4);
                    if(TimeInCase2 == 1){
                        TargetRuntime = RunTime + .2;
                        TimeInCase2++;
                    }
                    else if(Math.abs(TargetRuntime - RunTime) < .03){

                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                        robot.Flipper.setPower(0);
                        robot.Sweeper.setPower(0);

                        CurentCase = "WAIT2";
                        TimeInCase2 = 1;
                    }


                    break;

                case "WAIT2":



                    robot.Motor1.setPower(0);
                    robot.Motor2.setPower(0);
                    robot.Motor3.setPower(0);
                    robot.Motor4.setPower(0);
                    robot.Flipper.setPower(0);
                    robot.Sweeper.setPower(0);

                    CurentCase = "LaunchBall";
                    telemetry.addData("Runtime",RunTime);
                    telemetry.update();

                    break;

                case "LaunchBall":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.addData("Time in case", TimeInCase);
                    telemetry.addData("Current Encoder",CurrentEncoder);
                    telemetry.addData("Target Encoder", TargetEncoder);
                    telemetry.update();

                    if(TargetEncoder - CurrentEncoder < 3 && CurrentEncoder < HalfARotation+100 && Previouscase.equals("FirstTimeThrough")){
                        robot.Flipper.setPower(0);
                        TargetEncoder = TargetEncoder+HalfARotation;
                        TimeInCase++;
                        if(CurrentEncoder > HalfARotation - 3){
                            CurentCase= WAIT;
                        }
                    }
                    else if (Previouscase.equals("WAIT")){
                        if(TargetEncoder - CurrentEncoder < 3){
                            robot.Flipper.setPower(0);
                            TargetEncoder = TargetEncoder+HalfARotation;
                            TimeInCase++;
                            if(TimeInCase == 4){
                                robot.Flipper.setPower(0);
                                //CurentCase = DriveForward1;
                                CurentCase = "BallHit";

                            }
                        }
                        else{
                            robot.Flipper.setPower(.9);
                        }
                    }
                    else{
                        robot.Flipper.setPower(.9);
                    }



                    break;


                case "BallHit":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.update();

                    Motor1BallHit = Motor1BallHit-.01;
                    Motor1BallHit = Range.clip(Motor1BallHit, -1, 1);

                    robot.Motor1.setPower(Motor1BallHit);
                    robot.Motor2.setPower(Motor1BallHit);
                    robot.Motor3.setPower(Motor1BallHit);
                    robot.Motor4.setPower(Motor1BallHit);
                    if(TimeInCase2 == 1){
                        TimeInCase = 1;
                        TargetRuntime = RunTime + 4;
                        TimeInCase2++;
                    }
                    else if (Math.abs(TargetRuntime - RunTime) < .25) {
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                        CurentCase = "Spinny";
                        TimeInCase2 = 1;
                        TimeInCase = 1;
                    }


                    telemetry.addData("Time", RunTime);
                    telemetry.update();


                    break;

                case "Spinny":
                    robot.Motor1.setPower(1);
                    robot.Motor2.setPower(-1);
                    robot.Motor3.setPower(-1);
                    robot.Motor1.setPower(1);


                    if(TimeInCase2 == 1){
                        TimeInCase = 1;
                        TargetRuntime = RunTime + 4;
                        TimeInCase2++;
                    }
                    else if (Math.abs(TargetRuntime - RunTime) < .25) {
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                        CurentCase = "STOP";
                    }

                    break;


                case "DriveForward1":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.update();

                    Previouscase = CurentCase;

                    CurentCase = "TurnToBeacon";
                    break;

                case "TurnToBeacon":
                    telemetry.addData("CurentCase", CurentCase);
                    telemetry.update();

                    Previouscase = CurentCase;
                    TargetForTurnToBeacon = OrignalCompassValue + TargetForTurnToBeacon;

                    if(Math.abs(CompassValue - TargetForTurnToBeacon)> VarianceAllowedForTurnToBeacon/2){
                        DistanceFromTargetForTurnToBeacon = Math.abs(CompassValue - TargetForTurnToBeacon);
                        motor1ValueForturnToBeacon = DistanceFromTargetForTurnToBeacon*.01;
                        motor2ValueForturnToBeacon = -DistanceFromTargetForTurnToBeacon*.01;
                        motor3ValueForturnToBeacon = -DistanceFromTargetForTurnToBeacon*.01;
                        motor4ValueForturnToBeacon = DistanceFromTargetForTurnToBeacon*.01;
                        motor1ValueForturnToBeacon = Range.clip(motor1ValueForturnToBeacon, -1, 1);
                        motor2ValueForturnToBeacon = Range.clip(motor2ValueForturnToBeacon, -1, 1);
                        motor3ValueForturnToBeacon = Range.clip(motor3ValueForturnToBeacon, -1, 1);
                        motor4ValueForturnToBeacon = Range.clip(motor4ValueForturnToBeacon, -1, 1);
                        robot.Motor1.setPower(motor1ValueForturnToBeacon);
                        robot.Motor2.setPower(motor2ValueForturnToBeacon);
                        robot.Motor3.setPower(motor3ValueForturnToBeacon);
                        robot.Motor4.setPower(motor4ValueForturnToBeacon);

                    }
                    else{
                        CurentCase = "DetermineBothBeaconColor";
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);

                    }

                    break;


                case "DetermineBothBeaconColor":
                    Previouscase = CurentCase;

                    CurentCase = "BeaconDesision";
                    break;


                case "BeaconDesision":
                    Previouscase = CurentCase;

                    if (FirstBeaconColor.equals("Left")) {
                        CurentCase = "DriveForwardTillRightLight";
                    } else if (FirstBeaconColor.equals("Right")) {
                        CurentCase = "DriveForwardTillLeftLight";
                    }

                    break;


                case "DriveForwardTillLeftLight":
                    Previouscase = CurentCase;

                    if (ODLValue < WhiteLine){
                        offHeadingByThisForForwardTillLeftLight = CompassValue - OrignalCompassValue + headingForForwardTillLeftLight;
                        motor1ValueForForwardTillLeftLight = 1+offHeadingByThisForForwardTillLeftLight*.01;
                        motor2ValueForForwardTillLeftLight = 1-offHeadingByThisForForwardTillLeftLight*.01;
                        motor3ValueForForwardTillLeftLight = 1+offHeadingByThisForForwardTillLeftLight*.01;
                        motor4ValueForForwardTillLeftLight = 1-offHeadingByThisForForwardTillLeftLight*.01;
                        motor1ValueForForwardTillLeftLight = Range.clip(motor1ValueForForwardTillLeftLight, -1, 1);
                        motor2ValueForForwardTillLeftLight = Range.clip(motor2ValueForForwardTillLeftLight, -1, 1);
                        motor3ValueForForwardTillLeftLight = Range.clip(motor3ValueForForwardTillLeftLight, -1, 1);
                        motor4ValueForForwardTillLeftLight = Range.clip(motor4ValueForForwardTillLeftLight, -1, 1);
                        robot.Motor1.setPower(motor1ValueForForwardTillLeftLight);
                        robot.Motor2.setPower(motor2ValueForForwardTillLeftLight);
                        robot.Motor3.setPower(motor3ValueForForwardTillLeftLight);
                        robot.Motor4.setPower(motor4ValueForForwardTillLeftLight);

                    }
                    else if (ODLValue >= WhiteLine) {
                        CurentCase = "TurnToFrontOfBeacon";
                    }
                    break;


                case "DriveForwardTillRightLight":
                    Previouscase = CurentCase;

                    if (ODRValue < WhiteLine){
                        offHeadingByThisForForwardTillRightLight = CompassValue - (OrignalCompassValue + headingForForwardTillRightLight);
                        motor1ValueForForwardTillRightLight = 1+offHeadingByThisForForwardTillRightLight*.01;
                        motor2ValueForForwardTillRightLight = 1-offHeadingByThisForForwardTillRightLight*.01;
                        motor3ValueForForwardTillRightLight = 1+offHeadingByThisForForwardTillRightLight*.01;
                        motor4ValueForForwardTillRightLight = 1-offHeadingByThisForForwardTillRightLight*.01;
                        motor1ValueForForwardTillLeftLight = Range.clip(motor1ValueForForwardTillRightLight, -1, 1);
                        motor2ValueForForwardTillLeftLight = Range.clip(motor2ValueForForwardTillRightLight, -1, 1);
                        motor3ValueForForwardTillLeftLight = Range.clip(motor3ValueForForwardTillRightLight, -1, 1);
                        motor4ValueForForwardTillLeftLight = Range.clip(motor4ValueForForwardTillRightLight, -1, 1);
                        robot.Motor1.setPower(motor1ValueForForwardTillLeftLight);
                        robot.Motor2.setPower(motor2ValueForForwardTillLeftLight);
                        robot.Motor3.setPower(motor3ValueForForwardTillLeftLight);
                        robot.Motor4.setPower(motor4ValueForForwardTillLeftLight);

                    }
                    else if (ODRValue >= WhiteLine) {
                        CurentCase = "TurnToFrontOfBeacon";
                    }
                    break;


                case "TurnToFrontOfBeacon":
                    Previouscase = CurentCase;
                    TargetForTurnToBeacon = OrignalCompassValue + TargetForTurnToBeacon;

                    if(Math.abs(CompassValue - TargetForTurnToBeacon)>ToleranceLevelForTurnToFrontOfBeacon/2){
                        DistanceFromTargetForTurnToBeacon = Math.abs(CompassValue - TargetForTurnToBeacon);
                        motor1ValueForturnToBeacon = DistanceFromTargetForTurnToBeacon*.01;
                        motor2ValueForturnToBeacon = -DistanceFromTargetForTurnToBeacon*.01;
                        motor3ValueForturnToBeacon = -DistanceFromTargetForTurnToBeacon*.01;
                        motor4ValueForturnToBeacon = DistanceFromTargetForTurnToBeacon*.01;
                        motor1ValueForturnToBeacon = Range.clip(motor1ValueForturnToBeacon, -1, 1);
                        motor2ValueForturnToBeacon = Range.clip(motor2ValueForturnToBeacon, -1, 1);
                        motor3ValueForturnToBeacon = Range.clip(motor3ValueForturnToBeacon, -1, 1);
                        motor4ValueForturnToBeacon = Range.clip(motor4ValueForturnToBeacon, -1, 1);
                        robot.Motor1.setPower(motor1ValueForturnToBeacon);
                        robot.Motor2.setPower(motor2ValueForturnToBeacon);
                        robot.Motor3.setPower(motor3ValueForturnToBeacon);
                        robot.Motor4.setPower(motor4ValueForturnToBeacon);

                    }
                    else{
                        CurentCase = "DriveForwardTillHit";
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);

                    }



                    break;


                case "DriveForwardTillHit":
                    if(Math.abs(RangeValue-LastWorldRangeValue) > RangeValueForHitDetection){
                        robot.Motor1.setPower(1);
                        robot.Motor2.setPower(1);
                        robot.Motor3.setPower(1);
                        robot.Motor4.setPower(1);
                    }
                    else{
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                        CurentCase = "DriveBackFromBeacon";
                    }



                    break;


                case "DriveBackFromBeacon":

                    if(ToleranceLevelForDriveBackFromBeacon/2 < Math.abs(DistanceWantedFromWallForDriveBackFromBeacon-RangeValue)){
                        Motor1ForDriveBackFromBeacon = DistanceWantedFromWallForDriveBackFromBeacon-RangeValue*.2;
                        Motor2ForDriveBackFromBeacon = DistanceWantedFromWallForDriveBackFromBeacon-RangeValue*.2;
                        Motor3ForDriveBackFromBeacon = DistanceWantedFromWallForDriveBackFromBeacon-RangeValue*.2;
                        Motor4ForDriveBackFromBeacon = DistanceWantedFromWallForDriveBackFromBeacon-RangeValue*.2;
                        Range.clip(Motor1ForDriveBackFromBeacon, -1, 1);
                        Range.clip(Motor2ForDriveBackFromBeacon, -1, 1);
                        Range.clip(Motor3ForDriveBackFromBeacon, -1, 1);
                        Range.clip(Motor4ForDriveBackFromBeacon, -1, 1);
                        robot.Motor1.setPower(Motor1ForDriveBackFromBeacon);
                        robot.Motor2.setPower(Motor2ForDriveBackFromBeacon);
                        robot.Motor3.setPower(Motor3ForDriveBackFromBeacon);
                        robot.Motor4.setPower(Motor4ForDriveBackFromBeacon);

                    }
                    else{
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);

                        if (Previouscase.equals("TurnToFrontOfBeacon")) {
                            CurentCase = "CrossOverTheLeftLight";
                        } else if (Previouscase.equals("DriveLeftTillLeftLight") || Previouscase.equals("DriveLeftTillRightLight")) {
                            CurentCase = "STOP";
                        }

                    }





                    break;


                case "CrossOverTheLeftLight":
                    Previouscase = CurentCase;

                    OffHeadingByThisForCrossOverTheLeftLight = RangeValue - DistanceWantedForCrossOverTheLeftLine;
                    Motor1ForCrossOverTheLeftLine = 1+OffHeadingByThisForCrossOverTheLeftLight*.01;
                    Motor2ForCrossOverTheLeftLine = 1-OffHeadingByThisForCrossOverTheLeftLight*.01;
                    Motor3ForCrossOverTheLeftLine = 1+OffHeadingByThisForCrossOverTheLeftLight*.01;
                    Motor4ForCrossOverTheLeftLine = 1-OffHeadingByThisForCrossOverTheLeftLight*.01;
                    Motor1ForCrossOverTheLeftLine = Range.clip(Motor1ForCrossOverTheLeftLine, -1, 1);
                    Motor2ForCrossOverTheLeftLine = Range.clip(Motor2ForCrossOverTheLeftLine, -1, 1);
                    Motor3ForCrossOverTheLeftLine = Range.clip(Motor3ForCrossOverTheLeftLine, -1, 1);
                    Motor4ForCrossOverTheLeftLine = Range.clip(Motor4ForCrossOverTheLeftLine, -1, 1);
                    robot.Motor1.setPower(Motor1ForCrossOverTheLeftLine);
                    robot.Motor2.setPower(Motor2ForCrossOverTheLeftLine);
                    robot.Motor3.setPower(Motor3ForCrossOverTheLeftLine);
                    robot.Motor4.setPower(Motor4ForCrossOverTheLeftLine);





                    if (ODLValue < WhiteLine && LeftLineSightings == 0) {
                        LeftLineSightings = 1;
                    }

                    else if (ODLValue > WhiteLine && LeftLineSightings == 1) {
                        CurentCase = "SecondColorDesision";
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                    }

                    else {
                        CurentCase= "STOP";
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                    }
                    break;


                case "SecondColorDesision":
                    Previouscase = CurentCase;


                    if (SecondBeaconColor.equals("Left")) {
                        CurentCase = "DriveLeftTillRightLight";
                    } else if (SecondBeaconColor.equals("Right")) {
                        CurentCase = "DriveLeftTillLeftLight";
                    }

                    break;


                case "DriveLeftTillLeftLight":
                    Previouscase = CurentCase;

                    if (ODLValue < WhiteLine) {

                    }
                    else{
                        robot.Motor1.setPower(0);
                        robot.Motor2.setPower(0);
                        robot.Motor3.setPower(0);
                        robot.Motor4.setPower(0);
                        CurentCase = "DriveForwardTillHit";
                    }
                    break;


                case "DiveLeftTillRightLight":
                    Previouscase = CurentCase;


                    if (ODRValue < WhiteLine) {
                        CurentCase = "DriveForwardTillHit";
                    }
                    break;

                case "Stop":
                    robot.Motor1.setPower(0);
                    robot.Motor2.setPower(0);
                    robot.Motor3.setPower(0);
                    robot.Motor4.setPower(0);
                    robot.Flipper.setPower(0);
                    robot.Sweeper.setPower(0);
                    break;

                case "WAIT":
                    robot.Motor1.setPower(0);
                    robot.Motor2.setPower(0);
                    robot.Motor3.setPower(0);
                    robot.Motor4.setPower(0);
                    robot.Flipper.setPower(0);
                    robot.Sweeper.setPower(0);

                    if(TimeInCase2 == 1){
                        TargetRuntime = RunTime + 1;
                        TimeInCase2++;
                    }
                    else if(Math.abs(TargetRuntime - RunTime) < .05){
                        CurentCase = LaunchBall;
                        TimeInCase2 = 1;
                    }

                    telemetry.addData("Runtime",RunTime);


                    Previouscase = "WAIT";
                    break;







            }

        }


            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
