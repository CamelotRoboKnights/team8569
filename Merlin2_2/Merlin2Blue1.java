package org.firstinspires.ftc.teamcode.team.Merlin2_2;
import com.qualcomm.hardware.modernrobotics.PretendModernRoboticsUsbDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.team.Merlin2_2.Merlin2Auto;

/*
 * My cases are:
 *
 * Drive Forward
 * Launch
 * Load
 * Launch
 * Identify
 * Turn to first beacon
 * Go to white line going forward till right sensor
 * Turn to 0
 *
 * if Right drive left till hit
 * Back Up
 *
 * if Left Go to white line left till right sensor
 * Drive forward Till hit
 * Back Up
 * Go left Till left Light
 *
 * Go left Till Left right
 * Back up
 * Identify Beacon
 *
 * If right Drive till hit
 * Back Up
 * Turn to Ball
 *
 *
 * if Left drive Right
 * Drive Till hit
 * Back up
 * Turn to ball
 *
 * Drive to ball
 */
@Autonomous(name = "Blue1, BBC", group = "Merlin2")
public class Merlin2Blue1 extends Merlin2Auto {

    @Override
    public void init() {
        super.init();
        super.initCamera();

        telemetry.addData("Say", "Hello Driver");    //
        robot.navx_device.zeroYaw();



    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    private String CurrentCase = "GoForwardToBeAbleToTurn";
    private String CompletionClause = "";

    private String FirstBeaconSide = "";
    private String SecondBeaconSide = "";

    private double DriveForwardToBeAbleToTurnDistance = 5;

    private double AngleToDriveToFirstBeaconIdentifyingSpot = 19.5;

    private double DistanceToDriveForwardToBeaconIdentifyingSpot = 68;

    private double TurnToIdentifyFirstBeaconAndLaunchBalls = -129;

    private double TurnToIdentifySecondBeacon = 140.55;

    private double TurnToDriveTillSecondBeaconsWhiteLine = 115;

    private double SquareAgainstTheSecondBeacon = 178;

    private double DriveToHitTheLeftButtonOfTheSecondBeacon = 12;
    private String DriveToHitTheLeftButtonDirectionOfTheSecondBeacon = "Back";

    private double DriveToHitTheRightButtonOfTheSecondBeacon = 3;
    private String DriveToHitTheRightButtonDirectionOfTheSecondBeacon = "Back";

    private double MakeSureTheRobotIsAngledForToHitTheSecondBeacon = 175;

    private double DriveBackAfterHittingTheSecondBeacon = 12;

    private double DriveToGetCloseToTheFirstWhiteLine = 24;

    private double DriveToHitTheLeftButtonOfTheFirstBeacon = 3;
    private String DriveToHitTheLeftButtonOfTheFirstBeaconDirection = "Back";

    private double DriveToHitTheRightButtonOfTheFirstBeacon = 3;
    private String DriveToHitTheRightButtonOfTheFirstBeaconDirection = "Forward";

    private double SquareAgainstTheFirstBeacon = 178;

    private double MakeSureTheRobotIsAngledForToHitTheFirstBeacon = 178;

    private double DriveOntoTheCornerVortex = 40;

    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("FirstBeaconSide", FirstBeaconSide);
        telemetry.addData("SecondBeaconSide", SecondBeaconSide);

        switch (CurrentCase){
            case "GoForwardToBeAbleToTurn"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveForwardToBeAbleToTurnDistance, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToBeAbleToDriveForwardToBeaconIdentifyingSpot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToBeAbleToDriveForwardToBeaconIdentifyingSpot":
                CompletionClause = super.turnToGyroHeading(AngleToDriveToFirstBeaconIdentifyingSpot);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveForwardToBeaconIdentifyingSpot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveForwardToBeaconIdentifyingSpot"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(DistanceToDriveForwardToBeaconIdentifyingSpot, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifyFirstBeaconAndLaunchBalls";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToIdentifyFirstBeaconAndLaunchBalls":
                CompletionClause = super.turnToGyroHeading(TurnToIdentifyFirstBeaconAndLaunchBalls);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "IdentifyBeaconsAndLaunch";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "IdentifyBeaconsAndLaunch":
                CompletionClause = super.shootAndIdentify("BLUE");//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    FirstBeaconSide = super.ColorValue;
                    super.resetAll();
                }
                break;
            case "TurnToIdentifySecondBeacon":
                CompletionClause = super.turnToGyroHeading(TurnToIdentifySecondBeacon);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "IdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "IdentifySecondBeacon":
                SecondBeaconSide = super.choseSide("BLUE");
                if(!SecondBeaconSide.equals("")){
                    CurrentCase = "TurnToDriveTillSecondBeaconsWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToDriveTillSecondBeaconsWhiteLine":
                CompletionClause = super.turnToGyroHeading(TurnToDriveTillSecondBeaconsWhiteLine);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillSecondBeaconsWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillSecondBeaconsWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Left", "Right", .2);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstTheSecondBeacon":
                CompletionClause = super.turnToGyroHeading(SquareAgainstTheSecondBeacon);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    switch (SecondBeaconSide){
                        case "Left":
                            CurrentCase = "DriveToHitTheLeftButtonOfTheSecondBeacon";
                            break;
                        case "Right":
                            CurrentCase = "DriveToHitTheRightButtonOfTheSecondBeacon";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheLeftButtonOfTheSecondBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheLeftButtonOfTheSecondBeacon, DriveToHitTheLeftButtonDirectionOfTheSecondBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheRightButtonOfTheSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheRightButtonOfTheSecondBeacon, DriveToHitTheRightButtonDirectionOfTheSecondBeacon);

                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

//            case "MakeSureTheRobotIsAngledForToHitTheSecondBeacon"://Make sure one more time the foam is parallel to the wall
//                CompletionClause = super.turnToGyroHeading(MakeSureTheRobotIsAngledForToHitTheSecondBeacon);
//                if(CompletionClause.equals("Done")){
//                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
//                    CompletionClause = "NOTDONE";
//                    super.resetAll();
//                }
//                break;
            case "DriveTillTheSecondBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveToGetCloseToTheFirstWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToGetCloseToTheFirstWhiteLine":
                CompletionClause = super.driveBasedOnEncoders(DriveToGetCloseToTheFirstWhiteLine, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveToBeOnTheFirstBeaconWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToBeOnTheFirstBeaconWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Forward", "Left", .3);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstTheFirstBeacon":
                CompletionClause = super.turnToGyroHeading(SquareAgainstTheFirstBeacon);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    switch (SecondBeaconSide){
                        case "Left":
                            CurrentCase = "DriveToHitTheLeftButtonOfTheSecondBeacon";
                            break;
                        case "Right":
                            CurrentCase = "DriveToHitTheRightButtonOfTheSecondBeacon";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheLeftButtonOfTheFirstBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheLeftButtonOfTheFirstBeacon, DriveToHitTheLeftButtonOfTheFirstBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForToHitTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitTheRightButtonOfTheFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitTheRightButtonOfTheFirstBeacon, DriveToHitTheRightButtonOfTheFirstBeaconDirection);

                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForToHitTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForToHitTheFirstBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(MakeSureTheRobotIsAngledForToHitTheFirstBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheFirstBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheFirstBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveOntoTheCornerVortex":
                CompletionClause = super.driveBasedOnEncoders(DriveOntoTheCornerVortex, "Forward");
                if(CompletionClause.equals("Done")){
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;



            case"Done":
                super.done();
                break;
            default:
                super.broken();
                break;
        }
    }

    @Override
    public void stop() {
        super.stopCamera();
    }



}
