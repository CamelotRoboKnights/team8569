/* Our autonomous codes
 *
 * 1 at the end - The main autonomous that sets up at the normal spot does everything including: Launch, beacon, beacon, Cap ball
 * 2 at the end - A secondary autonomous that sets up close to the line and does: launch, Cap Ball.
 * 3 at the end - A main Autonomous that sets up at the normal spot and does: Launch, beacon, beacon, CornerVortex. NEEDS CREATING
 * 4 at the end - A tertiary Autonomous that sets as far from the corner vortex and does: launch, CornerVortex. NEEDS CREATIG
 *
 * For what is on the drivers station
 *
 * Blue- Blue side
 * Red- Red side
 * 1- Standard location
 * 2- Secondary location
 * L- Launch
 * B- Beacon
 * C- Cap Ball
 *
 *
 *
 */


package org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/*
 * My cases are:
 *
 * Drive forward 5
 * Turn to -47
 * Forward to 28
 * Turn to angle 43.87
 * Shoot/Identify
 * Forward Left Angle Drive Left Light see white line
 * Square against wall
 * Press beacon
 * Turn to 17 angle
 * Drive Forward 29 distance
 * Turn to angle 22.79
 * Identify
 * Square
 * Forward to white line
 * Square against wall
 * Press beacon
 * Back Right Angle Drive
 */
@Autonomous(name = "Blue Beacons", group = "Merlin3")
public class Blue1Beacons extends Merlin3AutoMethods {

    @Override
    public void init() {
        super.init();
        super.initCamera();

        telemetry.addData("Say", "Hello Driver");    //
        telemetry.addData("Blue 1", "Beacons");
        robot.navx_device.zeroYaw();



    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    private String CurrentCase = "DriveForwardAwayFromWallToBeAbleToTurn";
    private String CompletionClause = "";
    private String FirstBeaconSide = "";
    private String SecondBeaconSide = "";

    private double DriveForwardAwayFromWallToBeAbleToTurn = 5;

    private double TurnToGoToTheFirstBeaconIdentificationLocation = 16;

    private double GoForwardToTheFirstBeaconIdentificationLocation = 37;

    private double TurnToIdentifyAndShoot = -9;

    private double DriveForwardMore = 10;


    private double SquareAgainstWall = 0;

    private double DriveToHitLeftButtonOfTheFirstBeaconDistance = 1;
    private String DriveToHitLeftButtonOfTheFirstBeaconDirection = "Forward";

    private double DriveToHitRightButtonOfTheFirstBeaconDistance = 5;
    private String DriveToHitRightButtonOfTheFirstBeaconDirection = "Forward";

    private double DriveBackAfterHittingTheFirstBeacon = 14;

    private double TurnToGoToTheSecondBeaconIdentificationLocation = -5;//-17,-10

    private double GoToTheSecondBeaconIdentificationLocation = 35;
    private double GoToTheSecondBeaconIdentificationLocationLeftButton = 30;

    private double TurnToIdentifySecondBeacon = -10;

    private double TurnToGoToSecondBeacon = 0;

    private double DriveToHitLeftButtonOfTheSecondBeaconDistance = 5;
    private String DriveToHitLeftButtonOfTheSecondBeaconDirection = "Back";

    private double DriveToHitRightButtonOfTheSecondBeaconDistance = 1;
    private String DriveToHitRightButtonOfTheSecondBeaconDirection = "Bacl";

    private double DriveBackAfterHittingTheSecondBeacon = 12;

    private double BackRightDriveToHitTheCapBall = 60;



    @Override
    public void loop() {
        telemetry.addData("CurrentCase", CurrentCase);
        telemetry.addData("FirstSide", FirstBeaconSide);
        telemetry.addData("SecondSide", SecondBeaconSide);
        switch (CurrentCase){
            case "DriveForwardAwayFromWallToBeAbleToTurn":
                CompletionClause = super.driveBasedOnEncoders(DriveForwardAwayFromWallToBeAbleToTurn, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToGoToTheFirstBeaconIdentificationLocation";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToGoToTheFirstBeaconIdentificationLocation":
                CompletionClause = super.turnToGyroHeading(TurnToGoToTheFirstBeaconIdentificationLocation);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoForwardToTheFirstBeaconIdentificationLocation";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoForwardToTheFirstBeaconIdentificationLocation":
                CompletionClause = super.driveBasedOnEncoders(GoForwardToTheFirstBeaconIdentificationLocation, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifyAndShoot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToIdentifyAndShoot":
                CompletionClause = super.turnToGyroHeading(TurnToIdentifyAndShoot);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "Identify";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "Identify":
                FirstBeaconSide = super.choseSide("BLUE");
                if(!FirstBeaconSide.equals("")){
                    CurrentCase = "TurnToDriveToWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToDriveToWhiteLine":
                CompletionClause = super.turnToGyroHeading(55);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveLeftForwardToTheFirstWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveLeftForwardToTheFirstWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Back", "Right", .2);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstWallForFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstWallForFirstBeacon":
                CompletionClause = super.turnToGyroHeading(SquareAgainstWall);
                if(CompletionClause.equals("Done")){
                    switch (FirstBeaconSide){
                        case "Left":
                            CurrentCase = "DriveToHitLeftButtonOfTheFirstBeacon";
                            break;
                        case "Right":
                            CurrentCase = "DriveToHitRightButtonOfTheFirstBeacon";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitLeftButtonOfTheFirstBeacon"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitLeftButtonOfTheFirstBeaconDistance, DriveToHitLeftButtonOfTheFirstBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitRightButtonOfTheFirstBeacon":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitRightButtonOfTheFirstBeaconDistance, DriveToHitRightButtonOfTheFirstBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForTheHitOfTheFirstBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(SquareAgainstWall);
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
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheFirstBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToGoToTheSecondBeaconIdentificationLocationLeftButton";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToGoToTheSecondBeaconIdentificationLocationLeftButton":
                CompletionClause = super.turnToGyroHeading(TurnToGoToTheSecondBeaconIdentificationLocation);
                if(CompletionClause.equals("Done")){
                    switch (FirstBeaconSide){
                        case "Left":
                            CurrentCase = "GoToTheSecondBeaconIdentificationLocationLeftButton";
                            break;
                        case "Right":
                            CurrentCase = "GoToTheSecondBeaconIdentificationLocationRightButton";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheSecondBeaconIdentificationLocationRightButton":
                CompletionClause = super.driveBasedOnEncoders(GoToTheSecondBeaconIdentificationLocation, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheSecondBeaconIdentificationLocationLeftButton":
                CompletionClause = super.driveBasedOnEncoders(GoToTheSecondBeaconIdentificationLocationLeftButton, "Back");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToIdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToIdentifySecondBeacon":
                CompletionClause = super.turnToGyroHeading(TurnToIdentifySecondBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "IdentifySecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "IdentifySecondBeacon":
                SecondBeaconSide = super.choseSide("BLUE");
                if(!SecondBeaconSide.equals("")){
                    CurrentCase = "TurnToGoToSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TurnToGoToSecondBeacon":
                CompletionClause = super.turnToGyroHeading(TurnToGoToSecondBeacon);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoForwardToTheSecondBeaconWhiteLine";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoForwardToTheSecondBeaconWhiteLine":
                CompletionClause = super.driveUltilWhiteLine("Back", "Left", .2);
                telemetry.addData("Light", robot.LeftLight.getLightDetected());
                if(CompletionClause.equals("Done")){
                    CurrentCase = "SquareAgainstWallForTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "SquareAgainstWallForTheSecondBeacon":
                CompletionClause = super.turnToGyroHeading(SquareAgainstWall);
                if(CompletionClause.equals("Done")){
                    switch(FirstBeaconSide) {
                        case "Left":
                        switch (SecondBeaconSide) {
                            case "Left":
                                CurrentCase = "DriveToHitLeftButtonOfTheSecondBeaconLeft";
                                break;
                            case "Right":
                                CurrentCase = "DriveToHitRightButtonOfTheSecondBeaconLeft";
                                break;
                        }
                            break;

                        case "Right":
                            switch (SecondBeaconSide){
                                case "Left":
                                    CurrentCase = "DriveToHitLeftButtonOfTheSecondBeaconRight";
                                    break;
                                case "Right":
                                    CurrentCase = "DriveToHitRightButtonOfTheSecondBeaconRight";
                                    break;
                            }
                    }

                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitLeftButtonOfTheSecondBeaconLeft"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(DriveToHitLeftButtonOfTheSecondBeaconDistance, DriveToHitLeftButtonOfTheSecondBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitRightButtonOfTheSecondBeaconLeft":
                CompletionClause = super.driveBasedOnEncoders(DriveToHitRightButtonOfTheSecondBeaconDistance, DriveToHitRightButtonOfTheSecondBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitLeftButtonOfTheSecondBeaconRight"://Drive to the left so that the robot is only in front of the beacon's left button
                CompletionClause = super.driveBasedOnEncoders(5, DriveToHitLeftButtonOfTheSecondBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveToHitRightButtonOfTheSecondBeaconRight":
                CompletionClause = super.driveBasedOnEncoders(1, DriveToHitRightButtonOfTheSecondBeaconDirection);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "MakeSureTheRobotIsAngledForTheHitOfTheSecondBeacon"://Make sure one more time the foam is parallel to the wall
                CompletionClause = super.turnToGyroHeading(SquareAgainstWall);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveTillTheSecondBeaconIsHit";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveTillTheSecondBeaconIsHit"://Dive till the robot has hit the beacon
                CompletionClause = super.driveBasedOnEncoders(35, "Left");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "DriveBackAfterHittingTheSecondBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "DriveBackAfterHittingTheSecondBeacon"://Drive back after hitting the beacon
                CompletionClause = super.driveBasedOnEncoders(DriveBackAfterHittingTheSecondBeacon, "Right");
                if(CompletionClause.equals("Done")){
                    switch (SecondBeaconSide){
                        case "Left":
                            CurrentCase = "MakeSureItIsOnAngleForShootForLeft";
                            break;
                        case "Right":
                            CurrentCase = "MakeSureItIsOnAngleForShootForRight";
                            break;
                    }
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngleForShootForLeft":
                CompletionClause = super.turnToGyroHeading(40);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoForwardBeforeShootLeft";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "MakeSureItIsOnAngleForShootForRight":
                CompletionClause = super.turnToGyroHeading(45);//The robot makes sure it is on angle for the shot.
                if(CompletionClause.equals("Done")){
                    CurrentCase = "GoForwardBeforeShootRight";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoForwardBeforeShootLeft"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(22, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoForwardBeforeShootRight"://First Case that drives the robot forward to a position that can launch the balls and identify the beacon
                CompletionClause = super.driveBasedOnEncoders(14, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootFirstBall"://Shoot the first ball to try to make it in the hoop
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "LoadSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "LoadSecondBall"://Load the second ball in the flipper
                CompletionClause = super.loadBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootSecondBall"://Shoot the second ball into the hoop
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "BackRightDriveToHitTheCapBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "BackRightDriveToHitTheCapBall":
                CompletionClause = super.driveBasedOnEncoders(BackRightDriveToHitTheCapBall, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "Done";
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
