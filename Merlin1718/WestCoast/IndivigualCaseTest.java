package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;





import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Case Test", group = "Cardinal")
//@Disabled //Uncomment this if it is not wanted on the phone
public class IndivigualCaseTest extends OpMode {

    public WestCoastHardware robot = new WestCoastHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){//This only runs once
        robot.init(hardwareMap);
        robot.motoG.initCamera();
        robot.glyphCollector.topGrasper.close();
        robot.glyphCollector.bottomGrasper.open();
        ;//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed
    // distance of variables


    double versionNumber = 50;


    private String color = "red";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double spinRightToKnockOffRightJewel = 7;
    private double spinLeftToKnockOffLeftJewel  = -7;
    private double driveDistanceToRightColumn = 24;//
    private double driveDistanceToCenterColumn = 30;// 30
    private double driveDistanceToLeftColumn = 36;//36
    private double driveForwardToCryptobox = 8;
    private double driveAwayFromCryptobox = 5;//2



    private String currentCase = "ToLeftColumn";
    private boolean redJewel;
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("Version Number: ", versionNumber);
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", redJewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter": //Drops the sorter in preperation to sort jewls
                boolean doneYet;
                doneYet = robot.jewelSorter.lower();
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":  //determins color of jewl closest to cryptobox
                redJewel = robot.jewelSorter.isRed();
                column = robot.motoG.key();
                if(!column.equals("null")){
                    if(redJewel){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinRightToKnockOffRightJewel, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinLeftToKnockOffLeftJewel, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            //raise sorter
            case "RaiseSorter":
                doneYet = robot.jewelSorter.raise();
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = robot.westCoast.turnToGyroHeading(0, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = choseColumnCase(column);
                }
                break;

            case "ToRightColumn":
                doneYet = robot.westCoast.driveBasedOnEncodersAndGyro(driveDistanceToRightColumn, 1, 0, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;

            case "ToCenterColumn":
                doneYet = robot.westCoast.driveBasedOnEncodersAndGyro(driveDistanceToCenterColumn, 1, 0, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "SpinTo90";
                    currentCase = "Done";
                }
                break;

            case "ToLeftColumn":
                robot.westCoast.betterDriveBasedOnEncodersAndGyro(driveDistanceToLeftColumn, 0, robot.navx.getCurrentOrientation());
                doneYet = false;
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;

            case "SpinTo90":
                doneYet = robot.westCoast.turnToGyroHeading(90, robot.navx.currentOrientation);
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;

            case "DriveForward":
                doneYet = robot.westCoast.driveBasedOnEncodersAndGyro(driveForwardToCryptobox, 1, 90, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                robot.glyphCollector.topGrasper.open();
                doneYet = true;
                if(doneYet){
                    currentCase = "DriveForward2";
                }
                break;
            //drive forward to make shure glyph is in collumn
            case "DriveForward2":
                doneYet = robot.westCoast.driveBasedOnEncodersAndGyro(2, 1, 90, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            //back up
            case "DriveBack":
                doneYet = robot.westCoast.driveBasedOnEncodersAndGyro(driveAwayFromCryptobox, -1, 90, robot.navx.getCurrentOrientation());
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                robot.westCoast.drive(0,0);
                break;
            default:
                robot.westCoast.drive(0,0);
                telemetry.addData("What Are You", " Doing");
                telemetry.update();
        }
    }
    @Override
    public void stop(){}








    private String choseColumnCase(String column){
        switch (column){
            case "RIGHT":
                return "ToRightColumn";
            case "CENTER":
                return "ToCenterColumn";
            case "LEFT":
                return "ToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
