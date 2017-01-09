package org.firstinspires.ftc.teamcode.team.Merlin2;


import android.widget.Switch;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

import static android.R.attr.name;

@Autonomous(name = "Merlin2Red1", group = "Merlin2")
public class Merlin2Red1 extends Merlin2Auto {

    /* Declare OpMode members. */
    //Merlin2Hardware robot       = new Merlin2Hardware(); // use the class created to define a Pushbot's hardware
    @Override
    public void init() {

        robot.init(hardwareMap);
        super.initCamera();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    String CurrentCase = "GoForwardBeforeShoot";
    String CompletionClause = "";
    String BeaconSide = "";
    @Override
    public void loop() {
        switch (CurrentCase){
            case "GoForwardBeforeShoot":
                CompletionClause = super.driveBasedOnEncoders(10, "Forward");
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootFirstBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;

            case "ShootFirstBall":
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "LoadSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "LoadSecondBall":
                CompletionClause = super.loadBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "ShootSecondBall";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "ShootSecondBall":
                CompletionClause = super.launchBall();
                if(CompletionClause.equals("Done")){
                    CurrentCase = "FirstTurnToBeacon";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "FirstTurnToBeacon":
                CompletionClause = super.turnToGyroHeading(-45);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "Done";
                    //CurrentCase = "TryToIdentifyBeaconTakeOne";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "TryToIdentifyBeaconTakeOne":
                BeaconSide = super.choseSide("RED");
                if(!BeaconSide.equals("")){
                    CurrentCase = "GoToTheWhiteLineAfterShoot";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case "GoToTheWhiteLineAfterShoot":
                CompletionClause = super.driveUltilWhiteLine("Forward", "Right", .3);
                if(CompletionClause.equals("Done")){
                    CurrentCase = "TurnToBeaconForTheFirstTime";
                    CompletionClause = "NOTDONE";
                    super.resetAll();
                }
                break;
            case"Done":
                super.done();
                break;
            default:
                super.broken();
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    @Override
    public void stop() {
    super.stopCamera();
    }



}
