package org.firstinspires.ftc.teamcode.team.Merlin2_2;
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
@Autonomous(name = "Red1, BBC", group = "Merlin2")
public class Merlin2Red1 extends Merlin2Auto {

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
    private String CurrentCase = "MakeSureItIsOnAngle";
    private String CompletionClause = "";
    private String FirstBeaconSide = "";
    private String SecondBeaconSide = "";
    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("FirstSide", FirstBeaconSide);
        telemetry.addData("SecondSide", SecondBeaconSide);
        switch (CurrentCase){
            case "DriveForwardAwayFromWallToBeAbleToTurn":

                break;
            case "TurnToGoToTheFirstBeaconIdentificationLocation":

                break;
            case "GoForwardToTheFirstBeaconIdentificationLocation":

                break;
            case "TurnToIdentifyAndShoot":

                break;
            case "ShootAndIdentify":

                break;
            case "DriveLeft/ForwardToTheFirstWhiteLine":

                break;
            case "SquareAgainstWallForFirstBeacon":

                break;
            case "DriveToHitLeftButtonOfTheFirstBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(8, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitRightButtonOfTheFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(1, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(0);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheFirstBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheFirstBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheFirstBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(16, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToGoToTheSecondBeaconIdentificationLocation":

                break;
            case "GoToTheSecondBeaconIdentificationLocation":

                break;
            case "TurnToIdentifySecondBeacon":

                break;
            case "IdentifySecondBeacon":

                break;
            case "TurnToGoToSecondBeacon":

                break;
            case "GoForwardToTheSecondBeaconWhiteLine":

                break;
            case "SquareAgainstWallForTheSecondBeacon":

                break;
            case "DriveToHitLeftButtonOfTheSecondBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(8, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveToHitRightButtonOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitRightButtonOfTheSecondBeacon":
                CompletionClause = super.driveBasedOnEncoders(1, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(0);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheSecondBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveUntilHit("Red");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(16, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "BackRightDriveToHitTheCapBall":

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
