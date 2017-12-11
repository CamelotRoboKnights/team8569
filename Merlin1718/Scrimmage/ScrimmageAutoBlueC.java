package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.sql.Driver;

/**
 * Created by Zachary Ireland on 12/10/2017.
 */

@Autonomous(name = "BlueC", group = "Scrimmage")
//@Disabled //Uncomment this if it is not wanted on the phone
public class ScrimmageAutoBlueC extends ScrimmageMeathods {

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed

    private String color = "blue";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double spinRightToKnockOffRightJewel = -7;
    private double spinLeftToKnockOffLeftJewel  = 7;
    private double driveDistanceToRightColumn = 23;//
    private double driveDistanceToCenterColumn = 29;// 30
    private double driveDistanceToLeftColumn = 35;//36
    private double driveForwardToCryptobox = 10;
    private double driveAwayFromCryptobox = 5;//2



    private String currentCase = "DropSorter";
    private String jewel = "null";
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", jewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = sorter("down");
                glyphAuto("close");
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":
                jewel = super.jewelColor();
                column = super.key();
                if(!jewel.equals("null") && !column.equals("null")){
                    if(jewel.equals(color)){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = turnToGyroHeading(spinRightToKnockOffRightJewel, revOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = turnToGyroHeading(spinLeftToKnockOffLeftJewel, revOrientation());
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "RaiseSorter":
                doneYet = sorter("up");
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = turnToGyroHeading(0, revOrientation());
                if(doneYet){
                    currentCase = choseColumnCase(column);
                }
                break;
            case "ToRightColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToRightColumn, "Back");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToCenterColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToCenterColumn, "Back");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToLeftColumn":
                doneYet = driveBasedOnEncoders(driveDistanceToLeftColumn, "Back");
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "SpinTo90":
                doneYet = turnToGyroHeading(-90,revOrientation());
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveForward":
                doneYet = driveBasedOnEncoders(driveForwardToCryptobox, "Forward");
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                doneYet = glyphAuto("open");
                if(doneYet){
                    currentCase = "DriveForward2";
                }
                break;
            //DriveForward2 drives the robot forward even more to make shure that the glyph is in the collumn
            case "DriveForward2":
                doneYet = driveBasedOnEncoders(2, "Forward");
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            case "DriveBack":
                doneYet = driveBasedOnEncoders(driveAwayFromCryptobox, "Back");
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                moveMotorsPower(0,0,0,0);
                break;
            default:
                moveMotorsPower(0,0,0,0);
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
